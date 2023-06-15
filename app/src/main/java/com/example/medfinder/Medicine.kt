package com.example.medfinder

class medicine {
    var name: String?=null
    var Description: String?=null
    var uid:String?=null
    var img:String?=null
    constructor(){

    }
    constructor(name:String?,description:String?,uid:String?,img:String?){

        this.name=name
        this.Description=description
        this.uid=uid
        this.img=img

    }

}