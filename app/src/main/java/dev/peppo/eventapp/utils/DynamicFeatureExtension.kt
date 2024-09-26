package dev.peppo.eventapp.utils

import androidx.compose.runtime.Composable

@Composable
fun DFFavourite(navigateToDetail: (Int) -> Unit) {
    DynamicFeatureUtils.favourite(navigateToDetail)
}