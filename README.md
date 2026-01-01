# Restaurant Management System 🍽️

**Restaurant Management System** is a JavaFX-based application designed to manage essential restaurant operations such as **menu management, order processing, table management, user access control, and payment processing**.  
It is developed in **Java** following the **MVC (Model–View–Controller)** architecture for clean separation of concerns and maintainable code.

---

## 🌟 Features
- **Menu Management**: Add, remove, and view menu items (Appetizers, Main Courses, Desserts)  
- **Order Management**: Create, update, and track orders; supports quick order creation with Builder pattern  
- **Table Management**: Manage Regular, VIP, and Outdoor tables with real-time availability  
- **User Access Control**: Role-based dashboard access (Admin, Manager, Staff) using Proxy pattern  
- **Payment Processing**: Accept multiple payment types (Credit Card, Cash, Mobile Wallet) via Adapter pattern  
- **Design Patterns**: Singleton, Factory, Builder, Proxy, Adapter  
- **MVC Architecture**: Clean separation between Model, View, and Controller  

---

## 🏗️ Architecture
- **Model**: Handles business logic and data (Orders, MenuItems, Tables, Payments)  
- **View**: JavaFX UI built with FXML + CSS  
- **Controller**: Handles user interactions and updates models and views  

---

## 🧩 Design Patterns Used
| Pattern | Usage |
|---------|-------|
| Singleton | Centralized managers for Menu, Orders, Tables, PaymentSystem |
| Factory | Creates MenuItems and Tables dynamically |
| Builder | Constructs complex Order objects step by step |
| Proxy | Controls access to Dashboard based on user role |
| Adapter | Integrates multiple payment methods with legacy PaymentSystem |

---

## 📂 Folder Structure
src/
├─ main/
│ ├─ java/
│ │ ├─ com.example.projectfx
│ │ │ ├─ model/ # Data classes (Order, MenuItem, Table, etc.)
│ │ │ ├─ controller/ # Controllers for JavaFX views
│ │ │ ├─ patterns/ # Design pattern implementations
│ │ │ └─ Main.java # Entry point
│ └─ resources/
│ └─ view/ # FXML files for UI


---

## 🖥️ Screenshots
*(Replace with actual screenshots)*
- Login Screen  
- Dashboard (Admin / Manager / Staff)  
- Menu Management  
- Order Processing  
- Table Management  

---

## ⚡ Setup Instructions

### Prerequisites
- Java 11 or 17  
- IntelliJ IDEA  
- JavaFX SDK ([https://openjfx.io](https://openjfx.io))  

### Steps
1. Clone the repository:
```bash
git clone https://github.com/your-username/restaurant-management-system.git


