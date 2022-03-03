package com.bam.server;

import com.bam.MessageType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class GameServer {

    private final ServerSocket __serverSocket;

    public GameServer(ServerSocket serverSocket) {
        __serverSocket = serverSocket;
    }

    public void start() throws IOException {
        System.out.println("Server start...");

        GameField _gameField = new GameField(10);
        MessageType _type = null;
        Socket _socket = __serverSocket.accept();
        // поток чтения из сокета
        BufferedReader _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        // поток записи в сокет
        BufferedWriter _out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
        while (_type != MessageType.END)
        {
            String _msg = _in.readLine();
            String[] _strings = _msg.split("#");
            _type = MessageType.valueOf(_strings[0]);

            if (_type == MessageType.FIELD_CHANGE)
            {
               _out.write(_gameField.change(Integer.parseInt(_strings[1]), Integer.parseInt(_strings[2])));
               _out.flush();
            }


        }
    }
}
