package com.example.medfinder

class inventry {
    var i_id: String?=null
    var name: String?=null
    var Dosage: String?=null
    var qty: String?=null
    var price:String?=null
    var exp:String?=null

    constructor(){

    }

    constructor(i_id:String,name:String?,Dosage:String?,qty:String?,price: String?,exp:String?){

        this.i_id=i_id
        this.name=name
        this.Dosage=Dosage
        this.qty=qty
        this.price=price
        this.exp=exp

    }
}