# Lagom 1.3.x Change Log

## Lagom 1.3.10

*Released 1 November 2017*

* [1079](https://github.com/lagom/lagom/issues/1079) Fix unhandled event issue message in PersistentEntityTestDriver (HSalaila)
* [1081](https://github.com/lagom/lagom/issues/1081) Delete PersistentEntityTestDriver.runOne (James Roper)
* [1021](https://github.com/lagom/lagom/issues/1021) PathParamSerializer derived instances for traversables (Julien Jean Paul Sirocchi)
* [1070](https://github.com/lagom/lagom/issues/1070) Ensures .gitignore file is copied to the Maven archetype (Saniya Tech)
* [1056](https://github.com/lagom/lagom/issues/1056) OffSet storage need the eventProcessorId (NOUGUIER Olivier)
* [1063](https://github.com/lagom/lagom/issues/1063) Align the Lagom logback version with the Play one. (Jules Ivanic)
* [1062](https://github.com/lagom/lagom/issues/1062) Upgrades AkkaStreamKafkaVersion and KafkaVersion for Lagom 1.3 (Jules Ivanic)
* [1053](https://github.com/lagom/lagom/issues/1053) Service Router actions should use filtered request (Ignasi Marimon-Clos)
* [1052](https://github.com/lagom/lagom/issues/1052) Document ReadSide.register in the Cassandra/RDBMS read-side documentation (Micho)
* [1039](https://github.com/lagom/lagom/issues/1039) Lagom always returns binary data via websocket to Firefox (Jirka Kremser)
* [1049](https://github.com/lagom/lagom/issues/1049) Update Akka Persistence Cassandra to 0.30 (Tim Moore)
* [1044](https://github.com/lagom/lagom/issues/1044) Added serializer for ClusterStartupTaskActor.Execute (James Roper)
* [1046](https://github.com/lagom/lagom/issues/1046) Update Play to 2.5.18 (Tim Moore)


## Lagom 1.3.9

*Released 5 October 2017*

* [1029](https://github.com/lagom/lagom/issues/1029) wrap sql statement call in try / finally block (Renato Cavalcanti)
* [1026](https://github.com/lagom/lagom/issues/1026) removed scalastyle warnings, gramatical errors and spelling mistakes (Pallavi Singh)
* [1024](https://github.com/lagom/lagom/issues/1024) Change to execute to avoid syntax error (dr3s)
* [1025](https://github.com/lagom/lagom/issues/1025) align scala documentation with java one (Renato Cavalcanti)
* [1019](https://github.com/lagom/lagom/issues/1019) Improved the documentation to remove ambiguity in call description (Pallavi Singh)
* [1020](https://github.com/lagom/lagom/issues/1020) Adds link to CoC (Ignasi Marimon-Clos)
* [1017](https://github.com/lagom/lagom/issues/1017) Updates dependencies on docs (play akka and scala) (Ignasi Marimon-Clos)
* [1016](https://github.com/lagom/lagom/issues/1016) Adds .gitignore on maven archetype (Ignasi Marimon-Clos)
* [1011](https://github.com/lagom/lagom/issues/1011) Bumps play version to 2.5.17 (Ignasi Marimon-Clos)
* [1010](https://github.com/lagom/lagom/issues/1010) Allow no passivation to be configured (James Roper)
* [978](https://github.com/lagom/lagom/issues/978) Simplifies the actor system termination and JVM shutdown (Ignasi Marimon-Clos)
* [995](https://github.com/lagom/lagom/issues/995) Handle null state in persistent entity test driver (Tim Moore)
* [952](https://github.com/lagom/lagom/issues/952) Look up a migration using only the class name instead of the manifest containing the version as well (Olivier Deckers)
* [914](https://github.com/lagom/lagom/issues/914) Move `auto.offset.reset` to Kafka client (Renato Cavalcanti)
* Update sbt-header to 1.8.0 (Tim Moore)
* [989](https://github.com/lagom/lagom/issues/989) Remove copyright from files in Maven archetype (Tim Moore)
* [988](https://github.com/lagom/lagom/issues/988) Update sbt to 0.13.16 (Tim Moore)
* [987](https://github.com/lagom/lagom/issues/987) Silence "Resolving" messages in the build (Tim Moore)
* [986](https://github.com/lagom/lagom/issues/986) Update ZooKeeper to 3.4.10 (Tim Moore)


## Lagom 1.3.8

*Released 30 August 2017*

* [968](https://github.com/lagom/lagom/issues/968) Bumps Logback to 1.1.7 (Ignasi Marimon-Clos)
* [974](https://github.com/lagom/lagom/issues/974) Improves cluster termination to avoid deadlock (Ignasi Marimon-Clos)
* [969](https://github.com/lagom/lagom/issues/969) Corrected service name (t-h-a-l-i-s)
* [970](https://github.com/lagom/lagom/issues/970) Fixed inconsistency in code example (t-h-a-l-i-s)
* [955](https://github.com/lagom/lagom/issues/955) Adds JsonSerializer.compressed to conditionally compress when payload is too big (Ignasi Marimon-Clos)
* [948](https://github.com/lagom/lagom/issues/948) Patch CONTRIBUTING.md (Jimin Hsieh)


## Lagom 1.3.7

*Released 18 August 2017*

* [937](https://github.com/lagom/lagom/issues/937) Adding links to new Kubernetes guide and improving deployment co… (Ruth Stento)
* [946](https://github.com/lagom/lagom/issues/946) Reflect the new free version of ConductR (Christopher Hunt)
* [947](https://github.com/lagom/lagom/issues/947) Update WHITESOURCE_PROJECT_NAME (Tim Moore)
* [942](https://github.com/lagom/lagom/issues/942) Upgrade to latest sbteclipse (Ben McCann)
* [898](https://github.com/lagom/lagom/issues/898) Highlight Lagom service clients as a feature (Ruth Stento)
* [928](https://github.com/lagom/lagom/issues/928) Improved logging settings - defaulting to INFO instead of WARN (Renato Cavalcanti)
* [922](https://github.com/lagom/lagom/issues/922) Stop servers before completing the Async tests (Ignasi Marimon-Clos)
* [915](https://github.com/lagom/lagom/issues/915) Saves offset for unhandled events (Renato Cavalcanti)
* [926](https://github.com/lagom/lagom/issues/926) Update Akka to 2.4.20 (Renato Cavalcanti)
* [921](https://github.com/lagom/lagom/issues/921) Changed the \< and \> to < and > respectively. (Ashish Tomer)
* [917](https://github.com/lagom/lagom/issues/917) Fix Log4jLoggerConfigurator (Renato Cavalcanti)
* [896](https://github.com/lagom/lagom/issues/896) Fix retry logic in the subscription test example (Wayne Wang)
* [905](https://github.com/lagom/lagom/issues/905) Add EditorConfig file (Renato Cavalcanti)
* [894](https://github.com/lagom/lagom/issues/894) Implemented akka-http service gateway (James Roper)
* [888](https://github.com/lagom/lagom/issues/888) Strongly type the Lagom service router (James Roper)
* [890](https://github.com/lagom/lagom/issues/890) Format code with scalariform (Jules Ivanic)
* [889](https://github.com/lagom/lagom/issues/889) Fix https://github.com/lagom/lagom/issues/534 (Jules Ivanic)
* [886](https://github.com/lagom/lagom/issues/886) fixed maven plugun RunMojo (Dominik Kunz)


## Lagom 1.3.6

*Released 17 July 2017*

* [865](https://github.com/lagom/lagom/issues/865) Map Descriptor in ServiceClientInvocationHandler (Edouard Kaiser)
* [877](https://github.com/lagom/lagom/issues/877) Fix the match for failed futures (Tim Moore)
* [873](https://github.com/lagom/lagom/issues/873) Add method completedStatements with varargs (Yuliana Apaza)
* [859](https://github.com/lagom/lagom/issues/859) Quote sbt.version in the sbt new command (AmeerGN)
* [849](https://github.com/lagom/lagom/issues/849) Update docs to use describeService (Tim Moore)
* [847](https://github.com/lagom/lagom/issues/847) Warn when describeServices is overridden (Tim Moore)
* [845](https://github.com/lagom/lagom/issues/845) Fix typo (Yuliana Apaza)
* [843](https://github.com/lagom/lagom/issues/843) Update sbt-conductr version to 2.3.5 in docs (Ignasi Marimon-Clos)
* [838](https://github.com/lagom/lagom/issues/838) Travis injects values using env vars, not system props (Ignasi Marimon-Clos)
* [821](https://github.com/lagom/lagom/issues/821) Run whitesource in Travis CI (Ignasi Marimon-Clos)
* [833](https://github.com/lagom/lagom/issues/833) Issue #831, updated mentions of Production Suite to Enterprise Suite (Ruth Stento)
* [830](https://github.com/lagom/lagom/issues/830) Fix support for overriding describeService (Tim Moore)
* [828](https://github.com/lagom/lagom/issues/828) Add "group: edge" to .travis.yml (Renato Cavalcanti)
* [823](https://github.com/lagom/lagom/issues/823) deprecate Descriptor#publishing (Elijah Rippeth)
* [818](https://github.com/lagom/lagom/issues/818) deprecate typo-infused public API by introducing preferred API. (Elijah Rippeth)
* [810](https://github.com/lagom/lagom/issues/810) Add awaits to CqrsIntegrationTest (Ignasi Marimon-Clos)
* [812](https://github.com/lagom/lagom/issues/812) fix link broken due to capitalisation (mihbor)
* [803](https://github.com/lagom/lagom/issues/803) Log project names with dependency problems (Tim Moore)


## Lagom 1.3.5

*Released 13 June 2017*

* [794](https://github.com/lagom/lagom/issues/794) Bumps akka to 2.4.19 as suggested by Akka team
* [796](https://github.com/lagom/lagom/issues/796) Update jboss-logging to 3.3.0.Final
* [789](https://github.com/lagom/lagom/issues/789) Updates PlayJsonSerializer to first perform the class rename migration and then look up the transformation migration by the new class name (thanks [@crfeliz](https://github.com/crfeliz))
* [781](https://github.com/lagom/lagom/issues/781) Register a shutdown hook for ActorSytem in scaladsl lagom client
* [778](https://github.com/lagom/lagom/issues/778) Doc Fix: Update MessageBrokerApi.md  documentation (thanks [@crfeliz](https://github.com/crfeliz))
* [776](https://github.com/lagom/lagom/issues/776) Align maven archetype with lagom-java.g8
* [768](https://github.com/lagom/lagom/issues/768) Fixes Flaky Kafka tests
* [765](https://github.com/lagom/lagom/issues/765) Use appropriate offset in topic build op
* [757](https://github.com/lagom/lagom/issues/757) Avoid relying on default contact-point settings
* [751](https://github.com/lagom/lagom/issues/751) Update jackson reference.conf to use concatenation (thanks [@jaiew](https://github.com/jaiew))
* [752](https://github.com/lagom/lagom/issues/752) Allow Kafka consumers to batch messages
* [799](https://github.com/lagom/lagom/issues/799) Bumps APC version to 0.29
* [777](https://github.com/lagom/lagom/issues/777) Plug Circuit breaker to Java ConfigurationServiceLocator (thanks [@edouardKaiser](https://github.com/edouardKaiser))
* [782](https://github.com/lagom/lagom/issues/782) Actions.empty should be publicly accessible (thanks [@guizmaii](https://github.com/guizmaii))
* [775](https://github.com/lagom/lagom/issues/775) Add a PathParamSerializer for Double (thanks [@guizmaii](https://github.com/guizmaii))


## Lagom 1.3.4

*Released 16 May 2017*

* [700](https://github.com/lagom/lagom/issues/700) Fixed deprecation warning produced by forServer macro
* [740](https://github.com/lagom/lagom/issues/740) Allow disabling offsetStore auto creation (thanks [@vladimirshin](https://github.com/vladimirshin))
* [738](https://github.com/lagom/lagom/issues/738) Fixed default header checking for WebSockets (hat tip [@2beaucoup](https://github.com/2beaucoup)
* [741](https://github.com/lagom/lagom/issues/741) Adds missing placeholder on log
* [724](https://github.com/lagom/lagom/issues/724) Update RP docs and links to SBR  documentation
* [735](https://github.com/lagom/lagom/issues/735) Use a non-conflicting Cassandra config file name
* [707](https://github.com/lagom/lagom/issues/707) Support well known ports in the service registry
* [728](https://github.com/lagom/lagom/issues/728) Kafka service name override (thanks [@markusjura](https://github.com/markusjura))
* [729](https://github.com/lagom/lagom/issues/729) Ignores lagomCassandraPort when embedded C* is disabled
* [715](https://github.com/lagom/lagom/issues/715) Removes obsolete 'cassandra-config' from mvn archetype
* [716](https://github.com/lagom/lagom/issues/716) Improves sample tests to prevent flaky-ness
* [745](https://github.com/lagom/lagom/issues/745) Use https links where possible in documentation
* [725](https://github.com/lagom/lagom/issues/725) Update more links to HTTPS


## Lagom 1.3.3

*Released 28 April 2017*

* [435](https://github.com/lagom/lagom/issues/435) Fixed running embedded Cassandra in tests on Windows
* [633](https://github.com/lagom/lagom/pull/633) Deprecated multiple descriptors per service
* [637](https://github.com/lagom/lagom/pull/637) Enable metrics delivery by default in Scala
* [664](https://github.com/lagom/lagom/issues/664) Allow tests to share JVMs when running embedded Cassandra
* [690](https://github.com/lagom/lagom/pull/690) Fix connecting to Cassandra on non default ports
* [694](https://github.com/lagom/lagom/issues/694) Fix customising the groupId for Kafka subscriptions

Migration notes can be found [here](blog/lagom-1-3-3.html).


## Lagom 1.3.2

*Released 19 April 2017*

* [554](https://github.com/lagom/lagom/pull/554) Update Akka to 2.4.17
* [581](https://github.com/lagom/lagom/pull/581) Refactored dependencies into separate file
* [588](https://github.com/lagom/lagom/pull/588) Remove version from manifest to get serialiser from the registry
* [586](https://github.com/lagom/lagom/pull/586) Created dependency whitelist validation
* [599](https://github.com/lagom/lagom/pull/599) Created Maven dependency management pom
* [601](https://github.com/lagom/lagom/pull/601) Configure the Cassandra keyspace in the Maven archetype
* [600](https://github.com/lagom/lagom/pull/600) Remove unused archetype properties
* [609](https://github.com/lagom/lagom/pull/609) Add new `BadRequest` transport exception
* [594](https://github.com/lagom/lagom/pull/594) Adds test for ServiceDiscovery in LagomScala
* [613](https://github.com/lagom/lagom/pull/613) Deprecate the Cassandra keyspace build config
* [624](https://github.com/lagom/lagom/pull/624) Fix comment typo in `Reloader.scala`
* [632](https://github.com/lagom/lagom/pull/632) Warn on implicit keyspace config
* [636](https://github.com/lagom/lagom/pull/636) Remove reference to Cassandra in the `JdbcReadside` API documentation
* [684](https://github.com/lagom/lagom/pull/684) Ensure maven plugin adds right dependency for Play integration

Migration notes can be found [here](blog/lagom-1-3-2.html).


## Lagom 1.3.1

*Released 06 March 2017*

* [511](https://github.com/lagom/lagom/issues/511) Look Kafka broker config up from the service locator
* [532](https://github.com/lagom/lagom/issues/532) Circuit breakers are off by default in Scala API
* [553](https://github.com/lagom/lagom/issues/553) Update Akka to 2.4.17
* [564](https://github.com/lagom/lagom/issues/564) ConductR is not setting seed node information for a Lagom Scala app
* [498](https://github.com/lagom/lagom/issues/498) JDBC generate tables check for existing table does not work with a schema other than public


## Lagom 1.3.0

*Released 23 February 2017*

The main features of this release are:

* [Scala API](/documentation/1.3.x/scala/Home.html)
* [JPA support](/documentation/1.3.x/java/ReadSideJPA.html)
* [Improved testing APIs for message broker support](/documentation/1.3.x/java/MessageBrokerTesting.html)
* Switch from Activator to sbt 0.13.13 with Giter8 templates

For a full list of issues fixed in 1.3.0, see [GitHub](https://github.com/lagom/lagom/issues?utf8=%E2%9C%93&q=milestone%3A1.3.0).
