# ze-code-challenges
Ze Code Challenges

The entire project was developed with Java 8, Spring Boot and MySQL database.
To run the project locally, you must have installed Java 8, maven (version 3.6.1) and MySQL (version 5.0.12 or higher).
IDE indicated: Eclipse.
The data of your connection to the database (url, username and password) must be configured in the "application.properties" file (ze-code-challenges / src / main / resources /)
The project itself downloads all necessary dependencies through the Maven dependency manager.
The entire database is created automatically following the rules of the Model class, through JPA.
Once the project is executed and is live, the API documentation can be viewed through the URL http://localhost:8080/swagger-ui.html
The application was deployed through Heroku which can be accessed at the URL https://ze-code-challenges.herokuapp.com/ and documentation at the URL https://ze-code-challenges.herokuapp.com/swagger-ui.html
In addition, an image of the application will be created and added to the DockerHub (In a few days).
