---
title: Lagom 1.4.10 released!
date: 2019-01-11
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.4.10
---

The Lagom team has released Lagom 1.4.10.

This release upgrades Play (and transitively upgrades Akka and Akka HTTP) and Scala.

## Highlights

- Updates Play to [2.6.21](https://blog.playframework.com/play-2-6-21-released//) with some minor improvements. This upgrade transitively upgrades Akka and Akka HTTP. Make sure you review Play's announcement for details.

If you are a user of [Lightbend Orchestration](https://developer.lightbend.com/docs/lightbend-orchestration/current/setup/project-setup.html) you will have to upgrade your dependency of the `sbt-reactive-app` plugin to `1.6.1`.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.9...1.4.10) and the issues and pull requests assigned to the [1.4.10 milestone](https://github.com/lagom/lagom/milestone/41?closed=1).

## Updating a Lagom project to version 1.4.10

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author    
          2     673     661  Ignasi Marimon-Clos
          1      28      28  Dale Wijnand
          1       2       2  Fran Bermejo
