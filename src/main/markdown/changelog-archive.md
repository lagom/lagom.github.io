# Change Log Archive

## Lagom 1.2.3

*Released 06 March 2017*

* [476](https://github.com/lagom/lagom/issues/476) Invalid keyspaces when using lagom:runAll in Maven with unmanaged Cassandra
* [446](https://github.com/lagom/lagom/issues/446) Persistence tables fail to be created in MySQL
* [449](https://github.com/lagom/lagom/issues/449) No way to disable JDBC offset table creation
* [403](https://github.com/lagom/lagom/issues/403) KafkaLocalServer should not be loading properties from the classpath
* [426](https://github.com/lagom/lagom/issues/426) lagomExternalProject is broken
* [519](https://github.com/lagom/lagom/pull/519) Fixed Eclipse warnings for Maven archetype
* [498](https://github.com/lagom/lagom/issues/498) JDBC generate tables check for existing table does not work with a schema other than public

## Lagom 1.2.2

*Released 12 January 2017*

* [318](https://github.com/lagom/lagom/issues/318) bugfix: can't use JDBC persistence when usin Maven as build tool.

## Lagom 1.2.1

*Released 07 December 2016*

* [307](https://github.com/lagom/lagom/pull/307) Docs: Cassandra and cluster are disabled by default. (thanks [@mihbor](https://github.com/mihbor))
* [285](https://github.com/lagom/lagom/issues/285) Regression: Jackson exception when using immutables in lagom 1.2.0 (thanks [@mihbor](https://github.com/mihbor))
* [301](https://github.com/lagom/lagom/pull/301) Improve tests stability
* [274](https://github.com/lagom/lagom/pull/274) Performance: Avoid stream materialization on requests with no body

## Lagom 1.2.0

*Released 08 November 2016*

* [161](https://github.com/lagom/lagom/pull/161) Message broker/Kafka support
* [63](https://github.com/lagom/lagom/issues/63) JDBC support
* [126](https://github.com/lagom/lagom/issues/126) Generic read-side support
* [73](https://github.com/lagom/lagom/issues/73) Automatic offset handling
* [151](https://github.com/lagom/lagom/pull/151) Read-side overhaul, including sharded read sides

For a full list of issues fixed in 1.2.0, see [GitHub](https://github.com/lagom/lagom/issues?utf8=%E2%9C%93&q=milestone%3A1.2.0).

## Lagom 1.1.0

*Released 21 September 2016*

The primary feature of this release is Maven support.

* [101](https://github.com/lagom/lagom/issues/101) Assign service ports from ephemeral port range.
* [112](https://github.com/lagom/lagom/issues/112) Maven support.

## Lagom 1.0.0

*Released 21 July 2016*

First GA release of Lagom.

* [107](https://github.com/lagom/lagom/issues/107) Comprehensive error reporting in dev mode.
* [111](https://github.com/lagom/lagom/issues/111) Make circuit breakers a service locator concern.

## Lagom 1.0.0-M2

*Released 5 May 2016*

Second MVP release.  The major change in this release is that `ServiceCall` no longer has an ID parameter, instead parameters extracted from the URL are passed directly to the service interface method.  See issue [99](https://github.com/lagom/lagom/pull/99) for more details, and the [Service Descriptors](/documentation/1.0.x/ServiceDescriptors.html) documentation.

* [26](https://github.com/lagom/lagom/issues/26) Fix Cassandra chewing 100% of CPU
* [32](https://github.com/lagom/lagom/issues/32) Ensure default memory options are suitable for Lagom
* [41](https://github.com/lagom/lagom/issues/41) Improve service locator port conflict error reporting
* [53](https://github.com/lagom/lagom/pull/53) Improve dev mode specific dependency injection
* [75](https://github.com/lagom/lagom/pull/75) Ensure query parameters get added to client requests
* [91](https://github.com/lagom/lagom/issues/91) Fix Cassandra reconnection issues
* [99](https://github.com/lagom/lagom/pull/99) Rework URL parameter handling
* [102](https://github.com/lagom/lagom/pull/102) Ensure service interfaces can be implemented using a Scala trait

## Lagom 1.0.0-M1

*Released 10 March 2016*

First MVP release.
