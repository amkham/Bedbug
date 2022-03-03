package com.bam;

public class MessagePattern {


    /**
     * Структура сообщения отправляемого на клиент
     * @param type
     * @param msg
     * @return
     */
    public static String generate(MessageType type, String msg) {

        return type.name() + "#" + msg + "\n";
    }



}
