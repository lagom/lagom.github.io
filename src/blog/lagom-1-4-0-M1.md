---
title: Lagom 1.4.0-M1 is released!
date: 2017-06-29
author_github: ignasi35
tags: news
summary: >
    Lagom 1.4.0-M1 has arrived!
---

We are thrilled to announce that Lagom is moving fast into upgrading it's underlying stack (Akka, Play and Scala) to corresponding latest versions. This is the biggest change you may expect in the upcoming version 1.4.0 of Lagom. There are still [few issues open](https://github.com/lagom/lagom/milestone/17) and some of these pending issue could potentially be incompatible with the effort we've done in Lagom 1.4.0-M1 already.

Lagom 1.4.0 includes a new feature to [start and stop external services](https://github.com/lagom/lagom/pull/763) when using `sbt runAll`. This allows binding infrastructure services to the Developer Mode lifecycle improving the developer experience. This release also includes a new, community developed support to develop [Read-Side code using Slick](https://github.com/lagom/lagom/pull/714).

This is a preview release not ready for production so feel free to use it on a safe environment or a staging environment. We know we want to make some more improvements like using the new [Akka Coordinated shutdown](https://github.com/lagom/lagom/issues/742) or [use Akka's DData instead of persistent Shard Coordinator](https://github.com/lagom/lagom/issues/816). We still have to complete the upgrade to support Scala 2.12 but it's on our radar for the 1.4.0 release.

This release includes several improvements with an important number of community contributions by [erip](https://github.com/erip), [odwrotnie](https://github.com/odwrotnie), [yg-apaza](https://github.com/yg-apaza), [benmccann](https://github.com/benmccann) and  [himani1](https://github.com/himani1) in bug fixing, documentation and even adding features. Thanks all, you rock!

We're close to releasing a ConductR version that supports the 1.4.0 family of Lagom so if that's your preferred deployment strategy you will need to wait a few days until we've baked that release.



## Updating

This is a minor release so there are a few deprecated APIs you should migrate from. Also, the upgrade of Play and Akka may require you attention. Please have a look at the migration guide to 1.4 ([java](/documentation/1.4.x/java/Migration14.html) or [scala](/documentation/1.4.x/scala/Migration14.html)).

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

## Change log

See the [change log](/changelog.html) for more details on exactly what has been fixed in each release.
