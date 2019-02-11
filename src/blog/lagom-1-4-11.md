---
title: Lagom 1.4.11 released!
date: 2019-02-11
author_github: marcospereira
tags: news
summary: >
    The Lagom team has released Lagom 1.4.11
---

The Lagom team has released Lagom 1.4.11.

This release upgrades Akka to resolve a compatibility problem with Lightbend Orchestration 1.7.0.

## Highlights

- Updates Akka to version [2.5.20](https://akka.io/blog/news/2019/01/29/akka-2.5.20-released) which stabilizes some APIs and brings bug fixes.

If you are a user of [Lightbend Orchestration](https://developer.lightbend.com/docs/lightbend-orchestration/current/setup/project-setup.html) you will have to upgrade your dependency of the `sbt-reactive-app` plugin to `1.7.0`.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.10...1.4.11) and the issues and pull requests assigned to the [1.4.11 milestone](https://github.com/lagom/lagom/milestone/42?closed=1).

## Updating a Lagom project to version 1.4.11

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author
    1            4        2  Renato Cavalcanti
    1           23       10  Ignasi Marimon-Clos
    1            1        1  Marcos Pereira
    1            3        3  Tim Moore
    1           26        7  Dale Wijnand