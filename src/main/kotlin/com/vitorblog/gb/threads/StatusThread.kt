package com.vitorblog.gb.threads

import com.vitorblog.gb.Bot
import net.dv8tion.jda.core.entities.Game
import java.util.*

class StatusThread : TimerTask() {

    val status = arrayListOf("Minecraft", "JetBrains IDE", "shampoo na careca do Rosset")
    val statusTwitch = arrayListOf("a careca do Rosset", "JetBrains IDE")
    val bot = Bot.instance
    val jda = bot.jda!!
    val random = Random()

    override
    fun run() {
        try{
        var chance = random.nextInt(100)
        if (chance >= 20){
            var entry = status.get(Random().nextInt(status.size))
            jda.presence.game = Game.of(Game.GameType.DEFAULT, entry)
        } else {
            var entry = statusTwitch.get(Random().nextInt(statusTwitch.size))
            jda.presence.game = Game.of(Game.GameType.STREAMING, entry, "https://www.twitch.tv/focablog")
        }
        }catch (E:Exception){bot.error(E)}
    }

}