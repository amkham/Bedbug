package com.bam.client.forms;

import javax.swing.*;
import java.io.IOException;

public abstract class GameWindow extends JFrame {

    public interface FormActionListener
    {
        void sendMessage(String msg) throws IOException;
    }

    protected FormActionListener __formActionListener;

    public void setFormActionListener(FormActionListener formActionListener) {
        __formActionListener = formActionListener;
    }

    public GameWindow()
    {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    abstract  public void parseServerMessage(String msg);

}
