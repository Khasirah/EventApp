package dev.peppo.eventapp.favourite

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
import dev.peppo.core.domain.model.Event
import dev.peppo.eventapp.ui.common.UiState
import dev.peppo.eventapp.ui.components.DataEmpty
import dev.peppo.eventapp.ui.components.EventItem
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.loadKoinModules

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    loadKoinModules(favouriteModule)
    val favouriteViewModel: FavouriteViewModel = koinViewModel()
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