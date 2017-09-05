---
title: Lagom 1.4.0-M2!
date: 2017-09-05
author_github: ignasi35
tags: news
summary: >
    Lagom 1.4.0-M2!
---

We are very pleased to announce the availability of the second Milestone release of Lagom 1.4.0. This is a preview release of the Lagom 1.4 series, which is focused on upgrading all of its building blocks. This release includes Play 2.6 and Akka 2.5---like Lagom 1.4.0-M1---but both have been upgraded to the latest minor release. It also includes the latest Slick 3.2.1 and Kafka 0.11.0.0. We are still adding the finishing touches to support Scala 2.12 in the Lagom 1.4.x series.

Phew, that was a mouthful.

Unfortunately, we've decided that [supporting sbt 1.0](https://github.com/lagom/lagom/issues/932) is not a feature we will work on before releasing Lagom 1.4.0. At the moment it is still undecided if support for sbt 1.0 will come during the 1.4.x series or later.


### Migrating from other versions

The Lagom 1.4.x series is not focused on providing new, big features. Instead, it provides a collection of small improvements.

 * If you are migrating into Lagom 1.4.0-M2 from 1.4.0-M1 you should have a look at the release announcements for Lagom versions [1.3.6](/blog/lagom-1-3-6.html), [1.3.7](/blog/lagom-1-3-7.html), and [1.3.8](/blog/lagom-1-3-9.html). The migration guide to 1.4 ([java](/documentation/1.4.x/java/Migration14.html) or [scala](/documentation/1.4.x/scala/Migration14.html)) has also been improved, don't forget to review it.
 * If you are migrating into Lagom 1.4.0-M2 from a version in the Lagom 1.3.x series you should have a look at changes introduced in latest versions of the Lagom 1.3.x series and especially you should read the migration guide to 1.4 ([java](/documentation/1.4.x/java/Migration14.html) or [scala](/documentation/1.4.x/scala/Migration14.html)).

### Improvements

 * The Akka-HTTP based Service Gateway [introduced in Lagom 1.3.7](/blog/lagom-1-3-7.html) is enabled by default. It is possible to switch back to the old, Netty-based implementation. Please [raise an issue](https://github.com/lagom/lagom/issues/new) to let us know if you need to do this. The Netty-based implementation will be removed in a future version of Lagom, but we want to fix any regressions first. Check out the [docs](/documentation/1.4.x/java/ServiceLocator.html#Default-gateway-implementation) for more details.
 * Service Locators may return many URIs. If you have implemented your own Service Locator you may override the new `locateAll` methods so that a request for a service location returns not just one URI, but many. This was specifically included to connect to clustered services where the service SDK expects a collection of hosts and deals with the balancing internally (e.g. Cassandra or Kafka).
 * A new [`CircuitBreakersPanel`](https://github.com/lagom/lagom/issues/841) replaces the previously internal, now deprecated `CircuitBreakers`. This change is especially relevant for Service Locator implementors so they have access to the underlying API. A `CircuitBreakersPanel` instance holds all the Circuit Breakers for the current instance to connect to all the remote services.

### Community Contributions

This release includes great contributions from the community. Thanks Wayne Wang, Elijah Rippeth, Yuliana Apaza and surya prakash singh. Special thanks if this was your [first contribution](/get-involved.html).
