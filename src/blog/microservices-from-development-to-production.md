---
title: Microservices - From development to production
date: 2016-07-26
author_github: huntc
tags: conductr screencast
summary: >
    Let’s face it, microservices sound great, but they’re sure hard to set up and get going. There are service gateways to consider, setting up service discovery, consolidated logging, rolling updates, resiliency concerns… the list is almost endless. Distributed systems benefit the business, not so much the developer.

---

## Microservices sound great
Let’s face it, microservices sound great, but they’re sure hard to set up and get going. There are service gateways to consider, setting up service discovery, consolidated logging, rolling updates, resiliency concerns… the list is almost endless. Distributed systems benefit the business, not so much the developer.

Until now.

[![sbt to install microservices](http://img.youtube.com/vi/5qbX7UwuMYM/0.jpg)](http://www.youtube.com/watch?v=5qbX7UwuMYM)

Whatever you think of [sbt](http://www.scala-sbt.org/), the primary build tool of [Lagom](http://www.lagomframework.com/documentation/1.0.x/java/Home.html), it is a powerful beast. As such we’ve made it do the heavy lifting of packaging, loading and running your entire Lagom system, including Cassandra, with just one simple command:


```
sbt> install
```

This “install” command will introspect your project and its sub-projects, generate configuration, package everything up, load it into a local [ConductR](http://conductr.lightbend.com/docs/1.1.x/Home) cluster and then run it all.
*Just. One. Command.*
Try doing that with your >insert favourite build tool here<!

Lower level commands also remain available so that you can package, load and run individual services on a local ConductR cluster in support of getting everything right before pushing to production.

Lagom is aimed at making the developer productive when developing microservices. The ConductR integration now carries that same goal through to production.

Please watch the 8 minute video for a comprehensive demonstration, and be sure to [visit the “Lagom for production” documentation](http://www.lagomframework.com/documentation/1.0.x/java/ConductR.html) in order to keep up to date with your production options. While we aim for Lagom to run with your favourite orchestration tool, we think you’ll find the build integration for ConductR hard to beat. Finally, you can focus on your business problem, and not the infrastructure to support it in production.

Enjoy!
