package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.InvalidResinException
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.InvalidResinHistoryDataException
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.MixHistory
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository.ResinRepository

class AddMixHistory (
    private val repository:ResinRepository
) {

    @Throws(InvalidResinHistoryDataException::class)
    suspend operator fun invoke(mixHistory: MixHistory):Long {
        if(mixHistory.totalMix.isNaN()) {
            throw InvalidResinHistoryDataException("The total mix  can't be empty.")
        }

        return repository.insertMixHistory(mixHistory)
    }

}

