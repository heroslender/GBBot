package com.vitorblog.gb.commands

import com.vitorblog.gb.Bot
import com.vitorblog.gb.api.CMD
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO
import kotlin.random.Random

class Skripter : CMD {

    override var name = this.javaClass.simpleName
    override var p: User? = null
    override var mc: TextChannel? = null
    override var g: Guild? = null
    val random = Random

    override
    fun execute(p: User, msg: Message, a: List<String>, mc: TextChannel, g: Guild, e: GuildMessageReceivedEvent) {
        if (msg.mentionedUsers.isNotEmpty()) {
            val user = msg.mentionedUsers.first()
            var chance = user.name.length * 2 * 5
            var text = "Eu uso as vezes"

            if (user.isVitor() || user.id.equals(e.jda.selfUser.id))
                chance = -50

            if (chance < 20) {
                text = "Skript? Que nojo!"
            } else if (chance > 80) {
                text = "Skript? Eu amo skript!"
            }

            var file = File("${random.nextInt()}.png")
            genShip(file, user, chance)

            var eb = embed()
                .setTitle("Sera que __${user.name}__ é Skripter?")
                .setDescription("A chance de **__${user.name}__** ser **__Skripter__** é de: __**$chance%**__\n__Acho que ouvi ele falar__ \"`$text`\" :scream:")
            Bot.instance.jda!!.getTextChannelById("552527348260667431").sendMessage("a").addFile(file).queue { x ->
                eb.setImage(x.attachments[0].url)
                send(p.asMention, eb.build())
                file.delete()
            }
            return
        }
        reply("você precisa mencionar alguem para usar este comando $opoha **|** `!skripter @AlexHackers#3452 `")
    }

    fun genShip(file: File, user: User, chance: Int) {
        val avatar = download(user.avatarUrl, "png")
        var img = resize(ImageIO.read(avatar), 500, 500)
        var heart = ImageIO.read(File("hearts/normal.png"))
        val g = img.graphics
        if (chance < 20) {
            heart = ImageIO.read(File("hearts/broken.png"))
        } else if (chance > 80) {
            heart = ImageIO.read(File("hearts/gift.png"))
        }
        val skript = ImageIO.read(File("others/skript.png"))

        g.drawImage(heart, 70, 350, null)
        g.drawImage(skript, 240, 380, null)

        ImageIO.write(img, "png", file)
        avatar.delete()
    }

    private fun resize(img: BufferedImage, height: Int, width: Int): BufferedImage {
        val tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        val resized = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2d = resized.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()
        return resized
    }

    fun download(link: String, ext: String): File {
        var file = File("${random.nextInt()}.$ext")
        val c = URL(link).openConnection()

        c.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)")
        c.connect()
        ImageIO.write(ImageIO.read(c.getInputStream()), ext, file)

        return file
    }

}