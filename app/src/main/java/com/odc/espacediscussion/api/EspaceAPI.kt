package com.odc.espacediscussion.api

import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.MessageAPIResponseModel
import com.odc.espacediscussion.models.ResponseAPIModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EspaceAPI {
    @GET("/api/espaces")
    suspend fun apiTousLesEspaces(): Response<List<EspaceModel>>

    @POST("/api/espaces")
    suspend fun apiCreateEspace(@Body data: EspaceModel): Response<MessageAPIResponseModel>
}