package com.example.features.offer.domain.usecases

import com.example.features.offer.domain.CategoryService
import com.example.features.offer.domain.Offer
import com.example.features.offer.domain.OfferRepository
import com.example.features.offer.presentation.dto.OfferDto

class CreateOfferUseCase(private val offerRepository: OfferRepository, private val categoryService: CategoryService) {
    suspend operator fun invoke(offerDto: OfferDto, sellerId: String): Offer {
        val category = categoryService.getCategoryById(offerDto.categoryId)

        val offer = offerDto.toDomainModel(category, sellerId)

        return offerRepository.saveOffer(offer)
    }
}

