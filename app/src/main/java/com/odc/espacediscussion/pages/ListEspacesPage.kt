@file:OptIn(ExperimentalMaterialApi::class)

package com.odc.espacediscussion.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddComment
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.LocalAppCtxt
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.ui.theme.Orange1
import com.odc.espacediscussion.utils.FakeData
import com.odc.espacediscussion.utils.RoutesUtils
import com.odc.espacediscussion.utils.fromHex
import com.odc.espacediscussion.vm.EspaceVM
import com.odc.espacediscussion.vm.UserVM

@Composable
fun ListEspacesPage(userVM: UserVM, espaceVm: EspaceVM) {
    val espaces = espaceVm.listeEspace.value
    EspacesPageBody(espaces)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EspacesPageBody(espaces: List<EspaceModel>) {
    Scaffold(topBar = { AppBar(espaces) },
        backgroundColor = Color.fromHex("f8f9fa")) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(espaces) { espace ->
                EspacesItem(espace)
            }
        }
    }
}

@Composable
fun AppBar(espaces: List<EspaceModel>) {
    val appCtx = LocalAppCtxt.current
    TopAppBar(backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { appCtx.retourArriere() }) {
                Icon(Icons.Rounded.ArrowBackIos, "")
            }

        },
        title = { Text("Espaces de Discussion (${espaces.size})", fontSize = 15.sp) },
        actions = {
            IconButton(
                onClick = { appCtx.naviguer(RoutesUtils.EspaceForm.name) }) {
                Icon(Icons.Rounded.AddComment, "", tint = Color.fromHex(Orange1))
            }
        }
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100)
fun EspacesItem(data: EspaceModel = FakeData.espaces[0]) {
    val appCtx = LocalAppCtxt.current

    Card(modifier = Modifier.padding(5.dp), onClick = {
        appCtx.naviguer(RoutesUtils.ChatPage.name + "/${data.ID}")
    }, shape = RoundedCornerShape(5.dp)) {
        Row(Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top) {
            Box(modifier = Modifier.weight(.2f)) {

                IconButton(onClick = { },
                    modifier = Modifier
                        .then(Modifier.size(50.dp))
                        .border(2.dp,
                            if (data.creator_id == 1) Color.fromHex(Orange1) else Color.Transparent,
                            shape = CircleShape)
                ) {
                    Icon(imageVector = Icons.Rounded.People, "")
                }
            }
            Box(modifier = Modifier
                .weight(.8f)
                .heightIn(50.dp, 80.dp)) {
                Column(Modifier.fillMaxWidth()) {
                    Text(text = data.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = data.description,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis)
                }
            }
            Box(modifier = Modifier.weight(.2f), contentAlignment = Alignment.CenterEnd) {
                Column {
                    Icon(imageVector = Icons.Rounded.ChatBubble, "", tint = Color.Gray)
                    Text(text = "${data.countMessage}")

                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewEspacesPage() {
    EspacesPageBody(FakeData.espaces)
}



