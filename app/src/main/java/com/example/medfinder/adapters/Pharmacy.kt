package com.example.medfinder.adapters

class pharmacy {
    var name: String?=null
    var email: String?=null
    var uid: String?=null
    var phone:String?=null
    var address:String?=null
    var type:String?=null
    var reg_no:String?=null
    var approve:Boolean?=null


    constructor(){

    }

    constructor(name:String?,email:String?,uid:String,phone:String?,address: String?,type:String?,reg_no:String,approve:Boolean?){

        this.name=name
        this.email=email
        this.uid=uid
        this.phone=phone
        this.address=address
        this.type=type
        this.reg_no=reg_no
        this.approve=approve

    }
}