package com.vitorblog.gb

class Main {companion object {
    @JvmStatic
    fun main(args:Array<String>){
        val bot = Bot.instance
        bot.start(args[0])
        println("${bot!!.jda!!.selfUser.name} started")
    }
}}