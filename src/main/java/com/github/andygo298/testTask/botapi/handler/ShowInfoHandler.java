package com.github.andygo298.testTask.botapi.handler;

import com.github.andygo298.testTask.botapi.BotState;
import com.github.andygo298.testTask.botapi.InputMessageHandler;
import com.github.andygo298.testTask.botapi.cache.UserDataCache;
import com.github.andygo298.testTask.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


@Component
public class ShowInfoHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private UserDataCache userDataCache;

    public ShowInfoHandler(ReplyMessageService messageService, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_TOWN);
        return new SendMessage(message.getChatId().toString(), messageService.getReplyText("reply.info_text"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_INFO_MENU;
    }
}
