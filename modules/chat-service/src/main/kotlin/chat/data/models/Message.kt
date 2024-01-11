package com.example.chat.data.models


import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class Message(
    @BsonId
    val id:String= ObjectId().toString(),
    val senderId:String,
    val roomId:String,
    val content:String,
    val timestamp:Long,
)