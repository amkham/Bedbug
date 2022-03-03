package com.bam.run;

import com.bam.client.GameManager;
import com.bam.client.forms.game.GameField;
import com.bam.client.forms.game.GameMainForm;
import com.bam.server.GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket _serverSocket = new ServerSocket(8080);
        GameServer _gameServer = new GameServer(_serverSocket);
        _gameServer.start();

    }
}
