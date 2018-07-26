---
title: Lagom 1.5.0-M1 released!
date: 2018-07-25
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-M1
---

We are happy to announce the availability of Lagom 1.5.0-M1. This is the first milestone towards the releasing of Lagom 1.5.0 which moves Lagom closer to Play and Akka. This release includes Play 2.7.0-M2 and starts moving towards supporting scala 2.13.

The motivation behind moving Lagom closer to Play and Akka is twofold: Play and Akka users will be able to adopt Lagom more easily by reusing existing code or APIs they are already familiar with, and framework maintainers will reduce the amount of code to maintain and converge in conventions speeding up the development process.

## Highlights

- Updates Play to [2.7.0-M2](https://blog.playframework.com/play-2-7-0-m2-released/) (see also [2.7.0-M1](https://blog.playframework.com/play-2-7-0-milestone-1-released/)). Play 2.7.x improves failure handling in RDBMS failure scenarios and adopts [Akka's Coordinated Shutdown](https://doc.akka.io/docs/akka/current/actors.html#coordinated-shutdown). It also removes some deprecated APIs Lagom exposed transparently so you may need to review your code. Make sure to review upstream changes. As part of this update, the dependency to the Jackson library (for java JSON serialization) was bumped to version `2.9.6` which introduced some new default settings that may require you to review your serialization settings.
- Start adoption of [Akka's Service Discovery](https://developer.lightbend.com/docs/akka-management/current/discovery.html) which will become a building block of Lagom's `ServiceLocator` and help standardize service discovery scenarios. This will simplify production settings to tune cluster bootstrap and other service discovery strategies and combine them.
- Updates Alpakka Kafka (aka `reactive-kafka` and `akka-stream-kafka`) to version [0.22](https://github.com/akka/alpakka-kafka/releases).
- Improves Development Mode settings so each service can choose what network interface to bind to (defaults to `0.0.0.0`), which is particularly useful in some OS or corporate environments.
- Many documentation improvements, especially to remove deprecated APIs in our code examples.

See the issues and pull requests assigned to the [1.4.7 milestone](https://github.com/lagom/lagom/milestone/19?closed=1).

## Updating a Lagom project to version 1.5.0-M1

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:
*Elijah Rippeth, Ayush Prashar, sullis, Tim Moore, Renato Cavalcanti, Marcos Pereira and Ignasi Marimon-Clos*.
