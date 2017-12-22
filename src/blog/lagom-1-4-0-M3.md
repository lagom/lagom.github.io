---
title: Lagom 1.4.0-M3
date: 2017-11-30
author_github: TimMoore
tags: news
summary: >
    Lagom 1.4.0-M3
---

We are very pleased to announce the availability of the third milestone release of Lagom 1.4.0. Although we had originally hoped to have a release candidate available by now, there are still a few [known issues](https://github.com/lagom/lagom/milestone/25) with the 1.4.0 release that will take a little time to complete. In the meantime, there are enough changes since M2 that we decided to publish a new milestone to allow users on the bleeding edge to give them a try. Thanks for your patience!

Please note that we still do not recommend using a milestone release in production, as there may still be incompatible changes up until the first release candidate.

### Migrating from other versions

 * If you are migrating into Lagom 1.4.0-M3 from 1.4.0-M2 you should have a look at the release announcements for Lagom versions [1.3.9](/blog/lagom-1-3-9.html) and [1.3.10](/blog/lagom-1-3-10.html). The migration guide to 1.4 ([Java](/documentation/1.4.x/java/Migration14.html)/[Scala](/documentation/1.4.x/scala/Migration14.html)) has also been improved, don't forget to review it.
 * If you are migrating into Lagom 1.4.0-M3 from a version in the Lagom 1.3.x series you should have a look at changes introduced in latest versions of the Lagom 1.3.x series and especially you should read the migration guide to 1.4 ([Java](/documentation/1.4.x/java/Migration14.html)/[Scala](/documentation/1.4.x/scala/Migration14.html)). We also recommend updating to the latest stable 1.3.x release (currently 1.3.10 as of this writing) before attempting to update to 1.4.

### Improvements

* The Lagom circuit breaker can be configured to ignore certain exceptions. These exceptions will still be seen by the client, but they will no longer cause the circuit breaker to open. See the documentation on circuit breaker configuration ([Java](/documentation/1.4.x/java/ServiceClients.html#Circuit-Breaker-Configuration)/[Scala](/documentation/1.4.x/scala/ServiceClients.html#Circuit-Breaker-Configuration)) and service error handling ([Java](/documentation/1.4.x/java/ServiceErrorHandling.html)/[Scala](/documentation/1.4.x/scala/ServiceErrorHandling.html)) for more details.
* You can now [disable entity passivation](https://github.com/lagom/lagom/pull/1010) by setting `lagom.persistence.passivate-after-idle-timeout = 0` in `application.conf`. This allows your service to keep all entities resident in memory or control passivation more directly.
* The Message Broker API now provides the `Subscriber.withMetadata` method to allow consumers to access details about each method such as the Kafka message key. See the documentation on consuming message metadata ([Java](/documentation/1.4.x/java/MessageBrokerApi.html#Consuming-message-metadata)/[Scala](/documentation/1.4.x/scala/MessageBrokerApi.html#Consuming-message-metadata)) for details.
* The default port for the development mode service locator changed from 8000 to 9008. This is rarely used directly, so it should be transparent for most users, but 8000 is a common port for other services to run on (including the default debug port used by `mvnDebug`), so this will reduce the chance of conflicts.
* Several underlying dependencies were updated (including Play and Akka)

### Contributors

The Lagom maintainer team extends its gratitude to all of the people who have contributed to the release. There are now enough of you that we've decided to borrow [Akka's credits script](https://github.com/akka/akka/blob/master/scripts/authors.pl) to summarize the results:

```
commits added removed
    7     324      42 Renato Cavalcanti
    7     884     249 Ignasi Marimon-Clos
    6     286     256 Tim Moore
    5     296     153 Greg Methvin
    4     803     128 James Roper
    3      53      52 Pallavi Singh
    2      55       7 Elijah Rippeth
    2      36      39 Jirka Kremser
    1       6       0 Saniya Tech
    1       6       5 Marcos Pereira
    1      17       1 HSalaila
    1      71       8 Adam Chaulk
    1     309      21 Julien Jean Paul Sirocchi
    1       2       2 NOUGUIER Olivier
    1      39       8 Neelaksh
    1      36       0 Micho
    1       3       1 dr3s
    1      11      76 Ruth Stento
    1       1       1 Stijn De Haes
```
