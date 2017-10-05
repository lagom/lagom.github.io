---
title: Lagom 1.3.9 is released!
date: 2017-10-05
author_github: rcavalcanti
tags: news
summary: >
    The Lagom team has released Lagom 1.3.9
---

The Lagom team has released Lagom 1.3.9. For a list of the specific changes in Lagom 1.3.9, see the [change log](/changelog.html).

This release provides a few bug fixes and minor improvements. As an overview, this release contains the following:

- a few dependencies updates (Play 2.5.17, Sbt 0.13.16 and ZooKeeper 3.4.10)
 - the actor system termination when the cause of termination is a split brain has been simplified. This will make the code more maintainable but it has no impact on how Lagom behaves ([978](https://github.com/lagom/lagom/issues/978)). 
 - we've had a great community contribution fixing a bug that makes Lagom compatible with MariaDB ([1024](https://github.com/lagom/lagom/issues/1024)).
 - also from the community, we've had a contribution improving the persistent entities [Schema Evolution](https://www.lagomframework.com/documentation/1.3.x/java/Serialization.html#Schema-Evolution). This improvement only affects javadsl users ([952](https://github.com/lagom/lagom/issues/952)).


We've continued to get great pull requests from the Lagom community. Thanks to GitHub users [Pallavi Sing](https://github.com/PallaviSingh1992),
[dr3s](https://github.com/dr3s) and [Olivier Deckers](https://github.com/olivierdeckers) for your contributions to Lagom 1.3.9!



## Updating a Lagom project to version 1.3.9

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.
