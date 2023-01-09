package com.odc.espacediscussion.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.LocalAppCtxt
import com.odc.espacediscussion.R
import com.odc.espacediscussion.models.UserModel
import com.odc.espacediscussion.ui.theme.Orange1
import com.odc.espacediscussion.utils.fromHex
import com.odc.espacediscussion.vm.UserVM
import com.odc.espacediscussion.widgets.InputText
import com.odc.espacediscussion.widgets.TitleSection

@Composable
fun LoginPage(userVM: UserVM) {

    val connexionAPI: (data: UserModel) -> Unit = { d ->
        userVM.connecter(d)
    }


    LoginPageBody(connexionAPI)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginPageBody(connexionAPI: (data: UserModel) -> Unit) {
    val appCtx = LocalAppCtxt.current
    val context = LocalContext.current
    val identifiant = remember {
        mutableStateOf("")
    }
    Scaffold(backgroundColor = Color.fromHex("f8f9fa")) {
        Column(Modifier
            .fillMaxSize()
            .padding(24.dp)) {
            TitleSection("Espace de Discussion",
                "Partagez vos centres d'interêt")

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.weight(3f), verticalArrangement = Arrangement.Center) {
                InputText(value = identifiant.value,
                    label = "Saisir votre identifiant",
                    backgroundColor = Orange1) {
                    identifiant.value = it
                }
            }


            Spacer(modifier = Modifier.height(32.dp))

            Column(Modifier
                .weight(1f)
                .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Bottom) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Connectez-vous",
                        fontSize = 17.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Surface(
                        color = Color.Black,
                        shape = CircleShape,
                        elevation = 8.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Box {
                                IconButton(onClick = {
                                    connexionAPI(UserModel(identifiant = identifiant.value))
                                    //appCtx.changerpage(RoutesUtils.Espaces.name)
                                }) {

                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_round_arrow_forward),
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }

                            }
                        }
                    }
                }
            }

        }
    }
}


@Composable
@Preview
fun PreviewLoginPage() {
    LoginPageBody({})
}

