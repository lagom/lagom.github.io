---
title: Lagom 1.3.6 is released!
date: 2017-07-17
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.3.6
---

The Lagom team has released Lagom 1.3.6. For a list of the specific changes in Lagom 1.3.6, see the [change log](/changelog-1.3.x.html).

As an overview, this release contains the following:

- A fix for event publishing to Kafka to ensure that topic producers are automatically restarted after failure (with exponential back off)
- Deprecated APIs to fix typos and inconsistencies.
- Other small bug fixes and minor enhancements

We've continued to get great pull requests from the Lagom community. Thanks to GitHub users [Edouard Kaiser](https://github.com/edouardKaiser), [AmeerGN](https://github.com/AmeerGN), [Elijah Rippeth](https://github.com/erip), [mihbor](https://github.com/mihbor), and Outreachy intern [Yuliana Apaza](https://github.com/yg-apaza) for your contributions to Lagom 1.3.6!

## Updating a Lagom project to version 1.3.6

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.
