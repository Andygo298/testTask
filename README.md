## Telegram info bot.
Простой Telegram бот, который приветствует пользователя и предоставляет ему интересные факты о городах.

Для запуска приложения необходимо
- Скачать исходный код проекта.
- Установить [NGROK](https://ngrok.com/).
- Запустить NGROK 
- В терминале ввести: ngrok http 8080
- Далее в файле ```application.properties``` в поле ```telegrambot.webHookPath``` скопировать сгенерированный ngrok HTTPS URL  (ngrok не закрывать)
```java 
      telegrambot.userName=@Simple_TownInfo_Bot
      telegrambot.botToken=1505623579:AAFY3g4cjPQzQpcm9ddRCaYvG4YMoGEmn04
      telegrambot.webHookPath=YOUR WEB HOOK PATH
```
- После чего потребуется также установить webhook url для api.telegram.org
- Пример запроса выглядит следующим образом :
    - `https://api.telegram.org/botYOUR_BOT_TOKEN/setWebhook?url=YOUR WEB HOOK PATH`


* В проекте используется in-memory db H2. И для наполнения БД некоторыми данными во время запуска приложения присутствует файл `data.sql`
    * консоль H2 database будет доступна по адресу `http://localhost:8080/h2-console` 
* Для управления данными, можно воспользоваться REST Webservice.
    * Для удобства, после запуска приложения перейти по ссылке `http://localhost:8080/swagger-ui.html#`

Технологии, использованные в данном проекте:
- Maven
- Spring Boot
- Spring MVC
- Spring Data
- Hibernate ORM
- Swagger
- H2 in-memory database
- Telegrambots spring boot starter
- Lombok