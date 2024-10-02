package com.example.duncanclark.henrymedsproject.module

import android.app.Application
import androidx.room.Room
import com.example.duncanclark.henrymedsproject.datasource.database.UserScheduleDatabase
import com.example.duncanclark.henrymedsproject.datasource.mapper.StoredMapper
import com.example.duncanclark.henrymedsproject.datasource.remote.UserScheduleDataSourceImpl
import com.example.duncanclark.henrymedsproject.datasource.repository.UserScheduleRepositoryImpl
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCase
import com.example.duncanclark.henrymedsproject.domain.usecase.SetAvailabilityUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
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
}