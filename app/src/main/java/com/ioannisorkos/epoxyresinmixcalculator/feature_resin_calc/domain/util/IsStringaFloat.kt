package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.util

fun isFloat(s: String?) : Boolean {
    val regex = """^(-)?[0-9]{0,}((\.){1}[0-9]{0,}){0,1}$""".toRegex()
    return if (s.isNullOrEmpty()) false
    else regex.matches(s)
}


