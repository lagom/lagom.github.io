---
title: Lagom 1.4.7 released!
date: 2018-07-19
author_github: marcospereira
tags: news
summary: >
    The Lagom team has released Lagom 1.4.7
---

The Lagom team has released Lagom 1.4.7.

This release provides several bug fixes and updates.

## Highlights

- Updates Akka to [2.5.14](https://akka.io/blog/news/2018/07/13/akka-2.5.14-released)
- Updates Play to [2.6.17](https://blog.playframework.com/play-2-6-17-released/) (see also [Play 2.6.26 release notes](https://blog.playframework.com/play-2-6-16-released/)). Play 2.6.17 supports starting the application even when the used RDBMS is not available, but the default configuration is to fail fast. In Lagom, this configuration was overridden to not fail fast.
- [Read side actor restart](https://github.com/lagom/lagom/pull/1418) means that the read side now recovers after a failure in `globalPrepare`, making it more resilient.
- Many documentation improvements, especially to remove deprecated APIs in our code examples.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.6...1.4.7) and the issues and pull requests assigned to the [1.4.7 milestone](https://github.com/lagom/lagom/milestone/32?closed=1).

## Updating a Lagom project to version 1.4.7

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author
    12       121    197      Tim Moore
     6       195     86      Marcos Pereira
     2         4      4      adelababinciuc
     1       173     38      Renato Cavalcanti
     1        19     14      Ignasi Marimon-Clos
     1       168      8      Jason Pickens
     1         2      1      Fabrice Sznajderman
     1         5      1      anmolmehta93
     1        34     13      Elijah Rippeth
