package com.vitorblog.gb

import java.lang.Exception

class Main {companion object {

    var bot:Bot? = null

    @JvmStatic
    fun main(args:Array<String>){
        bot = Bot(args[0])
        try { bot!!.start() }catch (E:Exception){ bot!!.error(E) }
        println("${bot!!.jda!!.selfUser.name} started")
    }


}}