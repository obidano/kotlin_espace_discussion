package com.odc.espacediscussion.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.LocalAppCtxt
import com.odc.espacediscussion.R
import com.odc.espacediscussion.models.EspaceModel
import com.odc.espacediscussion.utils.fromHex
import com.odc.espacediscussion.vm.EspaceVM
import com.odc.espacediscussion.vm.UserVM
import com.odc.espacediscussion.widgets.InputText

@Composable
fun EspaceFormPage(userVM: UserVM, espaceVm: EspaceVM) {
    val envoieDonneesVersAPI: (data: EspaceModel) -> Unit = { data ->

    }

    EspaceFormPageBody()
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EspaceFormPageBody() {
    Scaffold(backgroundColor = Color.fromHex("f8f9fa"),
        topBar = { EspaceFormAppBar() }) {
        val context = LocalContext.current
        val nom = remember {
            mutableStateOf("")
        }
        val description = remember {
            mutableStateOf("")
        }

        Column(Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(R.drawable.community), "")
            Spacer(modifier = Modifier.height(100.dp))

            InputText(value = nom.value,
                label = "Nom de votre Nouvelle Espace",
                imageVector = Icons.Outlined.LocalLibrary,
                backgroundColor = "edede9") {
                nom.value = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            InputText(value = description.value,
                label = "Description",
                imageVector = Icons.Outlined.Description,
                backgroundColor = "edede9") {
                description.value = it
            }
            Column(Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Bottom) {
                FormButton(text = "Valider") {
                    Toast.makeText(context, "Formulaire valide ou non", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


@Composable
fun EspaceFormAppBar() {
    val appCtx = LocalAppCtxt.current
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { appCtx.retourArriere() }) {
                Icon(Icons.Rounded.ArrowBackIos, "")
            }

        },
        title = { Text("Creer votre Espace de discussion", fontSize = 15.sp) },
    )
}

@Composable
fun FormButton(
    modifier: Modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 10.dp),
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {

    Button(
        onClick = onClick, enabled = enabled, modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        elevation = ButtonDefaults.elevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text, modifier = Modifier.padding(horizontal = 35.dp), color = Color.White)
    }

}

@Composable
@Preview
fun PreviewEspaceFormPage() {
    EspaceFormPageBody()
}

