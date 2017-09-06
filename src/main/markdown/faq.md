# Frequently Asked Questions

Have a question about Lagom? We've sorted out a sample of frequently asked questions and provided answers below. If your question is not covered here, try these resources:

- [Lagom Gitter channel](https://gitter.im/lagom/lagom)
- [lagom-framework mailing list](https://groups.google.com/forum/#!forum/lagom-framework)
- [Stack Overflow Lagom questions](https://stackoverflow.com/questions/tagged/lagom)

## Is Lagom open source or commercial?

Lagom is completely open source, free to use, and has a vibrant and growing community. However, you do have the option to purchase a [Lightbend subscription](https://www.lightbend.com/platform/subscription) that provides technical support as well as license to use Lightbend Enterprise Suite, which includes a range of powerful application management, intelligent monitoring, and enterprise integration tooling.

## Does Lagom require me to use Cassandra for persistence?

No, Lagom supports using either Cassandra or relational databases, including MySQL, PostegreSQL, Oracle and H2. The Lagom development environment provides and uses Cassandra by default because it offers great scalability and replication features.  See more in the [Java documentation](https://www.lagomframework.com/documentation/1.3.x/java/PersistentEntity.html) or the [Scala documentation](https://www.lagomframework.com/documentation/1.3.x/scala/PersistentEntity.html).

## Why are event interfaces present in both API and impl classes with only slight differences between them?

This is an example of an anti-corruption layer. In Lagom, we use anti-corruption layers for all API payloads (requests/responses) and published events. While it might seem like unnecessary duplication, using anti-corruption layers allows the implementation to be changed in `-impl` classes without changing the public API. For example, you might want to enrich an internal event with some fields that you need on a read side but you don’t need on the published events.

For more information, see Mark Needham's blog on [anti-corruption layers](http://www.markhneedham.com/blog/2009/07/07/domain-driven-design-anti-corruption-layer/).
