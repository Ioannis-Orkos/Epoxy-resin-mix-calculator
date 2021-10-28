package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.resin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case.ResinUseCases
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.OrderType
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.ResinOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResinsViewModel @Inject constructor(
    private val resinUseCases: ResinUseCases
) : ViewModel() {

    private val _resins = mutableStateOf<List<Resin>>(emptyList())
    val resins: State<List<Resin>> = _resins


    var isAdded = mutableStateOf<Long>(0)



    private var getResinsJob: Job? = null

    init{
        getResins(ResinOrder.Date(OrderType.Ascending))
    }

    fun addResin(resin: Resin) {

       viewModelScope.launch(Dispatchers.IO) {
           isAdded.value = resinUseCases.addResin(resin)
        }
    }

    private fun getResins(resinOrder: ResinOrder){
        getResinsJob?.cancel()

        getResinsJob = resinUseCases.getResins(resinOrder)
            .onEach { resins ->
                    _resins.value=resins
            }
            .launchIn(viewModelScope)

    }
}