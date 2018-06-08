---
title: Lagom 1.4.6 released!
date: 2018-05-31
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.4.6
---

The Lagom team has released Lagom 1.4.6.

This release provides several bug fixes and updates.

## Highlights:

- Updates Akka to [2.5.13](https://akka.io/blog/news/2018/06/08/akka-2.5.13-released)
- Updates Akka Persistence JDBC to [3.4.0](https://github.com/dnvriend/akka-persistence-jdbc/releases/tag/v3.4.0)
- Fix in testkit to [allow tests using Play's DBAPI](https://github.com/lagom/lagom/pull/1355)
- Small fixes improving the error reporting for developers. E.g. improved error message in [some issues in the Service.Descriptor](https://github.com/lagom/lagom/issues/1362) and improved error message when there's [no connection settings for userland read-side DB](https://github.com/lagom/lagom/pull/1372)

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.5...1.4.6) and the issues and pull requests assigned to the [1.4.6 milestone](https://github.com/lagom/lagom/milestone/31?closed=1).

## Updating a Lagom project to version 1.4.6

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

<pre>
  commits  added  removed
      7     158     652 Tim Moore
      3      82      52 Ignasi Marimon-Clos
      1      22      14 Patrik Nordwall
      1     100      10 James Roper
      1      21       6 Renato Cavalcanti
      1       2       2 Wade Waldron
      1       2       2 Markus Hennerbichler
</pre>
