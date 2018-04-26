---
title: Lagom 1.3.10 is released!
date: 2017-11-01
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.3.10
---

The Lagom team has released Lagom 1.3.10. For a list of the specific changes in Lagom 1.3.10, see the [change log](/changelog-1.3.x.html).

This release provides a few bug fixes, minor improvements and dependency updates. As an overview, this release contains the following:

- Play updated to 2.5.18, fixing a security issue (see [Security Advisory](#security-advisory) below)
- Akka Persistence Cassandra updated to 0.30, fixing an issue where [events can be skipped in a read-side processor](https://github.com/lagom/lagom/issues/1032)
- You can now use [collection-value path parameters](https://github.com/lagom/lagom/issues/643) (for example, http://localhost:9000/users?username=alice&username=bob&username=carlos)
- Fixes a [compatibility issue](https://github.com/lagom/lagom/issues/1018) with Firefox and streaming service calls


We've continued to get great pull requests from the Lagom community. Special thanks to GitHub users [Jirka Kremser](https://github.com/Jiri-Kremser), [Micho](https://github.com/micho10), [Jules Ivanic](https://github.com/guizmaii), [Olivier Nouguier](https://github.com/cheleb), [Saniya Tech](https://github.com/saniyatech), [Julien Jean Paul Sirocchi](https://github.com/sirocchj) and [HSalaila](https://github.com/HSalaila) for your contributions to Lagom 1.3.10!

## Security Advisory

The Play 2.5.18 update contains a fix for [Improper Vary header handling in CORS filter](https://www.playframework.com/security/vulnerability/20171005-CorsVaryHeader). While this issue does not affect Lagom directly, some Lagom users might use Play's CORS filter in their services. If this applies to you, please read the Play security advisory carefully.

The Lagom team recommends that all users of Lagom read the Play [Security Vulnerabilities page](https://www.playframework.com/security/vulnerability) and subscribe to the [Play security announcements mailing list](https://groups.google.com/forum/#!forum/play-framework-security).

## Updating a Lagom project to version 1.3.10

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.
