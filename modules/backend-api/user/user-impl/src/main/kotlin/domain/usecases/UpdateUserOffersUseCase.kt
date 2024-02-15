package domain.usecases

import domain.UserDataRepository
import presentation.models.PostedOffersDto
import presentation.models.WishlistDto

internal class UpdateUserOffersUseCase(private val userRepository: UserDataRepository) {
    suspend fun invoke(userId: String, wishlistDTO: WishlistDto): PostedOffersDto {
        val offers = userRepository.updateUserOffers(userId, wishlistDTO.offers)
        return PostedOffersDto(offers)
    }
}