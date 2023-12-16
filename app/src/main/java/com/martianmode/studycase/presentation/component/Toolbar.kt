package com.martianmode.studycase.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.martianmode.studycase.R

@Composable
fun ToolBar(navController: NavHostController) {
    Box(
        Modifier
            .clickable { navController.popBackStack() }
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(16.dp)) {
        Box(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .width(48.dp)
                .height(48.dp)
                .background(MaterialTheme.colorScheme.onSecondary)) {
            Image(modifier = Modifier.align(Alignment.Center), painter = painterResource(id = R.drawable.back), contentDescription = "Back")
        }
    }
}