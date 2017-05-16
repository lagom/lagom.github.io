---
title: Introducing the Lagom code generator
date: 2017-05-16
author_github: ignasi35
tags: news scala java generator
summary: >
    Consuming 3rd party services with a Swagger spec just got easier!
---

We're happy to announce the first _alpha_ release of the [Lagom Descriptor Generator](https://github.com/lagom/sbt-lagom-descriptor-generator) a project started as a proof-of-concept and that we've recently improved. The motivation is to ease consuming third party APIs (be it lagom services, other services on your organization or public APIs in the wild) reducing the amount of code required.

We want Lagom to increase the types of services it can talk to. Until now Lagom was focused on providing RPC via HTTP/JSON as default and we already started working on an gRPC transport for Lagom both for producing services and consuming 3rd party gRPC endpoints. 

We had to make a decision since working in both (1) a fully featured code generator from OpenAPI specs to Lagom source code and (a) adding gRPC support into Lagom was beyond our capacity. Today we are calling the community to continue our work in the Lagom code generator while we focus our attention to gRPC. The _Lagom Descriptor Generator_ project may eventually generate Lagom API descriptor sources from seveal API specification format. At the moment only OpenaAPI v2 (aka _Swagger specs_) is supported.

The [Lagom Descriptor Generator](https://github.com/lagom/sbt-lagom-descriptor-generator) is open to community contributions —frequent and infrequent—, everyone helps out. It's a repo that loves new code and fixes. The Lagom core team will remain in an [integrator](https://www.lagomframework.com/community-process.html#definitions) role, the team will keep an eye on the project to assure its overall coherence but does not fully support it.

### Project Status 

The current implementation is a _crude_ generator that will convert a `swagger.json` (or yaml) into Lagom API source code. It is currently available as an `sbt` plugin but it could be [extended into a Maven plugin](https://github.com/lagom/sbt-lagom-descriptor-generator/issues/11) too. The GitHub repository includes `sbt-` on the name but that is a [mistake](https://github.com/lagom/sbt-lagom-descriptor-generator/issues/4).

The current implementation will read [OpenAPI v2](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md) files but we think other API specification formats (e.g. RAML) should be supported too. We added some [tips](https://github.com/lagom/sbt-lagom-descriptor-generator#supported-specs) on what should be done to add support for new specification formats.

Finally, this first available release is demoed in the [Pet Store application](https://github.com/ignasi35/lagom-pet-store/). That sample application adapts the famous Open API [Pet Store](http://petstore.swagger.io/) example into a Lagom application. The Lagom version is made of a LagomJava Pet Service, a LagomScala Store Service and a PlayJava web frontend. Each of the lagom services in the sample application describes its API using an OpenAPI v2 file located in `src/main/openapi/` instead of including the usual `xxx-service-api` project separately. Finally, the web frontend uses copies of each of the OpenAPi files it wants to consume. Read the project [README.md](https://github.com/ignasi35/lagom-pet-store/blob/master/README.md) for more details.