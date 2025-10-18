# Kaiburr Assessment Task 1: Java REST API

This project is a **production-level Java Spring Boot** application that exposes **REST endpoints** to create, manage, and execute shell command “tasks”.
It uses **MongoDB** for data persistence and includes **robust validation**, **security checks**, and **error handling** mechanisms.

---

## 🧩 Features

✅ **CRUD Operations** — Create, Read, Update, and Delete tasks
✅ **Task Search** — Find tasks by partial or full name
✅ **Secure Command Execution** — Executes only whitelisted commands (e.g., `echo`, `date`, `uname`)
✅ **Command Validation** — Blocks unsafe commands containing shell metacharacters
✅ **MongoDB Persistence** — Stores task execution history using embedded document model
✅ **RESTful API Design** — Implements proper HTTP verbs and status codes

---

## 🛠️ Tech Stack

| Component            | Technology             |
| -------------------- | ---------------------- |
| **Backend**          | Java 17, Spring Boot 3 |
| **Database**         | MongoDB                |
| **Build Tool**       | Apache Maven           |
| **Containerization** | Docker                 |

---


### 2️⃣ Start MongoDB using Docker

Run the following command from the project root:

```bash
docker-compose up -d
```

This will launch a MongoDB container exposed on **port 27017**.

### 3️⃣ Run the Spring Boot Application

Execute the app using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

The API server will start at:
👉 **[http://localhost:8081](http://localhost:8081)**

---

## 🌐 API Reference

**Base URL:** `http://localhost:8081`

---

### 1. 🟢 Create or Update a Task

**Endpoint:** `PUT /tasks`

**Example:**

```bash
curl -X PUT http://localhost:8081/tasks \
  -H "Content-Type: application/json" \
  -d '{"name": "System Info Task", "owner": "Your Name", "command": "uname -a"}'
```

---

### 2. 🔵 Get All Tasks or a Single Task

**Endpoints:**

* `GET /tasks`
* `GET /tasks?id={taskId}`

**Examples:**

```bash
# Get all tasks
curl http://localhost:8081/tasks

# Get a specific task by ID
curl http://localhost:8081/tasks?id=123
```

---

### 3. 🟣 Search Tasks by Name

**Endpoint:** `GET /tasks/search?name={substring}`

**Example:**

```bash
curl "http://localhost:8081/tasks/search?name=Hello"
```

---

### 4. 🟠 Execute a Task

**Endpoint:** `PUT /tasks/{id}/execute`

**Example:**

```bash
curl -X PUT http://localhost:8081/tasks/123/execute
```

---

### 5. 🔴 Delete a Task

**Endpoint:** `DELETE /tasks/{id}`

**Example:**

```bash
curl -X DELETE http://localhost:8081/tasks/123
```

---

## 🧾 Example Task Flow

1. Create a new task using the `/tasks` endpoint.
2. Retrieve and verify it via `/tasks` or `/tasks?id={id}`.
3. Execute it securely with `/tasks/{id}/execute`.
4. View logs and history stored in MongoDB.
5. Delete the task if no longer needed.
