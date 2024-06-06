package com.example.recipeapp.data.static_data

import com.example.recipeapp.R

data class Ingredient(
    val id:Int,
    val name:String,
    val image:Int,
    var quantity:String,
)

class Ingredients{
    var ingredientList = mutableListOf(
        Ingredient(
            id = 0,
            name = "Thịt heo",
            image = R.drawable.khong_thitheo_ing,
            quantity = ""
        ),
        Ingredient(
            id = 1,
            name = "Tôm sú",
            image = R.drawable.mot_tomsu_ing,
            quantity = ""
        ),
        Ingredient(
            id = 2,
            name = "Thịt ba chỉ",
            image = R.drawable.hai_thibachi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 3,
            name = "Chả lụa",
            image = R.drawable.ba_chalua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 4,
            name = "Thịt heo xay",
            image = R.drawable.bon_thitheoxay_ing,
            quantity = ""
        ),
        Ingredient(
            id = 5,
            name = "Bắp bò",
            image = R.drawable.nam_thitbapbo_ing,
            quantity = ""
        ),
        Ingredient(
            id = 6,
            name = "Thịt gà",
            image = R.drawable.sau_thitga_ing,
            quantity = ""
        ),
        Ingredient(
            id = 7,
            name = "Mực",
            image = R.drawable.bay_muc_ing,
            quantity = ""
        ),
        Ingredient(
            id = 8,
            name = "Sườn non",
            image = R.drawable.tam_suonnon_ing,
            quantity = ""
        ),
        Ingredient(
            id = 9,
            name = "Cá",
            image = R.drawable.chin_ca_ing,
            quantity = ""
        ),
        Ingredient(
            id = 10,
            name = "Thịt nạm",
            image = R.drawable.muoi_thitnam_ing,
            quantity = ""
        ),
        Ingredient(
            id = 11,
            name = "Giò heo",
            image = R.drawable.muoimot_gioheo_ing,
            quantity = ""
        ),
        Ingredient(
            id = 12,
            name = "Thịt bò tái",
            image = R.drawable.muoihai_thitbotai_ing,
            quantity = ""
        ),
        Ingredient(
            id = 13,
            name = "Chả cua",
            image = R.drawable.muoiba_chacua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 14,
            name = "Xà lách",
            image = R.drawable.muoibon_xalach_ing,
            quantity = ""
        ),
        Ingredient(
            id = 15,
            name = "Rau thơm",
            image = R.drawable.muoilam_rauthom_ing,
            quantity = ""
        ),
        Ingredient(
            id = 16,
            name = "Rau muống",
            image = R.drawable.muoisau_raumuong_ing,
            quantity = ""
        ),
        Ingredient(
            id = 17,
            name = "Giá",
            image = R.drawable.muoibay_giado_ing,
            quantity = ""
        ),
        Ingredient(
            id = 18,
            name = "Dưa leo",
            image = R.drawable.muoitam_dualeo_ing,
            quantity = ""
        ),
        Ingredient(
            id = 19,
            name = "Cà rốt",
            image = R.drawable.muoichin_carot_ing,
            quantity = ""
        ),
        Ingredient(
            id = 20,
            name = "Chuối chát",
            image = R.drawable.haimuoi_chuoichat_ing,
            quantity = ""
        ),
        Ingredient(
            id = 21,
            name = "Khế chua",
            image = R.drawable.haimot_khechua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 22,
            name = "Hành tím",
            image = R.drawable.haihai_hanhtim_ing,
            quantity = ""
        ),
        Ingredient(
            id = 23,
            name = "Tỏi",
            image = R.drawable.haiba_toi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 24,
            name = "Hành lá",
            image = R.drawable.haibon_hanhla_ing,
            quantity = ""
        ),
        Ingredient(
            id = 25,
            name = "Ngò gai",
            image = R.drawable.hailam_ngogai_ing,
            quantity = ""
        ),
        Ingredient(
            id = 26,
            name = "Gừng",
            image = R.drawable.haisau_gung_ing,
            quantity = ""
        ),
        Ingredient(
            id = 27,
            name = "Riềng",
            image = R.drawable.haibay_rieng_ing,
            quantity = ""
        ),
        Ingredient(
            id = 28,
            name = "Sả",
            image = R.drawable.haitam_sa_ing,
            quantity = ""
        ),
        Ingredient(
            id = 29,
            name = "Hành tây",
            image = R.drawable.haichin_hanhtay_ing,
            quantity = ""
        ),
        Ingredient(
            id = 30,
            name = "Lá chanh",
            image = R.drawable.bamuoi_lachanh_ing,
            quantity = ""
        ),
        Ingredient(
            id = 31,
            name = "Bí đỏ",
            image = R.drawable.bamot_bido_ing,
            quantity = ""
        ),
        Ingredient(
            id = 32,
            name = "Măng chua",
            image = R.drawable.bahai_mangchua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 33,
            name = "Bắp chuối",
            image = R.drawable.baba_bapchuoi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 34,
            name = "Bún tươi",
            image = R.drawable.babon_buntuoi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 35,
            name = "Miến",
            image = R.drawable.banam_mien_ing,
            quantity = ""
        ),
        Ingredient(
            id = 36,
            name = "Bánh tráng",
            image = R.drawable.basau_banhtrang_ing,
            quantity = ""
        ),
        Ingredient(
            id = 37,
            name = "Mì quảng",
            image = R.drawable.babay_miquang_ing,
            quantity = ""
        ),
        Ingredient(
            id = 38,
            name = "Bột mì",
            image = R.drawable.batam_botmi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 39,
            name = "Bột gạo",
            image = R.drawable.bachin_botgao_ing,
            quantity = ""
        ),
        Ingredient(
            id = 40,
            name = "Nước mắm",
            image = R.drawable.bonmuoi_nuocmam_ing,
            quantity = ""
        ),
        Ingredient(
            id = 41,
            name = "Đường",
            image = R.drawable.bonmot_duong_ing,
            quantity = ""
        ),
        Ingredient(
            id = 42,
            name = "Nước cốt chanh",
            image = R.drawable.bonhai_nuoccotchanh_ing,
            quantity = ""
        ),
        Ingredient(
            id = 43,
            name = "Ớt",
            image = R.drawable.bonba_ot,
            quantity = ""
        ),
        Ingredient(
            id = 44,
            name = "Muối",
            image = R.drawable.bonbon_muoi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 45,
            name = "Nước lọc",
            image = R.drawable.bonnam_nuocloc_ing,
            quantity = ""
        ),
        Ingredient(
            id = 46,
            name = "Bột ngọt",
            image = R.drawable.bonsau_botngot_ing,
            quantity = ""
        ),
        Ingredient(
            id = 47,
            name = "Tiêu",
            image = R.drawable.bonbay_tieu_ing,
            quantity = ""
        ),
        Ingredient(
            id = 48,
            name = "Hạt nêm",
            image = R.drawable.bontam_hatnem_ing,
            quantity = ""
        ),
        Ingredient(
            id = 49,
            name = "Nước cốt dừa",
            image = R.drawable.bonchin_nuocdua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 50,
            name = "Nước tương",
            image = R.drawable.nammuoi_nuoctuong_ing,
            quantity = ""
        ),
        Ingredient(
            id = 51,
            name = "Dầu ăn",
            image = R.drawable.nammot_dauan_ing,
            quantity = ""
        ),
        Ingredient(
            id = 52,
            name = "Mật ong",
            image = R.drawable.namhai_matong_ing,
            quantity = ""
        ),
        Ingredient(
            id = 53,
            name = "Ngũ vị hương",
            image = R.drawable.namba_nguvihuong_ing,
            quantity = ""
        ),
        Ingredient(
            id = 54,
            name = "Tương ớt",
            image = R.drawable.nambon_tuongot_ing,
            quantity = ""
        ),
        Ingredient(
            id = 55,
            name = "Trứng gà",
            image = R.drawable.namnam_trungga_ing,
            quantity = ""
        ),
        Ingredient(
            id = 56,
            name = "Trứng cút",
            image = R.drawable.namsau_trungcut_ing,
            quantity = ""
        ),
        Ingredient(
            id = 57,
            name = "Me",
            image = R.drawable.nambay_me_ing,
            quantity = ""
        ),
        Ingredient(
            id = 58,
            name = "Lạc",
            image = R.drawable.namtam_lacrang_ing,
            quantity = ""
        ),
        Ingredient(
            id = 59,
            name = "Đậu xanh",
            image = R.drawable.namchin_dauxanh_ing,
            quantity = ""
        ),
        Ingredient(
            id = 60,
            name = "Hạt sen",
            image = R.drawable.saumuoi_hatsen_ing,
            quantity = ""
        ),
        Ingredient(
            id = 61,
            name = "Sữa tươi",
            image = R.drawable.saumot_suatuoi_ing,
            quantity = ""
        ),
        Ingredient(
            id = 62,
            name = "Sữa chua",
            image = R.drawable.sauhai_suachua_ing,
            quantity = ""
        ),
        Ingredient(
            id = 63,
            name = "Xương heo",
            image = R.drawable.sauba_xuongheo_ing,
            quantity = ""
        ),
        Ingredient(
            id = 64,
            name = "Nấm",
            image = R.drawable.saubon_nam_ing,
            quantity = ""
        ),
        Ingredient(
            id = 65,
            name = "Đường phèn",
            image = R.drawable.saunam_duongphen_ing,
            quantity = ""
        ),
        Ingredient(
            id = 66,
            name = "Phở",
            image = R.drawable.sausau_pho_ing,
            quantity = ""
        ),
        Ingredient(
            id = 67,
            name = "Súp lơ",
            image = R.drawable.saubay_suploxanh_ing,
            quantity = ""
        ),
        Ingredient(
            id = 68,
            name = "Bắp cải",
            image = R.drawable.sautam_bapcai_ing,
            quantity = ""
        )
    )
}