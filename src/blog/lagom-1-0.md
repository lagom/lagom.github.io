---
title: Lagom 1.0 is released!
date: 2016-07-21
author_github: jroper
tags: news
summary: >
    The Lagom team are proud to announce that Lagom 1.0 has been released! We have been working hard on hardening
    Lagom and simplifying the APIs. We now have a framework that is production ready, and look forward to beginning
    this journey with a new way of writing microservices.  We're also launching a new website and blog, which we
    hope will serve the community well going forward.
---

The Lagom team are proud to announce that Lagom 1.0 has been released! We have been working hard on hardening Lagom and simplifying the APIs. We now have a framework that is production ready, and look forward to beginning this journey with a new way of writing microservices.  We're also launching a new website and blog, which we hope will serve the community well going forward.

In this post I'll recap the major features of Lagom, and then highlight some of the changes since the first milestone was released.

## Major features

Lagom is a microservices framework that was built to guide developers in best practices. From architecture to development to production, Lagom provides an opinionated view that intentionally constrains what a developer can do and how they should do it. There are four primary areas that Lagom does this in.

### Communication

Lagom provides a services API that allows services to invoke each other in a location transparent way. Serialization of requests and responses are handled for you, with Lagom mapping service calls down to idiomatic REST calls which can be easy interoperate with any other technology that speaks REST.

Communication between nodes is guarded by circuit breakers, with a simple API to inspect and report on circuit break metrics.  Lagom also provides streaming via WebSockets between services out of the box, utilizing Akka streams to implement this in an asynchronous fashion.

### Persistence

Lagom provides an event sourced persistence model with CQRS for queries. In a distributed system, since each service has its own database in a share nothing architecture, it's not possible to have a system wide view of the "current" state, like a traditional RDBMS tries to maintain. What you can have is knowledge of facts, or events, that happened in the past, and you can pass these events between systems. Taking an event centric view of the world gives you the power to deal with consistency issues, by focusing on the data that you can be sure of, the events that happened.

The Lagom persistence API provides a straight forward strongly typed way to implement event sourcing and CQRS, and guides developers in how to do this.

### Development

Lagom provides a high productivity development environment. The philosophy of the Lagom development environment is that any new developer to a team should be able to get up and running with that teams development environment in less than 10 minutes.

Lagom does away with the need to manage scripts and setup instructions for a development environment. These scripts are typically developed in house, are flaky, and get quickly out of date. Lagom takes all the responsibility of starting and managing the development environment itself, including the database, service locator, service gateway, and of course, starting each service.

Updates to code are reflected immediately, with Lagom hot reloading services whenever their code changes, avoiding productivity killing deployment cycles during development.

### Production

Out of the box, Lagom utilizes Lightbend ConductR for deployment. ConductR manages the deployment and scaling of each service, configures nodes of a service to cluster together via Akka clustering, provides databases and database configuration, and gives operations the tools necessary to manage and visualise the system.

Lagom services can be built as ConductR bundles, and will use the ConductR implementation of the service locator with zero configuration.

## New since M1

The biggest change in Lagom since M1 was released is the way that URL parameters in REST calls are managed has been greatly simplified. Previously they were modelled as a parameter on the service call, now they are passed directly to the service call builder. This alleviates the need to define ID serializers, which was one of the most confusing parts of the Lagom API.

Circuit breaking has been made a service locator responsibility, as service locators have knowledge of what nodes are available and can implement the circuit breaking on a per node basis.

Support has also been implemented for a configuration based service locator, for ad hoc production deployment.

## Get started

So why not try Lagom today! You can get started with the [documentation](http://www.lagomframework.com/documentation/1.0.x/java/Home.html), and be immediately productive with confidence as the framework guides you to implement your microservices.
