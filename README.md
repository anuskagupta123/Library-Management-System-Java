# 📚 Digital Library Management System (Java + JDBC + MySQL)

A complete **console-based Library Management System** developed as part of my **Java Development Internship at Oasis Infobyte**.

This project demonstrates **Admin & User login**, **book management**, **issue/return system**, **database integration**, and **real-time stock updates** using Java and MySQL.

---

## 🚀 Features

### ✅ **Admin Features**
- Add new books  
- View all books  
- Issue book to user  
- View issued book history  
- Auto reduce/increase quantity  
- Validation for book ID and username  

### ✅ **User Features**
- View available books  
- Search books  
- Return issued books  
- View issued books assigned to them  
- Auto quantity update on return  

---

## 🛠️ Technologies Used

| Component        | Technology |
|------------------|------------|
| Programming Lang | Java |
| Connectivity     | JDBC |
| Database         | MySQL 8 |
| IDE              | VS Code |
| Version Control  | Git & GitHub |

---

## 📂 Project Structure

```
LibraryManagement/
 ├── src/
 │    ├── Login.java
 │    ├── MainMenu.java
 │    ├── AdminModule.java
 │    ├── UserModule.java
 │    ├── DBConnection.java
 │── lib/
 │    ├── mysql-connector-j-9.5.0.jar
 ├── README.md
```

---

## 🗄️ Database Schema (MySQL)

### ✅ **users table**
```
id (INT, PK, AUTO_INCREMENT)
username (VARCHAR)
password (VARCHAR)
role (ENUM: 'admin', 'user')
```

### ✅ **books table**
```
id (INT, PK, AUTO_INCREMENT)
title (VARCHAR)
author (VARCHAR)
category (VARCHAR)
quantity (INT)
```

### ✅ **issued_books table**
```
id (INT, PK, AUTO_INCREMENT)
book_id (INT, FK → books.id)
username (VARCHAR)
issue_date (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
return_date (TIMESTAMP NULL)
```

---

## ⚙️ How to Run the Project

### ✅ 1. Clone the repository
```
git clone https://github.com/anuskagupta123/Library-Management-System-Java.git
```

### ✅ 2. Setup MySQL Database
```
CREATE DATABASE digital_library;
USE digital_library;
```

Create the three tables (users, books, issued_books).

### ✅ 3. Add JDBC Driver
Place `mysql-connector-j-9.5.0.jar` into:

```
LibraryManagement/lib/
```

### ✅ 4. Compile the program
```
javac -cp "lib/mysql-connector-j-9.5.0.jar" src/*.java
```

### ✅ 5. Run the program
```
java -cp "lib/mysql-connector-j-9.5.0.jar;src" Login
```

---

## 🎯 Features Demonstration

- Login as **admin**
- Add/View/Issue Books  
- View issue history  
- Login as **user**
- View/Search books  
- Return books (quantity increases automatically)

---

## ✅ Future Enhancements
- GUI using JavaFX  
- Export issued report to CSV  
- Add student registration  
- Email notification for due dates  
- Admin analytics dashboard  

---

## 👩‍💻 Author
**Anuska Gupta**  
Java Developer | Oasis Infobyte Intern  
GitHub: [@anuskagupta123](https://github.com/anuskagupta123)

---

## ⭐ Support
If you like this project, kindly ⭐ the repo!
