package com.github.andygo298.testTask.config;

import com.github.andygo298.testTask.SimpleTownInfoTelegramBot;
import com.github.andygo298.testTask.botapi.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class TelegramBotConfig {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public SimpleTownInfoTelegramBot simpleTownInfoTelegramBot(TelegramFacade telegramFacade) {
        SimpleTownInfoTelegramBot simpleTownInfoTelegramBot = new SimpleTownInfoTelegramBot(telegramFacade);
        simpleTownInfoTelegramBot.setBotUserName(botUserName);
        simpleTownInfoTelegramBot.setBotToken(botToken);
        simpleTownInfoTelegramBot.setWebHookPath(webHookPath);
        return simpleTownInfoTelegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
