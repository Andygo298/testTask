package com.github.andygo298.testTask.botapi.handler;

import com.github.andygo298.testTask.botapi.BotState;
import com.github.andygo298.testTask.botapi.InputMessageHandler;
import com.github.andygo298.testTask.botapi.cache.UserDataCache;
import com.github.andygo298.testTask.entity.Town;
import com.github.andygo298.testTask.repository.TownRepository;
import com.github.andygo298.testTask.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Component
public class AskTownHandler implements InputMessageHandler {

    private TownRepository townRepository;
    private ReplyMessageService messageService;
    private UserDataCache userDataCache;

    public AskTownHandler(ReplyMessageService messageService, TownRepository townRepository, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.townRepository = townRepository;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {

        StringBuilder outText = new StringBuilder();
        String townToSearch = message.getText();
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_TOWN);
        Optional<Town> townOptional = townRepository.findByTownName(townToSearch.toLowerCase().trim());
        if (townOptional.isPresent()) {
            Town town = townOptional.get();
            outText.append(String.format("► %1$-24s%2$-25s\n\n", messageService.getReplyText("message.town"), town.getTownName()))
                    .append(String.format("► %1$-25s%2$-25s\n\n", messageService.getReplyText("message.language"), town.getNativeLanguage()))
                    .append(String.format("► %1$-18s%2$-25s\n\n", messageService.getReplyText("message.population"), town.getPopulation().toString()))
                    .append(messageService.getReplyText("message.info_town")).append('\n')
                    .append(town.getTownInfo());
            return new SendMessage(message.getChatId().toString(), outText.toString());
        } else return new SendMessage(message.getChatId().toString(), messageService.getReplyText("error.not_found"));
    }

    @Override
    public BotState getHandlerName() {

        return BotState.ASK_TOWN;
    }
}
