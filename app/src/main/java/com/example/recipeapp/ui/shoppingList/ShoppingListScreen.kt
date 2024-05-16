package com.example.recipeapp.ui.shoppingList

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.dynamic_data.shopping.Shopping
import com.example.recipeapp.data.static_data.Ingredient
import com.example.recipeapp.data.static_data.Ingredients
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination

object ShoppingListDestination:NavigationDestination{
    override val route: String = "shopping list"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    recipeAppViewModel: RecipeAppViewModel,
    viewModel: ShoppingListViewModel = viewModel(factory = RecipeAppViewModel.Factory)
){
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
    }

    val checkStatePage = viewModel.uiState.checkStatePage
    val shoppingList by viewModel.shoppingList.observeAsState(emptyList())
    val ingredientList by viewModel.ingredientList.observeAsState(emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Danh sách mua sắm",
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    colorResource(id = R.color.primaryColor))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Column(
                modifier = Modifier.let {
                    if (checkStatePage) {
                        it.weight(1f)
                    } else {
                        it.height(0.dp)
                    }
                }
            ) {
                SelectList(
                    shoppingList = shoppingList,
                    viewModel = viewModel
                )
            }
            ButtonChangeState(
                checkStatePage = checkStatePage,
                onChangeStatePage = {
                    viewModel.changeCheckStatePage()
                }
            )
            RecipeList(
                viewModel = viewModel,
                ingredientList = ingredientList,
                modifier = Modifier.let {
                    if (!checkStatePage) {
                        it.weight(1f)
                    } else {
                        it.height(0.dp)
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}

@Composable
fun SelectList(
    shoppingList:List<Shopping>,
    viewModel: ShoppingListViewModel,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            )
    ){
        items(
            items = shoppingList
        ){ shopping ->
            var checkSelect by remember {
                mutableStateOf(false)
            }
            checkSelect = viewModel.checkListSelect(shopping.idProduct)
            val onChangeCheckSelect = {
                if(checkSelect){
                    viewModel.deleteListSelect(shopping.idProduct)
                }else{
                    viewModel.addListSelect(shopping.idProduct)
                }
                checkSelect = viewModel.checkListSelect(shopping.idProduct)
            }

            SelectCard(
                checkSelect = checkSelect,
                onChangeCheckSelect = onChangeCheckSelect,
                shopping = shopping,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun SelectCard(
    checkSelect: Boolean,
    onChangeCheckSelect:()->Unit,
    shopping: Shopping,
    viewModel: ShoppingListViewModel
){
    val product = Products().getProduct(shopping.idProduct)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable {
                onChangeCheckSelect()
            }
            .let {
                if (checkSelect) {
                    it.border(
                        width = 4.dp,
                        color = colorResource(id = R.color.primaryColor),
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    it.shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(2.dp)
                    )
                }
            }
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "imageFood",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .height(70.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
            )
            Text(
                text = "${product.timeComplete} phút",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.alpha(0.7f)
            )
        }
        IconButton(
            modifier = Modifier
                .height(90.dp)
                .clip(shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                .background(color = Color.Red)
                .border(1.dp, Color.Red),
            onClick = { viewModel.deleteShoppingList(product.id) }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "delete recipe",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun ButtonChangeState(
    checkStatePage:Boolean,
    onChangeStatePage:()->Unit,
){
    var description by remember {
        mutableStateOf("")
    }

    var shape by remember {
        mutableStateOf(RoundedCornerShape(0.dp))
    }

    description = if(checkStatePage){
        "Danh sách thành phần"
    }else{
        "Danh sách công thức"
    }

    shape = if(checkStatePage){
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    }else{
        RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.weight(1f))
        Button(
            shape = shape,
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primaryColor)),
            modifier = Modifier.weight(5f),
            onClick = { onChangeStatePage() }
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun RecipeList(
    viewModel: ShoppingListViewModel,
    ingredientList: List<Ingredient>,
    modifier: Modifier = Modifier
){
    val checkCompleteList by viewModel.completeList.observeAsState(emptyList())
    val productList by viewModel.productList.observeAsState(emptyList())
    var totalTrue by remember {
        mutableIntStateOf(0)
    }

    var checkFullComplete by remember {
        mutableStateOf(true)
    }

    var warning by remember {
        mutableStateOf("")
    }

    if(productList.isEmpty()){
        checkFullComplete = true
        warning = ""
    }

    if(ingredientList.isEmpty()){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "shopping cart",
                modifier = Modifier.size(250.dp),
                tint = colorResource(id = R.color.primaryColor)
            )
            Row {
                Text(
                    text = "hãy lựa chọn những công thức bạn muốn làm " +
                            "để chúng tôi có thể tạo danh sách những nguyên liệu " +
                            "mà bạn cần mua",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.primaryColor),
                    modifier = Modifier.weight(6f)
                )
            }
        }
    }else{
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = LinearOutSlowInEasing
                    )
                )
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = ingredientList
                ) { ingredient ->
                    val index = ingredientList.indexOf(ingredient)
                    RecipeCard(
                        checkComplete = checkCompleteList.getOrNull(index)?:false,
                        onChangeCheckComplete = {
                            viewModel.updateCompleteList(index)
                            if(checkCompleteList.getOrNull(index) == true){
                                totalTrue++
                            }else{
                                totalTrue--
                            }
                        },
                        ingredient = ingredient,
                        checkFullComplete = checkFullComplete
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(
                        color = colorResource(id = R.color.primaryColor),
                        thickness = 1.dp,
                        modifier = Modifier
                    )
                }
            }

            if (warning == "not enough") {
                Text(
                    text = "Bạn chưa thể hoàn thành việc mua hàng vì danh sách chưa được mua hết",
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 2,
                    color = Color.Red
                )
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primaryColor)),
                onClick = {
                    if (totalTrue == ingredientList.size) {
                        viewModel.completeShopping()
                        checkFullComplete = true
                        warning = ""
                    } else {
                        checkFullComplete = false
                        warning = "not enough"
                    }
                }
            ) {
                Text(
                    text = "Hoàn thành việc mua hàng",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
            }
        }
    }
}



@Composable
fun RecipeCard(
    checkComplete: Boolean,
    onChangeCheckComplete:()->Unit,
    ingredient: Ingredient,
    checkFullComplete:Boolean
){
    var textDecoration by remember {
        mutableStateOf(TextDecoration.None)
    }

    textDecoration = if(checkComplete){
        TextDecoration.LineThrough
    }else{
        TextDecoration.None
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(35.dp)
                .clickable { onChangeCheckComplete() }
        ){
            if(checkComplete){
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = colorResource(id = R.color.primaryColor),
                    modifier = Modifier
                        .size(35.dp)
                )
            }else{
                CircleIcon(
                    checkFullComplete = checkFullComplete
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = ingredient.image),
            contentDescription = "imageIngredient",
            modifier = Modifier
                .size(45.dp, 35.dp)
                .shadow(1.dp)
                .padding(2.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${ingredient.name} - ${ingredient.quantity}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 4.dp),
            textDecoration = textDecoration,
        )
    }
}

@Composable
private fun CircleIcon(
    checkFullComplete:Boolean,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(35.dp)
            .clip(shape = CircleShape)
            .background(Color.White)
            .let {
                if (checkFullComplete) {
                    it.border(4.dp, colorResource(id = R.color.primaryColor), shape = CircleShape)
                } else {
                    it.border(4.dp, Color.Red, shape = CircleShape)
                }

            }
    ) {
    }
}