package com.vitorblog.gb

import com.vitorblog.gb.api.CommandHandler
import com.vitorblog.gb.commands.*
import com.vitorblog.gb.threads.StatusThread
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.security.auth.login.LoginException
import kotlin.system.exitProcess

class Bot(token: String) {

    companion object {
        lateinit var instance: Bot
        val logger: Logger = LoggerFactory.getLogger("Bot")
    }

    var jda: JDA
    var commands = arrayListOf(Ajuda(), BotInfo(), GitHub(), Skripter(), Bolsonaro())

    init {
        instance = this

        try {
            jda = JDABuilder()
                .setToken(token)
                .build()

            jda.addEventListener(CommandHandler())

            startStatus()
        } catch (e: LoginException) {
            logger.error("Não foi possivel iniciar o bot! Token invalida.")
            exitProcess(1)
        } catch (e: Exception) {
            logger.error("Ocurreu um erro!", e)
            exitProcess(1)
        }
    }


    fun startStatus() {
        try {
            val timer = Timer()
            timer.schedule(StatusThread(), 0L, 20000L)
        } catch (E: Exception) {
            error(E)
        }
    }

    private val msgs = arrayListOf(
        "Mano, rolou um comunismo aqui",
        "Deveriamos ter investido mais na Usina Nuclear de Chernobil",
        "Soviet Union March"
    )

    fun error(E: Exception) {
        val msg = msgs[Random().nextInt(msgs.size - 1)]

        jda.getUserById("232201288862007296").openPrivateChannel()
            .queue{
                it.sendMessage("$msg```$E\n    ${E.stackTrace.joinToString("\n    ")}```").queue()
            }
    }

}
