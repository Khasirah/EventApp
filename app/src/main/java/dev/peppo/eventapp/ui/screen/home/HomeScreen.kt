package dev.peppo.eventapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.peppo.core.data.remote.response.EventResponse
import dev.peppo.eventapp.ui.common.UiState
import dev.peppo.eventapp.ui.components.EventItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    homeViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { result ->
        when (result) {
            is UiState.Loading -> {
                homeViewModel.getAllEvent()
            }

            is UiState.Success -> {
                HomeContent(
                    eventResponse = result.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    eventResponse: EventResponse,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(eventResponse.listEvents) { data ->
            EventItem(
                name = data.name,
                mediaCover = data.mediaCover,
                modifier = modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}