package dev.peppo.eventapp.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.peppo.eventapp.R
import dev.peppo.eventapp.data.remote.response.Event
import dev.peppo.eventapp.ui.common.UiState
import dev.peppo.eventapp.utils.DataMapper
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    eventId: Int,
    detailEventViewModel: DetailEventViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    var isFavouriteEvent = false
    detailEventViewModel.isFavouriteEvent.collectAsState().value.let {result ->
        when(result) {
            is UiState.Loading -> {
                detailEventViewModel.isFavouriteEvent(eventId)
            }
            is UiState.Success -> {
                isFavouriteEvent = result.data
            }
            is UiState.Error -> {}
        }
    }

    detailEventViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { result ->
        when(result) {
            is UiState.Loading -> {
                detailEventViewModel.getDetailEvent(eventId)
            }
            is UiState.Success -> {
                DetailContent(
                    event = result.data.event,
                    onBackClick = navigateBack,
                    isFavouriteEvent = isFavouriteEvent,
                    updateIsFavouriteEvent = {
                        detailEventViewModel.updateIsFavouriteEvent(
                            DataMapper.mapResponseToDomain(result.data.event),
                            isFavouriteEvent
                        )
                    },
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    event: Event,
    onBackClick: () -> Unit,
    isFavouriteEvent: Boolean,
    updateIsFavouriteEvent: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
        ) {
            AsyncImage(
                model = event.mediaCover,
                contentDescription = event.name,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .height(375.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                modifier = modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
            Icon(
                imageVector = if (isFavouriteEvent) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = stringResource(id = R.string.favourite_button),
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
                    .clickable { updateIsFavouriteEvent() }
            )
        }
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = event.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Text(
                text = event.summary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}