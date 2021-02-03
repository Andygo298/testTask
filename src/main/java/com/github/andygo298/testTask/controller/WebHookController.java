package com.github.andygo298.testTask.controller;

import com.github.andygo298.testTask.SimpleTownInfoTelegramBot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final SimpleTownInfoTelegramBot simpleTownInfoTelegramBot;

    public WebHookController(SimpleTownInfoTelegramBot simpleTownInfoTelegramBot) {
        this.simpleTownInfoTelegramBot = simpleTownInfoTelegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return simpleTownInfoTelegramBot.onWebhookUpdateReceived(update);
    }
}
