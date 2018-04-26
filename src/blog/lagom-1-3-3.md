---
title: Lagom 1.3.3 is released!
date: 2017-04-28
author_github: jroper
tags: news
summary: >
    The Lagom team has released Lagom 1.3.3
---

The Lagom team has released Lagom 1.3.3. This release is primarily a bugfix release, fixing issues related to Cassandra and Kafka.

## Updating

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

## Migration notes

Binding multiple Lagom service descriptors in one Lagom service has been deprecated in this release. Although your Lagom services should continue to function with no changes, we recommend you update your application to fix any deprecation warnings that may have been introduced.

If you are currently binding multiple Lagom service descriptors in one Lagom service, you should combine these into one. The reason for this change is that we found most microservice deployment platforms simply don't support having multiple names for the one service, hence a service that serves multiple service descriptors, each with their own name, would not be compatible with those environments.

Consequently, we have deprecated the methods for binding multiple service descriptors. To migrate, follow the instructions specific to your language:

### Java

Instead of:

```java
bindServices(serviceBinding(MyService.class, MyServiceImpl.class));
```

Use:

```java
bindService(MyService.class, MyServiceImpl.class);
```

### Scala

Instead of:

```scala
lazy val lagomServer = LagomServer.forServices(bindService[MyService].to(wire[MyServiceImpl]))
```

Use:

```scala
lazy val lagomServer = serverFor[MyService](wire[MyServiceImpl])
```

## Change log

See the [change log](/changelog-1.3.x.html) for more details on exactly what has been fixed in each release.
