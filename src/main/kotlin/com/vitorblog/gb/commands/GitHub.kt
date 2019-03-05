package com.vitorblog.gb.commands

import com.vitorblog.gb.api.CMD
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class GitHub : CMD {

    override var name = this.javaClass.simpleName
    override var p: User? = null
    override var mc: TextChannel? = null
    override var g:Guild? = null

    override
    fun execute(p:User, msg:Message, a:List<String>, mc:TextChannel, g:Guild, e: GuildMessageReceivedEvent) {
        reply("https://github.com/VitorBlog/GBBot $taokey")
    }

}