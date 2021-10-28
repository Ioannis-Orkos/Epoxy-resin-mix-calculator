package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.repository

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.data.data_source.ResinDao
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.MixHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.ResinAndHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository.ResinRepository
import kotlinx.coroutines.flow.Flow

class ResinRepositoryImpl(
    private val dao:ResinDao
): ResinRepository {

    override suspend fun insertResin(resin: Resin):Long{
        return dao.insertResin(resin)
    }

    override suspend fun insertMixHistory(mixHistory: MixHistory):Long {
        return dao.insertMixHistory(mixHistory)
    }

    override fun getResins(): Flow<List<Resin>> {
        return dao.getResins()
    }







    override fun getResinsAndMixHistories(): Flow<List<ResinAndHistory>> {
        return dao.getResinsAndMixHistories()
    }

    override fun getResinAndMixHistories(id: Int): Flow<List<ResinAndHistory>> {
        return dao.getResinAndMixHistories(id)
    }

    override suspend fun getResinById(id: Int): Resin? {
        return dao.getResinById(id)
    }



    override suspend fun deleteResin(resin: Resin) {
        //TODO("Not yet implemented")
    }
}