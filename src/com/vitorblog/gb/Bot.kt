package com.vitorblog.gb

import com.vitorblog.gb.api.CommandHandler
import com.vitorblog.gb.commands.Ajuda
import com.vitorblog.gb.commands.BotInfo
import com.vitorblog.gb.commands.GitHub
import com.vitorblog.gb.commands.Skripter
import com.vitorblog.gb.threads.StatusThread
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.lang.Exception
import java.util.*
import kotlin.reflect.full.createInstance

class Bot {

    var jda:JDA? = null
    var started = false
    var commands = arrayListOf(Ajuda(), BotInfo(), GitHub(), Skripter())

    companion object {
        val instance = Bot::class.createInstance()
    }

    fun start(token:String){
        jda = JDABuilder().setToken(token).buildAsync().awaitReady()
        jda!!.addEventListener(CommandHandler())
        started = true
        startStatus()
    }

    fun startStatus(){
        try {
            if (!started){
                return
            }
            var timer = Timer()
            timer.schedule(StatusThread(), 0L, 20000L)
        }catch (E:Exception){error(E)}
    }
    
    val msgs = arrayListOf("Mano, rolou um comunismo aqui", "Deveriamos ter investido mais na Usina Nuclear de Chernobil", "Soviet Union March")

    fun error(E:Exception){
        var msg = msgs.get(Random().nextInt(msgs.size-1))
        jda!!.getUserById("232201288862007296").openPrivateChannel().queue({ b -> b.sendMessage("${msg}```${E}\n    ${E.stackTrace.joinToString("\n    ")}```").queue() })
    }

}
