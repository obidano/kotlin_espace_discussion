package com.odc.espacediscussion.pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.LocalAppCtxt
import com.odc.espacediscussion.R
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.models.MessageModel
import com.odc.espacediscussion.utils.FakeData
import com.odc.espacediscussion.vm.EspaceVM
import com.odc.espacediscussion.vm.UserVM

@Composable
fun ChatPage(args: Bundle?, userVM: UserVM, espaceVm: EspaceVM) {
    val espaceID = args?.getInt("id") ?: 0
    val espace = espaceVm.listeEspace.value.find { i -> i.ID == espaceID }!!
    val messages = espaceVm.listeMessage["$espaceID"] ?: ArrayList<MessageModel>()
    ChatPageBody(espace, messages)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatPageBody(espace: EspaceModel, messages: List<MessageModel>) {
    Scaffold(topBar = { ChatAppBar(espace) }) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            ChatSection(Modifier.weight(1f), messages)
            EnvoiMessageSection()
        }
    }
}


@Composable
fun ChatAppBar(espace: EspaceModel) {
    val appCtx = LocalAppCtxt.current
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { appCtx.retourArriere() }) {
                Icon(Icons.Rounded.ArrowBackIos, "")
            }

        },
        title = { Text(espace.name, fontSize = 15.sp) },
    )
}

@Composable
fun ChatSection(
    modifier: Modifier = Modifier,
    messages: List<MessageModel>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        reverseLayout = true
    ) {
        items(messages) { msg ->
            MessageItem(msg)
            Divider(modifier=Modifier.padding(vertical = 10.dp))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EnvoiMessageSection() {
    val msgSaisi=remember{ mutableStateOf("")}
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        OutlinedTextField(
            placeholder = {
                Text("Message..")
            },
            value = msgSaisi.value,
            onValueChange = {
                msgSaisi.value = it
            },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send, ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.clickable {
                        //chats.add(Chat(message.value, "10:00 PM", true))
                        //message.value = ""
                    }
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        )
    }
}

@Composable
fun MessageItem(
    msg: MessageModel,
) {
    val isOut=msg.sender==1
    Column(
        modifier = Modifier.fillMaxWidth(),
       // horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isOut) MaterialTheme.colors.primary else Color(0xFF616161),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(
                text = msg.senderName,
                color = Color.White
            )
        }

        Text( text = msg.message,
            fontSize = 14.sp,
            color=Color.Black,
            modifier = Modifier.padding(start = 8.dp))
        Text(
            text = msg.time,
            fontSize = 12.sp,
            color=Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
@Preview
fun PreviewChatPage() {
    ChatPageBody(FakeData.espaces[0], messages = FakeData.messages)
}

