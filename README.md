# Notes

Notes is an application where you can do CRUD operations on your notes.
## Installation

Add disqo_notes database with a public schema in your PostgreSQL server.
Go to notes-web module
Find NotesApplication class and run from there or execute the command below
```bash
TODO add command here later
```

## Architecture

Application consists of the following modules:

notes-common: 
here you will find universal data types that can be used across all microservices (DTO's, enums ect. mainly datatypes that you use when exposing a client from a different microservices)

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

## Swagger UI
http://localhost:8082/api/v1/swagger-ui.html