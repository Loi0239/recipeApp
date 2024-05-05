package com.example.recipeapp.ui.favouriteRecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LayoutSaveRecipe(){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 15.dp)
        ) {
            Text(
                text = "Công thức đã lưu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        LazyRow {
            item {
                Category(name = "Đồ ăn nhanh", color = Color.Magenta)
            }
            item {
                Category(name = "Đồ ăn chậm", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row {
            Image()
        }
        Footer()
    }
}

@Composable
fun Category(name: String, color: Color){
    Spacer(modifier = Modifier.padding(start = 5.dp))
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding,
        border = BorderStroke(1.dp, color)
    ) {
        Text(text = name)
    }
    Spacer(modifier = Modifier.padding(end = 5.dp))
}

@Composable
fun Image(){
    Box(modifier= Modifier
        .fillMaxWidth()
        .padding(start = 35.dp, end = 35.dp)){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier= Modifier
                .size(width = 1000.dp, height = 200.dp)
                .clip(RoundedCornerShape(20.dp)))

        Column(modifier = Modifier
            .padding(end = 25.dp, top = 20.dp)
            .align(Alignment.TopEnd) ){
            Button(modifier = Modifier.size(70.dp,32.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(248, 235, 114, 255)),
                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding,
            ) {
                Icon(Icons.Default.Star, contentDescription = null)
                Text(text = "4.0", color = Color.Black, modifier = Modifier.size(width = 30.dp, height = 20.dp))
            }
        }
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 10.dp)
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null, Modifier.size(30.dp))
        }
        Column(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 20.dp)) {
            Text(text = "Spicy chicken burger with French fries  ",
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.size(height = 50.dp, width = 220.dp))
            Text(text = "(by Alex)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.3f))
        }
    }
}

@Composable
fun Footer(){
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 5.dp, bottom = 5.dp), horizontalArrangement = Arrangement.SpaceAround) {

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(Icons.Default.Home, contentDescription = null)
                }

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                }

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff129575),
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon( Icons.Default.Person, contentDescription = null)
                }
            }

        }
    }
}