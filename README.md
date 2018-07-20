# Flash Cards
A tool that help to study and memorize words and expressions to make learning new languages or studying sciences more easier and efficient

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- [Oracle Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL v5.7](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)

### Installing
- Make sure you add **JAVA_HOME** to path
- Make sure you add **Maven** to path, Instructions on [Windows](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) / [Ubuntu](http://www.baeldung.com/install-maven-on-windows-linux-mac)
- Make sure you set **root** user's password when installing **MySQL**
- Finally, you have to set the following properties in **application.properties** with your MySQL DB credentials:
   - spring.datasource.username
   - spring.datasource.password

### Running The App
```sh
$ mvn clean spring-boot:run
```

### Running The Tests
```sh
$ mvn clean test
```

## License

This project is licensed under the MIT License