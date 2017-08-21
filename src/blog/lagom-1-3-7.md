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

- Update Akka to 2.4.20 - [Security Patch Release](http://akka.io/blog/news/2017/08/10/akka-2.4.20-released)
- Changed logging default settings - file appender removed (see details below)
- Experimental Service Gateway based on akka-http
- Storing read-side processor offsets for unhandled events
- Automatic load of `LogbackLoggerConfigurator` for Lagom Scala Applications. Make sure you remove the [previously-documented](https://github.com/lagom/lagom/issues/534) workaround you might have added for it. 
- Other small bug fixes and minor enhancements


We've continued to get great pull requests from the Lagom community. Thanks to GitHub users [Dominik Kunz](https://github.com/domkun),
[Jules Ivanic](https://github.com/guizmaii), [Wayne Wang](https://github.com/WayneWang12), [Ashish Tomer](https://github.com/ashishtomer) and [Ben McCann](https://github.com/benmccann) for your contributions to Lagom 1.3.7!


## Logging settings changes
We have changed different aspects of the default production and development logging settings which are explained below. Make sure you take that into consideration to avoid losing logging information in production environments. Services that have a custom Logback or log4j configuration already will not be affected.

In case you wish to keep the previous settings, you can add your own custom logging settings as explained in [Java Guide](https://www.lagomframework.com/documentation/1.3.x/java/SettingsLogger.html#Custom-configuration) and [Scala Guide](https://www.lagomframework.com/documentation/1.3.x/scala/SettingsLogger.html#Custom-configuration). The previous settings can be found in the 1.3.6 versions of [`logback-lagom-default.xml`](https://github.com/lagom/lagom/blob/1.3.6/logback/src/main/resources/logback-lagom-default.xml) and [`logback-lagom-dev.xml`](https://github.com/lagom/lagom/blob/1.3.6/logback/src/main/resources/logback-lagom-dev.xml).

If you do have a custom configuration, but would like to incorporate the improvements made to the defaults, you can view the new versions of [`logback-lagom-default.xml`](https://github.com/lagom/lagom/blob/1.3.7/logback/src/main/resources/logback-lagom-default.xml) and [`logback-lagom-dev.xml`](https://github.com/lagom/lagom/blob/1.3.7/logback/src/main/resources/logback-lagom-dev.xml).

### Logging pattern layout
The pattern layouts have changed to provide more useful information. Development and production environments share almost the same layout. 

* No abbreviations of logger names and stacktraces,
* Timestamps have millisecond precision and a sortable format.  
  The timestamp format is now:
  - **Production:** `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'` in UTC,  
  - **Development:** `HH:mm:ss.SSS` in local time (date omitted).
  
If you have configured log parsers for the old format, we recommend to adapt them accordingly when upgrading.

### Logging level
Log level defaults to **INFO** instead of **WARN**. That includes the logging from Play, Akka and Lagom itself as well as the default root logger. 

### File Appenders
Since microservices should be easily movable between hosts, and be location-independent, we decided to remove Lagom's file appender. Instead, you can have your orchestration solution capture stdout and aggregate it. If desired, file appenders can be added by providing your own logging configuration as explained in  [Java Guide](https://www.lagomframework.com/documentation/1.3.x/java/SettingsLogger.html#Custom-configuration) and [Scala Guide](https://www.lagomframework.com/documentation/1.3.x/scala/SettingsLogger.html#Custom-configuration).

## Akka-http based Service Gateway (experimental)

We introduced a new implementation of the development mode service gateway based on `akka-http`. This is still experimental and therefore not enabled by default. In a future version of Lagom, this implementation will replace the current Netty-based implementation. Please try out the new service gateway implementation and raise an issue in GitHub if you find any problems.

You can enable it by adding the following in your build.

In sbt:

```scala
lagomServiceGatewayImpl in ThisBuild := "akka-http"
```

In Maven root project pom:

```xml
<plugin>
    <groupId>com.lightbend.lagom</groupId>
    <artifactId>lagom-maven-plugin</artifactId>
    <version>${lagom.version}</version>
    <configuration>
        <serviceGatewayImpl>akka-http</serviceGatewayImpl>
    </configuration>
</plugin>
```

## Updating a Lagom project to version 1.3.7

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.
