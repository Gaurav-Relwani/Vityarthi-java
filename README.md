# 📘 Library Management System (Core Java — File Storage)

A simple, beginner-friendly **Library Management System** built using **pure Java** without any frameworks (NO Spring Boot, NO JDBC, NO MySQL).  
The program uses text files (`books.txt`, `members.txt`, `loans.txt`) to store data, making it easy to understand and perfect for academic projects.

---

## 📌 Overview
This project allows users to manage books, members, and borrowing (loan) operations inside a library.  
It follows **Object-Oriented Programming (OOP)** principles and stores data persistently in plain text files.

This project fulfills the following academic requirements:
- CRUD operations  
- User/data management  
- Reporting/analytics  
- Minimum 5–10 classes  
- Clear modular structure  

---

## ✨ Features

### 📚 Book Management
- Add book  
- Update book  
- Delete book  
- Search by title or author  
- List all books  
- Available vs Issued book report  

### 👤 Member Management
- Add new member  
- Update member  
- Remove member  
- List all members  

### 🔄 Loan Management
- Issue book  
- Return book  
- Prevent double issuing  
- Track active loans  

### 📊 Reporting
- Total books  
- Available books  
- Issued books  
- Total members  
- Total active loans  

---

## 🛠️ Technologies / Tools Used
- **Java 8+**
- **Eclipse IDE**
- **Text File Storage** (No database)
- **OOP Principles**
- **Collections API**

---

## ▶️ Steps to Install & Run (Eclipse)

### 1️⃣ Download or clone the repository

### 2️⃣ Open Eclipse → File → Import → Existing Java Project  
Select the project folder.

### 3️⃣ Create required packages:
com.library
com.library.model
com.library.service
com.library.storage
com.library.util

### 4️⃣ Paste each class into its correct package.
### 5️⃣ Create folder:

Inside it create files:
books.txt
members.txt
loans.txt

(Leave them empty)

### 6️⃣ Run
Right-click `LibraryApp.java` → Run As → Java Application

# output
<img width="405" height="345" alt="image" src="https://github.com/user-attachments/assets/18426adf-7549-4c02-9909-ad1ee279fcfd" />
---

# 📄 Project Statement

## 1. Problem Statement
Libraries need a simple system to manage books, members, and borrowing operations.  
Traditional manual record-keeping is time-consuming, error-prone, and lacks reporting features.  
This project solves these issues through a lightweight Java-based Library Management System.

---

## 2. Scope of the Project
This project covers:
- Managing books (add, update, delete, search)
- Managing members (add, update, delete)
- Issuing and returning books
- Basic reporting (available books, issued books, total members)
- Persistent file-based storage

Out of scope:
- Online access  
- Database or networking  
- Advanced authentication  
- GUI application (this is console-based)

---

## 3. Target Users
- Students and beginners learning Java  
- Small libraries or personal book collections  
- Teachers who need simple demonstrative OOP projects  
- Academic assignments requiring CRUD and file handling  

---

## 4. High-Level Features
- **Book Management:** CRUD + search  
- **Member Management:** add/remove/edit/list  
- **Loan Management:** issue and return book  
- **Reporting:** issued books, available books, total books, total members  
- **File Storage:** data saved in `.txt` files for persistence  
- **Menu-driven UI:** clean console interface  
- **OOP Structure:** models, services, storage layer, and utilities  





