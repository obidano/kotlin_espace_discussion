package com.odc.espacediscussion.models

data class MessageModel(
    val message: String,
    val time: String,
    val sender: Int,
    val senderName: String,
    val espaceID: Int?,
    val espaceName: String?,
)
