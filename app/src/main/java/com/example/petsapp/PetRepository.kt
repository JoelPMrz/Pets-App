package com.example.petsapp

import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao){

    val allPets : Flow<List<Pet>> = petDao.getAllPets()

    suspend fun insert(pet : Pet){
        petDao.insert(pet)
    }

    suspend fun update(pet : Pet){
        petDao.update(pet)
    }

    suspend fun delete(pet:Pet){
        petDao.delete(pet)
    }


}