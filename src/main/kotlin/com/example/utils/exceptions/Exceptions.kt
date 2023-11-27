package com.example.utils.exceptions

class OfferCreationException(message: String) : Exception(message)
class ValidationException(message: String) : Exception(message)
class UserSavingException(message: String) : Exception(message)
class UserAlreadyExistsException(message: String) : Exception(message)
class InternalErrorException(message: String) : Exception(message)
class ResourceAccessDenied(message: String) : Exception(message)
class InvalidTokenException(message: String) : Exception(message)