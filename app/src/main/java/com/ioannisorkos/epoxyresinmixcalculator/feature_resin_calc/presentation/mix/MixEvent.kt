package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin

sealed class MixEvent{

    object ToggleMixTypeSelection:MixEvent()
    object ToggleQuickMixDialog:MixEvent()
    object ToggleSelectResinDialog:MixEvent()
    object ToggleAddResinDialog:MixEvent()
    object DismissAll:MixEvent()

    data class Source(val mixSource: MixSource):MixEvent()
    data class AddResin(val resin:Resin):MixEvent()
    data class SelectResin(val resin: Resin):MixEvent()
    data class SetRatioQuickMix(val ratio:Float):MixEvent()


    data class OnResinValueChange(val resinVal:String):MixEvent()
    data class OnHardenerValueChange(val hardenerVal:String):MixEvent()
    data class OnTotalMixValueChange(val totalMixVal:String):MixEvent()

}







//    data class Order(val quoteOrder: QuoteOrder): QuotesEvent()
//    data class DeleteQuote(val quote: Quote): QuotesEvent()
//    object RestoreQuote: QuotesEvent()