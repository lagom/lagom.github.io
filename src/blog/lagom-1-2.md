---
title: Lagom 1.2 is released!
date: 2016-11-08
author_github: TimMoore
tags: news
summary: >
    The Lagom team is proud to announce the release of Lagom 1.2!  This release introduces support for message brokers, JDBC, and an overhaul of read sides.
---

The Lagom team is proud to announce the release of Lagom 1.2!  This release introduces support for message brokers, JDBC, and an overhaul of read sides.

If you've been following along with the RCs, there have only been minor changes since RC2, but if you're upgrading from 1.1.0 or earlier, please be sure to follow the [migration guide](/documentation/1.2.x/java/Migration12.html), as there are some changes you'll need to make to your project.

Here's a recap of the major new features that were announced with the release candidates. You can see more details in the [change log](/changelog-archive.html) or on [GitHub](https://github.com/lagom/lagom/issues?utf8=%E2%9C%93&q=milestone%3A1.2.0).

---

## Message broker support

The biggest feature of Lagom 1.2 is message broker support. This means Lagom now allows both direct streaming of messages between services, as well as streaming of messages through a broker.

Lagom's message broker support does not require any one particular message broker implementation, however out of the box we have only implemented support for Kafka. Kafka is a popular very scalable message broker, and fits well in the Lagom architectural philosophy.

Lagom will automatically run Kafka for you when you run the `runAll` command, both in Maven and sbt, which makes getting started with a project that uses Kafka very simple. It also abstracts the publishing, partitioning, consuming and failure handling of messaging away from you, so you can focus primarily on your business concerns.

As with Lagom `ServiceCall`'s, which provide a protocol agnostic way of directly communicating with other services that maps down onto HTTP, Lagom provides a new `Topic` abstraction, which represents a topic that one service publishes, and one or more services consume. Through the use of service descriptors, Lagom allows all the serialization and deserialization logic for these messages to be captured and shared between services.

As a first and primary use case, we have targetted publishing a persistent entity event stream for the source of topics. This allows the implementation of remote read sides, as well as provides a great basis for guaranteed at least once delivery of events between services.

To explore more of Lagom's message broker support, read the [documentation](/documentation/1.2.x/java/MessageBroker.html).

## JDBC support

Feedback that we got from many potential users was that in order to introduce Lagom into their organisation, it would need to have support for using their existing relational database infrastructure. While a relational database is often not the best choice, particularly for storing event sourced entities, it's not necessarily a bad choice. We felt that having no JDBC support was an unnecessary blocker to organisations getting the benefits of a Lagom based architecture.

Lagom supports JDBC both for [storing persistent entities](/documentation/1.2.x/java/PersistentEntityRDBMS.html), as well as for implementing [read sides](/documentation/1.2.x/java/ReadSideRDBMS.html).

## Read side overhaul

Lagom's read side support has been overhauled. There are three major parts to this. First of all, read sides that talk to any data store can now be implemented - previously the read side processor API was specific to Cassandra. We still provide a Cassandra specific utility for building Cassandra read sides, as well as a new JDBC specific utility for building JDBC read sides, but these are just helpers for building read sides, you can build a read side in anything. Documentation for the new read side API can be found [here](/documentation/1.2.x/java/ReadSide.html).

The second is that read sides can now be sharded, by tagging persistent entity events with sharded tags. Lagom's read side processors now declare a list of tags that they process, rather than just one, and Lagom will automatically distribute the processing of these tags across the cluster. This is great for services with a high throughput of events, or when event processing can be very slow or expensive.

The final feature is that both the Cassandra and JDBC specific read side support utilities provide automatic offset tracking, meaning that your read side processors no longer need to explicitly load and persist offsets. This makes it much simpler to implement a read side processor. Documentation is available for both the [Cassandra read side support](/documentation/1.2.x/java/ReadSideCassandra.html) and the [JDBC read side support](/documentation/1.2.x/java/ReadSideRDBMS.html).

The existing Cassandra read side support is still supported, but is deprecated.

---

Thanks to everyone who provided feedback on the release candidates. There are more exciting things planned for Lagom 1.3, so please [Get Involved](/get-involved.html) and help shape the future of Lagom.
