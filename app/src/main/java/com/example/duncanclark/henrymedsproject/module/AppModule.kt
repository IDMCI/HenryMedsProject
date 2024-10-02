package com.example.duncanclark.henrymedsproject.module

import android.app.Application
import androidx.room.Room
import com.example.duncanclark.henrymedsproject.datasource.database.UserScheduleDatabase
import com.example.duncanclark.henrymedsproject.datasource.mapper.StoredMapper
import com.example.duncanclark.henrymedsproject.datasource.remote.UserScheduleDataSourceImpl
import com.example.duncanclark.henrymedsproject.datasource.repository.UserScheduleRepositoryImpl
import com.example.duncanclark.henrymedsproject.domain.usecase.ExampleGetScheduleUseCase
import com.example.duncanclark.henrymedsproject.domain.usecase.ExampleGetScheduleUseCaseImpl
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCase
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSystemClock(): Clock = Clock.System

    @Provides
    fun provideTimeZone(): TimeZone = TimeZone.currentSystemDefault()

    @Provides
    @Reusable
    fun provideSetAvailabilityUseCaseImpl(
        userScheduleRepositoryImpl: UserScheduleRepositoryImpl
    ): SetAvailabilityUseCase {
        return SetAvailabilityUseCaseImpl(userScheduleRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideRoomScheduleDatabase(app: Application): UserScheduleDatabase {
        return Room.databaseBuilder(
            app,
            UserScheduleDatabase::class.java,
            UserScheduleDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Reusable
    fun provideExampleUseCaseImpl(
        userScheduleRepositoryImpl: UserScheduleRepositoryImpl
    ): ExampleGetScheduleUseCase {
        return ExampleGetScheduleUseCaseImpl(userScheduleRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideUserScheduleDataSourceImpl(): UserScheduleDataSourceImpl {
        return UserScheduleDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideUserScheduleRepositoryImpl(
        dataSource: UserScheduleDataSourceImpl,
        database: UserScheduleDatabase,
        mapper: StoredMapper,
    ): UserScheduleRepositoryImpl {
        return UserScheduleRepositoryImpl(dataSource, database.userScheduleDao, mapper)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("<enter base url here>")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            // Might need to add additional/different logging interceptors here
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}