package ru.pruzhan.CitingBot.controllers;

import org.json.JSONObject;
import ru.pruzhan.CitingBot.objects.Message;

/**
 * Класс обработки JSON-строк
 * @author Dmitriy Fedrushkov
 */
 
public class JSONController {

    //Получение типа события из json-запроса
    public static String getType(String jsonString) {
        return new JSONObject(jsonString).getString("type");
    }

    //Получение данных о сообщении из json-запроса
    public static Message getNewMessage(String jsonString) {
        JSONObject jsonMessage = new JSONObject(jsonString).getJSONObject("object").getJSONObject("message");
        int peer_id = jsonMessage.getInt("peer_id");
        int user_id = jsonMessage.getInt("from_id");
        String text = jsonMessage.getString("text");
        return new Message(user_id, peer_id, text);
    }
}
