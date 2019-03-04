package com.vitorblog.gb.api

import com.vitorblog.gb.Main
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

class CommandHandler : ListenerAdapter() {

    override
    fun onGuildMessageReceived(e: GuildMessageReceivedEvent) {
        var p = e.author
        var msg = e.message
        var a = msg.contentRaw.split(" ")
        var g = e.guild
        var cmd = a[0].replace("!", "").replace("!!", "")

        if (e.channel.id.equals("538469711416131584")){
            msg.addReaction(g.getEmoteById(540305825806155776)).queue()
            msg.addReaction(g.getEmoteById(540305884270690339)).queue()
        }
        if (cooldown.contains(p)){
            e.channel.sendMessage("${p.asMention} **|** Vai com calma ai <:opoha:540308642407120896> Você precisa esperar um pouco para poder executar mais comandos <:taokey:546050885055283211>").queue()
            return
        }

        if (msg.contentRaw.startsWith("!", true) || msg.contentRaw.startsWith("!!", true) && !cooldown.contains(p)){
            try{
                var cmdd = Main.bot!!.commands.filter { b -> b.name.equals(cmd, true) }.firstOrNull()
                if (cmdd != null){

                    if (!e.channel.id.equals("537779130926891028") && !e.member.hasPermission(Permission.MANAGE_ROLES)){
                        e.channel.sendMessage("${p.asMention} **|** você só pode executar comandos no ${g.getTextChannelById("537779130926891028").asMention} <:taokey:546050885055283211>").queue({
                                b -> b.delete().queueAfter(5, TimeUnit.SECONDS)
                        })
                        return
                    }
                    cmdd.p = p
                    cmdd.mc = e.channel

                    cmdd.execute(p, msg, a, e.channel, g, e)
                    e.message.delete().queue()
                    if (!p.id.equals("232201288862007296"))
                        add(p)
                }
            }catch (E:Exception){
                Main.bot!!.error(E)
            }
        }
        if (e.message.mentionedUsers.contains(e.jda.selfUser)){
            if (a.size <= 1) {
                if (!p.id.equals("232201288862007296"))
                    add(p)
                e.channel.sendMessage("Olá, ${p.asMention}! Eu sou o robô do discord da __Gamer's Board__ <:taokey:546050885055283211> Para __saber mais__ __sobre mim__ use `!ajuda` <:joinha:541701246721982465>").queue()
            } else if (a[1].equals("taokey?", true)){
                e.channel.sendMessage("${p.asMention}, taokey <:taokey:546050885055283211>").queue()
            }
        }
    }
    var cooldown = ArrayList<User>()

    fun add(p: User){
        cooldown.add(p)
        var th = Thread(Runnable {
            TimeUnit.SECONDS.sleep(8)
            cooldown.remove(p)
        })
        th.start()
    }

    var hosts = arrayListOf("enxadahost", "hypehost", "battlehost", "phantomhost", "BlastHosting", "LostOnyx", "ReisHosting", "UltraHostBR")

    override
    fun onGuildMemberNickChange(e: GuildMemberNickChangeEvent) {
        if (e.newNick == null){
            return
        }
        hosts.forEach {
            b ->
            if (!e.newNick.replace(b, "", true).equals(e.newNick)){
                e.guild.controller.setNickname(e.member, e.newNick.replace(b, "TorradaHost", true)).queue()
            }
        }
    }

    override
    fun onGuildMemberJoin(e: GuildMemberJoinEvent) {
        hosts.forEach {
            b ->
            if (e.member.effectiveName.toLowerCase().contains(b)){
                e.guild.controller.setNickname(e.member, e.member.effectiveName.replace(b, "TorradaHost")).queue()
            }
        }
    }

}