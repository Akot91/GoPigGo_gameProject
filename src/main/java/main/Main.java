package main;

import gameFrame.GameScreen;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                GameScreen gs = new GameScreen();
            }
        });
    }
    
}
