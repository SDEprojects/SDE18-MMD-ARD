package com.mmd.graphics.screens;

import asciiPanel.AsciiPanel;
import com.mmd.util.ConsoleManager;
import com.mmd.util.MenuTrieNode;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RoomsItemsSurviveHelpScreen implements Screen{
    public PlayScreen playScreen;
    ConsoleManager cm = new ConsoleManager();
    MenuTrieNode whatAreRooms = cm.read_xml().getChild(0).getChild(1);
    MenuTrieNode whatAreItems = cm.read_xml().getChild(0).getChild(2);
    MenuTrieNode howDoISurvive = cm.read_xml().getChild(0).getChild(3);

    public RoomsItemsSurviveHelpScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    @Override
    public void displayInAP(AsciiPanel terminal) {
        terminal.writeCenter(whatAreRooms.getTitle(), 4, Color.orange);
        terminal.writeCenter(whatAreRooms.getDescription().substring(0,66), 5, Color.white);
        terminal.writeCenter(whatAreRooms.getDescription().substring(67,128), 6, Color.white);

        terminal.writeCenter(whatAreItems.getTitle(), 8, Color.orange);
        terminal.writeCenter(whatAreItems.getDescription().substring(0,79), 9, Color.white);
        terminal.writeCenter(whatAreItems.getDescription().substring(80,135), 10, Color.white);
        terminal.writeCenter(whatAreItems.getDescription().substring(135,199), 11, Color.white);

        terminal.writeCenter(howDoISurvive.getTitle(), 13, Color.orange);
        terminal.writeCenter(howDoISurvive.getDescription().substring(0,55), 14, Color.white);

        terminal.writeCenter("Press [B] to return to the previous screen", 20,Color.gray);
        terminal.writeCenter("Press [Backspace] to return to the loadingScreen", 21, Color.gray);
        terminal.writeCenter("Press [Q] to quit", 22, Color.gray);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                return new LoadingScreen(playScreen);
            case KeyEvent.VK_B:
                return new StoryDetailsHelpScreen(playScreen);
            case KeyEvent.VK_Q:
                return new QuitScreen(playScreen);
        }
        return new LoadingScreen(playScreen);
    }
}
