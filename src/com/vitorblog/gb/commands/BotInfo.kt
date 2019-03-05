package com.vitorblog.gb.commands

import com.vitorblog.gb.api.CMD
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class BotInfo : CMD {

    override var name = this.javaClass.simpleName
    override var p: User? = null
    override var mc: TextChannel? = null
    override var g:Guild? = null

    override
    fun execute(p:User, msg:Message, a:List<String>, mc:TextChannel, g:Guild, e: GuildMessageReceivedEvent) {
        var eb = embed()
            .setTitle("**Algumas informações sobre mim**", "https://gamersboard.com.br")
            .setDescription(
                    "**<:kotlin:545368913379065856> Rodando no __Kotlin 1.3.21__ \n" +
                    "<:jda:545369738143072277> Usando o __JDAFork (VitorBlog)__ \n" +
                    "<a:ping:545370788145135636> Com exatamente __${e.jda.ping}ms__\n" +
                    "$opoha Atualmente temos __${e.guild.members.size} Membros__\n" +
                    "<:virtus:549682556472393748> Usando __${((ram()/1024)/1024)}MB de Ram__\n" +
                    "$virtus Meu passa tempo favorito é __alisar a careca do ${rosset}__ **\n" +
                    "[Clique para entrar no fórum](https://gamersboard.com.br)")
            .setFooter(e.jda.selfUser.member().nickname, e.jda.selfUser.avatarUrl)
        send(p.asMention, eb.build())
    }

    fun ram():Long{
        val r = Runtime.getRuntime()
        return r.maxMemory() - r.freeMemory()
    }

}