---
title: Lagom 1.5.0-M2 released!
date: 2018-07-30
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-M2
---

We are happy to announce the availability of Lagom 1.5.0-M2 (we skipped Lagom 1.5.0-M1 due to an issue with the release process). This is the first milestone towards the releasing of Lagom 1.5.0 which moves Lagom closer to Play and Akka. This release includes Play 2.7.0-M2 and lays the groundwork for gRPC support in future 1.5.0 milestones with Akka Service Discovery integration.

The motivation behind moving Lagom closer to Play and Akka is twofold: Play and Akka users will be able to adopt Lagom more easily by reusing existing code or APIs they are already familiar with, and framework maintainers will reduce the amount of code to maintain and converge in conventions speeding up the development process.

Note that this should not be used in production, that Lightbend Orchestration (sbt-reactive-app) does not yet support Lagom 1.5.0-M2, and that ConductR will not be supported for Lagom 1.5.0. Also note that this is an unsupported, pre-release milestone and we don't guarantee API stability or binary compatibility between milestones.

## Highlights

### Updates Play to 2.7.x

Lagom 1.5.0-M2 bumps the Play version to [2.7.0-M2](https://blog.playframework.com/play-2-7-0-m2-released/) (see also [2.7.0-M1](https://blog.playframework.com/play-2-7-0-milestone-1-released/)). Play now exposes the [Akka Coordinated Shutdown](https://doc.akka.io/docs/akka/current/actors.html#coordinated-shutdown) instance as a first class citizen so you can register your shutdown code using it instead of using Play's `ApplicationLifecycle`.

The version of Akka HTTP powering the default HTTP Backend for both Play and Lagom was bumped to [10.1](https://akka.io/blog/news/2018/03/08/akka-http-10.1.0-released).

Upgrading to Play 2.7 may cause some errors on your code as some deprecated APIs were removed (please review the [Play 2.7 Migration Guide](https://github.com/playframework/playframework/blob/master/documentation/manual/releases/release27/migration27/Migration27.md) or the release announcements linked above). Play also updated the Jackson library (for java JSON serialization) to 2.9.6 which includes reviewed defaults so you may need to check your serializer settings. The changes in Jackson default settings may not cause compilation errors so we recommend you exercise your serializers in tests to detect any potential issue.

### gRPC, Service Discovery and more

We are laying groundwork for integration with [Akka gRPC](https://github.com/akka/akka-grpc) to add it as a transport alternative, allowing seamless communication between applications written using Akka, Play, Lagom and other stacks using gRPC.

[Akka's Service Discovery](https://developer.lightbend.com/docs/akka-management/current/discovery.html) will become a building block of Lagom's `ServiceLocator` and help standardize service discovery scenarios. This will simplify production settings to tune cluster bootstrap and other service discovery strategies and combine them. Lagom 1.5.0-M2 started the refactor process to move to an implementation that's based 100% on Akka's Service Discovery. This new library provides an extensible API for looking up service addresses by name from within Akka-based services, including integration with Kubernetes, Marathon, Consul, and others. It is similar to Lagom's ServiceLocator API, but can be used in any Akka or Play application, as well as in Lagom. It is a foundational building block for many of the upcoming cross-product features, including gRPC support, and with this release it works seamlessly in Lagom's runAll development mode without additional configuration. We are planning to integrate this more fully with Lagom in future 1.5.0 milestones and beyond.

This cross-product collaboration is the first of many more to come. The motivation is to allow users developers to more naturally reuse code between Akka, Play and Lagom. The promotion of Akka Coordinated Shutdown mentioned above is another example of this effort to provide more coherent APIs.

### Other improvements

- Updates Alpakka Kafka to version [0.22](https://github.com/akka/alpakka-kafka/releases). This also includes a version upgrade of the Kafka Server embedded in Development Mode to 1.0.1.

- Improves Development Mode settings so each service can choose what network interface to bind to, which is particularly useful in some OS or corporate environments where VPNs or proxies can interfere with inter-service communication. The new default for all services is to listen only on the loopback address `127.0.0.1` by default, which has better compatibility, but disallows communication from other hosts on the network. This can be configured to use another address if you need to share a Lagom development environment on a local network. Every service run in Developer Mode has slightly different settings, see the docs for [Service Gateway](/documentation/latest/java/ServiceLocator.html#Default-address1), [Service Locator](/documentation/latest/java/ServiceLocator.html#Default-address) or [your services](/documentation/latest/java/ConfiguringServicesInDevelopment.html#How-are-addresses-bound-by-services?).

- Many documentation improvements, especially to remove deprecated APIs in code examples.

See the issues and pull requests assigned to the [1.5.0-M2 milestone](https://github.com/lagom/lagom/milestone/19?closed=1).

## Updating a Lagom project to version 1.5.0-M2

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:
*Elijah Rippeth, Ayush Prashar, sullis, Tim Moore, Renato Cavalcanti, Marcos Pereira and Ignasi Marimon-Clos*.
