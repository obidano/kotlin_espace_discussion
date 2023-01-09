package com.odc.espacediscussion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.odc.espacediscussion.ui.theme.EspaceDiscussionODCTheme
import com.odc.espacediscussion.vm.EspaceVM
import com.odc.espacediscussion.vm.UserVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FileUtils.readFakeEspaces(this)
        setContent {
            val espaceVm: EspaceVM = viewModel()
            val userVM: UserVM = viewModel()

            EspaceDiscussionODCTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Router(userVM, espaceVm)
                }
            }
        }
    }
}
