package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.MixHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.ResinAndHistory
import kotlinx.coroutines.flow.Flow

interface ResinRepository {

    suspend fun insertResin(resin: Resin):Long

    suspend fun insertMixHistory(mixHistory: MixHistory):Long

    fun getResins(): Flow<List<Resin>>




    fun getResinsAndMixHistories(): Flow<List<ResinAndHistory>>

    fun getResinAndMixHistories(id: Int): Flow<List<ResinAndHistory>>

    suspend fun getResinById(id: Int): Resin?


    suspend fun deleteResin(resin: Resin)

}