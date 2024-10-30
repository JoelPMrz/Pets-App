package com.example.petsapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image : Int,
    val name : String,
    val category : String,
    val age : Int,
    val weight : Double,
    val about : String,
    val sex : String,
    val character : String
)
