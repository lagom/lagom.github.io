---
title: Lagom 1.3.8 is here!
date: 2017-08-30
author_github: ignasi35
tags: news
summary: >
    Lagom 1.3.8 is ready
---

The Lagom team has released Lagom 1.3.8. For a list of the specific changes in Lagom 1.3.8, see the [change log](/changelog-1.3.x.html).

This release fixes [a deadlock](https://github.com/lagom/lagom/issues/974) when shutting down a Lagom process in some scenarios and brings Scala DSL Json Serialization feature-parity by providing an opt-in [compressor](https://github.com/lagom/lagom/issues/955) for serialized messages. We also [upgraded](https://github.com/lagom/lagom/issues/968) the underlying logback library.

This release is no different than previous ones and we continue to have great community contributions. Thanks to GitHub users [t-h-a-l-i-s](https://github.com/t-h-a-l-i-s) and [Jimin Hsieh](https://github.com/jiminhsieh).


## Json Serialization compression

Compression, as described here, is only used for persistent events, persistent snapshots and remote messages within the service cluster. It is not used for messages that are serialized in the external API of the service.

JSON can be rather verbose and for large messages it can be beneficial to enable compression. That is done by using the `JsonSerializer.compressed[T]` builder method instead of the `JsonSerializer.apply[T]`:

```
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

object RegistryWithCompression extends JsonSerializerRegistry {
  override val serializers = Vector(

    // 'AddComment' uses `apply[T]()` .
    JsonSerializer[AddComment],

    // The AddPost message is usually rather big, so we want it compressed
    // when it's too large.
    JsonSerializer.compressed[AddPost]

  )
}
```

The serializer will by default only compress messages that are larger than 1024 bytes. This threshold can be changed with configuration property:

```
lagom.serialization.json {

  # The serializer will compress the payload if the message class
  # was registered using JsonSerializer.compressed and the payload
  # is larger than this value. Only used for remote messages within
  # the cluster of the service.
  compress-larger-than = 1024b

}
```
