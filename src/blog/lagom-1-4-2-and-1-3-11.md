---
title: Lagom 1.4.2/1.3.11 released!
date: 2018-03-22
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.4.2 and 1.3.11.
---

The Lagom team has released the versions 1.4.2 and 1.3.11. For a list of the specific changes in Lagom 1.4.2, see the [change log](/changelog.html), and for a list of changes on Lagom 1.3.11 see the [change log (1.3.x)](/changelog-1.3.x.html).

## Lagom 1.3.11

This release provides several bug fixes and improvements. As an overview, this release contains the following:

- Fixes a bug in loading settings from the `LagomClientFactory`. Special thanks to [two10](https://github.com/two10) for the detailed report and bugfix suggestion. ([#1256](https://github.com/lagom/lagom/pull/1256))
- Adds out-of-the-box support for UUID's on the Path Params on your `Service.Descriptors`. ([#1252](https://github.com/lagom/lagom/issues/1252))
- Fixes Exception deserialization for `BadRequest`. ([#1247](https://github.com/lagom/lagom/issues/1247))
- Update to Scala 2.11.12. ([#1197](https://github.com/lagom/lagom/issues/1197))
- Added a check to warn about slow startup problems (thanks [Edmondo Porcu](https://github.com/edmondo1984)).  ([#1112](https://github.com/lagom/lagom/issues/1112))
- Improves error messages in path param serializer resolution (thanks [Neelaksh](https://github.com/silver-soule)). ([#1093](https://github.com/lagom/lagom/issues/1093))
- Implementation review to allow mixed persistence using non-JDBC write-side with JDBC read-side (only applies to scala API). ([#1100](https://github.com/lagom/lagom/pull/1100))
- And several small improvements in the documentation!

This release is the last including backports of general bugfixes and the `1.3.x` family may only get critical bugfixes backported onwards. If you are still using Lagom 1.3.x, we recommend you upgraded to latest versions of Lagom.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.3.10...1.3.11) and the issues and pull requests assigned to the [1.3.11 milestone](https://github.com/lagom/lagom/milestone/24?closed=1).

## Lagom 1.4.2

This release provides a few bug fixes and minor improvements over what 1.3.11 provides already (see the summary above). As an overview, this release contains the following:

- Adds the message timestamp on the Kafka Message when using [metadata-rich subscriptions](https://www.lagomframework.com/documentation/1.4.x/scala/MessageBrokerApi.html#Consuming-message-metadata).  ([#1277](https://github.com/lagom/lagom/issues/1277))
- Fixes a bug preventing conflicts between persistence backends when writing tests with mixed persistence (applies only to java API). ( [#1263](https://github.com/lagom/lagom/issues/1263))
- Added a timeout on the offset polling when starting a read-side to prevent a potential read-side block in certain failure scenarios. [#1278](https://github.com/lagom/lagom/issues/1278)

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.1...1.4.2) and the issues and pull requests assigned to the [1.4.2 milestone](https://github.com/lagom/lagom/milestone/28?closed=1).


## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.


Special thanks to the following contributors who helped with the release 1.3.11:

```
commits  added  removed
    9     117      68 Tim Moore
    6     254      35 Ignasi Marimon-Clos
    6     668     605 Renato Cavalcanti
    2       6       6 Sarun Rattanasiri
    1       1       1 Bruno Unna
    1      11      76 Ruth Stento
    1      31       7 Lutz Huehnken
    1      19       0 Edmondo Porcu
    1      59       5 Neelaksh
    1       1       1 Brent Eritou
```

Special thanks to the following contributors who helped with the release 1.4.2:

```
commits  added  removed
      9     291      56 Ignasi Marimon-Clos
      6     176     139 Tim Moore
      1      23       5 Renato Cavalcanti
      1      12       0 Jason Pickens
```
