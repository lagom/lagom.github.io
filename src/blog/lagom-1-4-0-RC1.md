---
title: Lagom 1.4.0-RC1
date: 2017-12-22
author_github: renatocaval
tags: news
summary: >
    Lagom 1.4.0-RC1
---

It's Christmas! And we are very pleased to announce the availability of the first release candidate of Lagom 1.4.0. What a nice present to spent your time during the holiday season! :-)

Contrary to the previous milestone releases, a RC is production ready. We are now entering the release candidate phase (typically 4 weeks) in which only major bugs (if any) will be fixed. 


### Migrating from other versions

 * If you are migrating to Lagom 1.4.0-RC1 from 1.4.0-M3, look out for new deprecation warnings. The migration guide to 1.4 ([Java](/documentation/1.4.x/java/Migration14.html)/[Scala](/documentation/1.4.x/scala/Migration14.html)) has also been improved, don't forget to review it.
 * If you are migrating into Lagom 1.4.0-RC1 from a version in the Lagom 1.3.x series you should have a look at changes introduced in latest versions of the Lagom 1.3.x series and especially you should read the migration guide to 1.4 ([Java](/documentation/1.4.x/java/Migration14.html)/[Scala](/documentation/1.4.x/scala/Migration14.html)). We also recommend updating to the latest stable 1.3.x release (currently 1.3.10 as of this writing) before attempting to update to 1.4.

### Changelog

See the [full list of changes](https://github.com/lagom/lagom/compare/1.4.0-M3...1.4.0-RC1) and the [1.4.0-RC1 milestone](https://github.com/lagom/lagom/milestone/25?closed=1) on GitHub. The changes includes a few bug fixes and improvements, some of which we highlight below: 

* Ensure Akka cluster is left gracefully based on new [Akka Coordinated Shutdown](https://doc.akka.io/docs/akka/current/actors.html?language=scala#coordinated-shutdown)
* Maven Dependency POM and Archetype supporting Scala 2.11 and 2.12
* Upgraded to Akka 2.5.8
* Upgraded to Akka HTTP 10.0.11 
* Upgraded to Play 2.6.10
* Upgraded to akka-persistence-jdbc to 3.1.0
* Akka HTTP as default backend
* maxFrameLength config parameter for WebSocketClient

 
### Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release: *Tim Moore, Renato Cavalcanti, Ignasi Marimon-Clos, James Roper, Edmondo Porcu and datalchemist*.
```
