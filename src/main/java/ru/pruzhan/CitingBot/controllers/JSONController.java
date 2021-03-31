package ru.pruzhan.CitingBot.controllers;

import org.json.JSONObject;
import ru.pruzhan.CitingBot.objects.Message;


public class JSONController {

    public static String getType(String jsonString) {
        return new JSONObject(jsonString).getString("type");
    }

    public static Message getNewMessage(String jsonString) {
        JSONObject jsonMessage = new JSONObject(jsonString).getJSONObject("object").getJSONObject("message");
        int peer_id = jsonMessage.getInt("peer_id");
        int user_id = jsonMessage.getInt("from_id");
        String text = jsonMessage.getString("text");
        return new Message(user_id, peer_id, text);
    }
}
