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

- Updates Akka to [2.5.13](https://akka.io/blog/news/INVALID_URL)
- Updates Akka Persistence JDBC to [3.4.0](https://github.com/lagom/lagom/issues/1374)
- Fix in testkit to [allow tests using Play's DBAPI](https://github.com/lagom/lagom/pull/1355)
- Small fixes improving the error reporting for developers. E.g. improved error message in [some issues in the Service.Descriptor](https://github.com/lagom/lagom/issues/1362) and improved error message when there's [no connection settings for userland read-side DB](https://github.com/lagom/lagom/pull/1372)

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.5...1.4.6) and the issues and pull requests assigned to the [1.4.6 milestone](https://github.com/lagom/lagom/milestone/31?closed=1).

## Building GDPR capable systems with Lagom

Lightbend has investigated the various considerations that you may need to take into account while attempting to make your systems GDPR-compliant. To help our customers with this daunting task we can now offer the new module Akka GDPR that will assist you in building a compliant system.

Lagom Persistence is based on Event Sourcing, which means storing the entire event sequence leading to the total state of some entity. This also means, that deleting or modifying state in order to be compliant with GDPR means that all those events, which carry personal information, will have to be modified rather than just one single state. It can be difficult to modify all such events and also other places where the information may be stored, such as denormalized projections, snapshots and backups. Data shredding (aka crypto shredding) can be used to forget information instead of deleting or modifying it. This is achieved by encrypting the data with a key for a given data subject id (person) and deleting the key when that data subject is to be forgotten.

Learn more about Akka GDPR in the [documentation](https://developer.lightbend.com/docs/akka-commercial-addons/current/gdpr/index.html) and contact [Lightbend](https://www.lightbend.com/contact) for further discussion about how to use this module in your systems.


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
