package com.vitorblog.gb

import com.vitorblog.gb.api.CommandHandler
import com.vitorblog.gb.commands.Ajuda
import com.vitorblog.gb.commands.BotInfo
import com.vitorblog.gb.threads.StatusThread
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.lang.Exception
import java.util.*

class Bot(token:String) {

    var jda:JDA? = null
    var jdaBuilder:JDABuilder
    var started = false
    var commands = arrayListOf(Ajuda(), BotInfo())

    init {
        jdaBuilder = JDABuilder().setToken(token)
    }

    fun start(){
        jda = jdaBuilder.buildAsync().awaitReady()
        addListener(CommandHandler())
        started = true
        startStatus()
    }

    fun addListener(e:ListenerAdapter){
        jda!!.addEventListener(e)
    }

    fun startStatus(){
        try {
            if (!started){
                return
            }
            var timer = Timer()
            timer.schedule(StatusThread(this), 0L, 20000L)
        }catch (E:Exception){error(E)}
    }

    fun error(E:Exception){
        var msgs = arrayListOf("Mano, rolou um comunismo aqui", "Deveriamos ter investido mais na Usina Nuclear de Chernobil", "Soviet Union March")
        var msg = msgs.get(Random().nextInt(msgs.size-1))
        jda!!.getUserById("232201288862007296").openPrivateChannel().queue({ b -> b.sendMessage("${msg}```${E}\n    ${E.stackTrace.joinToString("\n    ")}```").queue() })
    }

}