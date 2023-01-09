package com.odc.espacediscussion.vm

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.odc.espacediscussion.depot.EspaceDepot
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.MessageModel
import com.odc.espacediscussion.utils.FakeData
import com.odc.espacediscussion.utils.SocketUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject

@HiltViewModel
class EspaceVM @Inject constructor(val depot: EspaceDepot, val socketUtils: SocketUtils) :
    ViewModel() {
    val listeEspace = mutableStateOf<List<EspaceModel>>(FakeData.espaces)
    val listeMessage = mutableStateMapOf<String, List<MessageModel>>()

    fun socketConnection() {
        socketUtils.startConnection()
        socketUtils.socket?.let {
            it.on(Socket.EVENT_CONNECT) {
                socketUtils.showNotifs("Connecté au Serveur ODC")
                Log.d("EVENT_CONNECT", ": Connect socket")
            }

            it.on("new_espace") {
                Log.d("new_espace", "${it[0]}")
            }

            it.on("new_message") {
                Log.d("new_message", "${it[0]}")
            }
        }
    }

    fun souscrire(espaceID: String) {
        socketUtils.socket?.emit("souscrire", espaceID)
    }


}