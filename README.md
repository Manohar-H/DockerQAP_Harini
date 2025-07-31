# Golf Club Tournament Management API

This Spring Boot application helps manage golf club members and tournaments. It supports full CRUD operations and features REST APIs for managing and searching members and tournaments.

---

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL (Local and AWS RDS)
- Docker & Docker Compose
- AWS RDS (MySQL)
- Postman for API testing

---

## Features

- Create, Read, Update, Delete (CRUD) for Members and Tournaments
- Search Members by Name
- Search Tournaments by Location
- Associate Members with Tournaments (many-to-many)
- REST APIs with JSON request/response
- Docker support for easy local setup
- AWS RDS for production-like environment

---

##  API Endpoints

###  Members
| Method | Endpoint                      | Description                |
|--------|-------------------------------|----------------------------|
| POST   | `/api/members`                | Create a new member        |
| GET    | `/api/members`                | List all members           |
| GET    | `/api/members/search?name=Alice` | Search member by name  |

###  Tournaments
| Method | Endpoint                           | Description                   |
|--------|------------------------------------|-------------------------------|
| POST   | `/api/tournaments`                 | Create a new tournament       |
| GET    | `/api/tournaments`                 | List all tournaments          |
| GET    | `/api/tournaments/search?location=New York` | Search tournament by location |

###  Association
| Method | Endpoint                               | Description                    |
|--------|----------------------------------------|--------------------------------|
| POST   | `/api/tournaments/{tournamentId}/add-member/{memberId}` | Add a member to a tournament |

---

##  Running with Docker

###  Prerequisites

- Docker installed
- Docker Compose installed

###  Steps

1. **Clone the repository**
   
   `git clone https://github.com/yourusername/golfclub-api.git`

   `cd golfclub-api`
   

2.	Build project

    `mvn clean install`

3.	Run via Docker Compose

    `docker-compose up --build`

4.	Verify

      `Visit http://localhost:8080/api/members or use Postman`

---

## AWS RDS Setup

### Current AWS RDS Details
•	Engine: MySQL 8.0.41
•	DB Identifier: golfclub-db
•	Endpoint: golfclub-db.corqv6hasing.us-east-1.rds.amazonaws.com
•	Port: 3306
•	Database: golfclub
•	Username: admin
•	Password: Password123

### Spring Boot DB Configuration (application.properties)
`spring.datasource.url=jdbc:mysql://golfclub-db.corqv6hasing.us-east-1.rds.amazonaws.com:3306/golfclub`
`spring.datasource.username=admin`
`spring.datasource.password=YOUR_PASSWORD`
`spring.jpa.hibernate.ddl-auto=update`
`spring.jpa.show-sql=true`

### Postman Testing Screenshots
•	✅ Member Created
•	✅ Tournament Created
•	✅ Member Added to Tournament
•	✅ Search Member
•	✅ Search Tournament

`DockerQAP_Harini/Screenshots`

## Author
*Harini Manohar*
*QAP-4*
*SD-12*