package dev.peppo.eventapp.ui.screen.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.peppo.eventapp.domain.model.Event
import dev.peppo.eventapp.ui.common.UiState
import dev.peppo.eventapp.ui.components.DataEmpty
import dev.peppo.eventapp.ui.components.EventItem
import dev.peppo.eventapp.ui.screen.ViewModelFactory

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    favouriteViewModel: FavouriteViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetail: (Int) -> Unit,
) {
    favouriteViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { result ->
        when(result) {
            is UiState.Loading -> {
                favouriteViewModel.getAllFavouriteEvent()
            }
            is UiState.Success -> {
                if (result.data.isEmpty()) {
                    DataEmpty(modifier = modifier)
                } else {
                    FavouriteContent(
                        favouriteEvent = result.data,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavouriteContent(
    favouriteEvent: List<Event>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(favouriteEvent) { data ->
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