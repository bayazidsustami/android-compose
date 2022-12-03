package com.example.composeapp.dicoding.jetheroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeapp.dicoding.jetheroes.data.HeroRepository
import com.example.composeapp.dicoding.jetheroes.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JetHeroesViewModel(
    private val repository: HeroRepository
): ViewModel() {
    private val _groupedHeroes = MutableStateFlow(
        repository.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedHeroes: StateFlow<Map<Char, List<Hero>>> get() = _groupedHeroes
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: HeroRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetHeroesViewModel::class.java)) {
            return JetHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown class " + modelClass.name)
    }
}