package com.example.recipeapp.data.static_data

import androidx.compose.animation.SizeTransform
import com.example.recipeapp.R

data class Product(
    val id:Int,
    val name:String,
    val image:Int,
    val timeComplete:Int,
    val ingredient: List<Ingredient>,
    val procedure: List<Procedure>,
    val category:List<Category>,
)

data class Procedure(
    val step:String,
    val des:String,
)

class Products{
    val productList = mutableListOf(
        Product(
            id = 0,
            name = "Gỏi cuốn",
            image = R.drawable.mot_goicuon_re,
            timeComplete = 35,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "300 g" },
                Ingredients().ingredientList[1].apply { quantity = "200 g" },
                Ingredients().ingredientList[2].apply { quantity = "200 g" },
                Ingredients().ingredientList[3].apply { quantity = "200 g" },
                Ingredients().ingredientList[34].apply { quantity = "300 g" },
                Ingredients().ingredientList[55].apply { quantity = "2 quả" },
                Ingredients().ingredientList[14].apply { quantity = "1 cây" },
                Ingredients().ingredientList[16].apply { quantity = "200 g" },
                Ingredients().ingredientList[17].apply { quantity = "200 g" },
                Ingredients().ingredientList[18].apply { quantity = "2 trái" },
                Ingredients().ingredientList[19].apply { quantity = "1 củ" },
                Ingredients().ingredientList[36].apply { quantity = "200 g" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[42].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "1.\tThịt heo: Rửa sạch, cho vào nồi luộc chín cùng 1 ít muối. Vớt thịt ra để nguội, xé nhỏ.\n" +
                            "2.\tTôm sú: Bóc vỏ, bỏ đầu, chỉ đen, rửa sạch. Cho tôm vào nồi luộc chín. Vớt tôm ra để nguội, cắt đôi.\n" +
                            "3.\tThịt ba chỉ: Rửa sạch, cho vào nồi luộc chín. Vớt thịt ra để nguội, cắt lát mỏng.\n" +
                            "4.\tChả lụa: Cắt lát mỏng.\n" +
                            "5.\tBún tươi: Chần qua nước sôi, vớt ra để ráo.\n" +
                            "6.\tTrứng gà: Luộc chín, bóc vỏ, cắt đôi.\n" +
                            "7.\tRau sống: Rửa sạch, để ráo. Xà lách, rau thơm, húng quế, húng lủi, diếp cá cắt nhỏ. Rau muống bào, giá đỗ rửa sạch.\n" +
                            "8.\tDưa leo: Rửa sạch, cắt sợi.\n" +
                            "9.\tCà rốt: Gọt vỏ, bào sợi.\n" +
                            "10.\tNước chấm: Pha nước mắm, đường, nước cốt chanh, tỏi băm, ớt băm, muối, nước lọc.\n"
                ),
                Procedure(
                    step = "Bước 2: Cuốn gỏi",
                    des = "1.\tTrải bánh tráng ra một mặt phẳng, nhúng bánh tráng vào nước ấm cho mềm.\n" +
                            "2.\tXếp lần lượt rau sống, bún, thịt heo, tôm sú, thịt ba chỉ, chả lụa, trứng gà, dưa leo, cà rốt lên bánh tráng.\n" +
                            "3.\tCuốn bánh tráng lại cho chặt tay.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 1,
            name = "Nem rán",
            image = R.drawable.hai_nemran_re,
            timeComplete = 60,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "500 g" },
                Ingredients().ingredientList[35].apply { quantity = "100 g" },
                Ingredients().ingredientList[64].apply { quantity = "50 g" },
                Ingredients().ingredientList[54].apply { quantity = "1 chén" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[42].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[23].apply { quantity = "2 tép" },
                Ingredients().ingredientList[43].apply { quantity = "1 trái" },
                Ingredients().ingredientList[18].apply { quantity = "2 trái" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tThịt heo: Rửa sạch, xay nhuyễn.\n" +
                            "•\tMỡ heo: Rửa sạch, xay nhuyễn.\n" +
                            "•\tMiến: Ngâm mềm, cắt nhỏ.\n" +
                            "•\tNấm mèo: Ngâm mềm, cắt nhỏ.\n" +
                            "•\tTrứng gà: Đánh tan.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tGia vị: Pha hỗn hợp gồm nước mắm, đường, bột ngọt, tiêu, muối, hạt nêm, nước cốt chanh, tỏi băm, ớt băm.\n" +
                            "•\tRau sống: Rửa sạch, để ráo. Xà lách, rau thơm, húng quế, húng lủi, diếp cá cắt nhỏ. Rau muống bào, giá đỗ rửa sạch.\n" +
                            "•\tDưa leo: Rửa sạch, cắt sợi.\n" +
                            "•\tCà rốt: Gọt vỏ, bào sợi.\n"
                ),
                Procedure(
                    step = "Bước 2: Trộn nhân nem",
                    des = "•\tCho thịt xay, mỡ xay, miến, nấm mèo, hành tím băm, hỗn hợp gia vị vào tô lớn, trộn đều.\n" +
                            "•\tƯớp nhân trong 30 phút cho thấm gia vị.\n"
                ),
                Procedure(
                    step = "Bước 3: Gói nem",
                    des = "•\tTrải bánh tráng nem ra mặt phẳng, thoa một lớp trứng gà mỏng lên bánh tráng.\n" +
                            "•\tCho một lượng nhân vừa đủ vào giữa bánh tráng.\n" +
                            "•\tCuốn nem lại cho chặt tay.\n"
                ),
                Procedure(
                    step = "Bước 4: Rán nem",
                    des = "•\tCho dầu ăn vào chảo, đun nóng.\n" +
                            "•\tCho nem vào rán vàng đều hai mặt.\n" +
                            "•\tVớt nem ra để ráo dầu.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 2,
            name = "Chả giò",
            image = R.drawable.ba_chagio_re,
            timeComplete = 45,
            ingredient = listOf(
                Ingredients().ingredientList[36].apply { quantity = "200 g" },
                Ingredients().ingredientList[54].apply { quantity = "1 chén" },
                Ingredients().ingredientList[0].apply { quantity = "300 g" },
                Ingredients().ingredientList[64].apply { quantity = "50 g" },
                Ingredients().ingredientList[35].apply { quantity = "100 g" },
                Ingredients().ingredientList[19].apply { quantity = "1 củ" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[49].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[42].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[23].apply { quantity = "2 tép" },
                Ingredients().ingredientList[43].apply { quantity = "1 trái" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tVỏ: Bánh tráng nem ngâm mềm trong nước ấm. Trứng gà đánh tan.\n" +
                            "•\tNhân: Thịt heo xay, tôm sú bóc vỏ, chỉ đen, rửa sạch. Nấm mèo ngâm mềm, cắt nhỏ. Miến ngâm mềm, cắt nhỏ. Cà rốt, khoai môn gọt vỏ, bào sợi. Hành tím băm nhuyễn. Pha hỗn hợp gia vị gồm nước mắm, đường, bột ngọt, tiêu, muối, hạt nêm, nước cốt chanh, tỏi băm, ớt băm. Rau sống rửa sạch, để ráo. Bún tươi chần qua nước sôi, vớt ra để ráo.\n" +
                            "•\tNước mắm chua ngọt: Pha nước mắm, đường, chanh, tỏi ớt băm.\n"
                ),
                Procedure(
                    step = "Bước 2: Trộn nhân chả giò",
                    des = "•\tCho thịt xay, tôm sú, nấm mèo, miến, cà rốt, khoai môn, hành tím băm, hỗn hợp gia vị vào tô lớn, trộn đều.\n" +
                            "•\tƯớp nhân trong 30 phút cho thấm gia vị.\n"
                ),
                Procedure(
                    step = "Bước 3: Cuốn chả giò",
                    des = "•\tTrải bánh tráng nem ra mặt phẳng, thoa một lớp trứng gà mỏng lên bánh tráng.\n" +
                            "•\tCho một lượng nhân vừa đủ vào giữa bánh tráng.\n" +
                            "•\tCuốn chả giò lại cho chặt tay.\n"
                ),
                Procedure(
                    step = "Bước 4: Chiên chả giò",
                    des = "•\tCho dầu ăn vào chảo, đun nóng.\n" +
                            "•\tCho chả giò vào chiên vàng đều hai mặt.\n" +
                            "•\tVớt chả giò ra để ráo dầu.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 3,
            name = "Gỏi bò",
            image = R.drawable.bon_goibo_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[5].apply { quantity = "500 g" },
                Ingredients().ingredientList[29].apply { quantity = "1 củ" },
                Ingredients().ingredientList[19].apply { quantity = "1 củ" },
                Ingredients().ingredientList[20].apply { quantity = "2 quả" },
                Ingredients().ingredientList[21].apply { quantity = "1 quả" },
                Ingredients().ingredientList[16].apply { quantity = "1 bó" },
                Ingredients().ingredientList[58].apply { quantity = "100 g" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[23].apply { quantity = "3 tép" },
                Ingredients().ingredientList[43].apply { quantity = "1 trái" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[45].apply { quantity = "100 ml" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tThịt bắp bò: Luộc chín, vớt ra để nguội, xé nhỏ.\n" +
                            "•\tHành tây: Cắt lát mỏng.\n" +
                            "•\tCà rốt: Gọt vỏ, bào sợi.\n" +
                            "•\tChuối chát: Gọt vỏ, bào sợi.\n" +
                            "•\tKhế chua: Gọt vỏ, cắt lát mỏng.\n" +
                            "•\tRau thơm: Rửa sạch, cắt nhỏ.\n" +
                            "•\tLạc rang, đậu phộng rang: Giã nhỏ.\n" +
                            "•\tGia vị: Pha nước mắm, đường, nước cốt chanh, tỏi băm, ớt băm, muối, nước lọc.\n"
                ),
                Procedure(
                    step = "Bước 2: Trộn gỏi",
                    des = "•\tCho thịt bò, hành tây, cà rốt, chuối chát, khế chua, rau thơm vào tô lớn, trộn đều.\n" +
                            "•\tRưới nước mắm pha lên trên, trộn đều cho gỏi thấm gia vị. Bánh tráng trộn\n"
                )
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 4,
            name = "Đậu phộng rang muối ớt",
            image = R.drawable.nam_dauphongranmuoiot_re,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[58].apply { quantity = "500 g" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[30].apply { quantity = "5 lá" },
                Ingredients().ingredientList[23].apply { quantity = "2 tép" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tĐậu phộng: Rửa sạch, để ráo. Nên chọn những hạt đậu phộng to, đều, không bị lép, mốc.\n" +
                            "•\tMuối: Cho vào chảo, rang trên lửa nhỏ cho đến khi muối chuyển sang màu vàng nhạt và dậy mùi thơm.\n" +
                            "•\tỚt bột: Chuẩn bị sẵn hoặc tự làm bằng cách phơi khô ớt tươi, xay nhuyễn.\n" +
                            "•\tLá chanh: Rửa sạch, cắt sợi (tùy chọn).\n" +
                            "•\tTỏi: Băm nhuyễn (tùy chọn).\n"
                ),
                Procedure(
                    step = "Bước 2: Rang đậu phộng",
                    des = "•\tPhương pháp 1 (rang trên bếp):\n" +
                            "o\tCho dầu ăn vào chảo, đun nóng.\n" +
                            "o\tCho đậu phộng vào chảo, đảo đều cho đến khi đậu vàng giòn.\n" +
                            "o\tVớt đậu phộng ra đĩa để ráo dầu.\n" +
                            "•\tPhương pháp 2 (rang bằng nồi chiên không dầu):\n" +
                            "o\tXếp đậu phộng vào nồi chiên không dầu.\n" +
                            "o\tSet nhiệt độ 180 độ C, hẹn giờ 15-20 phút.\n" +
                            "o\tLấy đậu phộng ra khỏi nồi, đảo đều cho đến khi nguội bớt.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 5,
            name = "Cá kho tộ",
            image = R.drawable.sau_cakhoto_re,
            timeComplete = 90,
            ingredient = listOf(
                Ingredients().ingredientList[9].apply { quantity = "1 kg" },
                Ingredients().ingredientList[22].apply { quantity = "10 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "2 trái" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[27].apply { quantity = "1 củ" },
                Ingredients().ingredientList[24].apply { quantity = "2 cây" },
                Ingredients().ingredientList[25].apply { quantity = "2 cây" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[42].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[49].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tCá: Rửa sạch, cắt khúc vừa ăn. Ướp cá với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tThịt ba chỉ: Rửa sạch, cắt miếng vừa ăn.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tTỏi: Băm nhuyễn.\n" +
                            "•\tỚt: Băm nhuyễn.\n" +
                            "•\tGừng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tRiềng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tHành lá, ngò gai: Rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Kho cá",
                    des = "•\tCho dầu ăn vào tộ đất, phi thơm hành tím băm.\n" +
                            "•\tCho thịt ba chỉ vào xào săn.\n" +
                            "•\tCho cá vào kho cùng, đảo nhẹ cho cá thấm gia vị.\n" +
                            "•\tNêm thêm nước mắm, đường, hạt nêm, tiêu, gừng băm, riềng băm, ớt băm.\n" +
                            "•\tCho nước dừa tươi (hoặc nước lọc) vào xâm xấp mặt cá.\n" +
                            "•\tKho cá với lửa nhỏ trong khoảng 45-60 phút cho đến khi cá chín mềm và nước kho sánh lại.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho hành lá, ngò gai cắt nhỏ vào đảo đều.\n" +
                            "•\tMúc cá kho tộ ra đĩa và thưởng thức nóng với cơm trắng.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 6,
            name = "Thịt kho tàu",
            image = R.drawable.bay_thitkhotau_re,
            timeComplete = 120,
            ingredient = listOf(
                Ingredients().ingredientList[2].apply { quantity = "500 g" },
                Ingredients().ingredientList[55].apply { quantity = "10 quả" },
                Ingredients().ingredientList[49].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[22].apply { quantity = "10 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "4 trái" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[27].apply { quantity = "1 củ" },
                Ingredients().ingredientList[24].apply { quantity = "2 cây" },
                Ingredients().ingredientList[25].apply { quantity = "2 cây" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tThịt ba chỉ: Rửa sạch, cắt miếng vuông vừa ăn. Ướp thịt với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tTrứng gà: Luộc chín, bóc vỏ.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tTỏi: Băm nhuyễn.\n" +
                            "•\tỚt: Băm nhuyễn.\n" +
                            "•\tGừng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tRiềng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tHành lá, ngò gai: Rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Kho thịt",
                    des = "•\tCho dầu ăn vào nồi, phi thơm hành tím băm.\n" +
                            "•\tCho thịt ba chỉ vào xào săn.\n" +
                            "•\tNêm thêm nước mắm, đường, hạt nêm, tiêu, gừng băm, riềng băm, ớt băm.\n" +
                            "•\tCho nước dừa tươi (hoặc nước lọc) vào xâm xấp mặt thịt.\n" +
                            "•\tKho thịt với lửa nhỏ trong khoảng 45-60 phút cho đến khi thịt chín mềm và nước kho sánh lại.\n" +
                            "•\tCho trứng gà đã luộc chín vào kho cùng thịt trong 10 phút.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho hành lá, ngò gai cắt nhỏ vào đảo đều.\n" +
                            "•\tNêm nếm gia vị cho vừa ăn (nếu cần thiết).\n" +
                            "•\tMúc thịt kho tàu ra đĩa và thưởng thức nóng với cơm trắng.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 7,
            name = "Sườn rim nước dừa",
            image = R.drawable.tam_suonrimnuocdua_re,
            timeComplete = 60,
            ingredient = listOf(
                Ingredients().ingredientList[8].apply { quantity = "500 g" },
                Ingredients().ingredientList[49].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[23].apply { quantity = "3 tép" },
                Ingredients().ingredientList[43].apply { quantity = "2 trái" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[27].apply { quantity = "1 củ" },
                Ingredients().ingredientList[24].apply { quantity = "2 cây" },
                Ingredients().ingredientList[25].apply { quantity = "2 cây" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tSườn non: Rửa sạch, chặt miếng vừa ăn. Ướp sườn với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tTỏi: Băm nhuyễn.\n" +
                            "•\tỚt: Băm nhuyễn.\n" +
                            "•\tGừng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tRiềng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tHành lá, ngò gai: Rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Rim sườn",
                    des = "•\tCho dầu ăn vào chảo, phi thơm hành tím băm.\n" +
                            "•\tCho sườn vào xào săn.\n" +
                            "•\tNêm thêm nước mắm, đường, hạt nêm, tiêu, gừng băm, riềng băm, ớt băm.\n" +
                            "•\tCho nước dừa tươi vào xâm xấp mặt sườn.\n" +
                            "•\tRim sườn với lửa nhỏ trong khoảng 45-60 phút cho đến khi sườn chín mềm và nước rim sánh lại.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho hành lá, ngò gai cắt nhỏ vào đảo đều.\n" +
                            "•\tNêm nếm gia vị cho vừa ăn (nếu cần thiết).\n" +
                            "•\tMúc sườn rim nước dừa ra đĩa và thưởng thức nóng với cơm trắng.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 8,
            name = "Gà kho sả ớt",
            image = R.drawable.chin_gakhosaot_re,
            timeComplete = 45,
            ingredient = listOf(
                Ingredients().ingredientList[6].apply { quantity = "1 con" },
                Ingredients().ingredientList[28].apply { quantity = "5 củ" },
                Ingredients().ingredientList[43].apply { quantity = "5 trái" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[22].apply { quantity = "10 củ" },
                Ingredients().ingredientList[30].apply { quantity = "5 lá" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" }

            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tGà: Rửa sạch, chặt miếng vừa ăn. Ướp gà với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tSả: Cắt khúc, băm nhuyễn phần gốc. Phần thân sả đập dập.\n" +
                            "•\tỚt: Băm nhuyễn.\n" +
                            "•\tGừng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tTỏi: Băm nhuyễn.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tRễ ngò: Băm nhuyễn (tùy chọn).\n" +
                            "•\tLá chanh: Rửa sạch, cắt sợi (tùy chọn).\n"
                ),
                Procedure(
                    step = "Bước 2: Kho gà",
                    des = "•\tCho dầu ăn vào nồi, phi thơm hành tím băm.\n" +
                            "•\tCho sả băm vào xào cho đến khi thơm.\n" +
                            "•\tCho gà vào xào săn.\n" +
                            "•\tNêm thêm nước mắm, đường, hạt nêm, tiêu, gừng băm, ớt băm, rễ ngò băm (nếu sử dụng).\n" +
                            "•\tCho nước dừa tươi (hoặc nước lọc) vào xâm xấp mặt gà.\n" +
                            "•\tKho gà với lửa nhỏ trong khoảng 45-60 phút cho đến khi gà chín mềm và nước kho sánh lại.\n" +
                            "•\tCho sả đập dập vào kho cùng trong 15 phút cuối cùng.\n" +
                            "•\tNêm nếm gia vị cho vừa ăn (nếu cần thiết).\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho lá chanh cắt sợi (nếu sử dụng) vào đảo đều.\n" +
                            "•\tMúc gà kho sả ớt ra đĩa và thưởng thức nóng với cơm trắng.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 9,
            name = "Tôm rim me",
            image = R.drawable.muoi_tomrimme_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[1].apply { quantity = "500 g" },
                Ingredients().ingredientList[57].apply { quantity = "100 g" },
                Ingredients().ingredientList[40].apply { quantity = "3 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[43].apply { quantity = "2 trái" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[26].apply { quantity = "2 củ" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[24].apply { quantity = "2 cọng" },
                Ingredients().ingredientList[45].apply { quantity = "200 ml" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tTôm rửa sạch, bóc vỏ, bỏ đầu và chỉ đen. Ướp tôm với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tMe chín: Ngâm me với nước ấm cho me mềm ra, lọc lấy phần nước cốt me.\n" +
                            "•\tỚt: Băm nhuyễn.\n" +
                            "•\tTỏi: Băm nhuyễn.\n" +
                            "•\tHành tím: Băm nhuyễn.\n" +
                            "•\tGừng: Gọt vỏ, băm nhuyễn.\n" +
                            "•\tHành lá, ngò rí: Rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Rim tôm",
                    des = "•\tCho dầu ăn vào chảo, phi thơm hành tím băm.\n" +
                            "•\tCho tôm vào xào săn.\n" +
                            "•\tNêm thêm nước mắm, đường, gừng băm, ớt băm.\n" +
                            "•\tĐảo đều cho tôm thấm gia vị.\n" +
                            "•\tCho nước cốt me và nước lọc (hoặc nước dừa tươi) vào xâm xấp mặt tôm.\n" +
                            "•\tRim tôm với lửa nhỏ trong khoảng 15-20 phút cho đến khi tôm chín mềm và nước rim sánh lại.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho hành lá, ngò rí cắt nhỏ vào đảo đều.\n" +
                            "•\tNêm nếm gia vị cho vừa ăn (nếu cần thiết).\n" +
                            "•\tMúc tôm rim me ra đĩa và thưởng thức nóng với cơm trắng.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 10,
            name = "Lẩu thái",
            image = R.drawable.muoimot_lauthai_re,
            timeComplete = 120,
            ingredient = listOf(
                Ingredients().ingredientList[63].apply { quantity = "1 kg" },
                Ingredients().ingredientList[49].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[29].apply { quantity = "1 củ" },
                Ingredients().ingredientList[28].apply { quantity = "3 củ" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[27].apply { quantity = "1 củ" },
                Ingredients().ingredientList[30].apply { quantity = "5 lá" },
                Ingredients().ingredientList[57].apply { quantity = "100 g" },
                Ingredients().ingredientList[12].apply { quantity = "500 g" },
                Ingredients().ingredientList[1].apply { quantity = "500 g" },
                Ingredients().ingredientList[7].apply { quantity = "500 g" },
                Ingredients().ingredientList[9].apply { quantity = "500 g" },
                Ingredients().ingredientList[15].apply { quantity = "4 g" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[34].apply { quantity = "100 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[42].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[43].apply { quantity = "5 trái" },
                Ingredients().ingredientList[44].apply { quantity = "3 muỗng cà phê" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Nấu nước dùng lẩu",
                    des = "•\tRửa sạch xương heo, cho vào nồi luộc sơ qua để lấy đi phần nước bẩn.\n" +
                            "•\tCho xương heo vào nồi, đổ nước dừa tươi vào hầm trong khoảng 1-2 tiếng cho đến khi xương mềm và nước dùng ngọt.\n" +
                            "•\tNướng cà chua, hành tây, sả, gừng, riềng cho đến khi thơm.\n" +
                            "•\tBăm nhuyễn cà chua, hành tây, sả, gừng, riềng.\n" +
                            "•\tCho hỗn hợp cà chua, hành tây, sả, gừng, riềng băm nhuyễn vào nồi nước dùng, nêm thêm muối, đường, hạt nêm, nước mắm cho vừa ăn.\n" +
                            "•\tCho lá chanh vào nồi nước dùng, nấu thêm 10 phút rồi tắt bếp.\n"
                ),
                Procedure(
                    step = "Bước 2: Sơ chế nguyên liệu nhúng lẩu",
                    des = "•\tThịt bò rửa sạch, thái mỏng.\n" +
                            "•\tTôm sú rửa sạch, bóc vỏ, bỏ đầu và chỉ đen.\n" +
                            "•\tMực rửa sạch, thái khoanh.\n" +
                            "•\tCá viên cắt đôi.\n" +
                            "•\tRau củ rửa sạch, cắt khúc vừa ăn.\n" +
                            "•\tBún tươi, mì gói ngâm nước cho mềm.\n"
                ),
                Procedure(
                    step = "Bước 3: Chuẩn bị gia vị chấm",
                    des = "•\tPha nước mắm với ớt, chanh, tỏi băm nhuyễn."
                ),
                Procedure(
                    step = "Bước 4: Thưởng thức",
                    des = "•\tCho nước dùng lẩu vào nồi, đun sôi.\n" +
                            "•\tCho các nguyên liệu nhúng lẩu vào nồi nước dùng, nấu chín.\n" +
                            "•\tĂn lẩu Thái cùng với bún tươi, mì gói và chấm với nước mắm ớt.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 11,
            name = "Mì quảng",
            image = R.drawable.muoihai_miquang_re,
            timeComplete = 60,
            ingredient = listOf(
                Ingredients().ingredientList[37].apply { quantity = "500 g" },
                Ingredients().ingredientList[6].apply { quantity = "500 g" },
                Ingredients().ingredientList[1].apply { quantity = "200 g" },
                Ingredients().ingredientList[14].apply { quantity = "50 g" },
                Ingredients().ingredientList[15].apply { quantity = "10 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[43].apply { quantity = "2 trái" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[51].apply { quantity = "2 muỗng canh" }
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tSợi mì Quảng ngâm nước ấm cho mềm.\n" +
                            "•\tThịt gà rửa sạch, chặt miếng vừa ăn. Ướp thịt với 1 muỗng cà phê muối, 1 muỗng cà phê tiêu trong 15 phút.\n" +
                            "•\tTôm rửa sạch, bóc vỏ, bỏ đầu và chỉ đen.\n" +
                            "•\tTrứng cút luộc chín, bóc vỏ.\n" +
                            "•\tRau củ rửa sạch, cắt nhỏ.\n" +
                            "•\tTỏi, ớt, gừng băm nhuyễn.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu nước dùng",
                    des = "•\tPhi thơm hành tím băm với dầu ăn, cho thịt gà vào xào săn.\n" +
                            "•\tNêm thêm nước mắm, muối, tiêu, bột nghệ, gừng băm vào xào chung.\n" +
                            "•\tCho nước vào nồi, nấu sôi.\n" +
                            "•\tCho sả đập dập vào nồi nước dùng, nấu thêm 15 phút.\n"
                ),
                Procedure(
                    step = "Bước 3: Chế biến mì Quảng",
                    des = "•\tCho mì Quảng vào nồi nước dùng, nấu cho đến khi mì chín mềm.\n" +
                            "•\tCho tôm vào nồi, nấu chín.\n" +
                            "•\tCho rau củ vào nồi, nấu chín sơ.\n" +
                            "•\tNêm nếm gia vị cho vừa ăn.\n"
                ),
                Procedure(
                    step = "Bước 4: Hoàn thành",
                    des = "•\tMúc mì Quảng ra tô, cho thịt gà, tôm, trứng cút, rau củ lên trên.\n" +
                            "•\tRắc thêm hành lá, hẹ, hành phi, ớt băm vào.\n" +
                            "•\tDùng nóng với nước mắm ớt.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 12,
            name = "Chè hạt sen",
            image = R.drawable.muoiba_chehatsen_re,
            timeComplete = 90,
            ingredient = listOf(
                Ingredients().ingredientList[60].apply { quantity = "200 g" },
                Ingredients().ingredientList[41].apply { quantity = "1 lít" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[65].apply { quantity = "50 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tHạt sen rửa sạch, tách tim sen. Ngâm hạt sen trong nước ấm khoảng 30 phút cho nở mềm.\n" +
                            "•\tLá dứa rửa sạch, cắt khúc.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu chè",
                    des = "•\tCho nước vào nồi, đun sôi.\n" +
                            "•\tCho lá dứa vào nồi, nấu thêm 5 phút cho nước có màu xanh và thơm mùi lá dứa.\n" +
                            "•\tVớt lá dứa ra.\n" +
                            "•\tCho hạt sen vào nồi, nấu khoảng 30 phút cho hạt sen chín mềm.\n" +
                            "•\tNêm thêm muối và đường vào nồi, khuấy đều cho tan.\n" +
                            "•\tNấu thêm 10 phút cho nước chè ngấm gia vị.\n" +
                            "•\t(Tùy chọn) Cho đường phèn vào nồi, nấu cho tan.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[2]
            )
        ),
        Product(
            id = 13,
            name = "Chè đậu xanh",
            image = R.drawable.muoibon_chedauxanh_re,
            timeComplete = 50,
            ingredient = listOf(
                Ingredients().ingredientList[59].apply { quantity = "200 g" },
                Ingredients().ingredientList[41].apply { quantity = "1 lít" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[65].apply { quantity = "50 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tĐậu xanh vo sạch, loại bỏ hạt lép. Ngâm đậu xanh trong nước ấm khoảng 30 phút cho nở mềm.\n" +
                            "•\tLá dứa rửa sạch, cắt khúc.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu chè",
                    des = "•\tCho nước vào nồi, đun sôi.\n" +
                            "•\tCho lá dứa vào nồi, nấu thêm 5 phút cho nước có màu xanh và thơm mùi lá dứa.\n" +
                            "•\tVớt lá dứa ra.\n" +
                            "•\tCho đậu xanh vào nồi, nấu khoảng 30 phút cho đậu xanh chín mềm.\n" +
                            "•\tNêm thêm muối và đường vào nồi, khuấy đều cho tan.\n" +
                            "•\tNấu thêm 10 phút cho nước chè ngấm gia vị.\n" +
                            "•\t(Tùy chọn) Cho đường phèn vào nồi, nấu cho tan.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[2]
            )
        ),
        Product(
            id = 14,
            name = "Sữa chua",
            image = R.drawable.muoinam_suachua_re,
            timeComplete = 600,
            ingredient = listOf(
                Ingredients().ingredientList[61].apply { quantity = "1 lít" },
                Ingredients().ingredientList[62].apply { quantity = "1 hộp" },
                Ingredients().ingredientList[41].apply { quantity = "50 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Khử trùng dụng cụ",
                    des = "•\tRửa sạch tất cả dụng cụ làm sữa chua (lọ thủy tinh, nắp đậy, muỗng khuấy) bằng nước nóng và xà phòng.\n" +
                            "•\tTrụng dụng cụ qua nước sôi để khử trùng.\n"
                ),
                Procedure(
                    step = "Bước 2: Làm hỗn hợp sữa chua",
                    des = "•\tCho sữa tươi vào nồi, đun sôi trên lửa nhỏ.\n" +
                            "•\tTắt bếp, để sữa nguội bớt đến khoảng 40°C (sờ vào sữa thấy ấm nhưng không nóng).\n" +
                            "•\tCho sữa chua cái vào tô, khuấy đều.\n" +
                            "•\tCho hỗn hợp sữa chua cái vào nồi sữa ấm, khuấy đều cho tan.\n" +
                            "•\t(Tùy chọn) Cho thêm đường vào hỗn hợp sữa chua, khuấy đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Ủ sữa chua",
                    des = "•\tCho hỗn hợp sữa chua vào các lọ thủy tinh đã khử trùng.\n" +
                            "•\tĐậy kín nắp lọ.\n" +
                            "•\tQuấn khăn ấm hoặc ủ sữa chua trong nồi cơm điện đã tắt (giữ ấm).\n" +
                            "•\tỦ sữa chua trong 6-8 tiếng (hoặc qua đêm).\n"
                ),
                Procedure(
                    step = "Bước 4: Kiểm tra và bảo quản",
                    des = "•\tSau khi ủ, mở nắp lọ sữa chua, nếu thấy sữa chua đông đặc, mịn màng là đã thành công.\n" +
                            "•\tCho sữa chua vào ngăn mát tủ lạnh để bảo quản.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[2]
            )
        ),
        Product(
            id = 15,
            name = "Phở",
            image = R.drawable.muoisau_pho_re,
            timeComplete = 240,
            ingredient = listOf(
                Ingredients().ingredientList[66].apply { quantity = "500 g" },
                Ingredients().ingredientList[63].apply { quantity = "2 kg" },
                Ingredients().ingredientList[12].apply { quantity = "300 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[65].apply { quantity = "30 g" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[29].apply { quantity = "1 củ" },
                Ingredients().ingredientList[22].apply { quantity = "3 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "6 trái" },
                Ingredients().ingredientList[15].apply { quantity = "20 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tXương bò rửa sạch, chặt thành miếng vừa ăn. Chần xương bò với nước sôi khoảng 5 phút để khử mùi hôi, sau đó rửa sạch lại với nước.\n" +
                            "•\tThịt bò rửa sạch, thái mỏng.\n" +
                            "•\tGừng, hành tây, hành tím, tỏi bóc vỏ, nướng thơm.\n" +
                            "•\tỚt rửa sạch, băm nhuyễn.\n" +
                            "•\tRau thơm rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu nước dùng",
                    des = "•\tCho xương bò vào nồi, đổ nước xâm xấp mặt xương.\n" +
                            "•\tNêm thêm 1 muỗng canh muối, 1 muỗng cà phê hạt nêm và 2 viên đường phèn vào nồi.\n" +
                            "•\tĐun sôi nồi nước dùng, sau đó hớt bọt và hạ lửa nhỏ, hầm trong khoảng 2-3 tiếng cho đến khi nước dùng ngọt và trong.\n" +
                            "•\tCho quế, hồi, hoa hồi vào nồi nước dùng, hầm thêm 30 phút.\n" +
                            "•\tVớt xương bò ra khỏi nồi.\n"
                ),
                Procedure(
                    step = "Bước 3: Chần thịt bò",
                    des = "•\tCho nước sôi vào nồi khác, nêm thêm 1 muỗng cà phê muối.\n" +
                            "•\tChần nhanh thịt bò trong nước sôi cho đến khi thịt tái.\n" +
                            "•\tVớt thịt bò ra đĩa, để ráo nước.\n"
                ),
                Procedure(
                    step = "Bước 4: Trụng bánh phở",
                    des = "•\tCho bánh phở vào tô, trụng bánh phở trong nước dùng nóng cho đến khi bánh phở mềm.\n" +
                            "•\tVớt bánh phở ra tô.\n"
                ),
                Procedure(
                    step = "Bước 5: Hoàn thành",
                    des = "•\tXếp thịt bò lên trên bánh phở.\n" +
                            "•\tChan nước dùng nóng vào tô.\n" +
                            "•\tRắc thêm hành lá, ngò gai, ớt băm và hành phi lên trên.\n" +
                            "•\tThưởng thức phở bò cùng với chanh, ớt, tương ớt và rau thơm.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[3]
            )
        ),
        Product(
            id = 16,
            name = "Bún chả Hà Nội",
            image = R.drawable.muoibay_bunchahn_re,
            timeComplete = 120,
            ingredient = listOf(
                Ingredients().ingredientList[13].apply { quantity = "200 g" },
                Ingredients().ingredientList[34].apply { quantity = "100 g" },
                Ingredients().ingredientList[33].apply { quantity = "20 g" },
                Ingredients().ingredientList[2].apply { quantity = "500 g" },
                Ingredients().ingredientList[0].apply { quantity = "200 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[52].apply { quantity = "5 ml" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[53].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[22].apply { quantity = "4 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[14].apply { quantity = "50 g" },
                Ingredients().ingredientList[15].apply { quantity = "10 g" },
                Ingredients().ingredientList[17].apply { quantity = "30 g" },
                Ingredients().ingredientList[18].apply { quantity = "2 trái" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tThịt: Rửa sạch thịt ba chỉ, thịt nạc vai và mỡ heo. Thịt ba chỉ thái miếng vừa ăn, thịt nạc vai xay nhuyễn, mỡ heo thái hạt lựu.\n" +
                            "•\tRau củ: Rau sống, bắp chuối bào, dưa chuột rửa sạch, để ráo nước. Xà lách, húng quế, tía tô, húng lủi nhặt lá, rửa sạch. Cà rốt, đu đủ xanh gọt vỏ, bào sợi.\n" +
                            "•\tNước chấm: Pha nước mắm với nước lọc, đường, nước cốt chanh, ớt băm, tỏi băm. Nêm nếm gia vị cho vừa ăn\n"
                ),
                Procedure(
                    step = "Bước 2: Ướp thịt",
                    des = "•\tCho thịt ba chỉ vào tô lớn, ướp với hỗn hợp gia vị gồm nước mắm, đường, mật ong, tiêu xay, bột ngọt, dầu ăn, hành tím băm, tỏi băm, dầu hào (tùy thích). Trộn đều và để thịt thấm gia vị trong 30 phút.\n" +
                            "•\tCho thịt nạc vai xay vào tô khác, ướp với 1 muỗng canh nước mắm, 1 muỗng cà phê tiêu xay, 1 muỗng cà phê bột ngọt. Trộn đều và để thịt nghỉ 15 phút.\n"
                ),
                Procedure(
                    step = "Bước 3: Nướng thịt",
                    des = "•\tLàm nóng than hoa hoặc lò nướng.\n" +
                            "•\tXiên thịt ba chỉ vào que, xếp lên vỉ nướng. Nướng thịt đến khi chín vàng đều hai mặt.\n" +
                            "•\tVo viên thịt nạc vai xay, xếp lên vỉ nướng và nướng chín vàng đều.\n" +
                            "•\tNướng mỡ heo cho đến khi vàng giòn.\n"
                ),
                Procedure(
                    step = "Bước 4: Thưởng thức",
                    des = "•\tXếp bún, thịt nướng, rau sống, bắp chuối bào, dưa chuột vào đĩa.\n" +
                            "•\tChan nước chấm lên trên hoặc chấm trực tiếp.\n" +
                            "•\tThêm ớt tươi, chanh tươi tùy theo khẩu vị.\n"
                )
            ),
            category = listOf(
                Categories().listCategory[3]
            )
        ),
        Product(
            id = 17,
            name = "Bún bò Huế",
            image = R.drawable.muoitam_bunbohue_re,
            timeComplete = 150,
            ingredient = listOf(
                Ingredients().ingredientList[5].apply { quantity = "400 g" },
                Ingredients().ingredientList[34].apply { quantity = "200 g" },
                Ingredients().ingredientList[26].apply { quantity = "1 củ" },
                Ingredients().ingredientList[28].apply { quantity = "2 củ" },
                Ingredients().ingredientList[29].apply { quantity = "1 củ" },
                Ingredients().ingredientList[24].apply { quantity = "2 cây" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[13].apply { quantity = "200 g" },
                Ingredients().ingredientList[14].apply { quantity = "50 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
                Ingredients().ingredientList[17].apply { quantity = "20 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[43].apply { quantity = "2 trái" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Nấu nước dùng",
                    des = "•\tRửa sạch xương bò, thịt bắp bò và giò heo. Cho vào nồi lớn, đổ nước xâm xấp, hầm trong 2 tiếng để lấy nước dùng.\n" +
                            "•\tNướng gừng, sả và hành tây cho thơm. Cho vào nồi nước dùng, hầm thêm 30 phút.\n" +
                            "•\tNêm nếm gia vị mắm ruốc, muối, tiêu xay cho vừa ăn.\n" +
                            "•\tThêm hành lá và hành tây cắt khúc vào nồi nước dùng, tắt bếp.\n"
                ),
                Procedure(
                    step = "Bước 2: Sơ chế topping",
                    des = "•\tThịt bò tái thái mỏng. Chả cua và tiết lợn luộc chín, cắt miếng vừa ăn.\n" +
                            "•\tRau sống rửa sạch, để ráo nước.\n"
                ),
                Procedure(
                    step = "Bước 3: Thưởng thức",
                    des = "•\tXếp bún, thịt bò tái, chả cua, tiết lợn, rau sống vào tô.\n" +
                            "•\tChan nước dùng nóng hổi lên trên.\n" +
                            "•\tThêm ớt tươi, chanh tươi, và mắm ruốc (tùy thích) để tăng hương vị.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[3]
            )
        ),
        Product(
            id = 18,
            name = "Bánh mì",
            image = R.drawable.muoichin_banhmi_re,
            timeComplete = 60,
            ingredient = listOf(
                Ingredients().ingredientList[38].apply { quantity = "100 g" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[45].apply { quantity = "200 ml" },
                Ingredients().ingredientList[0].apply { quantity = "200 g" },
                Ingredients().ingredientList[18].apply { quantity = "1 trái" },
                Ingredients().ingredientList[19].apply { quantity = "2 trái" },
                Ingredients().ingredientList[15].apply { quantity = "20 g" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[50].apply { quantity = "1 chén" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Làm vỏ bánh mì",
                    des = "•\tCho bột mì, men nở, nước ấm, muối, đường và dầu ăn vào tô lớn. Trộn đều bằng tay hoặc máy đánh bột cho đến khi hỗn hợp mịn và dẻo.\n" +
                            "•\tNhào bột trong 10-15 phút cho đến khi bột trở nên đàn hồi và mịn.\n" +
                            "•\tBọc tô bột bằng khăn ẩm và ủ bột ở nơi ấm áp trong 1 tiếng cho đến khi bột nở gấp đôi.\n" +
                            "•\tChia bột thành những phần bằng nhau, vo tròn và để bột nghỉ thêm 15 phút.\n" +
                            "•\tTạo hình bánh mì theo ý thích (hình baguette, hình ổ loa,..) và đặt lên khay nướng đã lót giấy nến.\n" +
                            "•\tPhủ khăn ẩm lên khay bánh mì và ủ thêm 30 phút cho đến khi bánh mì nở gấp đôi.\n" +
                            "•\tLàm nóng lò nướng ở 180°C.\n" +
                            "•\tPhết một lớp mỏng nước lên mặt bánh mì.\n" +
                            "•\tNướng bánh mì trong 20-25 phút cho đến khi vàng đều.\n"
                ),
                Procedure(
                    step = "Bước 2: Làm nhân bánh mì",
                    des = "•\tThịt heo quay hoặc pate cắt lát mỏng.\n" +
                            "•\tDưa leo và cà rốt gọt vỏ, thái sợi.\n" +
                            "•\tRau thơm rửa sạch, cắt nhỏ.\n" +
                            "•\tTrộn ớt băm, mayonnaise, nước mắm và tương ớt với nhau để làm nước sốt.\n"
                ),
                Procedure(
                    step = "Bước 3: Thưởng thức",
                    des = "•\tCắt bánh mì theo chiều dọc.\n" +
                            "•\tXếp thịt heo quay hoặc pate, dưa leo, cà rốt, rau thơm vào trong bánh mì.\n" +
                            "•\tChan nước sốt lên bánh mì và thưởng thức.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[3]
            )
        ),
        Product(
            id = 19,
            name = "Bánh cuốn",
            image = R.drawable.haimuoi_banhcuon_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[39].apply { quantity = "200 g" },
                Ingredients().ingredientList[45].apply { quantity = "100 ml" },
                Ingredients().ingredientList[0].apply { quantity = "500 g" },
                Ingredients().ingredientList[64].apply { quantity = "50 g" },
                Ingredients().ingredientList[22].apply { quantity = "1 củ" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[24].apply { quantity = "2 cây" },
                Ingredients().ingredientList[42].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[18].apply { quantity = "1 trái" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Làm vỏ bánh",
                    des = "•\tCho bột gạo, nước, muối và dầu ăn vào tô lớn. Khuấy đều cho đến khi hỗn hợp mịn và không bị vón cục.\n" +
                            "•\tĐậy kín tô bột và để nghỉ trong 30 phút.\n" +
                            "•\tLàm nóng xửng hấp.\n" +
                            "•\tLấy một muỗng canh bột bánh, tráng mỏng đều trên mặt vải căng trong xửng. Hấp bánh trong khoảng 1 phút cho đến khi bánh chín mềm và trong suốt.\n" +
                            "•\tDùng dụng cụ gỡ bánh ra khỏi vải và đặt lên đĩa.\n"
                ),
                Procedure(
                    step = "Bước 2: Làm nhân bánh",
                    des = "•\tNgâm mộc nhĩ và nấm hương trong nước ấm cho nở mềm. Sau đó, vớt ra, rửa sạch và băm nhỏ.\n" +
                            "•\tPhi thơm hành tím băm với dầu ăn. Cho thịt băm vào xào săn.\n" +
                            "•\tThêm mộc nhĩ, nấm hương, nước mắm, tiêu xay, hạt nêm vào xào chung. Nêm nếm gia vị cho vừa ăn.\n" +
                            "•\tThêm hành lá, ngò rí cắt nhỏ vào đảo đều rồi tắt bếp.\n"
                ),
                Procedure(
                    step = "Bước 3: Làm nước chấm",
                    des = "•\tPha nước mắm với nước lọc, đường, nước cốt chanh, ớt băm, tỏi băm. Nêm nếm gia vị cho vừa ăn."
                ),
                Procedure(
                    step = "Bước 4: Thưởng thức",
                    des = "•\tXếp bánh cuốn lên đĩa. Cho nhân thịt băm xào lên trên.\n" +
                            "•\tChan nước chấm và ăn kèm với rau sống, bắp chuối bào, dưa chuột.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[3]
            )
        ),
        Product(
            id = 20,
            name = "Thịt ba chỉ nướng",
            image = R.drawable.haimot_thibachinuong_re,
            timeComplete = 45,
            ingredient = listOf(
                Ingredients().ingredientList[2].apply { quantity = "500 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[52].apply { quantity = "50 ml" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[53].apply { quantity = "20 g" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[19].apply { quantity = "2 trái" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tThịt ba chỉ rửa sạch, cắt miếng vừa ăn.\n" +
                            "•\tƯớp thịt ba chỉ với hỗn hợp gia vị đã chuẩn bị trong ít nhất 30 phút cho thấm đều.\n" +
                            "•\tRau củ rửa sạch, cắt khúc vừa ăn.\n" +
                            "•\tPha nước chấm chua ngọt theo khẩu vị.\n"
                ),
                Procedure(
                    step = "Bước 2: Nướng thịt",
                    des = "•\tCó thể nướng thịt ba chỉ bằng than hoa, lò nướng hoặc nồi chiên không dầu.\n" +
                            "•\tNếu nướng bằng than hoa, xếp thịt lên vỉ nướng và nướng trên than hồng cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng lò nướng, làm nóng lò ở 200°C, xếp thịt lên khay nướng và nướng trong khoảng 20 phút cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng nồi chiên không dầu, xếp thịt vào nồi chiên, set nhiệt độ 180°C và nướng trong khoảng 15-20 phút cho đến khi chín vàng đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tGắp thịt ba chỉ nướng ra đĩa, ăn kèm với rau củ, bánh tráng và nước chấm."
                ),
            ),
            category = listOf(
                Categories().listCategory[4]
            )
        ),
        Product(
            id = 21,
            name = "Tôm nướng",
            image = R.drawable.haihai_tomnuong_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[1].apply { quantity = "1 kg" },
                Ingredients().ingredientList[52].apply { quantity = "50 ml" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[53].apply { quantity = "20 g" },
                Ingredients().ingredientList[19].apply { quantity = "2 trái" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tTôm rửa sạch, bóc vỏ, bỏ đầu và chỉ đen.\n" +
                            "•\tƯớp tôm với hỗn hợp gia vị đã chuẩn bị trong ít nhất 30 phút cho thấm đều.\n" +
                            "•\tRau củ rửa sạch, cắt khúc vừa ăn.\n" +
                            "•\tPha nước chấm chua ngọt theo khẩu vị.\n"
                ),
                Procedure(
                    step = "Bước 2: Nướng tôm",
                    des = "•\tCó thể nướng tôm bằng than hoa, lò nướng hoặc nồi chiên không dầu.\n" +
                            "•\tNếu nướng bằng than hoa, xếp tôm lên vỉ nướng và nướng trên than hồng cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng lò nướng, làm nóng lò ở 200°C, xếp tôm lên khay nướng và nướng trong khoảng 10-15 phút cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng nồi chiên không dầu, xếp tôm vào nồi chiên, set nhiệt độ 180°C và nướng trong khoảng 10-12 phút cho đến khi chín vàng đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tGắp tôm nướng ra đĩa, ăn kèm với rau củ, bánh tráng và nước chấm."
                ),
            ),
            category = listOf(
                Categories().listCategory[4]
            )
        ),
        Product(
            id = 22,
            name = "Mực nướng",
            image = R.drawable.haiba_mucnuong_re,
            timeComplete = 60,
            ingredient = listOf(
                Ingredients().ingredientList[7].apply { quantity = "2 kg" },
                Ingredients().ingredientList[52].apply { quantity = "50 ml" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[53].apply { quantity = "20 g" },
                Ingredients().ingredientList[19].apply { quantity = "2 trái" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[36].apply { quantity = "50 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tMực rửa sạch, để ráo nước.\n" +
                            "•\tNếu là mực ống, cần rút hết phần nội tạng và túi mực.\n" +
                            "•\tCắt mực thành từng khoanh dày khoảng 2-3 cm.\n" +
                            "•\tƯớp mực với hỗn hợp gia vị đã chuẩn bị trong ít nhất 30 phút cho thấm đều.\n" +
                            "•\tRau củ rửa sạch, cắt khúc vừa ăn.\n" +
                            "•\tPha nước chấm chua ngọt theo khẩu vị.\n"
                ),
                Procedure(
                    step = "Bước 2: Nướng mực",
                    des = "•\tCó thể nướng mực bằng than hoa, lò nướng hoặc nồi chiên không dầu.\n" +
                            "•\tNếu nướng bằng than hoa, xếp mực lên vỉ nướng và nướng trên than hồng cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng lò nướng, làm nóng lò ở 200°C, xếp mực lên khay nướng và nướng trong khoảng 10-15 phút cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng nồi chiên không dầu, xếp mực vào nồi chiên, set nhiệt độ 180°C và nướng trong khoảng 10-12 phút cho đến khi chín vàng đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tGắp mực nướng ra đĩa, ăn kèm với rau củ, bánh tráng và nước chấm."
                ),
            ),
            category = listOf(
                Categories().listCategory[4]
            )
        ),
        Product(
            id = 23,
            name = "Sườn nướng",
            image = R.drawable.haibon_suonnuong_re,
            timeComplete = 100,
            ingredient = listOf(
                Ingredients().ingredientList[8].apply { quantity = "1 kg" },
                Ingredients().ingredientList[30].apply { quantity = "20 g" },
                Ingredients().ingredientList[52].apply { quantity = "50 ml" },
                Ingredients().ingredientList[22].apply { quantity = "5 củ" },
                Ingredients().ingredientList[23].apply { quantity = "5 tép" },
                Ingredients().ingredientList[43].apply { quantity = "3 trái" },
                Ingredients().ingredientList[53].apply { quantity = "20 g" },
                Ingredients().ingredientList[19].apply { quantity = "2 trái" },
                Ingredients().ingredientList[14].apply { quantity = "20 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[36].apply { quantity = "50 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tSườn rửa sạch, chặt thành miếng vừa ăn.\n" +
                            "•\tƯớp sườn với hỗn hợp gia vị đã chuẩn bị trong ít nhất 30 phút cho thấm đều.\n" +
                            "•\tRau củ rửa sạch, cắt khúc vừa ăn.\n" +
                            "•\tPha nước chấm chua ngọt theo khẩu vị.\n"
                ),
                Procedure(
                    step = "Bước 2: Nướng sườn",
                    des = "•\tCó thể nướng sườn bằng than hoa, lò nướng hoặc nồi chiên không dầu.\n" +
                            "•\tNếu nướng bằng than hoa, xếp sườn lên vỉ nướng và nướng trên than hồng cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng lò nướng, làm nóng lò ở 200°C, xếp sườn lên khay nướng và nướng trong khoảng 20-25 phút cho đến khi chín vàng đều.\n" +
                            "•\tNếu nướng bằng nồi chiên không dầu, xếp sườn vào nồi chiên, set nhiệt độ 180°C và nướng trong khoảng 15-20 phút cho đến khi chín vàng đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tGắp sườn nướng ra đĩa, ăn kèm với rau củ, bánh tráng và nước chấm."
                ),
            ),
            category = listOf(
                Categories().listCategory[4]
            )
        ),
        Product(
            id = 24,
            name = "Rau muống xào tỏi",
            image = R.drawable.hainam_raumuonxaotoi_re,
            timeComplete = 15,
            ingredient = listOf(
                Ingredients().ingredientList[16].apply { quantity = "100 g" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tRửa sạch rau, cắt thành miếng vừa ăn.\n" +
                            "•\tTỏi bóc vỏ, băm nhuyễn.\n"
                ),
                Procedure(
                    step = "Bước 2: Xào rau củ",
                    des = "•\tLàm nóng chảo với dầu ăn. Cho tỏi băm vào phi thơm.\n" +
                            "•\tCho rau củ vào xào với lửa lớn cho đến khi rau củ chín tái.\n" +
                            "•\tNêm nếm muối, tiêu xay cho vừa ăn.\n" +
                            "•\tXào thêm khoảng 1 phút cho rau củ thấm gia vị rồi tắt bếp.\n"
                ),
                Procedure(
                    step = "Bước 3: Thưởng thức",
                    des = "•\tMúc rau củ xào tỏi ra đĩa và thưởng thức nóng với cơm trắng."
                ),
            ),
            category = listOf(
                Categories().listCategory[5]
            )
        ),
        Product(
            id = 25,
            name = "Rau củ xào nấm",
            image = R.drawable.haisau_raucuxaonam_re,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[67].apply { quantity = "100 g" },
                Ingredients().ingredientList[64].apply { quantity = "50 g" },
                Ingredients().ingredientList[19].apply { quantity = "50 g" },
                Ingredients().ingredientList[29].apply { quantity = "1 củ" },
                Ingredients().ingredientList[23].apply { quantity = "3 tép" },
                Ingredients().ingredientList[51].apply { quantity = "1 muỗng canh" },
                Ingredients().ingredientList[50].apply { quantity = "1 chén" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[24].apply { quantity = "1 cây" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tRửa sạch rau củ, cắt thành miếng vừa ăn.\n" +
                            "•\tNấm rửa sạch, cắt lát mỏng.\n" +
                            "•\tHành tây bóc vỏ, cắt múi cau.\n" +
                            "•\tTỏi bóc vỏ, băm nhuyễn.\n" +
                            "•\tHành lá, ngò rí rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Xào rau củ và nấm",
                    des = "•\tLàm nóng chảo với dầu ăn. Cho tỏi băm vào phi thơm.\n" +
                            "•\tCho hành tây vào xào cho đến khi mềm và chuyển sang màu vàng.\n" +
                            "•\tCho nấm vào xào cùng cho đến khi nấm chín mềm.\n" +
                            "•\tCho rau củ vào xào cùng với nấm và hành tây.\n" +
                            "•\tNêm nếm nước tương, hạt nêm, tiêu xay cho vừa ăn.\n" +
                            "•\tXào rau củ và nấm thêm 2-3 phút cho đến khi chín đều\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tTắt bếp, cho hành lá, ngò rí vào đảo đều.\n" +
                            "•\tMúc rau củ xào nấm ra đĩa và thưởng thức nóng với cơm trắng hoặc bún.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[5]
            )
        ),
        Product(
            id = 26,
            name = "Gỏi rau củ",
            image = R.drawable.haibay_goiraucu_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[68].apply { quantity = "200 g" },
                Ingredients().ingredientList[0].apply { quantity = "500 g" },
                Ingredients().ingredientList[1].apply { quantity = "200 g" },
                Ingredients().ingredientList[58].apply { quantity = "50 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tRửa sạch rau củ, cắt sợi hoặc bào mỏng.\n" +
                            "•\tTôm sú rửa sạch, bóc vỏ, luộc chín và cắt đôi.\n" +
                            "•\tThịt heo quay thái mỏng.\n" +
                            "•\tLạc rang giã nhỏ.\n" +
                            "•\tRau thơm rửa sạch, cắt nhỏ.\n" +
                            "•\tPha nước mắm chua ngọt với tỷ lệ 1:1 đường và nước mắm, thêm ớt băm và chanh (tùy thích).\n"
                ),
                Procedure(
                    step = "Bước 2: Trộn gỏi",
                    des = "•\tCho rau củ, tôm sú, thịt heo quay vào tô lớn.\n" +
                            "•\tRưới nước mắm chua ngọt lên trên và trộn đều.\n" +
                            "•\tThêm lạc rang, rau thơm và trộn đều lần nữa.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tCho gỏi ra đĩa và thưởng thức.\n" +
                            "•\tGỏi rau củ có thể ăn kèm với bánh tráng cuốn hoặc bún.\n"
                ),
            ),
            category = listOf(
                Categories().listCategory[5]
            )
        ),
        Product(
            id = 27,
            name = "Canh măng chua",
            image = R.drawable.haitam_canhmangchua_re,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[32].apply { quantity = "100 g" },
                Ingredients().ingredientList[63].apply { quantity = "300 g" },
                Ingredients().ingredientList[33].apply { quantity = "20 g" },
                Ingredients().ingredientList[15].apply { quantity = "5 g" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tMăng chua rửa sạch, cắt miếng vừa ăn.\n" +
                            "•\tXương heo rửa sạch, chặt miếng vừa ăn.\n" +
                            "•\tThịt heo rửa sạch, cắt miếng vừa ăn.\n" +
                            "•\tCà chua rửa sạch, cắt múi cau.\n" +
                            "•\tBắp chuối bào rửa sạch, vắt ráo nước.\n" +
                            "•\tRau thơm rửa sạch, cắt nhỏ.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu canh",
                    des = "•\tCho xương heo vào nồi, đổ nước xâm xấp mặt xương và hầm trong khoảng 1 tiếng cho đến khi xương mềm và tiết ra nước ngọt.\n" +
                            "•\tCho măng chua vào nồi nước dùng, hầm thêm 30 phút cho măng mềm.\n" +
                            "•\tThêm thịt heo, cà chua vào nồi và nấu thêm 15 phút.\n" +
                            "•\tNêm nếm gia vị (muối, đường, nước mắm, hạt nêm) cho vừa ăn.\n" +
                            "•\tCho bắp chuối bào vào nồi và nấu thêm 5 phút.\n" +
                            "•\tTắt bếp, cho rau thơm vào nồi và đảo đều.\n"
                ),
                Procedure(
                    step = "Bước 3: Hoàn thành",
                    des = "•\tMúc canh măng chua ra tô và thưởng thức nóng với cơm trắng."
                ),
            ),
            category = listOf(
                Categories().listCategory[6]
            )
        ),
        Product(
            id = 28,
            name = "Canh bí đỏ nấu tôm",
            image = R.drawable.haichin_canhbidonautom_re,
            timeComplete = 30,
            ingredient = listOf(
                Ingredients().ingredientList[31].apply { quantity = "1 trái" },
                Ingredients().ingredientList[1].apply { quantity = "200 g" },
                Ingredients().ingredientList[63].apply { quantity = "200 g" },
                Ingredients().ingredientList[44].apply { quantity = "2 muỗng cà phê" },
                Ingredients().ingredientList[41].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[40].apply { quantity = "2 muỗng canh" },
                Ingredients().ingredientList[46].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[47].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[48].apply { quantity = "1 muỗng cà phê" },
                Ingredients().ingredientList[53].apply { quantity = "1 muỗng cà phê" },
            ),
            procedure = listOf(
                Procedure(
                    step = "Bước 1: Sơ chế nguyên liệu",
                    des = "•\tBí đỏ gọt vỏ, bỏ hạt, cắt miếng vừa ăn.\n" +
                            "•\tTôm tươi rửa sạch, bóc vỏ, bỏ đầu và chỉ đen.\n" +
                            "•\tXương heo rửa sạch, chặt miếng vừa ăn.\n"
                ),
                Procedure(
                    step = "Bước 2: Nấu nước dùng",
                    des = "•\tCho xương heo vào nồi, đổ nước xâm xấp mặt xương và hầm trong khoảng 1 tiếng cho đến khi xương mềm và tiết ra nước ngọt. Nêm nếm gia vị (muối, hạt nêm) cho vừa ăn."
                ),
                Procedure(
                    step = "Bước 3: Nấu canh",
                    des = "•\tCho bí đỏ vào nồi nước dùng, nấu sôi.\n" +
                            "•\tThêm tôm tươi vào nồi và nấu thêm 5 phút.\n" +
                            "•\tNêm nếm gia vị (nước mắm, tiêu xay) cho vừa ăn.\n" +
                            "•\tTắt bếp, cho hành lá, ngò rí vào nồi và đảo đều.\n"
                ),
                Procedure(
                    step = "Bước 4: Thưởng thức",
                    des = "•\tMúc canh bí đỏ nấu tôm ra tô và thưởng thức nóng với cơm trắng."
                ),
            ),
            category = listOf(
                Categories().listCategory[6]
            )
        )
    )

    fun getProduct(idProduct: Int): Product{
        return productList[idProduct]
    }

    fun getNameProduct(idProduct:Int):String{
        return productList[idProduct].name
    }

    fun getIngredient(idProduct:Int):List<Ingredient>{
        return productList[idProduct].ingredient
    }

    fun getProductsByCategoryId(categoryId: Int): List<Product> {
        return productList.filter { product ->
            product.category.any { it.id == categoryId }
        }
    }

    fun getRandomProducts(count: Int = 2): List<Product> {
        return productList.shuffled().take(count)
    }

    fun getLastFiveProducts(): List<Product> {
        return productList.takeLast(5)
    }
}