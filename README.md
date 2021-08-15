# Landing-Page-Project
This is a project I made in my spare time.
This is a simple landing page of a (fictional) rental website. It is based on Spring Boot, basic HTML, CSS and JS (using Boostrap), a bit of Spring MVC. Build and run with Maven.

TO GET A QUICK LOOK AT THIS PROJECT:
https://longg99.github.io/Landing-Page-Project/

IMPORTANT!!! 
TO STORE THE EMAILS INTO THE DATABASE:
You'll need to set the config for your database first. Simply go src/main/resources and edit the file called application.properties.
In there you will find:
- spring.datasource.url="replace with your MySQL database URL".
- spring.datasource.username="replace with your MySQL username".
- spring.datasource.password="replace with your MySQL password".

TO RUN:
- Make sure you have Maven and MySQL installed.
- Clone the directory.
- Go to the directory.
- Make sure you change the properties in the "application.properties" as in the previous step.
- Open the directory in the terminal and run with: mvn spring-boot: run
- Go to http://localhost:8080 and enjoy! 

And that's it!

ISSUES:
- The SendGrid API used does not work anymore, you will need to create an account for that.

TO CHANGE SENDGRID API KEY, FOLLOW THIS TUTORIAL:
https://docs.sendgrid.com/ui/account-and-settings/api-keys

NOTES:
- I followed a tutorial to make this project; however, I alter many things in the original tutorial.
- This project is for demonstration purposes only, since my account with SendGrid is expired, no emails will be sent. You would need a SendGrid account to actually send the email.
- The service is this project is fictional!!!

Thank you for viewing this project! Enjoy!
