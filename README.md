# Notes

Notes is an application where you can do CRUD operations on your notes.

## Technology stack
SpringBoot 2
SpringDataJPA 2
SpringSecurity 5
Hibernate 5
PostgreSQL 9.6

Glassfish Jersey 2.26


## Installation

Add disqo_notes database with a public schema in your PostgreSQL server, please make sure you have PostgreSQL version 9.6 or below as above SpringDataJPA doesn't support.
Go to notes-web module
Find NotesApplication class and run from there or execute the command below
```bash
TODO add command here later
```
You should login to get Access token via
```
http://localhost:8082/login
```
login request body

```
{
	"username" : "a@a.com",
	"password" : "password",
	"rememberMe" : true
}
```
copy 
Authorization header 

and paste in request header so to be able to pass through authorization filters.

## Architecture

Application consists of the following modules:

notes-common: 
here you will find universal data types that can be used across all micro-services (DTO's, enums ect. mainly datatypes that you use when exposing a client from a different microservices)

notes-core: 
this is the application business logic holder. It connects to the underlying primary database and provides services for data manipulation. This layer doesn't contain any business logic validation. It validates only data entity constraints.
its structure can be represented as 
database
repositories
services: helper components

notes-facade: 
this is the application web layer. This layer exposes web requests. Though in the microservices environment the authentication/authorization layer should be a separate and work with a microservices gateway application to ensure the most security, in this case it is included in this layer and filters all the request that qualify for filtering.
This layer also includes business validation logic.

notes-client: NOT IMPLEMENTED YET
This is the public API of the application. It is a completely standalone module and can be used as a maven dependency in any other microservice.

notes-web:
This is the start point of application, all configurations form other modules hierarchically propagate down to this module. 

## API
You can make requests using

```curl -X GET \
  http://localhost:8082/api/v1/info \
  -H 'cache-control: no-cache'
```

```
curl -X POST \
  http://localhost:8082/api/v1/2/notes \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyZGV0YWlsaWQiOjMsImNyZWF0ZWQiOjE1NTA1MTcyOTkyNTQsImFjdGl2ZSI6dHJ1ZSwidHlwZSI6IkxPR0lOX1JFTUVNQkVSX01FIn0.N7GzIAbg0XmS5tDhNy3Nl7rKc2WjxfxIxrzEo2x0jSw' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"title" : "Some title23456789012345111",
	"note" : "Some note12345678901234567890123456789012345678901234"
}'
```
```
curl -X GET \
  http://localhost:8082/api/v1/1/notes/5 \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyZGV0YWlsaWQiOjMsImNyZWF0ZWQiOjE1NTA1MTcyOTkyNTQsImFjdGl2ZSI6dHJ1ZSwidHlwZSI6IkxPR0lOX1JFTUVNQkVSX01FIn0.N7GzIAbg0XmS5tDhNy3Nl7rKc2WjxfxIxrzEo2x0jSw' \
  -H 'cache-control: no-cache'
```

```
curl -X GET \
  'http://localhost:8082/api/v1/1/notes?page=0&size=5' \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyZGV0YWlsaWQiOjMsImNyZWF0ZWQiOjE1NTA1MTcyOTkyNTQsImFjdGl2ZSI6dHJ1ZSwidHlwZSI6IkxPR0lOX1JFTUVNQkVSX01FIn0.N7GzIAbg0XmS5tDhNy3Nl7rKc2WjxfxIxrzEo2x0jSw' \
  -H 'cache-control: no-cache'
```
```
curl -X PATCH \
  http://localhost:8082/api/v1/2/notes/5 \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyZGV0YWlsaWQiOjMsImNyZWF0ZWQiOjE1NTA1MTcyOTkyNTQsImFjdGl2ZSI6dHJ1ZSwidHlwZSI6IkxPR0lOX1JFTUVNQkVSX01FIn0.N7GzIAbg0XmS5tDhNy3Nl7rKc2WjxfxIxrzEo2x0jSw' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"title" : "Some patched title",
	"note" : null
}'
```
```
curl -X DELETE \
  http://localhost:8082/api/v1/2/notes/5 \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyZGV0YWlsaWQiOjMsImNyZWF0ZWQiOjE1NTA1MTcyOTkyNTQsImFjdGl2ZSI6dHJ1ZSwidHlwZSI6IkxPR0lOX1JFTUVNQkVSX01FIn0.N7GzIAbg0XmS5tDhNy3Nl7rKc2WjxfxIxrzEo2x0jSw' \
  -H 'cache-control: no-cache'
```

## Swagger UI
[SWAGGER](http://localhost:8082/swagger-ui.html)
