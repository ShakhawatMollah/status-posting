# Status Posting
A Project for Post a Status by checking in Location
# Features!
- Login & Registration using Spring Boot and Thymeleaf (Template Engine)
- User can post a status by checking in a location
- User can change the privacy of the post to public, private. 
- User can edit his status
- User can pin his status

## Getting Started
### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later
* MySQL 5.7 or later

### Clone
To get started you can simply clone this repository using git:
```
git clone https://github.com/shakhawatm/status-posting.git
cd status-posting
```

### Configuration
You have to provide the following settings:
```
#MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
```

The configuration is located in `src/main/resources/application.properties`.

### Run the Project
You can run the application from the command line using:
```
mvn clean spring-boot:run
```

### Migration
Insert the following Schemas:
```
insert  into `location`(`id`,`location`) values (1,'Sylhet'),(2,'Bandarban'),(3,'Khulna');
insert  into `role`(`id`,`name`) values (1,'ROLE_USER'),(2,'ROLE_STATUS');
```

### Test Status-Posting Web Application
1. Browse the following path `http://localhost:8080`

### Snapshot of output
- https://i.ibb.co/NYgQrgs/ER-of-Status-Posting.png
- https://i.ibb.co/MNFpKZM/Home-Page.png
- https://i.ibb.co/bsGQC9C/Login-Page.png
- https://i.ibb.co/6DQHDBB/Reg-Page.png
- https://i.ibb.co/TPbmmnv/Reg-Validation.png
- https://i.ibb.co/JtP47cx/Status-Entry-Form.png
- https://i.ibb.co/0sDGcYn/Status-Entry-Validation.png
- https://i.ibb.co/nzGsKDF/User-wise-Status-List.png
