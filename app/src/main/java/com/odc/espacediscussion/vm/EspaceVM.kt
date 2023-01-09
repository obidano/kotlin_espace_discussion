package com.odc.espacediscussion.vm

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.odc.espacediscussion.depot.EspaceDepot
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.MessageModel
import com.odc.espacediscussion.utils.FakeData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EspaceVM @Inject constructor(val depot: EspaceDepot) : ViewModel() {
    val listeEspace = mutableStateOf<List<EspaceModel>>(FakeData.espaces)
    val listeMessage = mutableStateMapOf<String, List<MessageModel>>()

}