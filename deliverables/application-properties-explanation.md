spring.application.name=Application
* Defines the primary application name within the Spring Boot ecosystem, which is used in logging, metrics, and application context identification.

---
server.port=8080
* Specifies the TCP port on which the embedded Tomcat web server listens for HTTP requests (making the app accessible at `http://localhost:8080`).
* **`# Default to MySql if no profile is selected`**
* **`# see application-mysql.properties`**
* **`# spring.profiles.active=h2`**
Lines starting with `#` are developer comments. They indicate that the embedded H2 database profile is currently disabled in favor of MySQL.

---
spring.profiles.active=mysql
* Sets the active Spring environment profile to `mysql`. This instructs Spring Boot to load and apply configuration settings from `application-mysql.properties`.

---
spring.jpa.hibernate.ddl-auto=update
* Configures Hibernate (the JPA provider) to automatically update the database schema on application startup according to the Java entity definitions without deleting existing table data.

---
spring.jpa.show-sql=true
* Enables console logging of all executed SQL statements, allowing developers to monitor database queries and inspect generated SQL during execution.

---
debug=true
Enables verbose Spring Boot debug logging during startup, generating a detailed Condition Evaluation Report to troubleshoot auto-configuration and bean creation issues.
