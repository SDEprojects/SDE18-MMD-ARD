package com.mmd.graphics.screens;

import asciiPanel.AsciiPanel;
import com.mmd.util.ConsoleManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoadingScreen implements Screen {
    PlayScreen playScreen;

    public LoadingScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    @Override
    public void displayInAP(AsciiPanel terminal) {
        ConsoleManager cm = new ConsoleManager();
        System.out.println(cm.l1 + cm.l2 + cm.l3);

        //terminal.writeCenter(cm.l1, 1, Color.black, Color.green);
        terminal.writeCenter(cm.l1, 2, Color.orange);
        terminal.writeCenter(cm.l2, 3, Color.orange);
        terminal.writeCenter(cm.l3, 20, Color.gray);

        // TODO: remove this later
        terminal.writeCenter("~matthew~ press <W> to see win screen", 15);
        terminal.writeCenter("~matthew~ press <L> to see lose screen", 16);


        terminal.writeCenter("[::]    Press [Enter] to start yet 'Another Random Destiny'    [::]", 11, Color.white);
        terminal.writeCenter("Press [Q] to quit", 22, Color.gray);
    }

    public Screen respondToUserInput(KeyEvent key) {
        System.out.println(key.getKeyCode());
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                return playScreen;
            case KeyEvent.VK_H:
                return new InitialHelpScreen(playScreen);
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
            case KeyEvent.VK_W:
                return new WinScreen(playScreen);
            case KeyEvent.VK_L:
                return new LoseScreen(playScreen);
            case KeyEvent.VK_Q:
                return new QuitScreen(playScreen);
        }
        return this;
//        return key.getKeyCode() == KeyEvent.VK_ENTER ? new TempScreen() : this;

    }
}
