# Easy Interview

## Application to facilitate candidates and interviewers

Easy Interview is a java backend application for candidate and interviewer to record their available interview times.

## What do you need to know

This tutorial was made with commands on Windows but the commands used can be used on other OS as long as the necessary
modifications are made. To run the application you must have java installed because you will need to run the
command `java -jar`

## How to use

- Go to `easyinterview` folder;
- run command to build the project:

```sh
./gradlew build
```

- Copy the created `app.jar` jar file to the folder `<easyinterview_folder>/build/libs` and paste it into the folder of
  your choice;
- To run the application, just run the command

```sh
java -jar app.jar
```

- Vá para o navegador e acesse a URL: `http://localhost:8080/api/swagger-ui/`. On this page you can create, update or
  delete Candidate and Interview and their slot. in addition to researching the periods in which it is possible to have
  an interview. For more instructions, see the flows section just below

## Features

| Feature | Situation |
| ------ | ------ |
| Create application with Spring Data Rest, QueryDSL and Operator lib to facilitate implementations and allow dynamic queries | Done |
| Create Candidate CRUD | Done |
| Create Interview CRUD | Done |
| Configure Swagger | Done |
| Create a Slot entity and relate it to the Candidate and the interviewer| Done |
| Create Period view | Done |
| Integration test | Done |
| Create documentation (README.md) | In process |

## Flows

Go to URL <http://localhost:8080/api/swagger-ui/>.

The `Candidate Entity`, `Interview Entity` and `Period Entity`
sessions have subsections to manage their respective entities. For `Candidate Entity`, `Interview Entity` sessions it is
possible to search, create, update and delete their respective entities, but in the `Period Entity` session it is only
possible to search. The others actions are not allowed and if executed, an error will be returned.

#### Describing subsections

- GET: retrieves a predefined number of records (also called record page). It is possible to set values for `page`
  , `size` and `sort` fields.
- POST: create a new record using json added in `Request body` text area.
- PUT: updates the record related to the passed id using json added in the `Request body` textarea.
- PATCH: update record specific fields related to passed id using json added in `Request body` textarea.
- DELETE: delete the record related to the passed id.

### Candidate

Examples to find, create, update and delete Candidate and their available times

> Note: To execute some action, just click on the desired subsection, on the `Try it` button, fill in the required fields (when applicable) and press `Execute`;

#### Retrieve candidates (GET)

Just run what is in the note above. Another possibility is on the browser using
URL: <http://localhost:8080/api/candidates>

#### Create candidate (POST)

Just run what is in the note above using a json in `Request body` textarea. For example:

```
{
  "name": "Candidate 3",
  "slots": [
    {
      "dateTime": "2022-01-02T08:00:00"
    },
    {
      "dateTime": "2022-01-03T08:00:00"
    },
    {
      "dateTime": "2022-01-04T10:00:00"
    },
    {
      "dateTime": "2022-01-06T16:00:00"
    }
  ]
}
```

#### Update candidate (PUT)

> Note: In this action, all entities passed by parameters will be updated.
> So if you are not setting the id then that entity will be removed and created next.
> To retrieve the complete object you can use the 'find by id' subsection to copy the entities with their respective ids

Just run what is in the note above using a json in `Request body` textarea.

For example (This example was copied from find by id subsection):

```
{
  "id": 3,
  "name": "Candidate 3",
  "slots": [
    {
      "id": 9,
      "dateTime": "2022-01-02T08:00:00",
      "candidateId": 3
    },
    {
      "id": 10,
      "dateTime": "2022-01-03T08:00:00",
      "candidateId": 3
    },
    {
      "id": 11,
      "dateTime": "2022-01-04T10:00:00",
      "candidateId": 3
    },
    {
      "id": 12,
      "dateTime": "2022-01-06T16:00:00",
      "candidateId": 3
    }
  ]
}
```

#### Update candidate field (PATCH)

> Note: In this action, all entities passed by parameters will be updated.
> So if you are not setting the id then that entity will be removed and created next.
> To retrieve the complete object you can use the 'find by id' subsection to copy the entities with their respective ids

Just run what is in the note above using a json in `Request body` textarea.

> Note: If you want to update candidate fields and times it is better to use the PUT subsection

For example (This example will update only candidate name):

