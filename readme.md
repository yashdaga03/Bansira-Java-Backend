
## Library Management System

This project consists of three services which completes the library management system. The first service is in Java Spring Boot (library), the second one is in Node JS Typescirpt Express (leaderboard), and the third one is communication-service with Node JS Javascript Express which helps us notify the winners.

Features -
- 
- Depending on Role, User/Admin can perform some Fetch, Create and Update operations for Books and Departments with Paginated APIs.
- Interesting one is that I have enabled scheduling which means that after a certain period of time, some functions will be called, this will help us in reseting the leader-board once every day, week and month. They are designed to be called in specific intervals only
- The leaderboard service gets the the current stats and calculates the leader out of all
- Once the leader is calculated, we are also sending a dummy email through communication-service for notification purpose. We can also add SMS, Push notifications, depending on the need. For simplicity, I have hardcoded sender's and receiver's email address for now. 
- Cool thing here is that I have configured sample HTML files, whatever HTML I write, communication-service using nodemailer but can update to Amazon SES will take it as a template and receiver will receive the email through this template.

Installation -
- 
- DockerFile is not added at the moment because the installation is pretty simple, just run "npm i", "npm run dev" (for communication-service), "npm start" (for leaderboard), "mvn clean test", "mvn clean install" (library) and run library main class
- .gitignore file is added there due to which .env file is not visible, you'll have to add .env file and for communication-service, just add SMTP_HOST=smtp.gmail.com
SMTP_PORT=587

- PORT number of each service: communication(3000); leaderboard(4000) and library(8080)

- For one service, I have added Controller Tests and for another, I have added Service Tests.
