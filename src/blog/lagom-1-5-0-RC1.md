---
title: Lagom 1.5.0-RC1 released!
date: 2018-11-16
author_github: renatocaval
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0-RC1
---

We are happy to announce the availability of Lagom 1.5.0-RC1. This is the first release candidate of Lagom 1.5.0. We expect this to be considerably more stable than the milestone releases, not only in terms of functionality but we are also moving closer to the idea of freezing the APIs.

As with milestones, the primary goal is to get feedback, so please let us know if something isn't working or you see something that should be improved. If there are changes not well documented in javadocs, scaladocs or our migration guides, please, let us know so that we can improve them before the general availability release.


## Highlights

### Upgrades for Play, Akka and Alpakka Kafka

- Play upgraded to 2.7.0-RC3 ([release notes](https://blog.playframework.com/play-2-7-0-rc3-released/)) 
- Akka upgraded to 2.5.18 ([release notes](https://akka.io/blog/news/2018/10/07/akka-2.5.18-released))
- Alpakka Kafka upgraded to 1.0-M1 ([release notes](https://doc.akka.io/docs/akka-stream-kafka/current/release-notes/1.0-M1.html))

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.5.0-M4...1.5.0-RC1) and the issues and pull requests assigned to the [1.5.0-RC1 milestone](https://github.com/lagom/lagom/milestone/39?closed=1).

## Updating a Lagom project to version 1.5.0-RC1

First, make sure you read the Lagom 1.5 Migration Guide for [Scala](https://www.lagomframework.com/documentation/latest/scala/Migration15.html) or [Java](https://www.lagomframework.com/documentation/latest/java/Migration15.html).

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

commits  added  removed  author    
      3    103      126  Dale Wijnand
      1     30        8  Ignasi Marimon-Clos
      1      4        4  danielklein45
      1     21       19  Martynas Mickevičius
