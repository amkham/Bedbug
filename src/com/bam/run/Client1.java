package com.bam.run;

import com.bam.client.GameManager;
import com.bam.client.forms.game.GameField;
import com.bam.client.forms.game.GameMainForm;

import java.io.IOException;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket _socket = new Socket("localhost", 8080);
        GameManager _gameManager1 = new GameManager(_socket);
        GameField _gameField = new GameField();
        _gameManager1.startGame(new GameMainForm(_gameField));
    }
}
