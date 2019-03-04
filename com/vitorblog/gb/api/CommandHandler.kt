package com.vitorblog.gb.api

import com.vitorblog.gb.Bot
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
        val p = e.author
        val msg = e.message
        var a = msg.contentRaw.split(" ")
        val g = e.guild
        val cmd = a[0].replace("!", "").replace("!!", "")

        if (e.channel.id.equals("538469711416131584")){
            msg.addReaction(g.getEmoteById(540305825806155776)).queue()
            msg.addReaction(g.getEmoteById(540305884270690339)).queue()
            return
        }
        if (cooldown.contains(p)){
            e.channel.sendMessage("${p.asMention} **|** Vai com calma ai <:opoha:540308642407120896> Você precisa esperar um pouco para poder executar mais comandos <:taokey:546050885055283211>").queue()
            return
        }

        if (msg.contentRaw.startsWith("!", true) || msg.contentRaw.startsWith("!!", true) && !cooldown.contains(p)){
            try{
                var cmdd = Bot.instance.commands.filter { b -> b.name.equals(cmd, true) }.firstOrNull()
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
                Bot.instance.error(E)
            }
        }
        if (e.message.mentionedUsers.contains(e.jda.selfUser) && a.size <= 1){
            if (!p.id.equals("232201288862007296"))
                add(p)
            e.channel.sendMessage("Olá, ${p.asMention}! Eu sou o robô do discord da __Gamer's Board__ <:taokey:546050885055283211> Para __saber mais__ __sobre mim__ use `!ajuda` <:joinha:541701246721982465>").queue()

        }
    }

    //Gambiarra dms
    var cooldown = ArrayList<User>()
    fun add(p: User){
        cooldown.add(p)
        var th = Thread(Runnable {
            TimeUnit.SECONDS.sleep(8)
            cooldown.remove(p)
        })
        th.start()
    }

}
