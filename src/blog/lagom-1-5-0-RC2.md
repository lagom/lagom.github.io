---
title: Lagom 1.5.0-RC2 released!
date: 2019-02-25
author_github: marcospereira
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-RC2
---

We are happy to announce the availability of Lagom 1.5.0-RC2. This is the second release candidate of Lagom 1.5.0. We expect this to be considerably more stable than the milestone releases, not only in terms of functionality but we are also moving closer to the idea of freezing the APIs. Our plan is to later promote this release candidate to a stable release if we don't find major issues.

Because of that, and as with milestones, the primary goal is to get feedback, so please let us know if something isn't working or you see something that should be improved. If there are changes not well documented in javadocs, scaladocs or our migration guides, please, let us know so that we can improve them before the general availability release.

## Highlights

### Upgrades for major dependencies

- Play upgraded to 2.7.0 ([release notes](https://blog.playframework.com/play-2-7-0-is-here/))
- Akka upgraded to 2.5.21 ([release notes](https://akka.io/blog/news/2019/02/13/akka-2.5.21-released), but we also recommend that you read other release notes since Akka 2.5.18)
- Alpakka Kafka upgraded to 1.0-RC2 ([release notes](https://doc.akka.io/docs/akka-stream-kafka/current/release-notes/1.0-RC2.html))
- Slick upgraded to 3.3.0 ([release notes](http://slick.lightbend.com/news/2019/01/30/slick-3.3.0-released.html))

There is also a brand new integration with [Akka Management](https://developer.lightbend.com/docs/akka-management/current/) providing an easier way to do cluster bootstrap, health-checks enabled by default, and works better with our [new implementation of ServiceLocator](https://github.com/lagom/akka-discovery-service-locator) based on [Akka Discovery](https://doc.akka.io/docs/akka/2.5/discovery/index.html).

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.5.0-RC1...1.5.0-RC2) and the issues and pull requests assigned to the [1.5.0-RC2 milestone](https://github.com/lagom/lagom/milestone/44?closed=1).

## Updating a Lagom project to version 1.5.0-RC2

First, make sure you read the Lagom 1.5 Migration Guide for [Scala](https://www.lagomframework.com/documentation/latest/scala/Migration15.html) or [Java](https://www.lagomframework.com/documentation/latest/java/Migration15.html).

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

```
commits  added  removed  author
     13    767      362   Renato Cavalcanti
      7   1090      697   Ignasi Marimon-Clos
      4    176      100   Marcos Pereira
      2      6        5   Martynas Mickevičius
      2     55       36   Dale Wijnand
      1     10        0   Zhonglai Zhang
      1     24       18   Enno
      1      2        2   Fran Bermejo
      1      2        2   Prashant Sharma
```