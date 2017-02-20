---
title: Lagom 1.3.0 is released!
date: 2017-02-24
author_github: jroper
tags: news
summary: >
    Lagom 1.3.0 has been released! This much anticipated release includes the new Lagom Scala API, an issue voted on by over 
    [400 people](https://github.com/lagom/lagom/issues/1). In addition, it also includes JPA support, allowing developers familiar
    with a more traditional Java stack to ease their way into Lagom without having to relearn everything at once.
---

Lagom 1.3.0 has been released! This much anticipated release includes the new Lagom Scala API, an issue voted on by over [400 people](https://github.com/lagom/lagom/issues/1). In addition, it also includes JPA support, allowing developers familiar with a more traditional Java stack to ease their way into Lagom without having to relearn everything at once.

## Scala API

The new Scala API provides feature parity with the Java API, meaning that both APIs are now first class citizens in the Lagom ecosystem. We are committed to providing a great microservices development framework for both languages, and this release is making good on that promise for Scala. Going forward with new features, in Lagom, the definition of done in Lagom is defined to mean that a feature is not complete unless it provides API and documentation parity for both the Scala and Java APIs.

Having said that the Scala and Java APIs have feature parity, this does not mean that the APIs and approaches used are identical. Our approach to creating Scala APIs in Lagom has been to provide Scala developers with more type safety and less reliance on reflection than the Java API uses.  Some major differences therefore between the Scala API and Java API are:

* Use of type class based serialization, based on play-json, rather than using Jackson's reflection based databind API.
* Use of compile time wired dependency injection, utilizing the cake pattern and [MacWire](https://github.com/adamw/macwire), rather than using Guice.
* Use of macros to implement service clients and to wire service implementations, rather than dynamic proxies and reflection.

We feel that developers that have already bought into the increased type safety that using Scala can give will appreciate this difference in approach, and so it makes sense to differ the Scala and Java APIs in this way.

To get started with the Lagom Scala API, read our [getting started guide](http://www.lagomframework.com/documentation/1.3.x/scala/Installation.html).

## JPA support

Lagom's new JPA support includes two important features:

* Managed execution of interactions with a JPA entity manager.
* Read side processor support with automatic offset management.

In a non blocking framework, the use of blocking APIs, such as JPA, can be very dangerous.  A few database queries can easily hold up the entire application, and the interaction between thread pools and connection pools is prone to deadlocks.  It's therefore important that any blocking operations are carefully managed, ensuring that the thread pools used by database operations are not allowed to block asynchronous IO operations from proceeding.

Lagom takes responsibility of this management, by providing APIs that allow developers to safely use blocking entity manager operations without interfering with asynchronous IO threads.  This is provided through the use of a [`JpaSession`](http://www.lagomframework.com/documentation/1.3.x/java/api/index.html?com/lightbend/lagom/javadsl/persistence/jpa/JpaSession.html) abstraction.

Lagom also offers specific support for JPA read-side processors, allowing users to seamlessly implement read-sides that focus on the database access logic, rather than having to concentrate on the mechanics of read-sides and offset tracking.  For more information, read the [JPA read-side documentation](http://www.lagomframework.com/documentation/1.3.x/java/ReadSideJPA.html)

## Getting started

Lagom is a framework which is designed to ease developers into writing reactive microservices, providing guide rails to ensure that they write services that are resilient and scalable.  For this reason, the getting started experience in Lagom is very important, and that's why we've started investing a lot of effort into the usability of our getting started guides.  This effort is on going, but Lagom 1.3 comes with the first glimpse of this work, with a reworked downloads page on the website (now renamed to [Get started](http://www.lagomframework.com/get-started.html)), and an improved getting started section in our documentation.

As we continue this effort, you should see an overhaul of the journey we take users on in our documentation, from design and development through to production.

## Improved test APIs

Another place that we've put effort into is providing how value test APIs to facilitate in testing Lagom services.  In 1.3, a message broker testkit has been introduced, to allow testing of message broker integration.  You can read about these APIs for [Java](http://www.lagomframework.com/documentation/1.3.x/java/MessageBrokerTesting.html) and [Scala](http://www.lagomframework.com/documentation/1.3.x/scala/MessageBrokerTesting.html).

## Migration

Lagom 1.3 is almost entirely binary and source compatible with 1.2 - we have taken great care to ensure that we only break compatibility when all other options have been exhausted. A short migration guide for upgrading from Lagom 1.2 can be found [here](http://www.lagomframework.com/documentation/1.3.x/java/Migration13.html). In addition, we've also provided a short guide for the steps necessary for migrating from using the Java API to using the Scala API, which can be found [here](http://www.lagomframework.com/documentation/1.3.x/scala/Migration13.html).