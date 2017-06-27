---
title: Event Sourcing vs CRUD
date: 2017-05-17
author_github: jroper
tags: event+sourcing crud
summary: >
    A side by side comparison of using event sourcing vs using CRUD.
---

As the Lagom project has grown in usage over the past 12 months since its first release, one of the biggest barriers to adoption that we've seen is Lagom's opinionated approach to using event sourcing for persistence. Many developers looking to write reactive microservices come from a CRUD background, often using relational databases to store data.

Lagom doesn't not support CRUD - you can use easily use any persistence technology available on the JVM in Lagom. However, the Lagom persistence APIs do not offer CRUD as a first class option, and the Lagom documentation pushes users in the direction of event sourcing.

To be honest, event sourcing does generally require writing more code up front than CRUD. Lagom does not push event sourcing because it reduces the amount of code. Lagom pushes event sourcing because the problems that microservices introduce - such as ensuring consistency between services - are very easily solved when you are using event sourcing for persistence, and very hard to solve when you're using CRUD for persistence. A service using CRUD might have less code to start with, but as soon as it starts sharing data with other services, problems relating to handling failures and synchronisation of state become very difficult problems to solve, and will often require implementing complex solutions, including manual state and failure tracking. In fact, in my experience of using CRUD, dealing with these problems usually requires manually implementing event logs and asynchronous batch processes to handle them, so developers end up implementing both event sourcing and CRUD, which is far more code and far harder to maintain. In contrast, event sourcing inherently deals with these problems as a property of the architectural approach, with no special code implemented by developers.

Put simply, using event sourcing over CRUD is an investment of more code and effort up front, but will yield a simpler, more resilient and easier to maintain system in the future. Lets take a look at how this looks in practice.

## A blog system

To demonstrate the difference between CRUD and event sourcing, we're going to use a blogging system as an example. A blogging system will have a service to manage blog posts, but then it will have other services, such as a search service for doing a natural text search on blog posts, a tag service for managing tags and returning blog posts by tag, and a recent post service for maintaining a list of recent blog posts to be rendered for example on the home page, or by an atom feed.

A blog post starts in the unpublished state, may be edited a few times, and then at some point will be published. Once published, the search service, tag service and recent posts service need to notified about the blog post so that they can update their indexes.

### CRUD code

Here is what the CRUD example might look like if we were using JPA. Note we have left out getters/setters/equals/hashCode for brevity. First we have a JPA entity:

@[entity](event-sourcing-vs-crud/jpa/Blog.java)

Then we have some code that handles the creation of the blog post, editing the blog post, and publishing the blog post:

@[operations](event-sourcing-vs-crud/jpa/BlogDao.java)

### Event sourcing code

Here is what the event sourcing example might look like using the Lagom persistence API.

At this point a quick introduction to event sourcing is needed. Unlike CRUD, where the current state of an entity is what is stored in the database, in event sourcing, the events that occured are stored in the database. The current state is determined by loading and playing those events, each time you need it.

A knee jerk reaction to hearing this approach for the first time is that loading many events from the database each time you need to load an entity is not very efficient, and that's right. And in practice, event sourcing frameworks have many built in optimisations that make this problem moot. In Lagom, entities are kept around in memory for a limited amount of time, so that subsequent loads don't need to go to the database. Another optimisation is called snapshotting, every so many events, a snapshot of the current state is stored to the database, which means next time the entity needs to be loaded from the database, the snapshot can be loaded first, and then only events since that snapshot was taken need to be loaded and replayed. But an important thing to remember is that these are just optimisations. Conceptually, the behaviour is that only events are stored.

Event sourcing has many advantages right off the bat - for example, you get an audit log for free. You can also use high performance databases that are optimised for appending only, since the only mutating operation needed to implement event sourcing is insert/append, you never delete data, and you never update data.

So far we've talked about events, which are the things that are actually stored, but an event sourced entity is not interacted with via events, it is interacted through commands. The different between a command and an event is simple, a command is a request for something to happen, while an event is something that did happen. This difference is often highlighted in the tense used for the names of the classes that represent events and commands, an event is always in past tense, so a blog post might have a `Published` event. A command however is an imperative, so the command to publish will be called `Publish`.

A persistent entity may refuse to process a command. For example, if you try to publish a blog post that hasn't been created, that command will be refused. Alternatively, a persistent entity may emity one or more events in response to a command, so when receiving the `Publish` command, after having validated that the blog post can be published, the entity will emit a `Published` event, which will be stored to the database, and the entities state can now be updated to be published.

We're using Lombok to generate getters/builders/equals/hashCode. First we define our events:

@[events](event-sourcing-vs-crud/BlogEvent.java)

Next we define the state that those events will manipulate:

@[state](event-sourcing-vs-crud/BlogState.java)

Now we define the commands. Note that each command has a reply type, in our case, each command replies with `Done`, but it could reply with more detailed information:

@[commands](event-sourcing-vs-crud/BlogCommand.java)

And finally we define the entity itself, which defines handlers that apply the events to the state (these will get run every time the entity is loaded from the database), and handlers to handle the commands and emit events (these only get run once when the command is submitted):

@[entity](event-sourcing-vs-crud/BlogEntity.java)

So, as previously stated, we have more code than the JPA example. Let's have a look at what happens when we interact with other services.

## Publishing events


