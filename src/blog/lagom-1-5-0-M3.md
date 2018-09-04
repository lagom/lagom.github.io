---
title: Lagom 1.5.0-M3 released!
date: 2018-09-04
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-M3
---

We are happy to announce the availability of Lagom 1.5.0-M3. This new milestone towards the releasing of Lagom 1.5.0 introduces important improvements to support gRPC in Lagom. This release includes Play 2.7.0-M3 and Akka 2.5.16.

Note that this should not be used in production, that Lightbend Orchestration (sbt-reactive-app) does not yet support Lagom 1.5.0-M3, and that ConductR will not be supported for Lagom 1.5.0. Also note that this is an unsupported, pre-release milestone and we don't guarantee API stability or binary compatibility between milestones.

## Highlights

### Updates Play and Akka

- Bump dependencies: Play was bumped to 2.7.0-M3 ([release notes](https://blog.playframework.com/play-2-7-0-m3-released/)) and, since Lagom 1.5.0-M2, we've bumped Akka to 2.5.16 ([release notes](https://akka.io/blog/news/2018/08/29/akka-2.5.16-security-fix-released)). See also the [release notes](https://akka.io/blog/news/2018/08/24/akka-2.5.15-released) for Akka 2.5.15.

## gRPC

Lagom 1.5.0-M2 started laying groundwork for integration with [Akka gRPC](https://github.com/akka/akka-grpc) to add it as a transport alternative, allowing seamless communication between applications written using Akka, Play, Lagom and other stacks using gRPC.

In this release,the following are introduced as part of the effort to support gRPC:

- It is now possible to add additional Play `Router`s to a Lagom Service (next to the Lagom Router built from the `Service.Descriptor`). This allows wiring a [Play router that handles gRPC](https://developer.lightbend.com/docs/akka-grpc/current/play-framework.html) traffic next to the Lagom router provided out of the box.
- Lagom's Dev Mode now binds an SSL port for each service next to HTTP port already bound. This changes the default behavior in Dev Mode and there’s [ongoing effort](https://github.com/lagom/lagom/issues/1537) to restore the default which only binds HTTP and make the use of SSL an opt-in.


See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.5.0-M2...1.5.0-M3) and the issues and pull requests assigned to the [1.5.0-M3 milestone](https://github.com/lagom/lagom/milestone/36?closed=1).

## Updating a Lagom project to version 1.5.0-M3

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

    commits  added  removed  author    
        9    1098     367    Renato Cavalcanti
        6    1469     608    Ignasi Marimon-Clos
        2      40      64    Tim Moore
        2       2       2    kunals201
        2       6       2    Ayush Prashar
        2       9       7    Dale Wijnand
        2      12      11    Marcos Pereira
        1      27      27    Elijah Rippeth
        1       1       1    Jo Vanthournout
        1      16      14    João Guitana
