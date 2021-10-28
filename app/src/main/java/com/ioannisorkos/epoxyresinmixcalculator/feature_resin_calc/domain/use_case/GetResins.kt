package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case

import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.repository.ResinRepository
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.OrderType
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.ResinOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetResins(
    private  val repository: ResinRepository
) {

    operator fun invoke(
        resinOrder: ResinOrder = ResinOrder.Date(OrderType.Ascending)
    ): Flow<List<Resin>> {
        return repository.getResins().map { resins ->
            when(resinOrder.orderType){
                is OrderType.Ascending -> {
                    when(resinOrder){
                        is ResinOrder.Date  -> resins.sortedBy {it.timestamp }
                        is ResinOrder.PartName  -> resins.sortedBy {it.partName }
                    }
                }

                is OrderType.Descending -> {
                    when(resinOrder) {
                        is ResinOrder.Date -> resins.sortedByDescending { it.timestamp }
                        is ResinOrder.PartName -> resins.sortedByDescending { it.partName }
                    }
                }
            }
        }

    }


}