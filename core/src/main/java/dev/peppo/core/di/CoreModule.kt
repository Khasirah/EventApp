package dev.peppo.core.di

import androidx.room.Room
import dev.peppo.core.data.local.room.EventRoomDatabase
import dev.peppo.core.data.remote.retrofit.ApiService
import dev.peppo.core.data.repository.EventsRepository
import dev.peppo.core.domain.repository.IEventRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<EventRoomDatabase>().eventDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EventRoomDatabase::class.java, "Event.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val repositoryModule = module {
    single<IEventRepository> { EventsRepository(get(), get()) }
}