package com.github.andygo298.testTask.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessageHandler {

    SendMessage handle(Message message);
    BotState getHandlerName();
}

