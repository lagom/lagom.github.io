---
title: Lagom 1.4.9 released!
date: 2018-11-15
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.4.9
---

The Lagom team has released Lagom 1.4.9.

This release provides a few bug fixes and an improvements.

## Highlights

- Updates Akka to [2.5.18](https://akka.io/blog/news/2018/10/07/akka-2.5.18-released). This Akka release introduces few improvements on the DNS implementation. It also improves the cluster membership handling so if you are using Akka's [Split Brain Resolver](https://developer.lightbend.com/docs/akka-commercial-addons/current/split-brain-resolver.html) you may want to upgrade.
- Updates Akka HTTP to [10.0.14](https://akka.io/blog/news/2018/09/06/akka-http-10.1.5-10.0.14-security-fix-released) with an important security fix. It's is unlikely that as a Lagom user you were exposed to the vulnerability, though.
- Updates Play to [2.6.20](https://blog.playframework.com/play-2-6-20-released/) with some minor improvements.
- Fixes [a regression](https://github.com/lagom/lagom/issues/1557) on `LagomClientFactory` introduced on the `1.4.x` series which was causing a `LagomClientFactory` running inside a Lagom service to interfere with the service in some scenarios causing failures at boot time.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.8...1.4.9) and the issues and pull requests assigned to the [1.4.9 milestone](https://github.com/lagom/lagom/milestone/37?closed=1).

## Updating a Lagom project to version 1.4.9

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author    
        4       4      10    Dale Wijnand
        3      82      48    Ignasi Marimon-Clos
        2       2       2    Martijn Riemers
        1       1       1    Ravi Thinakkal
        1     506      76    Renato Cavalcanti
