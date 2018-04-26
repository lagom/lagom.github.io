---
title: Lagom 1.4.1 released!
date: 2018-03-06
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.4.1
---

The Lagom team has released Lagom 1.4.1. For a list of the specific changes in Lagom 1.4.1, see the [change log](/changelog.html).

This release provides a few bug fixes and minor improvements. As an overview, this release contains the following:

- Updates Akka Persistence Cassandra to 0.60, which updates the bundled Cassandra version to 3.11.2. This fixes a crash on startup when using JDK versions 1.8.0_161 and later. ([#1204](https://github.com/lagom/lagom/issues/1204))
- Updates Akka to [2.5.11](https://akka.io/blog/news/2018/02/28/akka-2.5.11-released) (also includes changes from [2.5.10](https://akka.io/blog/news/2018/02/23/akka-2.5.10-released)).
- Updates Play to [2.6.12](https://blog.playframework.com/play-2-6-12-released/).
- Prevents `SaveSnapshotSuccess` from showing up in unhandled messages. ([#1209](https://github.com/lagom/lagom/issues/1209))

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.0...1.4.1) and the issues and pull requests assigned to the [1.4.1 milestone](https://github.com/lagom/lagom/milestone/27?closed=1).


## Updating a Lagom project to version 1.4.1

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.


## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

```
commits  added  removed
      6     87       60  Tim Moore
      3     54      123  Renato Cavalcanti
      1     31        7  Lutz Huehnken
```
