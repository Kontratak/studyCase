package com.martianmode.studycase.presentation.locations.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.martianmode.studycase.data.model.locationDetailsResponse.LocationDetailsResponse

@Composable
fun LocationItem(model: LocationDetailsResponse?, onLocationSelected: (id : Int?) -> Unit) {
    Column(
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(24.dp,24.dp,0.dp,24.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable { onLocationSelected(model?.id) }) {
        Text(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),text = model?.name ?: "", style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)) {
            Text(text = "Type", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(text = model?.type ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
        Column(
            Modifier.fillMaxWidth()) {
            Text(text = "Dimension", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primaryContainer))
            Text(text = model?.dimension ?: "Unknown", style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        }
    }
}
