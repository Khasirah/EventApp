package dev.peppo.eventapp.ui.screen.detail

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.peppo.eventapp.R
import dev.peppo.eventapp.data.remote.response.Event
import dev.peppo.eventapp.di.Injection
import dev.peppo.eventapp.ui.common.UiState
import dev.peppo.eventapp.ui.screen.ViewModelFactory

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    eventId: Int,
    detailEventViewModel: DetailEventViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    detailEventViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { result ->
        when(result) {
            is UiState.Loading -> {
                detailEventViewModel.getDetailEvent(eventId)
            }
            is UiState.Success -> {
                DetailContent(
                    modifier = modifier,
                    event = result.data.event,
                    onBackClick = navigateBack
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
    onBackClick: () -> Unit
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