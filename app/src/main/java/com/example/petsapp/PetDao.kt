package com.example.petsapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pet:Pet)

    @Update
    suspend fun update(pet: Pet)

    @Delete
    suspend fun  delete(pet:Pet)

    @Query("SELECT * FROM pets")
    fun getAllPets(): Flow<List<Pet>>
}