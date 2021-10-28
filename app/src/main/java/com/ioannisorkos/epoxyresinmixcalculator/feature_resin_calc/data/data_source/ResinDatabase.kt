package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.MixHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin

@Database(
    entities = [Resin::class,MixHistory::class],
    version = 1
)

abstract class ResinDatabase: RoomDatabase() {

    abstract val resinDao: ResinDao

    companion object {
        const val DATABASE_NAME = "resins_db"
    }
}