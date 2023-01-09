package com.odc.espacediscussion.api

import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.ResponseAPIModel
import com.odc.espacediscussion.models.UserAPIResponseModel
import com.odc.espacediscussion.models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("/api/user")
    suspend fun apiAuthentifier(@Body data: UserModel): Response<UserAPIResponseModel>
}