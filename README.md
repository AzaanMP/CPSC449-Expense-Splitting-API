# Expense Splitting API
A robust RESTful API built with **Spring Boot** to help users manage shared expenses, create groups, and track balances efficiently.
## Team Information

| Team Members Names | CWID |
|---|---|
| Azaan Mavandadipur | 820154672 |
| Alejandro Galvan | 845376177 |
| Kristin Valido | 877723205 |
| Ethan Paransky | 885778159 |

## 🚀 Features

*   **User Authentication**: Secure registration and login using JWT (JSON Web Tokens).
*   **Group Management**: Create and manage groups for different spending circles (e.g., Roommates, Trip 2024).
*   **Expense Tracking**: Log expenses within groups, specify amounts, and track who paid.
*   **Global Error Handling**: Centralized exception management for consistent API responses.
*   **Containerized**: Includes a Dockerfile for easy deployment.

---

## 🛠️ Tech Stack

*   **Framework**: Spring Boot
*   **Security**: Spring Security + JWT
*   **Build Tool**: Maven
*   **Database**: Configurable via `application.properties` (JPA/Hibernate)
*   **Deployment**: Docker

---

## 📂 Project Structure
```text
src/main/java/com/csuf/expensesplittingapi/
├── config/             # Security & Application configurations
├── controller/         # REST Endpoints (Auth, Expense, Group)
├── dto/                # Data Transfer Objects for API requests/responses
├── model/              # Database Entities (User, Group, Expense)
├── repository/         # Data Access Layer
└── security/           # JWT Filters and Utility classes
```

## ⚙️ Getting Started
- **Java 17** or higher
- **Maven 3.x**
- Docker (optional)

# ⚙️ Installation & Run
## **1. Clone the repository**
```text
git clone [https://github.com/your-username/CPSC449-Expense-Splitting-API.git](https://github.com/your-username/CPSC449-Expense-Splitting-API.git)
cd CPSC449-Expense-Splitting-API-master
```

## **2. Build the project**
```text
./mvnw clean install
```

## **3. Run the application**
```text
./mvnw spring-boot:run
```

##3. **Run with Docker**
```text
docker build -t expense-splitting-api .
docker run -p 8080:8080 expense-splitting-api
```

# 🚦API Endpoints

### 🔐 Authentication EndPoints
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate and receive JWT if credentials are valid|

### 👥 Group Management (Group Controller)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/groups` | Returns a list of all groups the authenticated user belongs to |
| `POST` | `/api/groups` | Creates a new group (e.g., "Apartment 4B") |
| `GET` | `/api/groups/{id}` | Gets details for a specific group including its members |
| `POST` | `/api/groups/{id}/members` | Adds a new user to the group via their user_id or email |

### 💸 Expense & Splitting (Expense Controller)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/expenses/group/{groupId}` |	Retrieves all expenses logged for a specific group |
| `POST` | `/api/expenses` | Creates a new expense. Expects amount, description, group ID, and payer |
| `PUT` | `/api/expenses/{id}` | Updates an existing expense's details |
| `DELETE` | `/api/expenses/{id}` | Removes an expense from the record |
| `GET` | `/api/expenses/balaces/{groupsId}` | Custom Logic: Calculates the net balance (who owes whom) within a group |

### 👤 User Profile (User Controller)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/users/me` | Returns the current authenticated user's profile information |
| `GET` | `/api/users/search?q=email` | Allows finding users by email to add them to groups |

# 🧪 Testing
```text
./mvnw test
```
