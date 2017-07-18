---
title: Lagom 1.3.6 is released!
date: 2017-07-17
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.3.6
---

Following from the [first preview release of Lagom 1.4.0](lagom-1-4-0-M1.md), several smaller improvements and fixes that were made since then have been backported to the 1.3 stable branch.

1.3.6 contains small bug fixes and minor enhancements, and a couple of APIs were deprecated to fix typos and inconsistencies. One important fix improves the reliability of publishing events to Kafka by ensuring that topic producers are always restarted after failure (with exponential back off).

We've continued to get great pull requests from the Lagom community. Thanks to GitHub users [Edouard Kaiser](https://github.com/edouardKaiser), [AmeerGN](https://github.com/AmeerGN), [Elijah Rippeth](https://github.com/erip), [mihbor](https://github.com/mihbor), and Outreachy intern [Yuliana Apaza](https://github.com/yg-apaza) for your contributions to Lagom 1.3.6!

## Updating

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

## Change log

See the [change log](/changelog.html) for more details on exactly what has been fixed in each release.
