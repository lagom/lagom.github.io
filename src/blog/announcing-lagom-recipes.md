---
title: Introducing the Lagom Recipes
date: 2018-02-13
author_github: ignasi35
tags: news recipes cookbook
summary: >
    Lagom recipes help you solve common problems when creating great applications with the Lagom Framework.
---

Lagom is an OSS framework to build microservice-based software systems. At Lightbend we sponsor the development of the framework and encourage contributions from the community, but we also help people get started, be more productive when using it or solve the bugs there may be in the framework code. We participate on the [Discuss Lagom Forum](https://discuss.lagomframework.com), [Gitter](https://gitter.im/lagom/lagom) channel and, of course, [Stack Overflow](https://stackoverflow.com/questions/tagged/lagom) and [GitHub issues](https://github.com/lagom/lagom/issues), but Lightbend also provides support for its [subscribers](https://www.lightbend.com/subscription) using Lagom. We sometimes detect the same questions pop up repeatedly on those channels of support. In order to reduce repeating the same answers over and over and because common problems should have readily available solutions, we created the [Lagom Recipes](https://github.com/lagom/lagom-recipes) repository.

### Lagom Recipes

Like a cookbook, the Lagom recipes repository contains a varied list of step-by-step solutions to common problems Lagom developers may face. Take for example the common request on the Mailing List: "How do I enable CORS for my Lagom API?". It's a simple enough request but in Lagom the solution is slightly different whether you are using the `javadsl` or the `scaladsl` so the common response to such a question was "Are you using java or scala?". It's a problem which involves some knowledge of [Play](https://www.playframework.com/) which is a building block of Lagom. Therefore the explanation ends up being a bit confusing. It is easier to prepare two recipes to answer the question for Java or Scala developers in mind and explain each step on the README.

Every recipe may contain a README file detailing the what files would you need to add or edit in order to complete the recipe. On top of that, recipes are not meant to start a project from scratch. Instead, each recipe details the necessary increment over an existing Lagom Service or a complete Lagom system of many services. The recipe is usually developed in few separate commits to demonstrate the increment necessary to complete your goal:

* a first commit sets up the minimum Lagom system
* a second commit introduces the changes necessary to complete the task and the README file explaining the changes.

The current list of recipes includes solutions for problems such as:

* [How do I enable CORS](https://github.com/lagom/lagom-recipes/tree/master/cors)?
* [How do I create a Subscriber only service](https://github.com/lagom/lagom-recipes/tree/master/consumer-service)?
* [How do I use RDBMS read-sides with Cassandra write-sides](https://github.com/lagom/lagom-recipes/tree/master/mixed-persistence)?
* [How do I create a stateless service in Lagom for Java that uses Play's Internationalization Support](https://github.com/lagom/lagom-recipes/tree/master/i18n)?
* [How do I manipulate Headers and Status Codes and test those cases](https://github.com/lagom/lagom-recipes/tree/master/http-header-handling)?

We hope adding this learning resource to the list of [documentation resources](https://www.lagomframework.com/documentation/) already available will help create great applications!
