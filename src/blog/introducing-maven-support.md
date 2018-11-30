---
title: Introducing Maven support!
date: 2016-08-18
author_github: jroper
tags: maven news
summary: >
    The Lagom team are proud to announce the introduction of Maven support for Lagom!  We've published Lagom 1.1.0-RC1
    with this support, and are keen to get feedback from users to see if it works.

---

**Update:** For the latest instructions on starting a Lagom project with Maven, see [Using Lagom with Java and Maven](/get-started-java-maven.html).

The Lagom team are proud to announce the introduction of Maven support for Lagom!  We've published Lagom 1.1.0-RC1 with this support, and are keen to get feedback from users to see if it works.

## Why Maven?

sbt is a very powerful build tool that made the implementation of Lagom's high productivity development environment very straight forward. Since it breaks a build up into very fine grained tasks that produce an output value, it's simple for the Lagom development environment to interrogate your build by executing those tasks to get the information it needs to setup Lagom's hot reloads. For this reason, we decided in the first release of Lagom to just support sbt.

Early feedback however has been that lack of Maven support is a blocker for many of our potential users. This is particularly true for large enterprise companies that have significant investments in their Maven infrastructure, including proxies, developer setup guides, and standardised pom files.  For this reason, we've decided to make the investment necessary to run Lagom in Maven.

## Let's take a look!

We've made use of the Maven archetype plugin for users to create new projects using archetypes.  So, this is what it looks like to create a new Lagom system using Maven:

```console
$ mvn archetype:generate -DarchetypeGroupId=com.lightbend.lagom \
  -DarchetypeArtifactId=maven-archetype-lagom-java \
  -DarchetypeVersion=1.1.0-RC1

[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:2.4:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:2.4:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] --- maven-archetype-plugin:2.4:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[WARNING] Archetype not found in any catalog. Falling back to central repository (http://repo.maven.apache.org/maven2).
[WARNING] Use -DarchetypeRepository=<your repository> if archetype's repository is elsewhere.
Define value for property 'groupId': : com.example
Define value for property 'artifactId': : my-first-system
Define value for property 'version':  1.0-SNAPSHOT: : 
Define value for property 'package':  com.example: : 
[INFO] Using property: service1ClassName = Hello
[INFO] Using property: service1Name = hello
[INFO] Using property: service2ClassName = Stream
[INFO] Using property: service2Name = stream
Confirm properties configuration:
groupId: com.example
artifactId: my-first-system
version: 1.0-SNAPSHOT
package: com.example
service1ClassName: Hello
service1Name: hello
service2ClassName: Stream
service2Name: stream
 Y: : 
 [INFO] ----------------------------------------------------------------------------
 [INFO] Using following parameters for creating project from Archetype: maven-archetype-lagom-java:1.1.0-RC1
 [INFO] ----------------------------------------------------------------------------
 [INFO] Parameter: groupId, Value: com.example
 [INFO] Parameter: artifactId, Value: my-first-system
 [INFO] Parameter: version, Value: 1.0-SNAPSHOT
 [INFO] Parameter: package, Value: com.example
 [INFO] Parameter: packageInPathFormat, Value: com/example
 [INFO] Parameter: service1ClassName, Value: Hello
 [INFO] Parameter: package, Value: com.example
 [INFO] Parameter: version, Value: 1.0-SNAPSHOT
 [INFO] Parameter: service2ClassName, Value: Stream
 [INFO] Parameter: groupId, Value: com.example
 [INFO] Parameter: service1Name, Value: hello
 [INFO] Parameter: artifactId, Value: my-first-system
 [INFO] Parameter: service2Name, Value: stream
 [INFO] project created from Archetype in dir: /home/jroper/tmp/my-first-system
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESS
 [INFO] ------------------------------------------------------------------------
 [INFO] Total time: 16.460 s
 [INFO] Finished at: 2016-08-18T22:47:02+10:00
 [INFO] Final Memory: 17M/307M
 [INFO] ------------------------------------------------------------------------
```

We now have our first Maven Lagom project!

## Run it

One of the best features of Lagom, and the hardest feature to implement in a build tool, is Lagom's high productivity development environment.  Let's check it out in Maven, we can run our project using the `lagom:runAll` goal:

```console
$ cd my-first-system
$ mvn lagom:runAll
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] my-first-system
[INFO] hello-api
[INFO] hello-impl
[INFO] stream-api
[INFO] stream-impl
[INFO] integration-test
[INFO] cassandra-config
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building my-first-system 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- lagom-maven-plugin:1.1.0-RC1:runAll (default-cli) @ my-first-system ---
[INFO] Starting embedded Cassandra server
[INFO] Cassandra server running at 127.0.0.1:4000
[INFO] Service locator is running at http://localhost:8000
[INFO] Service gateway is running at http://localhost:9000
[INFO] Service hello-impl listening for HTTP on 0:0:0:0:0:0:0:0:57797
[INFO] Service stream-impl listening for HTTP on 0:0:0:0:0:0:0:0:58445
[INFO] (Services started, press enter to stop and go back to the console...)
```

Some of the Maven output has been snipped here for brevity, but you can see that the following has happened:

* Cassandra has been started
* A service locator is running on port 8000
* A service gateway is running on port 9000
* Our services are running on various ports

Let's see if we can access one of the services:

```console
$ curl http://localhost:9000/api/hello/Maven
Hello, Maven!
```

## Hot reloads

It's one thing to be able to get started quickly to run services, but one of the biggest productivity killers that developers face in development is build/deploy loops.  Lagom takes care of that for you, by watching all of your sources, and automatically recompiling and reloading them as soon as it sees a change.

Let's add a small string to the messsages returned by the hello service, in `hello-impl/src/main/java/com/example/hello/impl/HelloServiceImpl.java`:

```java
  public ServiceCall<NotUsed, String> hello(String id) {
    return request -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<HelloCommand> ref = persistentEntityRegistry.refFor(HelloEntity.class, id);
      // Ask the entity the Hello command.
      return ref.ask(new Hello(id, Optional.empty()))
          .thenApply(msg -> msg + " <- that's the message");
    };
  }
```

As soon as I hit save, Maven outputs this:

```console
[INFO] Copying 1 resource
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /home/jroper/tmp/my-first-system/hello-impl/target/classes

--- (RELOAD) ---
```

And now if I hit the service again, you can see the updated message:

```console
$ curl http://localhost:9000/api/hello/Maven
Hello, Maven! <- that's the message
```

## Want to learn more?

Of course, there's much more to Lagom's Maven support, which you can find in the [Lagom documentation](/documentation/1.1.x/java/Home.html).

Why not get started today, all you need is JDK8 and Maven installed, and you're set to start developing a reactive microservices system.
