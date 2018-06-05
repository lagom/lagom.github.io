# Frequently Asked Questions

Have a question about Lagom? We've sorted out a sample of frequently asked questions and provided answers below. If your question is not covered here, try these resources:

- [Discuss Lagom forum](https://discuss.lagomframework.com)
- [Lagom Gitter channel](https://gitter.im/lagom/lagom)
- [Stack Overflow Lagom questions](https://stackoverflow.com/questions/tagged/lagom)

## Is Lagom open source or commercial?

Lagom is completely open source, free to use, and has a vibrant and growing community. However, you do have the option to purchase a [Lightbend subscription](https://www.lightbend.com/platform/subscription) that provides technical support as well as license to use Lightbend Enterprise Suite, which includes a range of powerful application management, intelligent monitoring, and enterprise integration tooling.

## Does Lagom require me to use Cassandra for persistence?

No, Lagom supports using either Cassandra or relational databases, including MySQL, PostegreSQL, Oracle and H2. The Lagom development environment provides and uses Cassandra by default because it offers great scalability and replication features.  See more in the [Java documentation](/documentation/current/java/PersistentEntity.html) or the [Scala documentation](/documentation/current/scala/PersistentEntity.html).

## Does Lagom require use of a message broker such as Kafka?

No, but it is very useful for common communication scenarios. Lagom's Message Broker API integrates Persistent Entities with message brokers such as [Apache Kafka](https://kafka.apache.org/) to provide publish and subscribe messaging between services with at-least-once delivery guarantees. This enables you to design decoupled and highly-resilient systems of microservices.

If you prefer not to run a message broker, however, the Message Broker API is completely optional. You can write Lagom services that communicate with each other directly by using HTTP requests or by streaming data over a WebSocket.

See more in the [Java documentation](/documentation/current/java/MessageBroker.html) or the [Scala documentation](/documentation/current/scala/MessageBroker.html).

## Does Lagom support message brokers other than Kafka?

The Message Broker API was designed to make it possible to integrate with alternative message broker services. Currently, Lagom only includes support for Kafka, but advanced users can write their own implementations of the Message Broker API. Future versions of Lagom might include new, officially-supported implementations.

If you are interested in official support for new message brokers, please review the [open Message Broker API feature requests](https://github.com/lagom/lagom/issues?q=is%3Aopen+is%3Aissue+label%3Atopic%3Amessage-broker-api+label%3Atype%3Afeature) in GitHub. You can vote or comment on existing issues, or create new ones.

## Does Lagom require using event sourcing and CQRS?

You can handle data persistence any way you like in a Lagom service, but we strongly encourage event sourcing and CQRS, and the Lagom Persistent Entity API is designed to support these patterns. For services where you do not wish to use event sourcing and CQRS, you use any data access client you choose, as long as you are careful to avoid I/O blocking the primary thread pool, which would cause performance problems and timeout errors.

Lagom provides utilities that can be used to issue non-blocking execution of database statements: `CassandraSession` ([Java](/documentation/current/java/api/com/lightbend/lagom/javadsl/persistence/cassandra/CassandraSession.html)/[Scala](/documentation/current/scala/api/index.html#com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession)), `JdbcSession` ([Java](/documentation/current/java/api/com/lightbend/lagom/javadsl/persistence/jdbc/JdbcSession.html)/[Scala](/documentation/current/scala/api/index.html#com.lightbend.lagom.scaladsl.persistence.jdbc.JdbcSession)), and `JpaSession` ([Java-only](/documentation/current/java/api/com/lightbend/lagom/javadsl/persistence/jpa/JpaSession.html)). Scala users might be interested in using [Slick](http://slick.lightbend.com/) for relational database access. The [Alpakka](https://developer.lightbend.com/docs/alpakka/current/) project offers Akka Streams connectors for a variety of data stores that can be conveniently used with both the Java and Scala flavors of Lagom. Many non-relational databases offer their own non-blocking, asynchronous clients, either officially or through third parties.

See more in the Lagom documentation on [Managing data persistence](/documentation/current/java/ES_CQRS.html) and [Advantages of Event Sourcing](/documentation/current/java/ESAdvantage.html).

## Can I use serialization methods other than JSON?

Yes, Lagom supports extensible serialization, both for messages transmitted between different services and for internal messages and persistent data used within a service. JSON is supported by default, as it is simple and well understood. Services that require higher performance or more compact data can use alternatives such as [Protocol Buffers](https://developers.google.com/protocol-buffers/), [Apache Avro](http://avro.apache.org/), [Kryo](https://github.com/EsotericSoftware/kryo), or any other serializer that you choose.

See more in the Lagom documentation on Message Serializers ([Java](/documentation/current/java/MessageSerializers.html)/[Scala](/documentation/current/scala/MessageSerializers.html)) and Persistent Entity Serialization ([Java](/documentation/current/java/Serialization.html)/[Scala](/documentation/current/scala/Serialization.html)).
