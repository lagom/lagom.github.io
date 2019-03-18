---
title: Lagom 1.5.0 released!
date: 2019-03-20
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0

---

We are happy to announce the availability of Lagom 1.5.0. This is the GA release of Lagom 1.5.0. We consider Lagom 1.5.0 to be production ready and invite all our users to upgrade.

This release introduces very few changes over [Lagom 1.5.0-RC2](https://www.lagomframework.com/blog/lagom-1-5-0-RC2.html), mostly documentation and a few version bumps of dependencies. Most remarkable is the final landing of [Alpakka-Kafka 1.0](https://akka.io/blog/news/2019/03/07/alpakka-kafka-1.0.1-released) into Lagom.

## Updating a Lagom project to version 1.5.0

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
     11     276     107  Ignasi Marimon-Clos
      1     364       0  Renato Cavalcanti
      1       8       2  Sergey Morgunov
      1       4       4  Martynas Mickevičius
      1       5       5  	Dale Wijnand
```

