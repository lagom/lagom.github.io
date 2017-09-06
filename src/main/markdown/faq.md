# Frequently Asked Questions

Have a question about Lagom? We've sorted out a sample of frequently asked questions and provided answers below. If your question is not covered here, try these resources:

- [Lagom Gitter channel](https://gitter.im/lagom/lagom)
- [lagom-framework mailing list](https://groups.google.com/forum/#!forum/lagom-framework)
- [Stack Overflow Lagom questions](https://stackoverflow.com/questions/tagged/lagom)

## Is Lagom open source or commercial?

Lagom is completely open source, free to use, and has a vibrant and growing community. However, you do have the option to purchase a [Lightbend subscription](https://www.lightbend.com/platform/subscription) that provides technical support as well as license to use Lightbend Enterprise Suite, which includes a range of powerful application management, intelligent monitoring, and enterprise integration tooling.

## Does Lagom require me to use Cassandra for persistence?

No, Lagom supports using either Cassandra or relational databases, including MySQL, PostegreSQL, Oracle and H2. The Lagom development environment provides and uses Cassandra by default because it offers great scalability and replication features.  See more in the [Java documentation](/documentation/current/java/PersistentEntity.html) or the [Scala documentation](/documentation/current/scala/PersistentEntity.html).

## Does Lagom require a me to run a Kafka cluster?

No, but it is very useful for common communication scenarios. Lagom's Message Broker API integrates Persistent Entities with [Apache Kafka](https://kafka.apache.org/) to provide publish and subscribe messaging between services with at-least-once delivery guarantees. This enables you to design decoupled and highly-resilient systems of microservices.

If you prefer not to run a Kafka cluster, however, the Message Broker API is completely optional. You can write Lagom services that communicate with each other directly by using HTTP requests or by streaming data over a WebSocket.

The Message Broker API was designed to make it possible to support for alternative message broker services in a future version of Lagom.

See more in the [Java documentation](/documentation/current/java/MessageBroker.html) or the [Scala documentation](/documentation/current/scala/MessageBroker.html).

## Does Lagom require using event sourcing and CQRS?

You can handle data persistence any way you like in a Lagom service, but we strongly encourage event sourcing and CQRS, and the Lagom Persistence API is designed to support these patterns. For services where you do not wish to use event sourcing and CQRS, you can execute arbitrary database statements using Lagom's `CassandraSession`, `JdbcSession` or `JpaSession`. You can also use any other data access client you choose, as long as you are careful to avoid I/O blocking the primary thread pool.

See more in the Lagom documentation on [Managing data persistence](/documentation/current/java/ES_CQRS.html) and [Advantages of Event Sourcing](/documentation/current/java/ESAdvantage.html).

## Can I use serialization methods other than JSON?

Yes, Lagom supports extensible serialization, both for messages transmitted between different services and for internal messages and persistent data used within a service. JSON is supported by default, as it is simple and well understood. Services that require higher performance or more compact data can use alternatives such as [Protocol Buffers](https://developers.google.com/protocol-buffers/), [Apache Avro](http://avro.apache.org/), [Kryo](https://github.com/EsotericSoftware/kryo), or any other serializer that you choose.

See more in the Lagom documentation on Message Serializers ([Java](/documentation/current/java/MessageSerializers.html)/[Scala](/documentation/current/scala/MessageSerializers.html)) and Persistent Entity Serialization ([Java](/documentation/current/java/Serialization.html)/[Scala](/documentation/current/scala/Serialization.html)).

## In the example applications, why are data classes duplicated between the service API and implementation projects?

This is an example of an anti-corruption layer. In Lagom, we use anti-corruption layers for all API payloads (requests/responses) and published events. While it might seem like unnecessary duplication, using anti-corruption layers allows the internal implementation to be changed without changing the public API. For example, you might want to enrich an internal event with some fields that you need on a read side but you don’t need on the published events.

For more information, see Mark Needham's blog on [anti-corruption layers](http://www.markhneedham.com/blog/2009/07/07/domain-driven-design-anti-corruption-layer/).

## Do I have to use ConductR to deploy Lagom services? Can I deploy with Kubernetes?

Lagom doesn't prescribe any particular production environment, however out of the box support for Lagom is provided by [Lightbend ConductR](https://www.lightbend.com/products/conductr). If you are interested in deploying on [Kubernetes](https://kubernetes.io/), see our guide that demonstrates [how to deploy the Chirper example application](https://developer.lightbend.com/guides/k8s-microservices/).

For more information on running Lagom services in production, see the [Java documentation](/documentation/current/java/ProductionOverview.html) or the [Scala documentation](/documentation/current/scala/ProductionOverview.html)
