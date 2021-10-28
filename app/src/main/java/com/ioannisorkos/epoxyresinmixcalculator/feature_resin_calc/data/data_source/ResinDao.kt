package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.data_source

import androidx.room.*
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.MixHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.ResinAndHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface ResinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResin(resin: Resin):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMixHistory(mixHistory: MixHistory):Long

    @Query("SELECT * FROM Resin")
    fun getResins(): Flow<List<Resin>>







    @Query("SELECT * FROM resin WHERE resinId = :id")
    suspend fun getResinById(id: Int): Resin?

    @Transaction
    @Query("SELECT * FROM Resin ")
    fun getResinsAndMixHistories(): Flow<List<ResinAndHistory>>

    @Transaction
    @Query("SELECT * FROM Resin WHERE resinId = :id")
    fun getResinAndMixHistories(id: Int): Flow<List<ResinAndHistory>>




    @Delete
    suspend fun deleteResin(resin: Resin)


//
//
//    @Transaction
//    @Query("SELECT * FROM User WHERE userId = :userId")
//    fun getUserAndLibraries(userId: Int): List<UserAndLibrary>

}