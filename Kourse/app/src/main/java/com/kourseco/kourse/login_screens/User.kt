package com.kourseco.kourse.login_screens

data class User(val phoneNumber: String,
                val uid : String,
                val name: String?,
                val address: String?) {
    constructor(): this("", "", null,null)
}