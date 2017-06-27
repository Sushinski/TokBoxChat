package com.sushinski.tokboxchat.model;


public class EventMessage {
    public enum Type{
        INFO(0),
        ERROR(1),
        SUCCESS(2);
        private int type_code;
        Type(int type_code){
            this.type_code = type_code;
        }
    }
    private final String message;
    private final int message_id;
    private final Type type;

    public EventMessage(Type type, int message_id, String message) {
        this.message_id = message_id;
        this.type = type;
        this.message = message;
    }

    public boolean isSuccessful() {
        return type != Type.ERROR;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageId(){
        return this.message_id;
    }

    public Type getType() {
        return type;
    }
}