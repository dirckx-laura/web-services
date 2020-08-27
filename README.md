# web-services<br/>
Subject Web Services during my academic career at Artesis Plantin Hogeschool.

**Spring-TML**<br/>
Assignment:<br/>
Start from the attached Spring-TML (Tomorrowland ticketing system) project and complete it so that :
1/ you can create a user (url : /users). A user comprises an email and a password and is saved in a Redis database. The key for a user always starts with 'user:', continues with the hashed combination of his email and password  (bytesToHex) and ends with the count of created users.
2/ you can login with an existing user (url : /login). If the combination of user and email is correct, he is redirected to the ticket sale (see buyTicket.html and getTicket). If the combination is not correct, an appropriate error message is shown.

User manual:
- open project
- type in terminal: ./mvnw spring-boot:run


**Eightball**<br/>
Assignment:<br/>
Make a WebService in Django with one url : http:localhost:/8000/question, that reads a random line from the file 'eightball.txt' and returns it in html format.

User manual:
- open project
- type in terminal: python manage.py runserver
- got to /eightball/

Getting an error? Be sure to change the path in \eightball\views.py to your path!

**JokeApplication<br/>
MUST USE JAVA 8!!**<br/>
Assignment:<br/>
Start van bijgevoegd project (unzip en import as Existing Maven Project) en maak een Spring boot WebService die toelaat om een random joke op te halen van een WebService (http://api.icndb.com/jokes/random) met een voornaam en achternaam in de request (bv. http://api.icndb.com/jokes/random?firstName=John&lastName=Doe).
Toon de joke in een JSP pagina. Hierna sla je de joke op in een in-memory database indien ze nog niet bestaat.

User manual:
- open project
- type in terminal: ./mvnw spring-boot:run

**Dateexam**<br/>
Assignment:<br/>
Let the user give a date, check if the date is between 2 other dates. If it is between those 2 other dates, show 'yes' and how many days it's away from when the form was submitted.
If it's not between those 2 other dates, show 'no' and  how many days it's away from when the form was submitted.

User manual:
- open project
- type in terminal: ./mvnw spring-boot:run

©Assignments provided by Possemiers Philippe and Artesis Plantin Hogeschool.



