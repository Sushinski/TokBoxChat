package com.sushinski.tokboxchat.model;


/**
 * EventBuses event POJO class
 */
public class EventMessage {
    public enum Type{
        INFO(0),
        ERROR(1),
        SUCCESS(2);
        private final int type_code;
        Type(int type_code){
            this.type_code = type_code;
        }
    }
    private final int message_id;
    private final Type type;

    public EventMessage(Type type, int message_id) {
        this.message_id = message_id;
        this.type = type;
    }

    public boolean isSuccessful() {
        return type != Type.ERROR;
    }

    public int getMessageId(){
        return this.message_id;
    }

    public Type getType() {
        return type;
    }
}