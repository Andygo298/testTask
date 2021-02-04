package com.github.andygo298.testTask.botapi.cache;

import com.github.andygo298.testTask.botapi.BotState;

public interface DataCache {

    void setUsersCurrentBotState(int userId, BotState botState);
    BotState getUsersCurrentBotState(int userId);

}
