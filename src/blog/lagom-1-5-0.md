---
title: Lagom 1.5.0 released!
date: 2019-03-20
author_github: ignasi35
tags: news
canonical_rel: http://developer.lightbend.com/
summary: >
    The Lagom team has released Lagom 1.5.0

---

We are happy to announce the availability of Lagom 1.5.0. This is the GA release of Lagom 1.5.0 and is production ready. We invite all our users to upgrade.

This version builds on top of the [2.7.x series of Play](https://blog.playframework.com/play-2-7-0-is-here/), [Alpakka Kafka 1.0](https://akka.io/blog/news/2019/02/28/alpakka-kafka-1.0-released), and the latest improvements on Akka and Akka Management. Here’s a list of the most relevant improvements.



# Akka Management

[Akka Management](https://developer.lightbend.com/docs/akka-management/current/) is a suite of tools for operating Akka powered applications. Akka Management uses a dedicated HTTP port to expose a few routes allowing remote inspection of the state of the Akka's Actor System. For example, if the process is a member of an Akka Cluster, these endpoints will report if the node already joined the cluster.

Lagom exposes the Akka Management HTTP port out of the box. Lagom will add Health Check routes by default. You can reuse library provided health checks or plugin your own. For example, Lagom uses cluster status to determine when the node is healthy. This is called Cluster Membership Check and is provided by the Akka Cluster HTTP Management library.

Another improvement Lagom 1.5 introduces, thanks to the adoption of Akka Management, is the simpler, more robust cluster formation via [Akka Cluster Bootstrap](https://developer.lightbend.com/docs/akka-management/current/bootstrap/). Instead of using a static list of seed nodes or relying on convoluted custom scripts to build the list of seed nodes dynamically, Cluster Bootstrap discovers the existing nodes via plugins adapting Akka Cluster Bootstrap to your orchestration environment (e.g. Kubernetes, Marathon). A static list of seed nodes is still supported but we recommend migrating to Cluster Bootstrap.

# Deployment

Lagom 1.5.0 will no longer support Lightbend Orchestration (which was [Incubating](https://developer.lightbend.com/docs/reactive-platform/2.0/support-terminology/index.html#incubating)). Despite accelerating the first deployment, using Lightbend Orchestration was producing friction and reducing flexibility to tune the deployment. Moving forward, there will be no automated handling of the deployment and the suggested way is to manually maintain the production settings, the deployment descriptors and related scripts.

If you are targeting OpenShift or Kubernetes environments the new a [Guide to Deploy Lightbend Applications](https://developer.lightbend.com/guides/openshift-deployment/) covers all the steps and details (including a sample application) to setup and tune your system.

# gRPC ([Incubating](https://developer.lightbend.com/docs/reactive-platform/2.0/support-terminology/index.html#incubating))

Lagom supports gRPC for cross-service communication. The original HTTP/JSON-based transport is not disappearing but, instead, Lagom introduces gRPC so users can choose to expose alternative transports increasing the adoption of their services.

This feature drove some improvements which non-gRPC users will also welcome.

Lagom already supported HTTP/2 since it is built on top of Play. In Lagom 1.5 we’ve reviewed all the necessary pieces so HTTP/2 can also be used on dev mode. In the same spirit, it is now also possible to use encrypted (TLS) communication on dev mode.

As a final improvement driven by the adoption of gRPC, it is now possible to embed any vanilla Play Router into a Lagom service. We call the support for Additional Routers. You are now able to easily reuse the Play and Akka building blocks whenever the Lagom API is not enough to complete the task at hand. Using Additional Routers it is trivial to reuse Play code you already had or extend Lagom with low-level features supported in Play but not exposed in Lagom directly (such as uploading files).

## Updating a Lagom project to version 1.5.0

First, make sure you read the Lagom 1.5 Migration Guide for [Scala](https://www.lagomframework.com/documentation/latest/scala/Migration15.html) or [Java](https://www.lagomframework.com/documentation/latest/java/Migration15.html).

To update an sbt project, change the version of `lagom-sbt-plugin` in your `project/plugins.sbt` file.

To update a Maven project, change the `lagom.version` property in your top-level `pom.xml`.

After updating, it is recommended to fix any new deprecation warnings you see when compiling or running your services.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

```
commits author    
     46 Ignasi Marimon-Clos
     33 Renato Cavalcanti
     16 Tim Moore
     15 Elijah Rippeth
     12 Dale Wijnand
     10 Marcos Pereira
      4 Martynas Mickevičius
      3 Ayush Prashar
      1 sullis
      1 danielklein45
      1 Zhonglai Zhang
      1 Sergey Morgunov
      1 Riccardo Sirigu
      1 Prashant Sharma
      1 Piyush Goyal
      1 Kunal sethi
      1 João Guitana
      1 Ido Shamun
      1 Fran Bermejo
      1 Enno
      1 Brent Eritou  
      1 0xflotus
```
