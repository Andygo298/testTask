package com.github.andygo298.testTask.botapi;

import com.github.andygo298.testTask.SimpleTownInfoTelegramBot;
import com.github.andygo298.testTask.botapi.cache.UserDataCache;
import com.github.andygo298.testTask.service.MainMenuService;
import com.github.andygo298.testTask.service.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramFacade {

    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private SimpleTownInfoTelegramBot simpleTownInfoTelegramBot;
    private MainMenuService mainMenuService;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache,
                          @Lazy SimpleTownInfoTelegramBot simpleTownInfoTelegramBot, MainMenuService mainMenuService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.simpleTownInfoTelegramBot = simpleTownInfoTelegramBot;
        this.mainMenuService = mainMenuService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }


        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.START_ECHO;
                break;
            case "ИНФО":
                botState = BotState.SHOW_INFO_MENU;
                break;
            case "ASK_TOWN":
                botState = BotState.ASK_TOWN;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);
        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        final int userId = buttonQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Введи название города чтобы получить информацию:");


        if ("buttonInfo".equals(buttonQuery.getData())) {
            callBackAnswer = new SendMessage(chatId, "куча инфы будя тута");
            userDataCache.setUsersCurrentBotState(userId, BotState.START_ECHO);
        }
        return callBackAnswer;
    }

}
