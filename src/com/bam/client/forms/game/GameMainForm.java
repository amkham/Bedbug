package com.bam.client.forms.game;

import com.bam.MessagePattern;
import com.bam.MessageType;
import com.bam.client.forms.GameWindow;

import javax.swing.*;
import java.awt.*;


/**
 * Главная форма игры
 */
public class GameMainForm extends GameWindow {

    private JPanel panel1;
    private JButton __button1;
    private JPanel __gameFieldJPanel;

    private final GameField __gameField;


    public GameMainForm(GameField gameField) {

        setSize(1200, 1000);

        setContentPane(panel1);
        __gameFieldJPanel.setLayout(new GridLayout());

        __gameField = gameField;
        __gameField.setFieldOnClickListener((x, y) -> {
            String _msg = MessagePattern.generate(MessageType.FIELD_CHANGE, x + "#" + y);
            super.__formActionListener.sendMessage(_msg);
        });

        __gameFieldJPanel.add(__gameField);

    }

    @Override
    public void parseServerMessage(String msg) {
        String[] _strings = msg.split("#");
        MessageType _type = MessageType.valueOf(_strings[0]);

        if (_type == MessageType.SUCCESS) {
            String _owner = _strings[1];
            boolean _isAlive = Boolean.parseBoolean(_strings[2]);
            int _x = Integer.parseInt(_strings[3]);
            int _y = Integer.parseInt(_strings[4]);
            __gameField.changeBug(_owner, _isAlive, _x, _y);
        }

        if (_type == MessageType.ERROR) {
           showErrorWindow(_strings[1]);
        }
    }

    private void showErrorWindow(String msg)
    {
        JDialog d = new JDialog(getFrames()[0], "Error");
        // create a label
        JLabel l = new JLabel(msg);

        d.add(l);

        // setsize of dialog
        d.setSize(100, 100);

        // set visibility of dialog
        d.setVisible(true);
    }

}
