package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.OrderType
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.ResinOrder

data class MixState(

    val mixSource: MixSource? = null,

    val resins: List<Resin> = emptyList(),
    val resinOrder: ResinOrder = ResinOrder.Date(OrderType.Descending),

    val selectedResin:Resin? = null,
    val ratio:Float? = null,

    //Toggle dialogs and views
    val isMixTypeSelectionVisible: Boolean = true,
    val isQuickMixDialogVisible: Boolean = false,
    val isSelectResinDialogVisible: Boolean = false,
    val isAddNewResinDialogVisible: Boolean = false,
)



sealed class MixSource{
    object QuickMix: MixSource()
    object FromResin: MixSource()
}

