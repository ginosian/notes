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

notes-common: here you will find universal data type that can be used across all microservices (DTO's, enums ect. mainly datatypes that you use when exposing a client from a different microservice)

notes-core: 




## API
You can make requests using

```curl -X GET \
  http://localhost:8082/api/v1/info \
  -H 'Postman-Token: 134e8b6e-e0cf-48d1-a908-ea43f075ada7' \
  -H 'cache-control: no-cache'
```