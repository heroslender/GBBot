package com.vitorblog.gb

import kotlin.system.exitProcess

class Main {companion object {
    @JvmStatic
    fun main(args:Array<String>){
        if (args.isEmpty()) {
            Bot.logger.error("A token do bot nao foi defenida :|")
            exitProcess(1)
        }

        val bot = Bot(args[0])

        Bot.logger.info("${bot.jda.selfUser.name} started")
    }
}}