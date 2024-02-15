package domain.usecases

import presentation.models.WishlistDto

internal class UserWishlistUseCase(private val userRepository: domain.UserDataRepository) {
    suspend fun get(userId: String): WishlistDto {
        val offers = userRepository.getUserWishList(userId)
        return WishlistDto(offers)
    }

    suspend fun put(userId: String, wishlistDTO: WishlistDto): WishlistDto {
        val offers = userRepository.updateUserWishlist(userId, wishlistDTO.offers)
        return WishlistDto(offers)
    }

}
