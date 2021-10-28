package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model.Resin
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixEvent
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.mix.MixViewModel
import com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util.isFloat

@ExperimentalComposeUiApi
@Composable
fun AddNewResinDialog(

    resins: List<Resin>,
    dialogVisible:Boolean,
    insertedResin:(Resin)->Unit,
    dialogDismiss:()->Unit,
    resin: Resin? = null
){


    if (dialogVisible) {

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = {
                dialogDismiss()
            }
        ) {

            val uniqueId = remember { mutableStateOf("") }
            val partName = remember { mutableStateOf("") }
            val description = remember { mutableStateOf("") }

            val isHardenerDif = remember { mutableStateOf(false) }

            val ratioA = remember { mutableStateOf("") }
            val ratioB = remember { mutableStateOf("") }

            val hardenerPartName = remember { mutableStateOf("") }
            val hardenerDescription = remember { mutableStateOf("") }



            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(Color(red = 12, green = 12, blue = 12, alpha = 221)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val focusManager = LocalFocusManager.current

                Text(
                    text = "Add resin",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                var checkUniqueId by remember { mutableStateOf(false) }

                resins.forEach { r ->
                    checkUniqueId = r.uniqueID.equals(uniqueId.value)
                }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = uniqueId.value,
                    onValueChange = { uniqueId.value = it },
                    label = { Text(text = "Unique ID") },
                    placeholder = { Text(text = "Unique ID") },
                    singleLine = true,
                    isError = checkUniqueId,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = partName.value,
                    onValueChange = { partName.value = it },
                    label = { Text(text = "Part Name") },
                    placeholder = { Text(text = "Part Name") },
                    singleLine = true,
                    isError = false,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text(text = "Description") },
                    placeholder = { Text(text = "Description") },
                    singleLine = true,
                    isError = false,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color(red = 32, green = 30, blue = 30, alpha = 191)),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.47f),
                        value = ratioA.value,
                        onValueChange = { ratioA.value = if (isFloat(it)) it else "" },
                        label = { Text(text = "Ratio A") },
                        placeholder = { Text(text = "Ratio A") },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                    )

                    Text(
                        text = ":",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = ratioB.value,
                        onValueChange = { ratioB.value = if (isFloat(it)) it else "" },
                        label = { Text(text = "Ratio B") },
                        placeholder = { Text(text = "Ratio B") },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(40.dp)
                        .background(Color(red = 117, green = 115, blue = 115, alpha = 191)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "Is Hardener different",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                    )

                    Switch(
                        checked = isHardenerDif.value,
                        onCheckedChange = { isHardenerDif.value = it }
                    )

                }

                if (isHardenerDif.value) {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        value = hardenerPartName.value,
                        onValueChange = { hardenerPartName.value = it },
                        label = { Text(text = "Hardener Part Name") },
                        placeholder = { Text(text = "Hardener Part Name") },
                        singleLine = true,
                        isError = false,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }),
                    )
//                OutlinedTextField(
//                    modifier = Modifier.fillMaxWidth(0.9f),
//                    value =  hardenerDescription.value,
//                    onValueChange = { hardenerDescription.value = it },
//                    label = { Text(text = "Hardener Des") },
//                    placeholder = { Text(text = "Hardener Des") },
//                    singleLine = true,
//                    isError = false,
//                    keyboardActions = KeyboardActions(
//                        onDone = { focusManager.moveFocus(FocusDirection.Down)
//                        }),
//                )

                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 20.dp),
                    //.background(Color(red = 54, green = 50, blue = 50, alpha = 191)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = {
                            dialogDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = "Cancel")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = {

                            if (checkUniqueId) {
                                uniqueId.value = ""
                                return@Button
                            }

                            var ratio: Float?

                            if (!partName.value.isNullOrBlank()) {
                                if (hardenerPartName.value.isNullOrBlank())
                                    hardenerPartName.value = partName.value + "B"
                            } else return@Button

                            if (isFloat(ratioA.value) && isFloat(ratioB.value) && ratioA.value.toFloat() > 0) {
                                ratio = ratioB.value.toFloat() / ratioA.value.toFloat()
                            } else return@Button



                            if (!uniqueId.value.isNullOrBlank() && !partName.value.isNullOrBlank()) {

                                val resin: Resin = Resin(
                                    uniqueID = uniqueId.value,
                                    partName = partName.value,
                                    ratio = ratio,
                                    description = description.value,
                                    hardenerPartName = hardenerPartName.value,
                                    timestamp = System.currentTimeMillis()
                                )
                                insertedResin(resin)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "ADD")
                    }
                }
            }
        }
    }
}