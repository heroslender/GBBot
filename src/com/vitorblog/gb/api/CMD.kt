package com.vitorblog.gb.api

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color
import java.io.File

interface CMD {

    //Arrumar as gambiarra
    var name:String
    var p:User?
    var mc:TextChannel?
    var g:Guild?
    val opoha get() = "<:opoha:540308642407120896>"
    val taokey get() = "<:taokey:546050885055283211>"
    val rosset get() = "<:rosset:540227428232986672>"
    val wegif get() = "<a:wegif:540228376233902105>"
    val skripter get() = "<:skripter:550766817086406677>"
    val passarin get() = "<a:passarin:540863567633711127>"
    val fudeu get() = "<:fudeu:549714964592721935>"
    val pqp get() = "<:pqp:541999434787979285>"
    val virtus get() = "<:virtus:549680873654583326>"

    fun execute(p:User, msg: Message, a:List<String>, mc:TextChannel, g:Guild, e: GuildMessageReceivedEvent)

    fun reply(s:String){
        mc!!.sendMessage("${p!!.asMention} **|** $s").queue()
    }

    fun reply(s:String, eb:MessageEmbed){
        mc!!.sendMessage(MessageBuilder().append("${p!!.asMention} **|** $s").setEmbed(eb).build()).queue()
    }

    fun send(s:String, eb:MessageEmbed){
        mc!!.sendMessage(MessageBuilder().append(s).setEmbed(eb).build()).queue()
    }

    fun sendDM(s:String){
        p!!.openPrivateChannel().queue({mc -> mc.sendMessage(s).queue()})
    }

    fun sendDM(s:String, eb:MessageEmbed){
        p!!.openPrivateChannel().queue({mc -> mc.sendMessage(MessageBuilder().append(s).setEmbed(eb).build()).queue()})
    }

    fun embed(): EmbedBuilder {
        return EmbedBuilder().setColor(Color.decode("#383840"))
    }

    fun User.isVitor():Boolean {
        return this.id.equals("232201288862007296")
    }

    fun User.member():Member {
        return g!!.getMember(this)
    }

}