package com.example.recipeapp.ui.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {
                Profile()
                Footer()
            }
        }
    }
}

@Composable
fun Profile() {
    Column {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Trang cá nhân",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "John Doe",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.padding(start = 35.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Công thức",
                    fontSize = 16.sp,
                    color = Color(0xFFA9A9A9)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "4",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Yêu thích",
                    fontSize = 16.sp,
                    color = Color(0xFFA9A9A9)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "14",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Column {
                Text(
                    text = "Nội trợ",
                    fontSize = 16.sp,
                    color = Color(0xffA9A9A9),
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71",
                    fontSize = 16.sp,
                    color = Color(0xffA9A9A9),
                    textAlign = TextAlign.Left
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 5.dp, end = 5.dp),color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(top = 25.dp))
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "Danh sách công thức của bạn",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            LazyColumn {
                items(5) { index ->
                    Card {
                        Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            Text(
                                text = "Công thức 1",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            Text(text = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum")
                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Text(text = "10 mins ago")
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
            }
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


@Preview(showSystemUi = true)
@Composable
fun PreviewMyApp() {
    Box {
        Profile()
        Footer()
    }
}
