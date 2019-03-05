package com.vitorblog.gb.api

import net.dv8tion.jda.core.entities.Webhook
import net.dv8tion.jda.webhook.WebhookClient
import net.dv8tion.jda.webhook.WebhookClientBuilder

class WebHook(url:String) {

    var client:WebhookClient

    init {
        val builder = WebhookClientBuilder(url)
        client = builder.build()
    }

    fun send(s:String){
        client.send(s)
    }

}