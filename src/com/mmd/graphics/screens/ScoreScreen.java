package com.mmd.graphics.screens;

import asciiPanel.AsciiPanel;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ScoreScreen implements Screen {
    public PlayScreen playScreen;

    public ScoreScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public String scoreScreenReadFromTxt(int x) {
        try {
            if (Files.readAllLines(Paths.get("resources/scores/final_scores.txt")).size() >= x
                    && Files.readAllLines(Paths.get("resources/scores/final_scores.txt")).get(x).length() > 0) {
                String line = Files.readAllLines(Paths.get("resources/scores/final_scores.txt")).get(x);
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void displayInAP(AsciiPanel terminal) {
        int x = 1;
        int y = 4;

        // create hashMap
        HashMap<Integer, String> scoresMap = new HashMap();
        // create treeMap to sort hashMap
        TreeMap<Integer, String> sortedScoresMap = new TreeMap<>();

        // for every line in the supplied txt file
        for (int i = 1; i < this.scoreScreenReadFromTxt(x).length(); i++) {
            // declare each line (String) from txt file
            String line = this.scoreScreenReadFromTxt(x);
            // declare score (Integer) between the ":" and "points" within each line
            int singleScore = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.indexOf("points")).trim());

            // store scores as key and values as associated string.
            scoresMap.put(singleScore, line); //overrides any string with the same key value

            // transfer hashMap k/v pair to treeMap (automatically sorts)
            sortedScoresMap.putAll(scoresMap);

            // Iterate to next score line in txt file
            x = x + 3;
        }

        if(!sortedScoresMap.isEmpty()) {
            for (Map.Entry<Integer, String> integerStringEntry : sortedScoresMap.entrySet()) {
                Map.Entry keyValuePair = (Map.Entry) integerStringEntry;
                // write to terminal -> all values of sorted TreeMap
                terminal.writeCenter(keyValuePair.getValue().toString(), y, Color.white);
                // print to next line in terminal/panel
                y++;
            }
        }

        terminal.writeCenter("SCORES:", 2, Color.orange);
        terminal.writeCenter("Press [ANY KEY] to go to the loading screen", 21, Color.gray);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) throws FileNotFoundException {
        playScreen.clearNameTxtFile();
        return new LoadingScreen(playScreen);
    }
}
