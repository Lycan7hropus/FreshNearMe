package com.example.utils.exceptions

class OfferCreationException(message: String) : Exception(message)
class ValidationException(message: String) : Exception(message)

class UserNotFoundException(userId: String) : RuntimeException("User with ID $userId not found.")