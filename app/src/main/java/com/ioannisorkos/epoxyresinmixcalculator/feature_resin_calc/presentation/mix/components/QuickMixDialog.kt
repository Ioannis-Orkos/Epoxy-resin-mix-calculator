package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ioannisorkos.epoxyresinmixcalculator.R
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixEvent
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.isFloat

@ExperimentalComposeUiApi
@Composable
fun QuickMixDialog(
    dialogVisible:Boolean,
    dialogDismiss:()->Unit,
    quickMixResult:(Float)->Unit
) {
    val aVal = remember { mutableStateOf("") }
    val bVal = remember { mutableStateOf("") }

    if (dialogVisible) {

        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.9f),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                    dialogDismiss()
            },


            text = {

                Column {

                    Text(text = "")

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        val focusManager = LocalFocusManager.current

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            value =  aVal.value,
                            onValueChange = { aVal.value = if(isFloat(it)) it else "" },
                            label = { Text(text = "Resin") },
                            placeholder = { Text(text = "Resin") },
                            singleLine = true,
                            isError = false,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.moveFocus(FocusDirection.Right)
                                }),
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            value = bVal.value,
                            onValueChange = { bVal.value = if(isFloat(it)) it else ""  },
                            label = { Text(text = "Hardener") },
                            placeholder = { Text(text = "Hardener") },
                            singleLine = true,
                            isError = false,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus()
                                }),
                        )
                    }
                }
            },
            title = {
                Text(text = stringResource(id = R.string.quick_mix_dialog))
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.quick_mix_dialog_close),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(onClick = {
                                dialogDismiss()
                            })
                    )
                    Text(
                        text = stringResource(id = R.string.quick_mix_dialog_add),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(onClick = {
                                if ( isFloat(aVal.value) && isFloat(bVal.value) && bVal.value.toFloat()>0 ){
                                    quickMixResult(
                                        bVal.value.toFloat()/aVal.value.toFloat())

//                                    aVal.value=""
//                                    bVal.value=""
                                }

                            })
                    )
                }
            },
        )
    }

}