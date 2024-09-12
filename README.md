# GreenPulse - Carbon Footprint Tracking Console Application

## Project Context
In a world where awareness of the environmental impact of human activities is growing, tracking and managing carbon consumption is becoming crucial. This project aims to develop a Java console application that enables users to measure and monitor their carbon footprint while providing analysis tools to help them understand their consumption habits and encourage more environmentally friendly behavior.

## Objectives
The primary goal is to create a **simple yet functional console application** that allows users to:
- **Manage their profile**
- **Track carbon consumption**
- **Analyze consumption patterns**

The project introduces and applies basic Java concepts, including Object-Oriented Programming (OOP), to lay the foundation for future enhancements.

### Key Features

#### 1. User Account Management
- **Create Account**: Users can create a new account by providing basic information such as name, age, and a unique identifier.
- **Modify Account**: Users can update their account information.
- **Delete Account**: Users can securely delete their accounts.

#### 2. Intuitive Console Interface
- Present information in a clear and accessible way to ensure readability and ease of understanding.

#### 3. Carbon Consumption Calculation
- Automatically calculate the total carbon consumption for each user based on entered data.
- Ensure real-time updates so that the displayed consumption is always accurate and up-to-date.

#### 4. Consumption Analysis
- Generate detailed reports on carbon consumption, available on a daily, weekly, and monthly basis.

#### 5. Integration with Database
- Implement persistence of user data and carbon consumption records using JDBC to interact with a relational database.
Transaction Management
- Ensure data integrity by using transactional operations when adding or modifying carbon consumption records.
Note: JDBC transactions are managed using commit() and rollback() to ensure data consistency. This ensures that changes are applied only when all operations succeed, and rolled back in case of failure.

## Technical Details
- **Language**: Java
- **Architecture**: Object-Oriented Programming
- **Application Type**: Console-based
