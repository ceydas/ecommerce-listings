# Backend Case Study for an E-commerce Firm
Containerized REST API for performing basic CRUD on listings.

## Overview - Basic Requirements
✅ All CRUD operations
<br/>
✅ Bad words filtering
<br/>
✅ H2 Database
<br/>
🚫 Unit tests

## Overview - Bonus
✅ Dockerized
<br/>
✅ Swagger UI documentation
<br/>
✅ Logging
<br/>
✅ Return all status updates for a given listing
<br/>
🚫 Integration tests

## This repository contains a listings REST API that allows users to:

- `GET /dashboard/listings` Get all listings
- `GET /dashboard/listings/{id}` Get a listing specified by an id.
- `GET /dashboard/listings/stats/status` Get statistics for listing status
- `GET /dashboard/listings/logs/{listingId}` Get all status updates for a given listing 
- `PUT /dashboard/listings/{id}` Update a listing's status (other properties are assumed to be immutable)
  - `PUT /dashboard/listings/{id}/activate` Activate a listing's status (only applicable for pending_approval)
  - `PUT /dashboard/listings/{id}/deactivate` Deactivate a listing's status (only applicable for active and pending_approval)
- `POST /dashboard/listings` Add new listing by providing a valid title, description and category.

- Log all requests that take more than 5ms to execute. 

## Quick Start Using Docker 
- Clone this repository.
- Run `docker-compose up --build`
- Go to `http://localhost:8080/swagger-ui.html`

## Quick Start Using Maven
- Clone this repository.
- Run `mvn clean install`
- Start the project.
- Go to `http://localhost:8080/swagger-ui.html`

## Swagger-UI Documentation
### `GET /dashboard/listings` Get all listings

### `GET /dashboard/listings/{id}` Get a listing specified by an id.

### `GET /dashboard/listings/stats/status` Get statistics for listing status

### Bonus: `GET /dashboard/listings/logs/{listingId}` Get all status updates for a given listing 

### `PUT /dashboard/listings/{id}` Update a listing's status (other properties are assumed to be immutable)
- If you modify a "PENDING_APPROVAL" state to "ACTIVE", you get an OK response.
- If you attempt to modify a "DUPLICATE" to "ACTIVE", you'll get an error response. 

### `POST /dashboard/listings` Add new listing by providing a valid title, description and category.
- You can select from the predefined sample objects and check the responses for different types of categories, titles and descriptions.
- If you duplicate an entry, the listing will be assigned "DUPLICATE" status and modifying it is **not** allowed.
- When a Merchandise listing is issued, the initial status will be set to 'ACTIVE'. Otherwise, it will be set to 'PENDING_APPROVAL'.

### Bonus: Log Requests that take more than 5ms to execute.

# Additional Resources / Tools
- https://github.com/google/re2j - A linear time regex matcher for Java

