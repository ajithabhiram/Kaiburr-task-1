Kaiburr Assessment Task 1: Java REST API

The application is a Java Spring Boot production-level application exposing REST endpoints to create, manage, and run shell command "tasks". It persists data in MongoDB and implements strong validation and error handling.

Features

CRUD Operations: Complete Create, Read, Update, Delete operations for tasks.
Task Search: Find tasks by name (substring matching).
Secure Command Execution: Runs whitelisted shell commands (echo, date, uname, etc.) and logs the output and timestamps.
Command Validation: Halt execution of unsafe commands or commands containing shell metacharacters.
MongoDB Persistence: Stores task execution history using an embedded document model.
RESTful API: Abides by standard REST principles using the right HTTP verbs and status codes.
Tech Stack

Backend: Java 17, Spring Boot 3
Database: MongoDB
Build Tool: Apache Maven
Containerization: Docker
Local Setup

Clone the repository:
git clone <your-repo-url>
cd <your-repo-folder>
Start MongoDB database with Docker: Open terminal at project root and execute:

docker-compose up -d
It will launch a MongoDB container and expose it on port 27017.

Execute Spring Boot Application: In same terminal, execute application using Maven Wrapper:

./mvnw.cmd spring-boot:run
API server will be started on http://localhost:8081.

API Reference

The base URL for all operations is http://localhost:8081.

1. Create or Update a Task

Endpoint: PUT /tasks
curl Example:
curl -X PUT http://localhost:8081/tasks -H "Content-Type: application/json" -d '{"name": "System Info Task", "owner": "Your Name", "command": "uname -a"}'
2. Get All Tasks or a Single Task

Endpoint: GET /tasks or GET /tasks?id={taskId}
curl Examples:
# Get all tasks
curl http://localhost:8081/tasks

# Get a single task by its ID
curl http://localhost:8081/tasks?id=123
3. Search Tasks by Name

Endpoint: GET /tasks/search?name={substring}
curl Example:
curl "http://localhost:8081/tasks/search?name=Hello"
4. Execute a Task

Endpoint: PUT /tasks/123/execute
curl Example:
curl -X PUT http://localhost:8081/tasks/123/execute
5. Delete a Task

Endpoint: DELETE /tasks/123
curl Example:
curl -X DELETE http://localhost:8081/tasks/123
