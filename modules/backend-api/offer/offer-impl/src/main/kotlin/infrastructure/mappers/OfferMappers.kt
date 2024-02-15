package infrastructure.mappers

import domain.Offer
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import presentation.dto.OfferDto
import utils.DtoDomainConverter

@Mapper
internal interface OfferDtoConverter: DtoDomainConverter<Offer, OfferDto> {

    companion object {
        val INSTANCE: OfferDtoConverter = Mappers.getMapper(OfferDtoConverter::class.java)
    }

    override fun convertToDto(obj: Offer): OfferDto
    @Mappings(
        Mapping(target = "id", source = "offerDto.id", defaultExpression = "java(java.util.UUID.randomUUID().toString())"),
        Mapping(target = "postedTime", expression = "java(java.time.LocalDateTime.now())"),
        Mapping(target = "category", expression = "java(???CategoryApi???)"),
        Mapping(target = "geoPoint", expression = "java(new GeoPoint(java.util.Arrays.asList(offerDto.coordinates.longitude, offerDto.coordinates.latitude)))")
    )
    override fun convertToModel(objDto: OfferDto): Offer

}

