# Backend Case Study for an E-commerce Firm
Containerized REST API for performing basic CRUD on listings. 

## Overview
✅ All CRUD operations
<br/>
✅ Bad words filtering
<br/>
✅ H2 Database
<br/>
✅ Dockerized
<br/>
✅ Swagger UI documentation
<br/>
✅ Logging requests that take more than 5ms to execute.
<br/>
✅ Return status update history for a given listing
<br/>
🚫 Unit tests
<br/>
🚫 Integration tests

## This repository contains a listings REST API that allows users to:

- `GET /dashboard/listings` Get all listings

- `GET /dashboard/listings/{id}` Get a listing specified by an id.

- `GET /dashboard/listings/stats/status` Get statistics for listing status

- `GET /dashboard/listings/logs/{listingId}` Get status update history for a given listing

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
<img width="1145" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/6a479ac0-13ca-4e06-8666-112b5760b79c">

### `GET /dashboard/listings/{id}` Get a listing specified by an id.
<img width="1403" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/ac02a1ba-ed00-4a23-b4c9-959266408946">

### `GET /dashboard/listings/stats/status` Get statistics for listing status
<img width="1402" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/d5ded2a3-fbfa-4aec-a460-b919baadb79b">


### `GET /dashboard/listings/logs/{listingId}` Get status update history for a given listing 
<img width="1402" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/70516735-5b77-4889-9eb5-b3b1e400b298">

### `PUT /dashboard/listings/{id}` Update a listing's status (other properties are assumed to be immutable)
- If you modify a "PENDING_APPROVAL" state to "ACTIVE", you get an OK response.
<img width="1403" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/c072c685-7e75-4268-a2e2-c01921bb585b">

- If you attempt to modify a "DUPLICATE" to "ACTIVE", you'll get an error response. 
<img width="1403" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/89708730-181f-48d5-9536-6d0f2f219de3">

### `POST /dashboard/listings` Add new listing by providing a valid title, description and category.
- You can select from the predefined sample objects and check the responses for different types of categories, titles and descriptions.
- If you duplicate an entry, the listing will be assigned "DUPLICATE" status and modifying it is **not** allowed.
- When a Merchandise listing is issued, the initial status will be set to 'ACTIVE'. Otherwise, it will be set to 'PENDING_APPROVAL'.
<img width="1391" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/c94061a0-1876-454a-b8ec-8115695069e1">

### Log Requests that take more than 5ms to execute.
<img width="1401" alt="image" src="https://github.com/ceydas/ecommerce-listings/assets/26047050/61df2e02-a69a-456b-a348-424a6b0768e1">

# Additional Resources / Tools
- https://github.com/google/re2j - A linear time regex matcher for Java

