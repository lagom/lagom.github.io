---
title: Introducing the Lagom code generator
date: 2017-05-12
author_github: ignasi35
tags: news scala java code
summary: >
    Consuming 3rd party services with a Swagger spec just got easier!
---

We're happy to announce the first _alpha_ release of the [Lagom Descriptor Generator](https://github.com/lagom/sbt-lagom-descriptor-generator) a project started as a proof-of-concept and that we've recently improved. The motivation is to ease consuming third party APIs (be it lagom services, other services on your organization or public APIs in the wild) reducing the amount of code required.

We want Lagom to increase the types of services it can talk to and we will work on supporting gRPC both for producing services and to consume 3rd party gRPC endpoints. Working in both a fully featured code generator from OpenAPI specs and gRPC support is beyond our capacity at the moment, today we are calling the community to continue our work in the code generator.[Lagom Descriptor Generator](https://github.com/lagom/sbt-lagom-descriptor-generator) does not have contributors, it only has maintainers —frequent and infrequent— and everyone helps out. It's a repo that loves new maintainers as well as old maintainers. :-) The Lagom core team keeps an eye on the project to assure its overall coherence but does not fully support it.

### Status 

The current implementation is a _crude_ generator that will convert a `swagger.json` (or yaml) into Lagom API source code. It is currently available as an `sbt` plugin and the GitHub repository even includes `sbt-` on the names (which is a [mistake](https://github.com/lagom/sbt-lagom-descriptor-generator/issues/4). It could be [extended into a Maven plugin](https://github.com/lagom/sbt-lagom-descriptor-generator/issues/11) too.

The current implementation will read OpenAPI v2 files (aka swagger) but we think other API specification formats (e.g. RAML) could be supported too. We added some [tips](https://github.com/lagom/sbt-lagom-descriptor-generator#supported-specs) on what should be done to add support for new specification formats.

Finally, this first available release is demoed in the [Pet Store application](https://github.com/ignasi35/lagom-pet-store/). That sample application adapts the famous Open API [Pet Store](http://petstore.swagger.io/) example into a Lagom application made of a LagomJava Pet Service, a LagomScala Store Service and a PlayJava web frontend. Each of the lagom services in the sample application describes its API using an OpenAPI v2 file located in `src/main/openapi/` instead of including the usual `xxx-service-api` project separately. Finally, the web frontend uses copies of each of the OpenAPi files it wants to consume. Read the project [README.md](https://github.com/ignasi35/lagom-pet-store/blob/master/README.md) for more details.