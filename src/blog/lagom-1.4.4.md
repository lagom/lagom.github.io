---
title: Lagom 1.4.4 released!
date: 2018-03-28
author_github: renatocaval
tags: news
summary: >
    The Lagom team has released Lagom 1.4.4
---

The Lagom team has released Lagom 1.4.4. 
(we are skipping 1.4.3 due to a small mistake in the release process)

This release provides a fix for [#1250](https://github.com/lagom/lagom/issues/1250). 

[#1250](https://github.com/lagom/lagom/issues/1250) only affects Lagom projects making usage of JDBC persistence layer, being it for the journal and/or for read-side tables. 

This release upgrades the versions of the underlying Slick library to `3.2.3` and the `akka-persistence-jdbc` plugin to version `3.3.0`.

## Slick 3.2.3 special note

Since Slick 3.2.2, the `maxConnections` must equal `numThreads`. This is to prevent some situations where a deadlock could occur. A warning will be emitted in case `maxConnections > numThreads` and a runtime exception will be thrown if `maxConnections < numThreads`. Therefore the best possible configuration is `maxConnections == numThreads`. See [slick/slick#1855](https://github.com/slick/slick/pull/1855) for detailed information.

These values are configured by Lagom automatically under the configuration key `db.default.async-executor`.

```
db.default {
  async-executor {
    # number of objects that can be queued by the async executor
    queueSize = 10000
    
    # 5 * number of cores
    numThreads = 20

    # same as number number of threads
    minConnections = 20

    # same as number of threads
    maxConnections = 20
  }
}
```

If you haven't changed the settings for your `async-executor`, you have nothing to do. However, if you have configured your `aync-executor` with other values, you should review it and make sure that `maxThreads == maxConnections`.

See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.2...1.4.4) and the issues and pull requests assigned to the [1.4.4 milestone](https://github.com/lagom/lagom/milestone/29?closed=1).


## Updating a Lagom project to version 1.4.4

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.


## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.
