# Task Manager REST API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-4.x-green)
![Docker](https://img.shields.io/badge/Docker-compose-blue)
![Maven](https://img.shields.io/badge/Apache%20Maven-3.8-red)

## 1. Project Overview

This project is a production-grade, secure RESTful API built with **Java 17** and **Spring Boot 3**. It provides a robust backend service for creating, managing, and executing shell command "tasks". The application is designed with a focus on security, scalability, and maintainability, using **MongoDB** for persistent storage and **Docker** for easy environment setup.

The core functionality allows users to define tasks with specific shell commands, execute them on demand, and maintain a historical record of each execution, including start time, end time, and output.

---

## 2. Core Features

* **Idempotent CRUD Operations**: Full support for Create, Read, Update, and Delete operations on tasks using standard REST conventions. The `PUT` endpoint for creation is idempotent.
* **Dynamic Task Search**: A dedicated endpoint to search for tasks by their `name` using partial or full string matching.
* **Secure by Design Command Execution**:
    * **Whitelisting**: Only a pre-approved list of safe commands (e.g., `echo`, `date`, `uname`) can be executed, preventing arbitrary code execution.
    * **Input Sanitization**: Proactively validates and rejects any command containing shell metacharacters (`|`, `&`, `;`, `>`, `<`, etc.) to mitigate command injection vulnerabilities.
* **Persistent Execution History**: Leverages MongoDB's flexible document model to store a complete history of all executions for each task, embedded within the task document itself.
* **Layered Architecture**: Follows best practices with a clear separation of concerns into Controller (API layer), Service (Business Logic), and Repository (Data Access) layers.
* **Robust Error Handling**: Implements a centralized exception handling mechanism to provide clear, consistent, and meaningful error responses for various scenarios (e.g., 404 Not Found, 400 Bad Request).

---

## 3. Architecture & Design Decisions

* **Framework**: **Spring Boot** was chosen for its rapid development capabilities, embedded Tomcat server, and powerful dependency management, which simplifies the creation of stand-alone, production-ready applications.
* **Database**: **MongoDB** was selected as the database for its schema-less flexibility and scalability. The ability to embed the `taskExecutions` array directly within the `Task` document is a natural fit for this use case, simplifying queries and ensuring data atomicity at the document level.
* **Data Model**: The primary entity is the `Task`, which contains fields like `id`, `name`, `owner`, and `command`. A list of `TaskExecution` objects is embedded within each `Task` document to track its history.
    ```
    Task {
      id: string,
      name: string,
      owner: string,
      command: string,
      taskExecutions: [
        {
          startTime: datetime,
          endTime: datetime,
          output: string
        }, ...
      ]
    }
    ```
* **Containerization**: **Docker Compose** is used to manage the MongoDB instance, ensuring a consistent and isolated database environment that can be spun up or torn down with a single command.

---

## 4. Setup and Running the Application

### Prerequisites
* Java Development Kit (JDK) 17 or later
* Apache Maven 3.6+
* Docker and Docker Compose

### Step-by-Step Instructions

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/your-username/your-repo.git](https://github.com/your-username/your-repo.git)
    cd your-repo
    ```

2.  **Start MongoDB using Docker**
    From the project's root directory, run the following command to start a MongoDB container in the background.
    ```bash
    docker-compose up -d
    ```
    > This command will pull the MongoDB image and run it on the default port `27017`.

3.  **Run the Spring Boot Application**
    Use the Maven wrapper included in the project to build and run the application.
    ```bash
    ./mvnw spring-boot:run
    ```
    You should see the application start up, and the final logs will indicate that it's running on `http://localhost:8080`.

---

## 5. API Endpoints & Usage Showcase

The following examples demonstrate the complete workflow of creating, executing, retrieving, and deleting a task. The screenshots are from Postman.

**Base URL**: `http://localhost:8080`

### 1. Create a Task (`PUT /tasks`)

This endpoint creates a new task with a specified ID. If a task with the same ID already exists, it will be updated.

* **Request Body**: A JSON object representing the task.
* **Success Response**: `201 Created` with the newly created task object.

**Screenshot: Creating a new task in Postman**
![Create a new task](https://i.imgur.com/39wPj3O.png)

### 2. Execute a Task (`PUT /tasks/{id}/execute`)

This endpoint triggers the execution of the command associated with a task and records the result.

* **URL Parameter**: The `id` of the task to execute.
* **Success Response**: `200 OK` with the updated task object, now containing the new execution details in the `taskExecutions` array.

**Screenshot: Executing the task and viewing the result**
![Execute a task](https://i.imgur.com/eB3jV4j.png)

### 3. Find a Task by Name (`GET /tasks/findByName`)

This endpoint searches for tasks where the name field contains the provided query string.

* **Query Parameter**: `name={substring}`
* **Success Response**: `200 OK` with an array of matching task objects, including their full execution history.

**Screenshot: Searching for the task by name "Hello"**
![Find a task by name](https://i.imgur.com/jWvM7mF.png)

### 4. Delete a Task (`DELETE /tasks/{id}`)

This endpoint permanently removes a task from the database.

* **URL Parameter**: The `id` of the task to delete.
* **Success Response**: `204 No Content`, indicating successful deletion with no response body.

**Screenshot: Deleting the task**
![Delete a task](https://i.imgur.com/mU4b4tq.png)

---

## 6. Future Improvements

* **Asynchronous Task Execution**: Implement asynchronous processing for long-running commands using Spring's `@Async` to prevent blocking the API request thread.
* **Authentication & Authorization**: Integrate Spring Security with JWT to secure endpoints and ensure that only authorized users can create or modify tasks.
* **Enhanced Search**: Expand the search functionality to allow filtering by multiple fields like `owner`, creation date, etc.
* **Full Dockerization**: Create a `Dockerfile` for the Spring Boot application itself so that the entire application stack (API + DB) can be managed and deployed via Docker Compose.
