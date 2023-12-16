package com.martianmode.studycase.presentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.martianmode.studycase.presentation.component.BottomBar
import com.martianmode.studycase.presentation.component.ToolBar
import com.martianmode.studycase.presentation.component.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(navController : NavHostController,title : String?,bottomBarEnabled : Boolean? = true,topBarEnabled : Boolean? = true,content: (@Composable () -> Unit)? = {}) {

    Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.primary), topBar = { if(topBarEnabled == true) TopBar(title ?: "")}, bottomBar = { if(bottomBarEnabled == true) BottomBar(navController) else ToolBar(navController) }) {
        Surface(
            Modifier
                .padding(it),
            color = MaterialTheme.colorScheme.primary) {
            content?.invoke()
        }
    }
}