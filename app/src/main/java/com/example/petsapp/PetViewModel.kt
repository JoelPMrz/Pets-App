package com.example.petsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository): ViewModel() {

    val allPets: LiveData<List<Pet>> = repository.allPets.asLiveData()

    init {
        checkAndInsertDefaultPets()
    }

    private fun checkAndInsertDefaultPets() {
        allPets.observeForever { pets ->
            if (pets.isNullOrEmpty()) {
                insertDefaultPets()
            }
        }
    }

    private fun insertDefaultPets() {
        viewModelScope.launch {
            val defaultPets = listOf(
                Pet(0, R.drawable.pet_image1, "Max", "Labrador Retriever", 3, 10.5, "Max fue rescatado de un refugio tras pasar sus primeros años de vida en condiciones difíciles. A pesar de todo, mantiene su carácter alegre y juguetón, siempre moviendo la cola en busca de cariño. Su mayor alegría es salir a correr al parque y jugar con otros perros. Es un compañero leal y protector, ideal para una familia activa que disfrute de las actividades al aire libre.", "Macho", "Juguetón"),
                Pet(0, R.drawable.pet_image2, "Luna", "Siamés", 2, 5.0, "Luna es una gatita curiosa y astuta que fue encontrada sola en una zona urbana. Desde su rescate, ha demostrado ser una exploradora nata, siempre investigando cada rincón de la casa. Luna necesita un hogar donde se sienta segura y querida, ya que es algo independiente, pero muy dulce con quienes ganan su confianza. Le encanta descansar en las alturas, observando todo a su alrededor.", "Hembra", "Independiente"),
                Pet(0, R.drawable.pet_image3, "Rocky", "Pastor Alemán", 4, 12.0, "Rocky fue encontrado en una granja abandonada, donde había estado sobreviviendo solo. Es un perro noble y obediente, que necesita una familia comprometida. Es protector con sus dueños y siempre está alerta, además de ser cariñoso y leal. Le encanta correr y se siente en su mejor momento cuando tiene tareas o juegos que mantengan su mente ocupada. Rocky sería ideal para una familia activa que pueda brindarle mucho amor y espacio para explorar.", "Macho", "Leal"),
                Pet(0, R.drawable.pet_image4, "Mimi", "Persa", 1, 4.3, "Mimi es una hermosa gatita de pelaje suave que fue rescatada de una colonia de gatos callejeros. Desde entonces, ha mostrado ser muy dulce y juguetona, siempre buscando compañía para acurrucarse. Aunque al principio es algo tímida, una vez que se siente cómoda muestra su lado curioso. Su mayor felicidad es una ventana soleada donde pueda observar el exterior en tranquilidad.", "Hembra", "Curiosa"),
                Pet(0, R.drawable.pet_image5, "Toby", "Conejo Belier", 2, 1.2, "Toby es un conejo enano extremadamente cariñoso y tranquilo. Fue rescatado de una situación de abandono y ha aprendido a confiar nuevamente en las personas. Es perfecto para familias con niños, ya que disfruta de la compañía humana y le gusta ser acariciado. Toby es una mascota fácil de cuidar y se adapta bien a espacios interiores, donde se siente seguro y feliz.", "Macho", "Tierno"),
                Pet(0, R.drawable.pet_image6, "Nala", "Hamster Sirio", 1, 0.05, "Nala es una hamster Sirio llena de energía y personalidad. Desde que fue rescatada, ha mostrado ser muy sociable, además de adorar explorar y construir sus propios escondites. A Nala le encanta acurrucarse en sus mantas y siempre está ocupada buscando pedacitos de comida para guardar. Perfecta para una familia que busque una mascota pequeña y divertida, ya que es muy entretenido verla interactuar con su entorno.", "Hembra", "Enérgica"),
                Pet(0, R.drawable.pet_image7, "Simba", "Beagle", 1, 8.7, "Simba es un beagle amigable y curioso, que fue rescatado de un hogar donde no recibía los cuidados necesarios. Ahora, busca una familia que lo mime y le brinde el amor que merece. Le encanta pasear y descubrir nuevos olores, y su espíritu juguetón lo hace perfecto para acompañar a los niños en sus juegos. Es un gran compañero de aventuras y siempre tiene una actitud alegre y amable.", "Macho", "Valiente"),
                Pet(0, R.drawable.pet_image8, "Lola", "Maine Coon", 3, 4.8, "Lola es una gata Maine Coon rescatada de la calle. A pesar de su tamaño y aspecto imponente, es una gata muy tranquila y cariñosa. Disfruta de los momentos de calma y es una gran compañera para aquellos que valoran la tranquilidad en el hogar. Lola es ideal para alguien que quiera una mascota independiente pero que, a la vez, sea cariñosa y disfrute de la compañía humana.", "Hembra", "Tranquila"),
                Pet(0, R.drawable.pet_image9, "Rex", "Bulldog Inglés", 6, 15.0, "Rex es un bulldog inglés que necesita un hogar donde se sienta querido. Es tranquilo y disfruta de los momentos de relajación. Aunque no es muy activo, le encanta la compañía y es ideal para familias que busquen una mascota tranquila. Su carácter cariñoso y su lealtad son inigualables, y siempre está dispuesto a recibir una buena sesión de mimos.", "Macho", "Obediente"),
                Pet(0, R.drawable.pet_image10, "Daisy", "Canario", 2, 0.3, "Daisy es un canario alegre que fue rescatado de una tienda de mascotas donde no recibía los cuidados necesarios. Ahora, busca un hogar donde pueda llenar de alegría con su canto. Perfecto para familias o personas que quieran una mascota que les anime el día con su melodiosa voz. Daisy es un compañero ideal para quienes buscan un toque de naturaleza en casa.", "Hembra", "Alegre")
            )
            defaultPets.forEach { repository.insert(it) }
        }
    }

    fun addPet(image: Int,
               name : String,
               category : String,
               age : Int,
               weight : Double,
               about : String,
               sex : String,
               character : String){
        viewModelScope.launch {
            val pet = Pet(0, image, name, category, age, weight, about, sex, character)
            repository.insert(pet)
        }
    }

    fun updateContact(pet:Pet){
        viewModelScope.launch {
            repository.update(pet)
        }
    }

    fun deletePet(pet:Pet){
        viewModelScope.launch {
            repository.delete(pet)
        }
    }

}

class PetViewModelFactory(private val repository: PetRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PetViewModel::class.java )){
            @Suppress("UNCHECKED_CAST")
            return PetViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}