package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin

@ExperimentalComposeUiApi
@Composable
fun SelectResinDialog(
    resins: List<Resin>,
    dialogVisible:Boolean,
    selectedResin:(Resin)->Unit,
    dialogDismiss:()->Unit
){


    if (dialogVisible) {

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                dialogDismiss()
            }) {

            Column {

                Text(text = "")




                LazyColumn {
                    itemsIndexed(resins) { index, value ->
                        Text(value.partName,modifier = Modifier.clickable {
                            selectedResin(value)
                        })
                    }
                }

            }
        }

    }

}