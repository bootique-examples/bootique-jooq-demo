[![Build Status](https://travis-ci.org/bootique-examples/bootique-jooq-demo.svg)](https://travis-ci.org/bootique-examples/bootique-jooq-demo)
# bootique-jooq-demo

The example explains one of various use cases for using jOOQ in your application built on [Bootique](https://bootique.io).
Notably, [jOOQ](https://www.jooq.org) as a SQL builder with code generation. 

*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo

**Note:** run the script `testdb.sql` against a database to create a schema. [MySQL](https://www.mysql.com) in used in the example.
      
Here is how to build it:
```bash
git clone git@github.com:bootique-examples/bootique-jooq-demo.git
cd bootique-jooq-demo
mvn package
```
One of the main jOOQ's assets is code generation. jOOQ's code generator reverse-engineers a database schema 
into a set of Java classes modelling tables, records, sequences, POJOs, DAOs, stored procedures, user-defined types, etc.
There are two approaches to generate source code: 
1. Maven build process via the official **jOOQ-codegen-maven** plugin:

*pom.xml*
```xml
<plugin>
    <groupId>org.jooq</groupId>
    <artifactId>jooq-codegen-maven</artifactId>
    <version>${jooq-version}</version>

    <executions>
        <execution>
            <id>java-generator</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>generate</goal>
            </goals>

            <configuration>
                <jdbc>
                    <driver>com.mysql.jdbc.Driver</driver>
                    <url>jdbc:mysql://localhost:3306</url>
                    <user>root</user>
                </jdbc>
                <generator>
                    <database>
                        <name>org.jooq.util.mysql.MySQLDatabase</name>
                        <includes>.*</includes>
                        <inputSchema>testdb</inputSchema>
                    </database>
                    <target>
                        <packageName>io.bootique.jooq.demo.generated</packageName>
                        <directory>/Users/your_user/bootique-jooq-demo/src/main/java</directory>
                    </target>
                </generator>
            </configuration>
        </execution>
    </executions>
</plugin>
```
2. Code generation via command-line. 
It'll need configuration of jOOQ's code generator in the *library.xml*:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<configuration xmlns="http://www.jooq.org/xsd/jooq-codegen-3.8.0.xsd">
    <!-- Configure the database connection here -->
    <jdbc>
        <driver>com.mysql.jdbc.Driver</driver>
        <url>jdbc:mysql://localhost:3306</url>
        <user>root</user>
    </jdbc>
    <!--Configuration elements related to code generation-->
    <generator>
        <database>
            <name>org.jooq.util.mysql.MySQLDatabase</name>
            <includes>.*</includes>
            <inputSchema>testdb</inputSchema>
        </database>
        <target>
            <directory>/Users/your_user/bootique-jooq-demo/src/main/java</directory>
            <encoding>UTF-8</encoding>
            <packageName>io.bootique.jooq.demo.generated</packageName>
        </target>
    </generator>
</configuration>
```
Then put the XML configuration files jooq-3.8.2.jar, jooq-meta-3.8.2.jar, jooq-codegen-3.8.2.jar, the JDBC driver into a directory, 
e.g. temporal one. 

Go into the directory:
```bash
cd  $TMPDIR
```
Call `org.jooq.util.GenerationTool` class with the `library.xml` file as an argument:
```bash
java -classpath \ 
jooq-3.8.2.jar:jooq-meta-3.8.2.jar:jooq-codegen-3.8.2.jar:mysql-connector-java-6.0.6.jar:. \ 
org.jooq.util.GenerationTool ~/bootique-jooq-demo/src/main/resources/library.xml
```

## Run the Demo 

Check the options available in your app:
```bash
java -jar target/bootique.jooq.demo-1.0-SNAPSHOT.jar 
```
```
OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -d, --demo
           Demo command selecting data from db.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.
```
Provide configuration for Bootique in *config.yml*:
```yaml
jdbc:
  default:
    url: jdbc:mysql://localhost:3306/testdb
    driverClassName: com.mysql.jdbc.Driver
    initialSize: 1
    username: root
    password:

jooq:
  dialect: MYSQL
  executeLogging: true
```
Run the demo command: 
```bash
java -jar target/bootique.jooq.demo-1.0-SNAPSHOT.jar --config=config.yml --demo
```
Result:
```
+----+---------------+-----------------------------------------+
|  id|name           |host                                     |
+----+---------------+-----------------------------------------+
|   1|ObjectStyle LLC|https://www.objectstyle.com/about        |
+----+---------------+-----------------------------------------+
+----+---------------+-----------------------------------------+
|  id|name           |host                                     |
+----+---------------+-----------------------------------------+
|   2|Bootique       |http://bootique.io/docs/0/getting-started|
+----+---------------+-----------------------------------------+
+----+---------------+-----------------------------------------+
|  id|name           |host                                     |
+----+---------------+-----------------------------------------+
|   3|LinkMove       |https://github.com/nhl/link-move         |
+----+---------------+-----------------------------------------+

```








    
    






        
        
     
