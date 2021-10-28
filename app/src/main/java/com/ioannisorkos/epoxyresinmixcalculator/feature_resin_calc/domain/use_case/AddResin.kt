package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.InvalidResinException
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository.ResinRepository

class AddResin (
    private val repository:ResinRepository
) {

    @Throws(InvalidResinException::class)
    suspend operator fun invoke(resin: Resin):Long {
        if(resin.partName.isBlank()) {
            throw InvalidResinException("The partName of the resin can't be empty.")
        }

        if(resin.ratio.isNaN()) {
            throw InvalidResinException("The ratio of the resin can't be empty.")
        }
        return repository.insertResin(resin)
    }

}