```
{
  "name": "Candidate 3 - updated only name"
}
```

#### Delete candidate (DELETE)

Just run what is in the note above using the id of the entity you want to remove.

### Interviewer

Examples to find, create, update and delete Interviewer and their available times

> Note: To execute some action, just click on the desired subsection, on the `Try it` button, fill in the required fields (when applicable) and press `Execute`;

#### Retrieve interviewers (GET)

Just run what is in the note above. Another possibility is on the browser using
URL: <http://localhost:8080/api/interviewers>

#### Create interviewer (POST)

Just run what is in the note above using a json in `Request body` textarea. For example:

```
{
  "name": "Interviewer 3",
  "code": "Code 3",
  "slots": [
    {
      "dateTime": "2022-01-02T08:00:00"
    },
    {
      "dateTime": "2022-01-03T08:00:00"
    },
    {
      "dateTime": "2022-01-04T10:00:00"
    },
    {
      "dateTime": "2022-01-06T16:00:00"
    }
  ]
}
```

#### Update interviewer (PUT)

> Note: In this action, all entities passed by parameters will be updated.
> So if you are not setting the id then that entity will be removed and created next.
> To retrieve the complete object you can use the 'find by id' subsection to copy the entities with their respective ids

Just run what is in the note above using a json in `Request body` textarea.

For example (This example was copied from find by id subsection):

```
{
  "id": 3,
  "name": "Interviewer 3",
  "code": "Code 3",
  "slots": [
    {
      "id": 9,
      "dateTime": "2022-01-02T08:00:00",
      "interviewerId": 3
    },
    {
      "id": 10,
      "dateTime": "2022-01-03T08:00:00",
      "interviewerId": 3
    },
    {
      "id": 11,
      "dateTime": "2022-01-04T10:00:00",
      "interviewerId": 3
    },
    {
      "id": 12,
      "dateTime": "2022-01-06T16:00:00",
      "interviewerId": 3
    }
  ]
}
```

#### Update interviewer field (PATCH)

> Note: In this action, all entities passed by parameters will be updated.
> So if you are not setting the id then that entity will be removed and created next.
> To retrieve the complete object you can use the 'find by id' subsection to copy the entities with their respective ids

Just run what is in the note above using a json in `Request body` textarea.

> Note: If you want to update interviewer fields and times it is better to use the PUT subsection

For example (This example will update only interviewer name):

```
{
  "name": "Interviewer 3 - updated name",
  "code": "Code 3 - updated code"
}
```

#### Delete interviewer (DELETE)

Just run what is in the note above using the id of the entity you want to remove.

### Period

Examples for making dynamic queries, leveraging the QueryDSL and Operators libraries. This approach can be used in all
entities.

> Note: To execute some action, just click on the desired subsection, on the `Try it` button, fill in the required fields (when applicable) and press `Execute`;

> Note 2: In this section the actions to save or update entity is blocked.

#### Period interviewers (Using URL in browser for easier understanding)

Just run what is in the note above. Another possibility is on the browser using

Retrieve periods

<http://localhost:8080/api/periods>

> Note 3: In period entity I decide to create one projection to hide ids.

Retrieve periods using projection

<http://localhost:8080/api/periods?projection=period>

Retrieve periods filtering by candidate name

<http://localhost:8080/api/periods?projection=period&candidateName=Candidate%201>

Retrieve periods filtering by candidate name but using a part of the name

<http://localhost:8080/api/periods?projection=period&candidateName=contains(Candidate)>

Retrieve periods filtering by candidate name, but using a part of the name and not case sensitive

<http://localhost:8080/api/periods?projection=period&candidateName=ci(contains(CANDIDATE))>

Retrieve periods filtering by candidate name but using a part of the name sorting by descending interviewer name
limiting the amount of records

<http://localhost:8080/api/periods?projection=period&candidateName=contains(Candidate)&size=2&sort=interviewerName,desc>

Retrieve periods filtering by date

<http://localhost:8080/api/periods?projection=period?dateTime=2022-01-02T10:00:00>

Retrieve periods filtering by date range

<http://localhost:8080/api/periods?projection=period&dateTime=2022-01-02T09:00:00&dateTime=2022-01-02T11:00:00>

## References

<https://gt-tech.bitbucket.io/spring-data-querydsl-value-operators/README.html>