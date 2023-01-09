package com.odc.espacediscussion.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odc.espacediscussion.depot.UserDepot
import com.odc.espacediscussion.models.UserAPIResponseModel
import com.odc.espacediscussion.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(val depot: UserDepot) : ViewModel() {
    val userConnecte = mutableStateOf<UserModel?>(null)
    val loading = mutableStateOf<Boolean?>(null)


    fun connecter(data: UserModel) {
        viewModelScope.launch {
            loading.value = true
            val reponse: UserAPIResponseModel? = depot.creerCompte(data)
            userConnecte.value = reponse?.user
            Log.d("", "connecter: ${reponse}")
            loading.value = false

        }
    }

}