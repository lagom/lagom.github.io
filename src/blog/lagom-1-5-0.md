---
title: "Lagom 1.5 Released: Akka Management, Kubernetes, OpenShift, gRPC, Couchbase and more"
date: 2019-04-16
author_github: ignasi35
tags: news
summary: >
    The Lagom team has released Lagom 1.5.0

---

We are happy to announce the availability of Lagom 1.5.0. This is the GA release of Lagom 1.5.0 and is production ready. We invite all our users to upgrade.

This version builds on top of the [2.7.x series of Play](https://blog.playframework.com/play-2-7-0-is-here/), [Alpakka Kafka 1.0](https://akka.io/blog/news/2019/02/28/alpakka-kafka-1.0-released), and the latest improvements on Akka and Akka Management. See GitHub for the [full list of commits](https://github.com/lagom/lagom/compare/1.4.11...1.5.0). 

Here’s a list of the most relevant improvements.

## Akka Management

[Akka Management](https://developer.lightbend.com/docs/akka-management/current/) is a suite of tools for operating Akka powered applications. Akka Management uses a dedicated HTTP port to expose a few routes allowing remote inspection of the state of the Akka Actor System. For example, if the process is a member of an Akka Cluster, these endpoints will report if the node already joined the cluster.

Lagom exposes the Akka Management HTTP port out of the box. Lagom will add Health Check routes by default. You can reuse library provided health checks or plugin your own. For example, Lagom uses cluster status to determine when the node is healthy. This is called Cluster Membership Check and is provided by the Akka Cluster HTTP Management library.

Another improvement Lagom 1.5 introduces, thanks to the adoption of Akka Management, is the simpler, **more robust cluster formation via [Akka Cluster Bootstrap](https://developer.lightbend.com/docs/akka-management/current/bootstrap/)**. Instead of using a static list of seed nodes or relying on convoluted custom scripts to build the list of seed nodes dynamically, Cluster Bootstrap discovers the existing nodes via plugins adapting Akka Cluster Bootstrap to your orchestration environment (e.g. Kubernetes, Marathon). A static list of seed nodes is still supported but we recommend migrating to Cluster Bootstrap.

## Deployment with Kubernetes or Red Hat OpenShift

Lagom 1.5.0 will no longer support Lightbend Orchestration (which was [Incubating](https://developer.lightbend.com/docs/reactive-platform/2.0/support-terminology/index.html#incubating)). Despite accelerating the first deployment, using Lightbend Orchestration was producing friction and reducing flexibility to tune the deployment. Moving forward, there will be no automated handling of the deployment and the suggested way is to manually maintain the production settings, the deployment descriptors and related scripts.

If you are targeting OpenShift or Kubernetes environments the new [Guide to Deploy Lightbend Applications](https://developer.lightbend.com/guides/openshift-deployment/) covers all the steps and details (including a sample application) to set up and tune your system.

## Akka gRPC integration

Lagom 1.5.0 introduces [incubating](https://developer.lightbend.com/docs/lightbend-platform/2.0/support-terminology/index.html#incubating) support for  [Akka gRPC](https://developer.lightbend.com/docs/akka-grpc/current/) for cross-service communication. The original HTTP/JSON-based transport is not disappearing but, instead, Lagom introduces gRPC so users can choose to expose alternative transports increasing the adoption of their services.

This feature drove some smaller improvements which non-gRPC users will also welcome.
Lagom already supported HTTP/2 since it is built on top of Play. In Lagom 1.5 we’ve reviewed all the necessary pieces so HTTP/2 can also be used on dev mode. In the same spirit, it is now also possible to use encrypted (TLS) communication on dev mode. 

As a final improvement driven by the adoption of gRPC, Lagom 1.5 now supports Additional Routers ([Java API](https://www.lagomframework.com/documentation/1.5.x/java/AdditionalRouters.html) / [Scala API](https://www.lagomframework.com/documentation/1.5.x/scala/AdditionalRouters.html)), which lets you embed any vanilla Play Router into a Lagom service. You are now able to easily reuse the Play and Akka building blocks whenever the Lagom API is not enough to complete the task at hand. Using Additional Routers it is trivial to reuse Play code you already had or extend Lagom with low-level features supported in Play but not exposed in Lagom directly (such as uploading files).

## Couchbase Persistence Support

Lagom 1.5 also offers optional support for [Couchbase](https://www.couchbase.com/) as a new database option for persistent entities and read side processors through the [Akka Persistence Couchbase](https://doc.akka.io/docs/akka-persistence-couchbase/current/) module. Currently available in an [Incubating](https://developer.lightbend.com/docs/lightbend-platform/2.0/support-terminology/index.html#incubating) early access release, Akka Persistence Couchbase has been built to work with Lagom 1.5 and the 1.0 release is coming soon. See the documentation for [Couchbase Persistent Entities](https://doc.akka.io/docs/akka-persistence-couchbase/current/lagom-persistent-entity.html) and [Couchbase Read-Side support](https://doc.akka.io/docs/akka-persistence-couchbase/current/lagom-read-side.html) for more details.


## Migrating from previous versions

This release introduces very little changes over the `1.5.0-RC2` so if you already moved to that version the upgrade should be trivial. If you are still using Lagom `1.4.x` we recommend you first migrate to the latest version available of Lagom 1.4 series and then migrate to Lagom 1.5. If you are using a version older than `1.4.x`, you should migrate one version at a time. For example: from `1.3.9` to `1.3.11` (because `1.3.11` is `1.3.latest`), then from `1.3.11` to `1.4.latest` and finally to `1.5.x`. Read the appropriate [release notes](https://github.com/lagom/lagom/releases) and migration guides ([Java](https://www.lagomframework.com/documentation/latest/java/Migration15.html) / [Scala](https://www.lagomframework.com/documentation/latest/scala/Migration15.html)) on each step.

## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com/) for their continued sponsorship of the Lagom core team’s efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

```
commits author    
     53 Ignasi Marimon-Clos
     37 Renato Cavalcanti
     20 Tim Moore
     16 Dale Wijnand
     15 Elijah Rippeth
     11 Marcos Pereira
      4 Martynas Mickevičius
      3 Ayush Prashar
      2 Alden Torres
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
      1 Enno
      1 Corey Auger
      1 Brent Eritou
      1 0xflotus
```
