# Landing-Page-Project
This is a project I made in my spare time.
This is a simple landing page of an (fictional) rental website. It is based on Spring Boot, basic HTML, CSS and JS (using Boostrap), a bit of Spring MVC. Build and run with Maven.

TO RUN:
- Make sure you have Maven and MySQL installed.
- Clone the directory.
- Go to the directory, open in the terminal and run with: mvn spring-boot: run
- Go to http://localhost:8080 and enjoy!
To store the email into the database, you'll need to set the config for your database first. Simply go src/main/resources and edit the file called application.properties.
In there you will find:
- spring.datasource.url="replace with your MySQL database URL".
- spring.datasource.username="replace with your username".
- spring.datasource.password="replace with your password".


And that's it!

ISSUES:
- The SendGrid API used does not work anymore, you will need to create an account for that.

NOTES:
- I followed a tutorial to make this project; however, I alter many things in the original tutorial.
- This project is for demonstration purposes only, since my account with SendGrid is expired, no emails will be sent. You would need a SendGrid account to actually send the email.
- The service is this project is fictional!!!

Thank you for reading this file! Enjoy!
