package dev.peppo.eventapp.di

import dev.peppo.core.domain.usecase.EventInteractor
import dev.peppo.core.domain.usecase.EventUseCase
import dev.peppo.eventapp.ui.screen.detail.DetailEventViewModel
import dev.peppo.eventapp.ui.screen.favourite.FavouriteViewModel
import dev.peppo.eventapp.ui.screen.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EventUseCase> { EventInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailEventViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
}