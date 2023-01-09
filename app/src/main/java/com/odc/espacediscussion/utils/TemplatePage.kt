package com.odc.espacediscussion.utils

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.LocalAppCtxt

@Composable
fun BasePage() {
    BasePageBody()
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BasePageBody() {
    Scaffold(topBar = { BaseAppBar() }) {

    }
}


@Composable
fun BaseAppBar() {
    val appCtx = LocalAppCtxt.current
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { appCtx.retourArriere() }) {
                Icon(Icons.Rounded.ArrowBackIos, "")
            }

        },
        title = { Text("Titre", fontSize = 15.sp) },
    )
}

@Composable
@Preview
fun PreviewBasePage() {
    BasePageBody()
}

