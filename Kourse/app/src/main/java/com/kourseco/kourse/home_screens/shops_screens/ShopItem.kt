package com.kourseco.kourse.home_screens.shops_screens

class ShopItem {

    var shopItemId : String? = null
    var shopItemImage : String? = null
    var shopItemName: String? = null
    var shopItemPrice: String? = null
    var shopItemCount : Int = 0


    constructor() {}

    constructor(shopItemId: String, shopItemImage: String, shopItemName: String,
                shopItemPrice: String, shopItemCount: Int) : this() {
        this.shopItemId = shopItemId
        this.shopItemImage = shopItemImage
        this.shopItemName = shopItemName
        this.shopItemPrice = shopItemPrice
        this.shopItemCount = shopItemCount
    }
}