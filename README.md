# UnivagoBookingAPI

"Hotel? No. Room? Like... one single room? Univago!"

Univago is a booking API that allows an application to check for room availability, place a reservation, cancel it or modify it.

Univago is a REST API written in Java using Spring Boot and Jersey. It acts as a black box that can be accessed with HTTP requests, there is no frontend interface.

## Installation

Download the project, open it with an IDE then run it.

## Usage

Open a web browser and go to localhost:8080 to see the Swagger UI which contains the documentation and where you can send test requests.

## Shortcuts

* As there is no databases, the API generate a list of test bookings, every interaction with the API interact with this list which reset every time the application restart.
* The dates follows the dd/MM/yyyy pattern and there is no verification that user requests follow this pattern or not.
* We assume that every first name-last name combination is unique, meaning that people don't share the same first name and last name.
* No hours/minutes management has been made, a reservation lasts at least from 00:00 to 23:59 
* There is no unit testing.
