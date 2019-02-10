# Vinilshop
[![Build Status](https://travis-ci.org/robertoamadeuneto/vinilshop.svg?branch=master)](https://travis-ci.org/robertoamadeuneto/vinilshop)

A Spring Boot application created to buy music albums. The albums catalog is made available through an integration with the Spotify API at the application startup.

## Clone
Run: `git clone https://github.com/robertoamadeuneto/vinilshop.git`.

## Build
To build this project, run this Maven command: `mvn clean install`. You can also use `mvn test` if you just want to run the tests.

## Run
A PostgreSQL database called `vinilshop` on port `5432` is required to run this project.
If you have it, just run the following command inside the project folder: `java -jar target/vinilshop.jar`.
The application will be available through the port`8080`.

## Endpoints
### Albums
1) `/api/albums`. Http method: GET.
Description Finds a paginated list of albums.
Available query params:
- `genre`: filters the albums list by a specific genre. Example: http://localhost:8080/api/albums?genre=rock
- `sort`: orders the result by a specific property. Example: http://localhost:8080/api/albums?sort=name,asc

2) `/api/albums/{id}` Http method: GET.
Finds a specific album by its identifier. Example: http://localhost:8080/api/albums/1

### Sells
1) `/api/sells`. Http method: GET.
Finds a paginated list of sells.
Available query params:
- `initialDate` and `finalDate`: filters the sells by a range of dates. Example: http://localhost:8080/api/sells?initialDate=10/02/2019&finalDate=11/02/2019
- `sort`: orders the result by a specific property. Example: Example: http://localhost:8080/api/albums?sort=finishedAt,desc

2) `/api/sells/{id}` Http method: GET.
Finds a specific sell by its identifier.

3) `/api/sells`. Http mthod: POST.
Initiates a new sell. It's not required to pass an object on the request body: Example: http://localhost:8080/api/sells

3) `/api/sells`. Http mthod: PATCH.
Finishes a new sell. It's not required to pass an object on the request body: Example: http://localhost:8080/api/sells

### Sell Items
1) `/api/sells/{idSell}/items`. Http method: GET.
Finds all items of a specific sell.  Example: http://localhost:8080/api/sells/1/items

2) `/api/sells/{idSell}/items/{id}`
Finds a specific item of a specific sell. Example: http://localhost:8080/api/sells/1/items/1

3) `/api/sells/{idSell}/items`. Http mthod: POST.
Adds an item to a sell. Example: http://localhost:8080/api/sells/1/items - Request body: `{"sell": { "id": 1 }, "album": {"id": 1}}`

3) `/api/sells/{idSell}/items/{id}`. Http mthod: DELETE.
Removes an item from a sell. Example: http://localhost:8080/api/sells/1/items/1

## API Docs
Access`http://localhost:8080/swagger-ui.html` to read the complete API documentation.
