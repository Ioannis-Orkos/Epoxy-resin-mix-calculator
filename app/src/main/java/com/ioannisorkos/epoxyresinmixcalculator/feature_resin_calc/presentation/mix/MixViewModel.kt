package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.InvalidResinException
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.use_case.ResinUseCases
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.OrderType
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.ResinOrder
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.isFloat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MixViewModel @Inject constructor(
    private val resinUseCases: ResinUseCases
) : ViewModel() {


    private val _state = mutableStateOf(MixState())
    val state: State<MixState> = _state

    private val _resinVal = mutableStateOf("")
    val resinVal: State<String> = _resinVal

    private val _hardenerVal = mutableStateOf("")
    val hardenerVal: State<String> = _hardenerVal

    private val _totalMixVal = mutableStateOf("")
    val totalMixVal: State<String> = _totalMixVal





    private var getResinsJob: Job? = null


    init{
        getResins(ResinOrder.Date(OrderType.Ascending))
    }





    fun onEvent(event: MixEvent) {
        when (event) {
            is MixEvent.ToggleMixTypeSelection -> {
                _state.value = state.value.copy(
                    isMixTypeSelectionVisible = !state.value.isMixTypeSelectionVisible,
                )
            }
            is MixEvent.ToggleSelectResinDialog -> {
                _state.value = state.value.copy(
                    isSelectResinDialogVisible = !state.value.isSelectResinDialogVisible,
                    //isMixTypeSelectionVisible = false
                )
           }
           is MixEvent.ToggleQuickMixDialog -> {
                _state.value = state.value.copy(
                    isQuickMixDialogVisible = !state.value.isQuickMixDialogVisible,
                    //isMixTypeSelectionVisible = false
                )
           }
           is MixEvent.ToggleAddResinDialog -> {
                _state.value = state.value.copy(
                    isAddNewResinDialogVisible = !state.value.isAddNewResinDialogVisible,
                    //isMixTypeSelectionVisible = false
                )
           }
           is MixEvent.DismissAll -> {
                _state.value = state.value.copy(
                    isQuickMixDialogVisible = false,
                    isMixTypeSelectionVisible = false,
                    isSelectResinDialogVisible = false,
                    isAddNewResinDialogVisible = false
                )
           }
           is MixEvent.Source -> {
            _state.value = state.value.copy(
                mixSource = event.mixSource
            )
           }
           is MixEvent.SetRatioQuickMix -> {
                _state.value = state.value.copy(
                    ratio = event.ratio
                )
               onEvent(MixEvent.Source(MixSource.QuickMix))
               onEvent(MixEvent.DismissAll)
           }

           is MixEvent.AddResin -> {
               viewModelScope.launch(Dispatchers.IO) {
                   try {
                       if(resinUseCases.addResin(event.resin)>0){
                           onEvent(MixEvent.SelectResin(resin = event.resin))
                       }
                   }catch (e: InvalidResinException){
                       // error response here
                   }
               }
           }
           is MixEvent.SelectResin -> {
                onEvent(MixEvent.Source(MixSource.FromResin))
                _state.value = state.value.copy(
                    selectedResin = event.resin,
                    ratio = event.resin.ratio
                )
               onEvent(MixEvent.DismissAll)
           }
           is MixEvent.OnResinValueChange -> {
               if(isFloat(event.resinVal)) {
                   val float:Float = event.resinVal.toFloat()

                   _hardenerVal.value = String.format("%.2f",float * (state.value.ratio?:0.0F))
                   _totalMixVal.value = String.format("%.2f", (float+( float * (state.value.ratio?:0.0F))))
                   _resinVal.value =  event.resinVal
               } else {
                   _hardenerVal.value = ""
                   _totalMixVal.value = ""
                   _resinVal.value = ""
               }
           }
           is MixEvent.OnHardenerValueChange -> {
               if(isFloat(event.hardenerVal)) {
                   // val float:Float = String.format("%.2f", event.resinVal).toFloat()
                   val float:Float = event.hardenerVal.toFloat()

                   _resinVal.value = String.format("%.2f",float *( 1/(state.value.ratio?:0.0F)))
                   _totalMixVal.value = String.format("%.2f", (float+( float * (1/(state.value.ratio?:0.0F)))))
                   _hardenerVal.value =  event.hardenerVal
               } else {
                   _hardenerVal.value = ""
                   _totalMixVal.value = ""
                   _resinVal.value = ""
               }
           }
           is MixEvent.OnTotalMixValueChange -> {
               if(isFloat(event.totalMixVal)) {
                   // val float:Float = String.format("%.2f", event.resinVal).toFloat()
                   val float:Float = event.totalMixVal.toFloat()

                   _resinVal.value = String.format("%.2f",(float*100) / ( 100+(100*(state.value.ratio?:0.0F))))
                   _hardenerVal.value = String.format("%.2f",(float*100) / ( 100+(100*(state.value.ratio?:0.0F))* (state.value.ratio?:0.0F)))
                   _totalMixVal.value =  event.totalMixVal
               } else {
                   _hardenerVal.value = ""
                   _totalMixVal.value = ""
                   _resinVal.value = ""
               }
           }
        }
    }



    private fun getResins(resinOrder: ResinOrder){
        getResinsJob?.cancel()

        getResinsJob = resinUseCases.getResins(resinOrder)
            .onEach { resins ->
                    _state.value = state.value.copy(
                        resins = resins
                )
            }
            .launchIn(viewModelScope)
    }


}