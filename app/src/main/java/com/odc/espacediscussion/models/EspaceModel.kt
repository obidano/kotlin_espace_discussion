package com.odc.espacediscussion.models

data class EspaceModel(
    val ID: Int?, val name: String, val description: String,
    val creator_id: Int, val countMessage: Int = 0,
)
