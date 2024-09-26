package dev.peppo.eventapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import dev.peppo.eventapp.factory.DynamicFeaturePackageFactory

object DynamicFeatureUtils {

    @Composable
    fun favourite(navigateToDetail: (Int) -> Unit): Boolean {
        return loadDF(
            navigateToDetail = navigateToDetail,
            className = DynamicFeaturePackageFactory.Favourite.FAVOURITE_SCREEN,
            methodName = DynamicFeaturePackageFactory.Favourite.COMPOSE_METHOD_NAME
        )
    }

    @Composable
    private fun loadDF(
        navigateToDetail: (Int) -> Unit,
        className: String,
        methodName: String,
        objectInstance: Any = Any()
    ): Boolean {
        val dfClass = loadClassByReflection(className)
        if (dfClass != null) {
            val composer = currentComposer
            val method = findMethodByReflection(
                dfClass,
                methodName
            )
            if (method != null) {
                val isMethodInvoke = invokeMethod(method, objectInstance, navigateToDetail, composer, 0)
                return isMethodInvoke
            } else {
                return false
            }
        } else {
            return false
        }
    }
}