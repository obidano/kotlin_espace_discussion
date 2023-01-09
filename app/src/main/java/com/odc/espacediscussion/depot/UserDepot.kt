package com.odc.espacediscussion.depot

import com.odc.espacediscussion.api.UserAPI
import com.odc.espacediscussion.models.UserAPIResponseModel
import com.odc.espacediscussion.models.UserModel
import javax.inject.Inject

class UserDepot @Inject constructor(val api: UserAPI) {

    suspend fun creerCompte(data: UserModel): UserAPIResponseModel? {
        val reponse = api.apiAuthentifier(data)
        return reponse.body()

    }

}