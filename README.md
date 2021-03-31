# CitingBot
Just AI test case

Инструкция по запуску
1. Создать группу в ВК, в настройках группы в разделе "Работа с API" создать ключ доступа, в вкладке CallBack API указать версию(желательно последнюю) и включить сервер.
Указать тип событий "Входящие сообщения".
Подробнее - https://vk.com/dev/callback_api, https://vk.com/dev/bots_docs
2. git clone https://github.com/pruzhan/CitingBot.git
3. В файле src/main/resources/application.properties указать:
access_token = *созданный вами ключ доступа*, confirm_code = *строка, которую должен вернуть сервер на вкладке Callback API из п.1*, v = *Версия Callback API*
4. cd CitingBot
5. mvn clean package
6. cd target
7. java -jar CitingBot-1.0.jar
8. ngrok http 8080 (В новом окне в терминале)
9. В разделе "Работа с API" в поле "адрес" ввести http://*.ngrok.io/callback (вместо * сгенерированный ngrok адрес) и подтвердить адрес.
В случае успешной настройки адрес будет подтверждён и бот будет отвечать на ваши сообщения.
