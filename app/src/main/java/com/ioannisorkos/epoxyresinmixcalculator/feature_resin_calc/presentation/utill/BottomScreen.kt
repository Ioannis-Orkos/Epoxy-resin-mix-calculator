package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.presentation.utill

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreen(val route:String, val title:String, val icon:ImageVector){

    object MixBottomScreen: BottomScreen("mix_screen","MIX", Icons.Filled.Home)
    object ResinsBottomScreen: BottomScreen("resin_screen","Resin", Icons.Filled.List)
    object Setting: BottomScreen("setting_screen","Setting", Icons.Filled.Settings)

}


val bottomNavigationItems:List<BottomScreen> = listOf(
    BottomScreen.MixBottomScreen,
    BottomScreen.ResinsBottomScreen,
    BottomScreen.Setting
)

