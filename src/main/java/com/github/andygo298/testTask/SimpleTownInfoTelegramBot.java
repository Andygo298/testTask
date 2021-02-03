package com.github.andygo298.testTask;

import com.github.andygo298.testTask.botapi.TelegramFacade;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Setter
public class SimpleTownInfoTelegramBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    private TelegramFacade telegramFacade;

    public SimpleTownInfoTelegramBot(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        final BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);

        return replyMessageToUser;
    }
}
