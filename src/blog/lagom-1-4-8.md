---
title: Lagom 1.4.8 released!
date: 2018-08-31
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.4.8
---

The Lagom team has released Lagom 1.4.8.

This release provides a bug fix and an improvement.

## Highlights

- Updates Akka to [2.5.16](https://akka.io/blog/news/2018/08/29/akka-2.5.16-security-fix-released). This Akka release introduces a security fix for a vulnerability affecting users of Akka Cluster with TLS enabled _and_ use a particular `random-number-generator`. See the [Akka announcement](https://akka.io/blog/news/2018/08/29/akka-2.5.16-security-fix-released) for more details.
- Allow missing application.conf for service clients ([PR 1442](https://github.com/lagom/lagom/issues/1442) contributed by Elijah Rippeth)
- ... and many documentation improvements.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.7...1.4.8) and the issues and pull requests assigned to the [1.4.8 milestone](https://github.com/lagom/lagom/milestone/34?closed=1).

## Updating a Lagom project to version 1.4.8

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author    
        3      15      11    Ignasi Marimon-Clos
        3      19      22    Renato Cavalcanti
        2      12      11    Marcos Pereira
        2       2       2    kunals201
        1       1       1    Jo Vanthournout
        1      36      64    Tim Moore
        1       2       2    Himani Arora
        1      14       2    Elijah Rippeth
