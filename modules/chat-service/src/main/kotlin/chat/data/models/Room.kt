package com.example.chat.data.models

import kotlinx.serialization.SerialName

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class Room(
    @BsonId
    @SerialName("room_id") val roomId:String=ObjectId().toString(),
    @SerialName("user_ids") val userIds:List<String>,
    @SerialName("product_id") val productId:String
)