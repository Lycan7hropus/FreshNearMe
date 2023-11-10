package com.example.features.offer.presentation.dto

import com.example.models.Offer
import kotlinx.serialization.Serializable

@Serializable
class OffersDto (val offers: List<Offer> = listOf())