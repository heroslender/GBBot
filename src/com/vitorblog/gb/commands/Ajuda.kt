package com.vitorblog.gb.commands

import com.vitorblog.gb.api.CMD
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class Ajuda : CMD {

    override var name = this.javaClass.simpleName
    override var p: User? = null
    override var mc: TextChannel? = null
    override var g:Guild? = null

    override
    fun execute(p:User, msg:Message, a:List<String>, mc:TextChannel, g:Guild, e: GuildMessageReceivedEvent) {
        var eb = embed()
        eb.appendDescription("Olá, ${p.asMention}! Eu sou o robô do discord da Gamer's Board $taokey \n" +
                "\n" +
                "» Fórum: [gamersboard.com.br](https://gamersboard.com.br)")
        eb.setImage("https://cdn.discordapp.com/attachments/538408869735563265/538413696729219094/logo_gb.png")
        sendDM("", eb.build())
        eb = embed()
        eb.appendDescription(" **» Comandos do discord **\n" +
                "\n __!ajuda__ **|** Mostra essa mensagem $opoha" +
                "\n __!botinfo__ **|** Mostra as minhas informações $rosset" +
                "\n __!github__ **|** Mostra o meu repositório no GitHub <:github:552536695019667476>" +
                "\n __!skripter__ **|** Mostra a chance de uma pessoa ser Skripter $skripter")
        sendDM("", eb.build())
        eb = embed()
        eb.appendDescription(" **» Comandos do fórum**\n" +
                "\n __!recentes__ **|** Em breve $pqp" +
                "\n")
            .setFooter("Em desenvolvimento.", e.jda.getUserById("232201288862007296").avatarUrl)
        sendDM("", eb.build())
        reply("Enviei para você no privado $taokey")
    }

}