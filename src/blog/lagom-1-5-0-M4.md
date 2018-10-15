---
title: Lagom 1.5.0-M4 released!
date: 2018-10-15
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-M4
---

We are happy to announce the availability of Lagom 1.5.0-M4. This new milestone towards the releasing of Lagom 1.5.0 continues to improve support for gRPC in Lagom. This release includes Play 2.7.0-M4, Akka 2.5.17 and Akka-HTTP 10.1.5.

Note that ConductR will not be supported for Lagom 1.5.0. Also note that this is an unsupported, pre-release milestone and we don't guarantee API stability or binary compatibility between milestones.

## Highlights

### Updates Play and Akka

- Bump dependencies: Play was bumped to 2.7.0-M4 ([release notes](https://blog.playframework.com/play-2-7-0-m4-released/)) and, since Lagom 1.5.0-M2, we've bumped Akka to 2.5.17 ([release notes](https://akka.io/blog/news/2018/09/27/akka-2.5.17-released)) and Akka-HTTP to 10.1.5([release notes](https://akka.io/blog/news/2018/09/06/akka-http-10.1.5-10.0.14-security-fix-released)).

## gRPC

Previous milestones of Lagom 1.5.0 started laying the groundwork for integration with [Akka gRPC](https://github.com/akka/akka-grpc) to add it as a transport alternative. In Lagom 1.5.0-M4 we've improved the Lagom testkit so you can also test the gRPC of your Lagom application.

In this release, the following are introduced as part of the effort to support gRPC:

- It is now possible to run the `TestServer` with SSL enabled and build a gRPC client to interact with the gRPC endpoints offered.
- Lagom 1.5.0-M3 enabled SSL by default in Lagom's Dev Mode. In Lagom 1.5.0-M4 we've restored the previous behavior where only HTTP is used in Dev Mode and made SSL an Opt-In feature in Dev Mode. Use `lagomServiceEnableSsl in ThisBuild := true` to start your service with both HTTP and HTTPS ports bound.

## Miscellaneous improvements

This release includes a few other small improvements:

* [1475](https://github.com/lagom/lagom/issues/1475) handle no body as a valid input contributed by Ido Shamun
* [1572](https://github.com/lagom/lagom/issues/1572) which improves Dev Mode's Service Gateway to stack Host header into X-Forwarded-Host so the target service running behind service Gateway still sees the data sent on the original request.


See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.5.0-M3...1.5.0-M4) and the issues and pull requests assigned to the [1.5.0-M4 milestone](https://github.com/lagom/lagom/milestone/38?closed=1).

## Updating a Lagom project to version 1.5.0-M4

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author    
        8     347      94    Ignasi Marimon-Clos
        6     342     242    Dale Wijnand
        4     515      77    Renato Cavalcanti
        2      68      10    Marcos Pereira
        2       2       2    Martijn Riemers
        1       1       1    Ravi Thinakkal
        1     148     146    Kunal sethi
        1     156       2    Ido Shamun
