package ru.pruzhan.CitingBot.objects;

public class Message {

    private final int user_id;
    private final int peer_id;
    private final String text;


    public Message(int user_id, int peer_id, String text) {
        this.user_id = user_id;
        this.peer_id = peer_id;
        this.text = text;
    }

    public int getPeer_id() {
        return peer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getText() {
        return text;
    }

}
