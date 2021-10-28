package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixEvent
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixViewModel

@ExperimentalComposeUiApi
@Composable
fun MixTypeSelection(
    isVisible:Boolean,
    selection:(MixEvent)->Unit,
    modifier: Modifier = Modifier
) {
    if(isVisible){

//        val activity = (LocalContext.current as? Activity)
//
//
//        Dialog(
//            properties = DialogProperties(usePlatformDefaultWidth = false),
//            onDismissRequest = {
//                activity?.finish()
//            }
//        ) {

            Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(red = 0, green = 0, blue = 0, alpha = 193))
                        .padding(end = 20.dp,bottom = 100.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    Button(onClick = { selection(MixEvent.ToggleQuickMixDialog) }) {
                        Text(text = "Quick Mix")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(onClick = {  selection(MixEvent.ToggleSelectResinDialog) }) {
                        Text(text = "Mix from list")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(onClick = { selection(MixEvent.ToggleAddResinDialog)}) {
                        Text(text = "Add new resin to the list")
                    }
                }
            }
        }
//    }