package com.github.andygo298.testTask.botapi.cache;

import com.github.andygo298.testTask.botapi.BotState;
import com.github.andygo298.testTask.entity.UserProfile;

public interface DataCache {

    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfile getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfile userProfileData);
}
