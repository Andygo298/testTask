package com.github.andygo298.testTask.botapi.cache;

import com.github.andygo298.testTask.botapi.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class UserDataCache implements DataCache{

    private Map<Integer, BotState> usersBotStates = new HashMap<>();
//    private Map<Integer, UserProfile> usersProfileData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.get(userId);
        if (isNull(botState)) {
            botState = BotState.START_ECHO;
        }
        return botState;
    }

}
