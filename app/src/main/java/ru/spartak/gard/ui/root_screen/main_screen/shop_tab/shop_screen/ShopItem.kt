package ru.spartak.gard.ui.root_screen.main_screen.shop_tab.shop_screen

import ru.spartak.gard.R

sealed class ShopItem (val name:String, val imageId:Int, val price:Int) {
    object VBucks1000:ShopItem(name="Fortnite 1 000 V-Bucks", imageId = R.drawable.fortnite_image_tmp, 3000)
    object VBucks2800:ShopItem(name="Fortnite 2 800 V-Bucks", imageId = R.drawable.fortnite_image_tmp, 5000)
    object VBucks13500:ShopItem(name="Fortnite 13 500 V-Bucks", imageId = R.drawable.fortnite_image_tmp, 13500)
}