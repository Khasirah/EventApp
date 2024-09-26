package dev.peppo.eventapp.factory

object DynamicFeaturePackageFactory {
    private const val PACKAGE = "dev.peppo.eventapp."

    object Favourite {
        const val FAVOURITE_SCREEN = PACKAGE.plus("favourite.FavouriteScreenKt")
        const val COMPOSE_METHOD_NAME = "FavouriteScreen"
    }
}