# Brazilian Jiu-Jitsu School Website

Welcome to the Kimura Jiu-Jitsu School Website project! This project aims to develop a user-friendly and intuitive website for a Brazilian Jiu-Jitsu school owned by a non-tech-savvy instructor. The website will be built using Java, Spring Frameworks, Angular, PostgreSQL, and Salesforce to provide a seamless experience for both the school’s staff and its students.

## Project Overview

The primary goal of this project is to create a website that is not only easy to navigate for students and visitors but also simple to manage for the school’s employees. The school’s instructor and staff may not have a strong technical background, so the focus is on delivering a solution that is reliable and requires minimal technical knowledge to operate.

### Key Features

- **Administrator UI:** A secure and intuitive user interface that allows school employees to easily update and manage website content, such as class schedules, announcements, and student information.
- **Responsive Design:** The website will be fully responsive, ensuring a great user experience across all devices, from desktops to mobile phones.
- **Integration with Salesforce:** Seamless integration with Salesforce to manage student enrollment, payments, and CRM functionalities.
- **Robust Backend:** Built with Java and Spring Frameworks, the backend will handle data processing, user authentication, and interactions with the PostgreSQL database.
- **Angular Frontend:** The frontend will be developed using Angular, providing a dynamic and responsive user interface for both the public website and the administrator panel.
- **Secure Data Management:** Utilizing PostgreSQL for secure data storage and management, ensuring that sensitive student information is well protected.

## Technologies Used

- **Java & Spring Frameworks:** Powering the backend with robust and scalable solutions for data management, business logic, and security.
- **Angular:** Creating a dynamic, responsive, and user-friendly frontend for both the public website and admin UI.
- **PostgreSQL:** Secure and reliable relational database management system for storing and managing all website data.
- **Salesforce:** Integrating with Salesforce for CRM capabilities, allowing the school to efficiently manage student information, communications, and payment processing.
- **HTML/CSS/TypeScript:** Building a clean and responsive design that is easy to navigate and visually appealing.

Getting Started
Prerequisites

Before you start, make sure you have the following installed:

    Java 11 or later
    Node.js and npm
    Angular CLI
    PostgreSQL
    Salesforce Developer Account

Installation

1. Clone the repository.
   
git clone https://github.com/mikeladderbush/BJJSite.git
cd BJJSite


2. Backend Setup:

    Navigate to the backend directory and build the Spring Boot application:

    cd BJJSite
    ./mvnw clean install
    ./mvnw spring-boot:run

    Configure PostgreSQL database connection in application.properties.

3. Frontend Setup:

    Navigate to the frontend directory and install the Angular dependencies:
   
        cd \BJJSite\bjjsite-angular-app
        npm install
        ng serve

    Salesforce Integration:
        Configure the Salesforce API keys and endpoints in the application.properties or as environment variables.

4. Running the Application

Once the backend and frontend are set up, you can access the website locally:

    Backend: http://localhost:8080
    Frontend: http://localhost:4200

5. Deployment

To deploy the website, follow the specific instructions for your cloud provider or hosting service. Ensure that the PostgreSQL database is accessible and that Salesforce integration settings are correctly configured.
