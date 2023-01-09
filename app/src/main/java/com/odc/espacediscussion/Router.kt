package com.odc.espacediscussion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.odc.espacediscussion.interfaces.IAppCtxt
import com.odc.espacediscussion.pages.ChatPage
import com.odc.espacediscussion.pages.EspaceFormPage
import com.odc.espacediscussion.pages.ListEspacesPage
import com.odc.espacediscussion.pages.LoginPage
import com.odc.espacediscussion.utils.RoutesUtils
import com.odc.espacediscussion.vm.EspaceVM
import com.odc.espacediscussion.vm.UserVM

val LocalAppCtxt = staticCompositionLocalOf<IAppCtxt> {
    object : IAppCtxt {
        override fun retourArriere() = Unit
        override fun naviguer(route: String) = Unit
        override val connectedUSer = null
    }
}

@Composable
fun Router(userVM: UserVM, espaceVm: EspaceVM) {
    val navCtrl = rememberNavController()

    val navigationFn = object : IAppCtxt {
        override val connectedUSer = userVM.userConnecte.value

        override fun retourArriere() {
            navCtrl.popBackStack()
        }

        override fun naviguer(route: String) {
            navCtrl.navigate(route)
        }
    }

    CompositionLocalProvider(LocalAppCtxt provides navigationFn) {
        NavHost(navController = navCtrl, startDestination = RoutesUtils.Login.name) {

            // page authentification
            composable(RoutesUtils.Login.name) {
                LoginPage(userVM)
            }

            // liste des espaces de doscussion
            composable(RoutesUtils.Espaces.name) {
                ListEspacesPage(userVM, espaceVm)
            }

            // creer un espace de discussion
            composable(RoutesUtils.EspaceForm.name) {
                EspaceFormPage(userVM, espaceVm)
            }

            composable(
                RoutesUtils.ChatPage.name+ "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                ChatPage(it.arguments, userVM, espaceVm)
            }
        }
    }


}