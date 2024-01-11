package com.example.chat.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val id:String= ObjectId().toString(),
    val senderId:String,
    val roomId:String,
    val content:String,
    val timestamp:Long,
)