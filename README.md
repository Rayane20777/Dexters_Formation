# Dexter's Formation - Educational Management System

## Project Description
A comprehensive educational management system designed to streamline the administration of educational programs, class scheduling, instructor management, and student enrollment. The system provides RESTful APIs for seamless integration and management of educational resources.

## Application Objectives
- Facilitate program and course management
- Optimize class scheduling and room allocation
- Streamline instructor assignment and availability tracking
- Manage student enrollment and progress tracking
- Provide real-time capacity management for classes

## Technical Architecture
- **Backend**: Spring Boot REST API
- **Database**: PostgreSQL for robust data persistence
- **Testing**: Comprehensive unit and integration testing
- **Documentation**: Swagger/OpenAPI for API documentation
- **Security**: JWT-based authentication (planned)

## Project Structure


src/

├── main/

│ ├── java/com/example/dexters_formation/

│ │ ├── controller/ # REST endpoints

│ │ ├── entity/ # Domain models

│ │ ├── repository/ # Data access layer

│ │ └── service/ # Business logic

│ └── resources/ # Application properties

└── test/

└── java/com/example/dexters_formation/

├── integration/ # Integration tests

└── unit/ # Unit tests


## API Endpoints

### Classes
- `GET /classes` - List all classes
- `GET /classes/{id}` - Get class by ID
- `POST /classes` - Create new class
- `PUT /classes/{id}` - Update class
- `DELETE /classes/{id}` - Delete class
- `GET /classes/by-program/{programId}` - Find classes by program
- `GET /classes/available` - Find classes with available spots
- `GET /classes/by-instructor-speciality` - Find classes by instructor speciality
- `GET /classes/by-name` - Search classes by name

### Programs
- `GET /programs` - List all programs
- `GET /programs/{id}` - Get program by ID
- `POST /programs` - Create new program
- `PUT /programs/{id}` - Update program
- `DELETE /programs/{id}` - Delete program
- `GET /programs/by-level-and-status` - Find programs by level and status
- `GET /programs/by-status` - Find programs by status

### Instructors
- `GET /instructors` - List all instructors
- `GET /instructors/{id}` - Get instructor by ID
- `POST /instructors` - Create new instructor
- `PUT /instructors/{id}` - Update instructor
- `DELETE /instructors/{id}` - Delete instructor
- `GET /instructors/by-speciality` - Find instructors by speciality
- `GET /instructors/by-lastname` - Find instructors by last name
- `GET /instructors/available` - Find available instructors

### Learners
- `GET /learners` - List all learners
- `GET /learners/{id}` - Get learner by ID
- `POST /learners` - Create new learner
- `PUT /learners/{id}` - Update learner
- `DELETE /learners/{id}` - Delete learner

## Setup & Installation

1. Prerequisites:
   - JDK 8+
   - Maven 3.8+
   - PostgreSQL 12+
   - Git

2. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/dexters-formation.git
   ```

3. Configure database:

   properties
   
      spring.datasource.url=jdbc:postgresql://localhost:5432/dexters_formation

      spring.datasource.username=your_username

      spring.datasource.password=your_password
   

4. Build and run:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   
## Testing
The project includes comprehensive unit and integration tests:

### Unit Tests
- ClassesServiceImplTest
- ProgramServiceImplTest
- InstructorServiceImplTest
- LearnerServiceImplTest

### Integration Tests
- ClassesIntegrationTest
- ProgramIntegrationTest
- InstructorIntegrationTest
- LearnerIntegrationTest

Run tests using:
```bash
mvn test
```


## Contributing
1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## License
This project is licensed under the MIT License.

## Installation Guide

### Prerequisites
- JDK 17 or higher
- Maven 3.8+
- PostgreSQL 12+
- Git

### Database Setup
1. Create PostgreSQL database:
```sql
CREATE DATABASE dexters_formation;
```

2. Configure application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dexters_formation
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Application Setup
1. Clone repository:
```bash
git clone https://github.com/your-username/dexters-formation.git
cd dexters-formation
```

2. Build application:
```bash
mvn clean install
```

3. Run application:
```bash
mvn spring-boot:run
```

4. Access application:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

## Testing
The application includes comprehensive test coverage:

### Integration Tests
Based on:
```
startLine: 27
endLine: 187
```
from ClassesIntegrationTest.java

### Unit Tests
Following patterns from:
```
startLine: 28
endLine: 165
```
from ClassesServiceImplTest.java

## Future Improvements
1. Authentication & Authorization
   - JWT implementation
   - Role-based access control
   - OAuth2 integration

2. Advanced Features
   - Automated scheduling system
   - Resource conflict detection
   - Attendance tracking
   - Grade management

3. Technical Enhancements
   - Caching implementation
   - API rate limiting
   - Async operations
   - Event-driven architecture

## Screenshots
(Coming soon)
- Dashboard overview
- Class management interface
- Instructor scheduling
- Student enrollment process

## Authors and Contact
- Lead Developer: [Your Name]
- Email: [your.email@example.com]
- GitHub: [your-github-profile]

## Support
For support and queries:
1. Raise an issue in the GitHub repository
2. Contact the development team
3. Check the documentation

## License
This project is licensed under the MIT License - see the LICENSE file for details.