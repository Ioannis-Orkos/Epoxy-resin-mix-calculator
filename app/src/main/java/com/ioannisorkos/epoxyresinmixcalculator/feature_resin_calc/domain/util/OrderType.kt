package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util

sealed class OrderType{
    object Ascending:OrderType()
    object Descending:OrderType()
}
