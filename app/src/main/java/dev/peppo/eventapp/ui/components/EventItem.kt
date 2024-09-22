package dev.peppo.eventapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.peppo.eventapp.ui.theme.EventAppTheme

@Composable
fun EventItem(
    name: String,
    mediaCover: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = mediaCover,
            contentDescription = "test",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(164.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(
            modifier = modifier
                .height(12.dp)
        )
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EventItemPreview() {
    EventAppTheme {
        EventItem(
            name = "test",
            mediaCover = "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/event/dos-dicoding_ignite_path_to_mobile_engineering_mc_130824104459.png"
        )
    }
}