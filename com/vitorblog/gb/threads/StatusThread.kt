package com.vitorblog.gb.threads

import com.vitorblog.gb.Bot
import net.dv8tion.jda.core.entities.Game
import java.util.*

class StatusThread(j:Bot) : TimerTask() {

    var jda = j.jda!!
    var bot = j
    var status = arrayListOf("Minecraft", "JetBrains IDE")
    var statusTwitch = arrayListOf("a careca do Rosset", "JetBrains IDE")
    val RANDOM = Random()

    override
    fun run() {
        try{
        var chance = Random().nextInt(100)
        if (chance >= 20){
            var entry = status.get(RANDOM.nextInt(status.size))
            jda.presence.game = Game.of(Game.GameType.DEFAULT, entry)
        } else {
            var entry = statusTwitch.get(RANDOM.nextInt(statusTwitch.size))
            jda.presence.game = Game.of(Game.GameType.STREAMING, entry, "https://www.twitch.tv/focablog")
        }
        }catch (E:Exception){bot.error(E)}
    }

}
