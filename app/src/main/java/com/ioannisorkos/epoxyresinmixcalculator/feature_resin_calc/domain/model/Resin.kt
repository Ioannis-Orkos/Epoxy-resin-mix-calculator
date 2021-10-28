package com.ioannisorkos.epoxyresinmixcalculator.feature_resin_calc.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["uniqueID"], unique = true)])
data class Resin(
    val uniqueID:String,
    val partName:String,
    val description:String? = null,
    val totalWeight:Float? = null,
    val potLife:Int? = null,
    val ratio:Float,
    val hardenerPartName:String,
    val hardenerDescription:String? = null,
    val timestamp:Long,
    @PrimaryKey val resinId:Int? = null
){
    companion object{

    }

}

class InvalidResinException(message: String): Exception(message)