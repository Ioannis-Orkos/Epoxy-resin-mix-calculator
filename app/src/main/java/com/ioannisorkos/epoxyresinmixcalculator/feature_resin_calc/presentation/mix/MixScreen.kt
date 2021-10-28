package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components.AddNewResinDialog
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components.MixTypeSelection
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components.QuickMixDialog
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components.SelectResinDialog

@ExperimentalComposeUiApi
@Composable
fun MixScreen(
    viewModel: MixViewModel = hiltViewModel()
){

    val state = viewModel.state.value
    val focusManager = LocalFocusManager.current


    AddNewResinDialog(
        viewModel.state.value.resins,
        state.isAddNewResinDialogVisible,
        { resin ->
            viewModel.onEvent(MixEvent.AddResin(resin = resin))
        },
        {
            viewModel.onEvent(MixEvent.ToggleAddResinDialog)
        }
    )

    QuickMixDialog(
        state.isQuickMixDialogVisible,
        {
            viewModel.onEvent(MixEvent.ToggleQuickMixDialog)
        },
        {
           viewModel.onEvent(MixEvent.SetRatioQuickMix(it))
        }
    )

    SelectResinDialog(
        viewModel.state.value.resins,
        state.isSelectResinDialogVisible,
        { resin ->
            viewModel.onEvent(MixEvent.SelectResin(resin = resin))
        },
        {
            viewModel.onEvent(MixEvent.ToggleSelectResinDialog)
        }
    )





    Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                
                if (state.mixSource == MixSource.FromResin){
                    Text(text = "${state.selectedResin?.partName} selected")
                    Button(onClick = { viewModel.onEvent(MixEvent.ToggleMixTypeSelection)}) {
                        Text(text = "Change")
                    }
                } else if (state.mixSource == MixSource.QuickMix){
                    Text(text = "Quick mix selected")
                    Button(onClick = { viewModel.onEvent(MixEvent.ToggleMixTypeSelection) }) {
                        Text(text = "Change")
                    }
                } else{
                    Text(text = "Select to start")
                    Button(onClick = { viewModel.onEvent(MixEvent.ToggleMixTypeSelection) }) {
                        Text(text = "Select")
                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value =  viewModel.resinVal.value,
                    onValueChange = {viewModel.onEvent(MixEvent.OnResinValueChange(it))},
                    label = { Text(text = "Resin(100)") },
                    placeholder = { Text(text = "Resin(100)") },
                    singleLine = true,
                    maxLines = 1,
                    enabled = (state.ratio!=null),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.moveFocus(FocusDirection.Right)
                        }),
                )

                Spacer(modifier = Modifier.width(20.dp))


                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.hardenerVal.value,
                    onValueChange = {viewModel.onEvent(MixEvent.OnHardenerValueChange(it))},
                    label = { Text(text = String.format("Hardener(%.2f)",((state.ratio?:1f)*100)))},
                    placeholder = { Text(text = String.format("Hardener(%.2f)",((state.ratio?:1f)*100)))},
                    singleLine = true,
                    maxLines = 1,
                    enabled = (state.ratio!=null),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus()
                        }),
                )

            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.3f),
                value =  viewModel.totalMixVal.value,
                onValueChange = {viewModel.onEvent(MixEvent.OnTotalMixValueChange(it))},
                label = { Text(text = "Total") },
                placeholder = { Text(text = "Total") },
                singleLine = true,
                isError = false,
                enabled = (state.ratio!=null),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Right)
                    }),
            )

            Spacer(modifier = Modifier.height(20.dp))

            if ((state.mixSource == MixSource.FromResin) &&
                !viewModel.totalMixVal.value.isNullOrBlank() &&
                viewModel.totalMixVal.value.toFloat() > 0.0f)
                {

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Add to History")
                    }
            }

        }


    MixTypeSelection(
        state.isMixTypeSelectionVisible,
        {
            when(it){
                is MixEvent.ToggleQuickMixDialog -> {
                    viewModel.onEvent(it)
                    return@MixTypeSelection
                }
                is MixEvent.ToggleSelectResinDialog -> {
                    viewModel.onEvent(it)
                    return@MixTypeSelection
                }
                is MixEvent.ToggleAddResinDialog -> {
                    viewModel.onEvent(it)
                    return@MixTypeSelection
                }
                else -> {
                    return@MixTypeSelection
                }
            }
        }
    )


    }
















