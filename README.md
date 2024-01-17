
[<img alt="forthebadge" height="24" src="https://forthebadge.com/images/featured/featured-contains-cat-gifs.svg"/>](https://forthebadge.com)
![freshood-back.png](assets%2Ffreshood-back.png)

## Detailed Project Overview

This Kotlin-based web application serves as an advanced platform for posting and managing offers. It allows users to
create, manage, and interact with a wide range of offers, while also providing functionalities for user authentication
and profile management.

### Why fresh near me? 
According to Michigan state university, local food has several benefits superiority over market food

- **Full Flavor**: Locally grown foods are harvested at peak ripeness, ensuring maximum flavor. This allows farmers to focus on flavor rather than durability for shipping.

- **Rich in Nutrients**: Local foods, having a shorter time from harvest to table, are likely to retain more nutrients.

- **Boosts Local Economy**: Spending on local foods contributes significantly to the local economy. Research of local economic flows has demonstrated that the food system in Michigan [contributed $680 million to state earnings in 2011.](https://www.canr.msu.edu/cea/uploads/files/valuingmilocalfoodsystem.pdf)

- **Environmental Benefits**: Buying local supports farmland maintenance and reduces the environmental impact of long-distance food transport.

- **Food Safety**: Shorter supply chains mean fewer chances for contamination, making local foods safer.

- **Transparency**: Direct interaction with local growers provides insights into food production practices, enhancing consumer knowledge.


According to Iowa State University [article](https://www.cals.iastate.edu/news/2022/research-shows-significant-environmental-benefits-local-food-production), large-scale production model had significantly greater global warming potential in all stages of food production and distribution than that of the medium- and small-scale production models. 
Typical **local vegetable production** produces **less than half the emissions** and use 10% of the water than that of conventional food systems.

### Core Technologies and Frameworks

<img alt="img_1.png" src="img_1.png" width="32"/>
<img alt="img_2.png" src="img_2.png" width="32"/>
<img alt="img_3.png" src="img_3.png" width="32"/>
<img alt="img_4.png" src="img_4.png" width="32"/>
<img alt="img_5.png" src="img_5.png" width="32"/>

- **Kotlin**: Primary programming language
- **Ktor**: Asynchronous web framework used for building servers and clients in connected systems.
- **MongoDB**: For flexible and scalable infra storage.
- **Koin**: Lightweight dependency injection framework.
- **Keycloak**: Authentication service

### Architectural Patterns and Key Design Choices

- **Clean Architecture**: The application adheres to the principles of Clean Architecture, ensuring the separation of
  concerns and independence of different layers like infra, domain, and presentation.
- **Repository Pattern**: This pattern is evident in classes like `CategoryRepositoryImpl` and `OfferRepositoryImpl`,
  providing a clean abstraction layer over infra access mechanisms.
- **Factory Pattern**: Used in classes like `CategoryFactory`, simplifying object creation and enhancing flexibility.
- **Use Case/Interactor Pattern**: Each business logic operation is encapsulated in a use case (
  e.g., `CreateCategoryUseCase`, `GetUserOffersUseCase`), promoting a clean separation of concerns and making the code
  more readable and testable.


![overall.svg](assets%2Foverall.svg)

## User Authentication Process

1. **User Login and Registration**:
    - Users can log in or register through a traditional website form or via a REST API endpoint.
    - This process is managed by Keycloak.


2. **Credential Storage**:
    - After successful authentication, Keycloak stores the user's credentials in its database.


3. **User Registration Notification**:
    - When a new user registers, the Backend-API receives a notification.
    - Keycloak sends an HTTP request with the user's data to the specified endpoint, requiring the creation of a
      custom [SPI](https://github.com/Lycan7hropus/keycloak-listener-kotlin).


4. **Storing User Info in the Database**:
    - The Backend-API saves the user's information in the application's database after successful validation and
      authorization.
    - This information is used for personalizing the user experience and managing the account.


5. **Token Validation**:
    - The Backend-API and chat service validate the token by sending it to the Keycloak server.
    - The server provides information about the user or returns a response if the token is invalid.

![authflow.svg](assets%2Fauthflow.svg)

## Detailed Breakdown of Functionalities by Module

### User Module Functionalities

- **Retrieve Basic User Information**: Gets basic details about a user, specified by the user ID.

- **Fetch User Offers**: Retrieves offers associated with a specific user, identified by their user ID.

- **Get Detailed User Info (Authenticated)**: Obtains comprehensive information about the currently authenticated user.

- **Retrieve User Wishlist (Authenticated)**: Gets the wishlist belonging to the currently authenticated user.

- **Update User Wishlist (Authenticated)**: Allows the currently authenticated user to update their wishlist.

- **Update User Offers (Authenticated)**: Enables the currently authenticated user to update their offers.

### Categories Module Functionalities

- **Create Category (Admin Only)**: Allows administrators to create new categories by submitting category data.

- **Update Category (Admin Only)**: Enables administrators to update existing categories. The specific update logic is
  to be implemented.

- **List All Categories**: Retrieves a list of all available categories.

- **Get Category Details**: Fetches details of a specific category identified by its unique ID.
  Based on the provided Kotlin code for offer routes, here's a straightforward description of each route's functionality
  within the Offer Module:

### Offer Module Functionalities

- **List All Offers**: Retrieves a list of all offers, optionally filtered by category, distance, and coordinates.

- **Search Offers by Name**: Fetches offers based on a search query for offer names. Requires the query parameter '
  name'.

- **Get Single Offer by ID**: Retrieves details of a specific offer, identified by its ID.

- **Add New Offer (Authenticated)**: Allows authenticated users to add a new offer. Requires an `OfferDto` object in the request body.

- **Update Existing Offer (Authenticated)**: Enables authenticated users to update an existing offer specified by its ID. Requires an `OfferDto` object in
        the request body.


### Separate Chat Service

### Installation



[<img alt="forthebadge" src="https://forthebadge.com/images/badges/works-on-my-machine.svg" width="300"/>](https://forthebadge.com)

<img alt="img.png" src="img.png" width="300"/>