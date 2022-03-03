package com.bam.client.forms.game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GameField extends JPanel {

    private final int  _size = 10;
    JButton[][] __field = new JButton[_size][_size];

    private OnClickListener __fieldOnClickListener;

    public interface OnClickListener {
        void onClick(int x, int y) throws IOException;
    }

    public GameField()
    {

        setSize(500,500);
        setLayout(new GridLayout(10,10));
        createGameField();
        setVisible(true);

        __field[1][1].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/green.png"))));
        __field[8][8].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/blue.png"))));

    }


    public void setFieldOnClickListener(OnClickListener fieldOnClickListener) {
        __fieldOnClickListener = fieldOnClickListener;
    }

    private void createGameField()
    {
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size ; j++) {

                __field[i][j] = createFieldButton();

                int finalI = i;
                int finalJ = j;

                __field[i][j].addActionListener(e -> {
                    try {
                        __fieldOnClickListener.onClick(finalI, finalJ);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                add(__field[i][j]);
            }
        }
    }


    private JButton createFieldButton()
    {

        JButton _jButton = new JButton();
        _jButton.setSize(10,10);
        _jButton.setBackground(Color.white);

        return _jButton;
    }

    public void changeBug(String owner, boolean alive, int x, int y)
    {
        ImageIcon _imageIcon = getImage(owner, alive);
        __field[x][y].setIcon(_imageIcon);
    }

    private ImageIcon getImage(String owner, boolean alive)
    {
        if (owner.equals("Player-green")) {
            if (alive)
            {
                return new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/green.png")));
            }
            else return new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/bDestroy.png")));
        }
        else {
            if (alive)
            {
                return new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/blue.png")));
            }
            else return new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/bam/client/resource/gDestroy.png")));
        }

    }

}
