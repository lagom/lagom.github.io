---
title: Lagom 1.4.5 released!
date: 2018-05-16
author_github: TimMoore
tags: news
summary: >
    The Lagom team has released Lagom 1.4.5
---

The Lagom team has released Lagom 1.4.5.

This release provides several bug fixes and updates.

## Highlights:

- Updates Play to [2.6.13](https://blog.playframework.com/play-2-6-13-released-2/)
- Updates Akka to [2.5.12](https://akka.io/blog/news/2018/04/13/akka-2.5.12-released)
- Updates Akka HTTP to [10.0.13](https://akka.io/blog/news/2018/03/23/akka-http-10.0.13-released)
- Fixes a bug in the TestKit for services using RDBMS persistence that overrides the database driver selected in `application.conf` with H2 ([#1297](https://github.com/lagom/lagom/pull/1297))
- Fixes a bug in the RDBMS persistence module that could cause perpetual timeout errors if creating the offset table fails on startup ([#1336](https://github.com/lagom/lagom/issues/1336))

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.4...1.4.5) and the issues and pull requests assigned to the [1.4.5 milestone](https://github.com/lagom/lagom/milestone/30?closed=1).


## Updating a Lagom project to version 1.4.5

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Updating to sbt 1.x

Support for sbt 1.x was quietly released with Lagom 1.4.0, but at the time we had not yet thoroughly tested it with Lagom, so we did not yet document this support or recommend it for most users.

Some adventurous users have been using it since then, and in the meantime we have updated all of our example applications and new project templates. Since we haven't encountered any new problems with sbt 1.x, we now recommend it for all users of Lagom with sbt.

You can update the sbt version in the `project/build.properties` file. The most recent version at the time of this writing is [1.1.5](https://developer.lightbend.com/blog/2018-05-09-sbt-1-1-5-patch-release/).

You might also need to update other plugins, global configuration, or custom build configuration. See the [sbt documentation](https://www.scala-sbt.org/1.x/docs/Migrating-from-sbt-013x.html) for details on migrating from version 0.13.x.


## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

<pre>
    8     342      81 Ignasi Marimon-Clos
    4     150      58 Tim Moore
    4       4       4 Play Team
    3       6       6 Brent Eritou
    2      57      40 Sebastien Coquelin
    1       1       3 Renato  Cavalcanti
    1      15      20 Jules Ivanic
    1       1       1 Riccardo Sirigu
    1      91      88 Patrick Prémont
    1      55       1 Kyle Florence
    1       8       6 Ido Shamun
    1      22       3 James Roper
</pre>
