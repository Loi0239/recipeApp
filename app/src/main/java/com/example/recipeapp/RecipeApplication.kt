package com.example.recipeapp

import android.app.Application
import com.example.recipeapp.data.AppContainer
import com.example.recipeapp.data.AppDataContainer

class RecipeApplication: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}