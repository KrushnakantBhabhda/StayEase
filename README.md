Hotel Management Aggregator API
This project is a RESTful API service developed using Spring Boot to streamline the room booking process for a hotel management aggregator application. The service uses MySQL for data persistence and implements essential features such as user registration, hotel management, and booking management. The API is secured with JWT tokens for authentication and authorization.

Key Features
Single Room Type: The application assumes all bookings are for two guests.
Hotel Management: Allows storing and managing hotel details, including hotel name, location, description, and the number of available rooms.
Booking Management: Enables customers to book rooms, with hotel managers authorized to cancel bookings.
Authentication and Authorization: Implemented using JWT tokens with three roles: CUSTOMER, HOTEL MANAGER, and ADMIN.
Public and Private Endpoints:
Public endpoints can be accessed by anyone (e.g., Registration, Login).
Private endpoints require authentication and are role-based (e.g., Booking a room).
Logging and Error Handling: Logs information and errors, and handles common errors gracefully with appropriate HTTP codes.
Unit Testing: Includes basic unit tests using MockMvc and Mockito.
Assumptions
The application supports only one type of room, and all bookings are for two guests.
Any hotel manager can update hotel details, regardless of ownership.
Check-in and check-out functionalities are handled by another service.
API Features
User Registration and Login
Registration:
Fields: Email, Password, First Name, Last Name, Role.
Passwords are encrypted and stored using BCrypt.
Default role is "Customer" if not specified.
A JWT token is generated upon successful registration.
Login:
Users can log in using their email and password.
A JWT token is generated upon successful login.
Hotel Management
Fields: Hotel Name, Location, Description, Number of Available Rooms.
Public Endpoints: Anyone can browse all available hotels.
Private Endpoints:
ADMIN: Create and delete hotels.
HOTEL MANAGER: Update hotel details.
Booking Management
Customers: Can book rooms using the service.
Hotel Managers: Can cancel bookings.
Fields: Hotel ID, Booking ID.
API Endpoints
POST /register: User registration (Public).
POST /login: User login (Public).
GET /hotels: Browse all available hotels (Public).
POST /hotels: Create a new hotel (Admin only).
PUT /hotels/{hotelId}: Update hotel details (Hotel Manager only).
DELETE /hotels/{hotelId}: Delete a hotel (Admin only).
POST /hotels/{hotelId}/book: Book a room (Customer only).
DELETE /bookings/{bookingId}: Cancel a booking (Hotel Manager only).
Setup and Running the Application
Prerequisites
Java 17 or later
Maven 3.6 or later
MySQL 8.0 or later
Steps
Clone the repository:

bash
Copy code
git clone https://github.com/your-repo/hotel-management-aggregator.git
cd hotel-management-aggregator
Configure MySQL:

Create a database named hotel_management.
Update the application.properties file with your MySQL credentials.
Build the project:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
java -jar target/hotel-management-aggregator-0.0.1-SNAPSHOT.jar
Testing
Run the unit tests using Maven:

bash
Copy code
mvn test
Logging and Error Handling
The application logs key events and errors to provide visibility into the system's operations.
Common errors are handled gracefully, returning appropriate HTTP codes such as 404 for "User not found".
Deployment
The application can be deployed on any server that supports Java and has access to MySQL.
Instructions for generating a JAR file for deployment are provided above.
