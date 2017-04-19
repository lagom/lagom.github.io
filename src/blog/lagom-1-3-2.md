---
title: Lagom 1.3.2 is released!
date: 2017-04-19
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.3.2
---

The Lagom team has just released Lagom 1.3.2. This release includes bug fixes and other minor changes, a dependency management improvement for Maven users, and deprecates a feature that proved to be confusing.

## Updating

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

If you are using Lagom's support for Maven builds or Cassandra persistence, there are some other (optional) steps you should follow below.

## Dependency management in Maven

Lagom brings together several rich libraries and frameworks and this means many transitive dependencies. Managing these dependencies and avoiding conflicts can be tricky.

Lagom 1.3.2 introduces a new POM artifact that you can use in your top-level `pom.xml` in order to control the versions of transitive dependencies brought in by both Lagom and other libraries you depend on. This relies on Maven's support for [importing dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Importing_Dependencies) in the `dependencyManagement` section of your POM.

To use this, follow these steps:

1. Open the top-level `pom.xml` for each of your Maven Lagom projects in an editor
2. Add an import-scope dependency for `lagom-maven-dependencies` to the `dependencyManagement` section, within the nested `dependencies` element:

    ```xml
    <dependency>
        <groupId>com.lightbend.lagom</groupId>
        <artifactId>lagom-maven-dependencies</artifactId>
        <version>${lagom.version}</version>
        <scope>import</scope>
        <type>pom</type>
    </dependency>
    ```

3. Remove any other previous entries for dependencies with the `com.lightbend.lagom` `groupId`, and the entries for `akka-actor_2.11` and `akka-stream_2.11`.

This ensures that the resolved versions of all artifacts that Lagom depends on, directly or indirectly, match the versions that Lagom was tested with.

New projects built from the Lagom Maven archetype include this configuration by default. You might want to [create a new project from the archetype](https://www.lagomframework.com/documentation/1.3.x/java/GettingStartedMaven.html) to compare the generated `pom.xml` with your existing projects.

This `dependencyManagement` feature of Maven has no exact equivalent in sbt. We have found sbt's default conflict resolution algorithm to be less surprising than Maven's, and has fewer problems in practice, but we are looking into possibilities to improve this further in sbt as well.

## Deprecated: default Cassandra keyspaces

Lagom's persistence needs a few tables to store its data. These tables are stored in Cassandra keyspaces. Each service should use a unique keyspace name so that the tables of different services do not conflict with each other. You need to configure the keyspaces that are used for these tables in the `application.conf` file of each of your service implementation projects. For example:

```
my-service.cassandra.keyspace = my_service

cassandra-journal.keyspace = ${my-service.cassandra.keyspace}
cassandra-snapshot-store.keyspace = ${my-service.cassandra.keyspace}
lagom.persistence.read-side.cassandra.keyspace = ${my-service.cassandra.keyspace}
```

Previous versions of Lagom automatically calculated a default Cassandra keyspace for each service, based on the name of the service project, and injected this keyspace configuration in development mode. When running in production, these calculated keyspaces were not used, resulting in multiple services sharing the same keyspaces by default if these properties were not configured in `application.conf`.

Because of this unexpected inconsistency between the development and production environments, Lagom 1.3.2 deprecates the development environment behavior and prints a warning if these properties are not defined in a service that uses Cassandra persistence. The `cassandraKeyspace` parameter for the Lagom Maven plugin and `lagomCassandraKeyspace` setting for the Lagom sbt plugin are also deprecated, as it was not clear to everyone that these settings only affect the development environment, and are not used when keyspaces are configured in `application.conf`.

New projects built from the Lagom Maven archetype or using `sbt new` and Lagom's g8 templates (either [java](https://github.com/lagom/lagom-java.g8) or [scala](https://github.com/lagom/lagom-scala.g8) ) include this configuration by default, using the name of the project it prompts you for to assign a default keyspace to the example persistent service implementation. You can change this default as required, and should be sure to configure unique keyspaces for additional service implementations as you create them.

In Lagom 1.4, services that use Cassandra persistence will fail on startup when these properties are not defined.

See [Storing Persistent Entities in Cassandra](https://www.lagomframework.com/documentation/1.3.x/java/PersistentEntityCassandra.html#Configuration) for configuration details and [issue #578](https://github.com/lagom/lagom/issues/578) for more background.

## Change log

See the [change log](/changelog.html) for more details on exactly what has been fixed in each release.
