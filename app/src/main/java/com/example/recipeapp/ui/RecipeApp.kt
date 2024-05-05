package com.example.recipeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.navigation.RecipeNavHost

@Composable
fun RecipeApp(
    navController: NavHostController = rememberNavController(),
    recipeAppViewModel: RecipeAppViewModel = viewModel(factory = RecipeAppViewModel.Factory)
){
    val footerState = recipeAppViewModel.footerState.value
    Box(modifier = Modifier.fillMaxSize()) {
        RecipeNavHost(
            navController = navController,
            recipeAppViewModel = recipeAppViewModel
        )
        if(footerState){
            Footer(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier){
    val viewModel: RecipeAppViewModel = viewModel()
    Box(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val footerItems = viewModel.footerItems.observeAsState(initial = emptyList()).value
            footerItems?.forEach { footerItem ->
                Icon(
                    footerItem.nameIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { viewModel.onItemClicked(footerItem.id) }
                        .let {
                            if (footerItem.checked) {
                                it
                                    .background(color = Color(0xffECECEC))
                                    .padding(8.dp)
                            } else {
                                it
                                    .background(color = Color.Unspecified)
                                    .padding(8.dp)
                            }
                        },
                    tint = if (footerItem.checked) {
                        colorResource(id = R.color.primaryColor)
                    } else {
                        Color.Unspecified
                    }
                )
            }
        }
    }
}