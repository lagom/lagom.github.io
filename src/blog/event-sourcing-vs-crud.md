---
title: From CRUD to Event Sourcing
date: 2018-03-20
author_github: jroper
tags: event+sourcing crud
summary: >
    A side by side comparison of using Event Sourcing vs using CRUD
---

As the Lagom project has grown in usage, one of the biggest barriers to adoption that we've seen is Lagom's opinionated approach to using event sourcing for persistence. Many developers looking to write reactive microservices come from a [CRUD](https://www.codecademy.com/articles/what-is-crud) background, often using relational databases to store data.

Lagom's service API can be used alongside CRUD-style persistence - you can easily use any persistence technology available on the JVM in Lagom. However, the Lagom persistence APIs do not offer CRUD as a first class option, and the [Lagom documentation](https://www.lagomframework.com/documentation/1.4.x/java/PersistentEntity.html) pushes users in the direction of event sourcing.

To be honest, event sourcing does generally require writing more code up front than CRUD. Lagom does not push event sourcing because it reduces the amount of code. Lagom pushes event sourcing because the problems that microservices introduce - such as ensuring consistency between services - are very easily solved when you are using event sourcing for persistence, and very hard to solve when you're using CRUD for persistence. A service using CRUD might have less code to start with, but as soon as it starts sharing data with other services, problems relating to handling failures and synchronisation of state become very difficult problems to solve, and will often require implementing complex solutions, including manual state and failure tracking. In fact, in my experience of using CRUD, dealing with these problems usually requires manually implementing event logs and asynchronous batch processes to handle them, so developers end up implementing both event sourcing and CRUD, which is far more code and far harder to maintain. In contrast, event sourcing inherently deals with these problems as a property of the architectural approach, with no special code implemented by developers.

Put simply, using event sourcing over CRUD is an investment of more code and effort up front, but will yield a simpler, more resilient and easier to maintain system in the future. Lets take a look at how this looks in practice.

A few months ago I had the chance to speak about this topic at the Reactive Summit'17. Here's the recording of the talk.


<div id="meet-lagom" class="flex-video widescreen">
  <iframe width="560" height="315" src="//www.youtube.com/embed/holjbuSbv3k" frameborder="0" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen=""></iframe>
</div>
