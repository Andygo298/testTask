package com.github.andygo298.testTask.botapi.handler;

import com.github.andygo298.testTask.botapi.BotState;
import com.github.andygo298.testTask.botapi.InputMessageHandler;
import com.github.andygo298.testTask.service.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StartEchoHandler implements InputMessageHandler {

    private ReplyMessageService messageService;

    public StartEchoHandler(ReplyMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START_ECHO;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String chatId = inputMsg.getChatId().toString();

        SendMessage replyToUser = messageService.getReplyMessage(chatId, "reply.start_echo");
        replyToUser.setReplyMarkup(getInlineMessageButtons());
        replyToUser.setParseMode(ParseMode.HTML);
        return replyToUser;
    }
    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonInputName = new InlineKeyboardButton("ПРОДОЛЖИТЬ");
        InlineKeyboardButton buttonInfo = new InlineKeyboardButton("ИНФО");

        buttonInputName.setCallbackData("buttonContinue");
        buttonInfo.setCallbackData("buttonInfo");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonInputName);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonInfo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
