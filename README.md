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

## **3. Run with Docker**
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

### 💸 Expense & Splitting (Expense Controller)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/expenses/{id}` |	Retrieves all expenses logged for a specific ID |
| `POST` | `/api/expenses` | Creates a new expense. Expects amount, description, group ID, and payer |
| `GET` | `/api/expenses/` |	Retrieves all expenses |
| `PUT` | `/api/expenses/{id}` | Updates an existing expense's details |
| `DELETE` | `/api/expenses/{id}` | Removes an expense from the record 

# 🧪 Testing
```text
./mvnw test
```
# **ScreenShots**
## **1) Register Endpoint**
- Registered 2 users
   <img width="1782" height="1003" alt="Screenshot 2026-05-02 233605" src="https://github.com/user-attachments/assets/18006f62-7af6-44ff-90d1-af01ef139051" />
   <img width="1727" height="911" alt="Screenshot 2026-05-03 155102" src="https://github.com/user-attachments/assets/34d1ab65-c5e4-4740-8c6c-d0ebfc62b4ed" />

##   **2) Login Endpoint**
- Logged in each user
      <img width="1669" height="904" alt="Screenshot 2026-05-03 155756" src="https://github.com/user-attachments/assets/69937548-a36b-49f4-9226-d60812079ed8" />
      <img width="1678" height="940" alt="Screenshot 2026-05-03 160245" src="https://github.com/user-attachments/assets/e9e66acf-0c09-41ab-af08-31b5778fd48f" />

##   **3) Create Group Endpoint**
- Created 2 events
      <img width="1678" height="960" alt="Screenshot 2026-05-03 164353" src="https://github.com/user-attachments/assets/7fb7eceb-9388-436d-8c11-7fd05501db01" />
      <img width="1723" height="906" alt="Screenshot 2026-05-03 164429" src="https://github.com/user-attachments/assets/4ca5bfcd-911b-4302-b08c-9d1b6a0443db" />

##   **4) Get All groups Endpoint**
- Retrieved all groups
      <img width="1677" height="908" alt="Screenshot 2026-05-03 164442" src="https://github.com/user-attachments/assets/1cef5211-8a38-404c-b36c-54314c330760" />


##   **5) Post Expenses Endpoint**
```text
   JSON Body:
      {
        "description": "Friend group trip to Miami!!",
        "amount": 50000.00,
        "date": "2026-07-11T00:00:00",
        "groupId": 2
      }
   ```
   <img width="1722" height="905" alt="Screenshot 2026-05-03 172419" src="https://github.com/user-attachments/assets/4b77ce7c-f414-4560-ac08-40f47f916757" />

##   **6) Get All Expenses Endpoint**
   <img width="1720" height="957" alt="Screenshot 2026-05-03 172502" src="https://github.com/user-attachments/assets/a13c2d42-c3e6-48dc-8369-d5eddd501d89" />

##   **7) Get One Expense Endpoint**
   <img width="1727" height="953" alt="Screenshot 2026-05-03 172533" src="https://github.com/user-attachments/assets/31eb9897-2551-474b-b257-caba325d1e30" />

##   **8) Update Expense Endpoint**
   <img width="1672" height="899" alt="Screenshot 2026-05-03 172755" src="https://github.com/user-attachments/assets/836f44db-83a8-4487-83df-f3f27f6a50cc" />

##   **9) Delete Expense Endpoint**
   <img width="1696" height="898" alt="Screenshot 2026-05-03 172831" src="https://github.com/user-attachments/assets/6f2489fa-d768-456f-a5c4-fe05d80d7935" />

# 📷 Video

Link: https://youtu.be/PRsRyR_OXKw




      

  
   
