<!--- Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com> -->
# Lagom contributor guidelines

## Prerequisites

Before making a contribution, it is important to make sure that the change you wish to make and the approach you wish to take will likely be accepted, otherwise you may end up doing a lot of work for nothing. The Lagom site is the sum of several sources, so make sure the change you are working on happens in the proper repository. In case of doubt, feel free to open an [issue](https://github.com/lagom/lagom.github.io/issues) to discuss the proper approach.

## Development tips

- See [README.md](README.md) for instructions on running the Lagom site locally.
- The Lagom site is the materialization of several sources: [lagom.github.io's _template_ branch](https://github.com/lagom/lagom.github.io/tree/template), [Lagom's `docs/` folder](https://github.com/lagom/lagom/tree/master/docs) and Lagom's API docs for both Java and Scala as generated from the sources in the [Lagom repository](https://github.com/lagom/lagom). All information coming from the [Lagom repository](https://github.com/lagom/lagom) is created from several branches so that many versions of the docs can be maintained.

## Contributing to the documentation

To fix an issue in the documentation or add to an existing page, go to the live page on http://www.lagomframework.com/documentation/ and click the link to the source code at the bottom of the page.

To contribute larger documentation changes, for example, adding pages, you might prefer to edit the source files in a forked repository of https://github.com/lagom/lagom/ and create a Pull Request. You'll find the documentation source files in [`docs/manual`](https://github.com/lagom/lagom/tree/master/docs/manual).

## Pull request procedure

1. Make sure you have signed the [Lightbend CLA](https://www.lightbend.com/contribute/cla); if not, sign it online.
2. Ensure that your contribution meets the following guidelines:
    1. Documents Java (both Maven and sbt), Scala usage
    2. If it includes class names, links to API docs or code samples, they are consistent with the version and language you are documenting.
    3. New files must:
        * Follow naming conventions
        * Follow indentation conventions (e.g. one line per paragraph)
        * Not use ``@author`` tags since it does not encourage [Collective Code Ownership](http://www.extremeprogramming.org/rules/collective.html).
3. Submit a pull request.

If the pull request does not meet the above requirements then the code should **not** be merged, or even reviewed, regardless of how good or important it is. No exceptions.
