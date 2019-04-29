---
title: "Lagom 1.5.1 and 1.4.12 Released: Akka Service Discovery integration"
date: 2019-04-30
author_github: renatocaval
tags: news
summary: >
    The Lagom team has released Lagom 1.5.1 and 1.4.12
---


The Lagom team has released the versions 1.5.1 and 1.4.12. For a list of the specific changes in Lagom 1.5.1, see the [change log (1.5.1)](https://github.com/lagom/lagom/compare/1.5.0...1.5.1), and for a list of changes on Lagom 1.4.12 see the [change log (1.4.12)](https://github.com/lagom/lagom/compare/1.4.11...1.4.12).

## Highlights

These two releases bring into Lagom a new `Service Locator` implementation, based on [Akka's Service Discovery](https://doc.akka.io/docs/akka/2.5/discovery/index.html). This component was previously released as an apart library, [lagom-akka-discovery-service-locator](https://github.com/lagom/lagom-akka-discovery-service-locator/), and intended to be a replacement for the Service Locator previously offered by [Lightbend Orchestration](https://developer.lightbend.com/docs/lightbend-orchestration/current/index.html).

In order to allow a smoother transition for Lagom 1.4 users, we decided to include it in 1.4.12 and 1.5.1. So users can choose to first migrate their Lagom 1.4 application to Akka Service Discovery and then to Lagom 1.5. Of course, this is not a required step. It is also possible to migrated directly from Lagom 1.4 and Lightbend Orchestration to Lagom 1.5 and Akka Service Discovery.

For more information, consult the corresponding documentation for Akka Discovery integration:
 
 * [Java API Lagom 1.4](https://www.lagomframework.com/documentation/1.4.x/java/AkkaDiscoveryIntegration.html)
 * [Scala API Lagom 1.4](https://www.lagomframework.com/documentation/1.4.x/scala/AkkaDiscoveryIntegration.html)
 * [Java API Lagom 1.5](https://www.lagomframework.com/documentation/1.5.x/java/AkkaDiscoveryIntegration.html)
 * [Scala API Lagom 1.5](https://www.lagomframework.com/documentation/1.5.x/scala/AkkaDiscoveryIntegration.html)
 
 

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release: _Renato Cavalcanti_, _Steve Swing_, _Ignasi Marimon-Clos_, _Dale Wijnand_, _Tim Moore_, _Alden Torres_.
