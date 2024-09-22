package dev.peppo.eventapp

import android.app.Application
import dev.peppo.eventapp.core.di.databaseModule
import dev.peppo.eventapp.core.di.networkModule
import dev.peppo.eventapp.core.di.repositoryModule
import dev.peppo.eventapp.di.useCaseModule
import dev.peppo.eventapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}