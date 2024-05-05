package com.example.recipeapp.ui.recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutIngredient()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LayoutIngredient(){
    Scaffold(topBar = { Topbar() }, containerColor = Color.White) { padding->
        Column(modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)) {
            Image()
            Profile()
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row {
                Button(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0,150,136)),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(text = "Nguyên liệu")
                }
                Button(onClick = { /*TODO*/ },
                    enabled = false,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0,150,136),
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color(0,150,136)
                    ),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(text = "Cách làm")
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Column {
                Ingredient(img = R.drawable.ic_launcher_background, name = "Tomatos", kg = "500g")
            }
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(){
    Column(modifier = Modifier.padding(end = 16.dp)) {
        CenterAlignedTopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(imageVector = Icons.Default.ArrowBack,
                        contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White)
        )

    }

}

@Composable
fun Image(){
    Box(modifier= Modifier.fillMaxWidth()){

        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier= Modifier
                .size(width = 1000.dp, height = 200.dp)
                .clip(RoundedCornerShape(20.dp)))

        Column(modifier = Modifier.padding(start=275.dp,top=10.dp) ){
            Button(modifier = Modifier.size(70.dp,32.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(248, 235, 114, 255)),
                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
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
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 10.dp, bottom = 10.dp)
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null, Modifier.size(30.dp))
        }
    }
    Spacer(modifier = Modifier.padding(top = 16.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(50.dp)
    ) {
        Box {
            Text(text = "Spicy chicken burger with French fries  ",
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.size(height = 50.dp, width = 220.dp))
        }

        Text(text = "(13k Reviews)",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 20.dp, height = 30.dp)
                .alpha(0.3f))

    }
}

@Composable
fun Profile(){
    Row(modifier = Modifier.padding(top=16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription =null, modifier = Modifier.size(40.dp))
            Column {
                Text(text = "User",fontWeight = FontWeight(weight = 1000),
                    style = MaterialTheme.typography.titleMedium)
                Row {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Text(text = "Da Nang, Viet Nam", modifier = Modifier.alpha(0.3f))
                }
            }
        }
    }
}

@Composable
fun Ingredient(img:Int,name:String,kg:String){
    Box(
        Modifier
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = img), contentDescription =null,
                Modifier
                    .size(70.dp)
                    .padding(16.dp) )
            Text(text = name,Modifier.padding(20.dp),
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,)
            Text(text = kg,
                Modifier
                    .padding(top = 20.dp, bottom = 20.dp, start = 100.dp)
                    .alpha(0.3f),
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Right
            )
        }
    }
}