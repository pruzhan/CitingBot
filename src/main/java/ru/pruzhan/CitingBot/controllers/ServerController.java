package ru.pruzhan.CitingBot.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pruzhan.CitingBot.objects.Message;

import java.util.Random;
import java.util.function.Predicate;

/**
 * Класс-контроллер сервера, обрабатывающий запросы и генерирующий на них ответ
 * @author Dmitriy Fedrushkov
 */

@RestController
public class ServerController {
    @Value("${access_token}")
    private String ACCESS_TOKEN;
    @Value("${confirm_code}")
    private String CONFIRM;
    @Value("${v}")
    private String V;

    private final RestTemplate template = new RestTemplate();

    private static final String CALLBACK_EVENT_CONFIRM = "confirmation";
    private static final String CALLBACK_EVENT_NEW_MESSAGE = "message_new";

    //Обработка ответа на события Callback API от сервера
    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody
    String callbackResponse(@RequestBody String request) {
        //Проверка условия, является ли событием подтверждение Callback API или нет
        Predicate<String> isConfirm = s -> s.equals(CALLBACK_EVENT_CONFIRM);
        return isConfirm.test(JSONController.getType(request)) ? CONFIRM : sendMessage(request);
    }

    //Отправка сообщения
    private String sendMessage(String request) {
        try {
            //Если событие - новое сообщение, сформировать ответ для отправки ответного сообщения
            if (JSONController.getType(request).equals(CALLBACK_EVENT_NEW_MESSAGE))
                template.getForObject(buildMessage(JSONController.getNewMessage(request)), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Сервер должен вернуть статус ОК
        return "OK";
    }

    //Формирование запроса для отправки сообщения ботом
    private String buildMessage(Message message) {
        String VK_SEND_MESSAGE = "https://api.vk.com/method/messages.send";
        String textMessage = "[id" + message.getUser_id() + "|Human] say: " + message.getText();
        return VK_SEND_MESSAGE + "?peer_id=" + message.getPeer_id()  + "&message=" + textMessage +
                "&random_id=" + new Random().nextInt() + "&access_token=" + ACCESS_TOKEN + "&v=" + V;
    }
}
