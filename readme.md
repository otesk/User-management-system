# User management system

## Functioning of the system
User management system is a simple user management 
application.

Before starting the work, you need to create a 
database named *user_management_system* and 
register the properties of connecting to 
the database in the properties file named [*application.properties*](./src/main/resources/application.properties).

The system provides 2 roles: User and Admin. Also, 
there is the ability to go to the home page for 
non-logged users. 

If the user is not logged in, the system will ask to 
go to the login page for further work;

Actions available to User:
- View user list;
- View any user's details.

Actions available to Admin:
- View user list;
- View any user's details;
- Create a new user;
- Editing an existing user;
- User lock/unlock.

The system contains a file for migrations 
to the database with three users with 
the following names and passwords:
1. admin, admin1234 :
    - user with the role "Admin";
    - has full access to the system.
2. user, user1234 :
    - user with the role "User"; 
    - has limited access to the system.
3. lockeduser, lockuser1234 :
    - user with the role "User";
    - has status "Inactive";
    - does not have access to the system;
    
## Testing of the system
Before starting tests, you need to create a 
database named *user_management_system_test* and 
register the properties of connecting to 
the database in the properties file named [*application-test.properties*](./src/test/resources/application-test.properties).
    
### Additionally

To view the documentation, follow the [documentation link](./javadoc/index.html). 

The bcrypt algorithm was used to 
encode passwords when writing to the database.

To filter by the username, was used a sorting algorithm 
for ascending editorial distance (Levenshtein distance) 
for usernames, then the resulting list of users 
was sorted alphabetically.

The system supports validation of input parameters ([validation conditions](./src/main/java/com/otesk/ums/dto/UserAccountDTO.java)).

### Used technologies

- Java 11;
- Spring Boot, Spring Mvc, Spring Data, Spring Core, Spring Security
- MySQL;
- Freemarker Template;
- Gradle;
- Flyway.
