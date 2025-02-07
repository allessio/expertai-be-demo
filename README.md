# Installation and usage
------------------------------------------------------------------------------------------------------------
## Prerequisites
------------------------------------------------------------------------------------------------------------

- MySQL
- Tomcat

------------------------------------------------------------------------------------------------------------
## DB installation
------------------------------------------------------------------------------------------------------------

- run DDL_demo.sql
- run DML_demo.sql

------------------------------------------------------------------------------------------------------------
## Installation of custom dependencies
------------------------------------------------------------------------------------------------------------

- install external jar to .m2:
	1) locate nlapi-java-sdk-1.0.0-RC.1.jar
	2) mvn install:install-file -Dfile=[PATH_TO_JAR]\nlapi-java-sdk-1.0.0-RC.1.jar -DgroupId=ai.expert.nlapi -DartifactId=nlapi -Dversion=1.0.0-RC.1 -Dpackaging=jar

- add dependency to pom:

	<dependency>
		  <groupId>ai.expert.nlapi</groupId>
		  <artifactId>nlapi</artifactId>
		  <version>1.0.0-RC.1</version>
	</dependency>
	
------------------------------------------------------------------------------------------------------------
## App properties setup
------------------------------------------------------------------------------------------------------------
	
- in application.properties:
	- set [expertai.inputFolder]
	- set [expertai.outputFolder]
	- set [expertai.username]
	- set [<expertai.password]
	
- in log4j.properties set [log4j.appender.A2.File]


------------------------------------------------------------------------------------------------------------
## Usage - API
------------------------------------------------------------------------------------------------------------
API documentation:
http://localhost:8080/expertai/swagger-ui/index.html

Examples of usage:
http://localhost:8080/expertai/api/contents/all								- [GET] all contents from DB
http://localhost:8080/expertai/api/contents/217								- [GET] content with ID=217
http://localhost:8080/expertai/analysis/category							- [POST] analyze categories for ALL persisted documents 
http://localhost:8080/expertai/analysis/full/?docIDFrom=256&docIDTo=333		- [POST] provide full analysis for documents with IDs from 256 to 333