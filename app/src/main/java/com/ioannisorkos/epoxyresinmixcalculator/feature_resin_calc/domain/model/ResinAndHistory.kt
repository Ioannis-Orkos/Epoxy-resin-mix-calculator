package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model

import androidx.room.Embedded
import androidx.room.Relation


//One to Many Relationship DB
data class ResinAndHistory(
    @Embedded val resin: Resin,
    @Relation(
        parentColumn = "resinId",
        entityColumn = "resinMixId"
    )
    val library: List<MixHistory>

)





