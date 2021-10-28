package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util

sealed class ResinOrder(val orderType: OrderType){
    class Date(orderType: OrderType):ResinOrder(orderType)
    class PartName(orderType: OrderType):ResinOrder(orderType)


    fun copy(orderType: OrderType):ResinOrder{
          return when(this){
              is Date -> Date(orderType)
              is PartName -> PartName(orderType)
          }
    }

}

