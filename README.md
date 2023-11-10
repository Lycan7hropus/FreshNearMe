# Project Readme: GeoLocation-Based Offer Service

## Overview

This project is a Ktor-based server application that provides a service for posting and searching for offers based on geolocation and category filtering. It uses MongoDB for data persistence.

## Features

- **Offer Posting**: Users can post offers with details such as name, category, price, and location.
- **Geospatial Queries**: Offers can be searched based on proximity to a given location and within a specified distance range.
- **Category Filtering**: Offers can be filtered by categories, which are scalable and can have nested sub-categories.

## Technology Stack

- **Backend Framework**: Ktor (Kotlin-based)
- **Database**: MongoDB with geospatial indexing capabilities
- **Serialization**: kotlinx.serialization for data serialization
- **Dependency Injection**: Koin for managing dependencies


## Examples of API Endpoints

1. **Post an Offer**
    - `POST /offers`
    - Body: Offer details including location.

2. **Get All Offers**
    - `GET /offers`
    - Query Parameters: `category`, `distance`, `coordinates`.

## Database Schema

- **Offer Collection**
    - Fields: `id`, `name`, `category`, `price`, `geoLocation`, etc.
    - Index: Geospatial index on `geoLocation`.
 basic guide to getting started with the project. You can expand it with more detailed instructions, configurations, and descriptions as needed.