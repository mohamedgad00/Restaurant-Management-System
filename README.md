# Restaurant Management System 🍽️

**Restaurant Management System** is a JavaFX-based application designed to manage essential restaurant operations such as **menu management, order processing, table management, user access control, and payment processing**.  
It is developed in **Java** following the **MVC (Model–View–Controller)** architecture for clean separation of concerns and maintainable code.

## 🌟 Features
- **Menu Management**: Add, remove, and view menu items (Appetizers, Main Courses, Desserts)  
- **Order Management**: Create, update, and track orders; supports quick order creation with Builder pattern  
- **Table Management**: Manage Regular, VIP, and Outdoor tables with real-time availability  
- **User Access Control**: Role-based dashboard access (Admin, Manager, Staff) using Proxy pattern  
- **Payment Processing**: Accept multiple payment types (Credit Card, Cash, Mobile Wallet) via Adapter pattern  
- **Design Patterns**: Singleton, Factory, Builder, Proxy, Adapter  
- **MVC Architecture**: Clean separation between Model, View, and Controller  

## 🏗️ Architecture
- **Model**: Handles business logic and data (Orders, MenuItems, Tables, Payments)  
- **View**: JavaFX UI built with FXML + CSS  
- **Controller**: Handles user interactions and updates models and views
  
## 🧩 Design Patterns Used
| Pattern | Usage |
|---------|-------|
| Singleton | Centralized managers for Menu, Orders, Tables, PaymentSystem |
| Factory | Creates MenuItems and Tables dynamically |
| Builder | Constructs complex Order objects step by step |
| Proxy | Controls access to Dashboard based on user role |
| Adapter | Integrates multiple payment methods with legacy PaymentSystem |

## 📂 Folder Structure
```plaintext
projectfx/
│
├── patterns/
│   ├── singleton/
│   │   ├── AppDataSingleton.java
│   │   ├── OrderManager.java
│   │   ├── PaymentSystem.java
│   │   └── TableManager.java
│   │
│   ├── factory/
│   │   ├── MenuItemFactory.java
│   │   └── TableFactory.java
│   │
│   ├── builder/
│   │   └── OrderBuilder.java
│   │
│   ├── proxy/
│   │   ├── Dashboard.java
│   │   └── DashboardProxy.java
│   │
│   └── adapter/
│       └── PaymentAdapter.java
│
├── model/
│   ├── Order.java
│   ├── Table.java
│   ├── Appetizer.java
│   ├── Dessert.java
│   ├── MainCourse.java
│   ├── RegularTable.java
│   ├── OutdoorTable.java
│   └── VIPTable.java
│
└── ui/
    ├── controllers/
    └── views/
```


## ⚡ Setup Instructions

### Prerequisites
- Java 11 or 17  
- IntelliJ IDEA  
- JavaFX SDK ([https://openjfx.io](https://openjfx.io))  

### Steps
1. Clone the repository:
```bash
git clone https://github.com/mohamedgad00/Restaurant-Management-System.git
```
2. Open in IntelliJ IDEA
3. Add JavaFX SDK library:
```bash
File → Project Structure → Libraries → + → Java → select javafx/lib
```
4. Set VM Options for Run Configuration:
```bash
--module-path "PATH_TO_FX_LIB" --add-modules javafx.controls,javafx.fxml
```
5. Run Main.java (extends Application)



