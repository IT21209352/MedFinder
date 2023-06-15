package com.example.medfinder

import java.net.Inet4Address

class User {
    var name: String?=null
    var email: String?=null
    var uid: String?=null
    var phone:String?=null
    var address:String?=null
    var type:String?=null

    constructor(){

    }

    constructor(name:String?,email:String?,uid:String,phone:String?,address: String?,type:String?){

        this.name=name
        this.email=email
        this.uid=uid
        this.phone=phone
        this.address=address
        this.type=type
    }

}