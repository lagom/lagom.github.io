---
title: Lagom 1.3.7 is released!
date: 2017-08-18
author_github: rcavalcanti
tags: news
summary: >
    The Lagom team has released Lagom 1.3.7
---

The Lagom team has released Lagom 1.3.7. For a list of the specific changes in Lagom 1.3.7, see the [change log](/changelog.html).

As an overview, this release contains the following:

- Update Akka to 2.4.20 - (Security Patch Release)[http://akka.io/blog/news/2017/08/10/akka-2.4.20-released] 
- Changed logging default settings - file appender removed (see details below)
- Experimental Service Gateway based on akka-http
- Storing read-side processor offsets for unhandled events
- Upgrade to latest sbteclipse
- Automatic load of `LogbackLoggerConfigurator` for Lagom Scala Applications
- Other small bug fixes and minor enhancements

We decided to remove the existing file appender. A microservices based application must keep its services movable. In that case, it doesn’t make sense to write to files. Instead, your orchestration solution of choice would better capture the _stdout_ and aggregate it. If desired, it can be explicitly added as explained in the [Java Guide](https://www.lagomframework.com/documentation/1.3.x/java/SettingsLogger.html#Configuring-Logging) and [Scala Guide](https://www.lagomframework.com/documentation/1.3.x/scala/SettingsLogger.html#Configuring-Logging)

We've continued to get great pull requests from the Lagom community. Thanks to GitHub users [Dominik Kunz](https://github.com/domkun),
[Jules Ivanic](https://github.com/guizmaii), [Wayne Wang](https://github.com/WayneWang12), [Ashish Tomer](https://github.com/ashishtomer) and [Ben McCann](https://github.com/benmccann) for your contributions to Lagom 1.3.7!

## Updating a Lagom project to version 1.3.7

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.
