package com.bam.client;

import com.bam.MessagePattern;
import com.bam.client.forms.GameWindow;
import com.bam.client.forms.game.GameMainForm;
import com.bam.MessageType;

import java.io.*;
import java.net.Socket;

public class GameManager extends Thread {

    private GameWindow __gameWindow;

    private final Socket __clientSocket;

    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет

    public GameManager(Socket clientSocket) throws IOException {
        __clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(__clientSocket.getInputStream()));
        // писать туда же
        out = new BufferedWriter(new OutputStreamWriter(__clientSocket.getOutputStream()));
    }

    public GameWindow getGameWindow() {
        return __gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        if (__gameWindow != null)
            __gameWindow.setVisible(false);
        __gameWindow = gameWindow;

        __gameWindow.setFormActionListener(this::sendMessageToServer);

        __gameWindow.setVisible(true);
    }

    public void startGame(GameWindow window) {
        setGameWindow(window);
        __gameWindow.setVisible(true);
        start();

    }

    private void  sendMessageToServer(String msg) throws IOException {
        out.write(msg);
        out.flush();
    }

    @Override
    public void run() {
        try {

            while (true) {

                String _msg = in.readLine();
                __gameWindow.parseServerMessage(_msg);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
