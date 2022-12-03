package com.example.composeapp.dicoding.jetheroes.data

import com.example.composeapp.dicoding.jetheroes.model.Hero
import com.example.composeapp.dicoding.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> = HeroesData.heroes
}