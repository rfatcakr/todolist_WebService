# ToDoList App

Build Restful CRUD API for a simple ToDoList application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/rfatcakr/todolist_WebService.git
```

**2. Create Mysql database**
```bash
create database todolists_db
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/eastodolist-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

	
## User info
    GET 
	------ "/user" -> returns all users
    ------ "/user/{username}" -> returns the user who is name is sent
    POST 
	------ "/user" -> add a user
    PUT
	------ "user/{id}" -> update user by his id
    DELETE
	------ "user/{id}" -> delete user by his id

## Lists
    GET 
	------ "/todolist" -> returns all lists
    ------ "/todolist/list/{id}" -> returns list by its id
    ------ "/todolist/user/{user_id}" -> returns list by user id
    POST 
	------ "/user" -> add a user
    PUT
	------ "/todolist/list/{id}" -> update list by its id
	------ "/todolist/list/{id}" -> update list by its id
	------ "/todolist/user/{user_id}" -> update list where user id is defined
    DELETE
	------ "/todolist/list/{id}" -> delete list by its id

## Items
	No time to explain :\

You can test them using postman or any other rest client.

## Learn how to create a React client for this web service

You can find the tutorial for this application on my blog -

<https://github.com/rfatcakr/ToDoList_Client>
