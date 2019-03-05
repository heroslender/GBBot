package com.vitorblog.gb.commands

import com.vitorblog.gb.api.CMD
import com.vitorblog.gb.api.WebHook
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class Bolsonaro : CMD {

    override var name = this.javaClass.simpleName
    override var p: User? = null
    override var mc: TextChannel? = null
    override var g:Guild? = null
    var webhook = WebHook("https://discordapp.com/api/webhooks/552577918006722561/fXQhWdWRiz4_ZkaYAnN2SniSgh8U0mAgxQf1r_bfmp99ZgGmrOML9LFsBhXhpPmuDlni")
    val msgs = arrayListOf("Benino veste azul Benina veste rosa", "Tem que se fude e acabo", "Skripter bom é skripter banido", "Se tem tinta na frente já sei que laga", "", "")

    override
    fun execute(p:User, msg:Message, a:List<String>, mc:TextChannel, g:Guild, e: GuildMessageReceivedEvent) {
        val msg = msgs.random()
        webhook.send("${p.asMention}, $msg, taokey? $taokey")
    }

}