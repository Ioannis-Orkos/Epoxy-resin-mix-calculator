package com.ioannisorkos.epoxyresinmixcalculator.di

import android.app.Application
import androidx.room.Room
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.data_source.ResinDatabase
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.repository.ResinRepositoryImpl
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository.ResinRepository
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case.AddResin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case.GetResins
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case.ResinUseCases
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
    fun provideResinDatabase(app: Application): ResinDatabase {
        return Room.databaseBuilder(
            app,
            ResinDatabase::class.java,
            ResinDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideResinRepository(db: ResinDatabase): ResinRepository {
        return ResinRepositoryImpl(db.resinDao)
    }


    @Provides
    @Singleton
    fun provideResinUseCases(repository: ResinRepository): ResinUseCases {
        return ResinUseCases(
            getResins = GetResins(repository),
            addResin = AddResin(repository),
        )
    }

}