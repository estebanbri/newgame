package org.example.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            up = true;
        }
        if(code == KeyEvent.VK_S) {
            down = true;
        }
        if(code == KeyEvent.VK_A) {
            left = true;
        }
        if(code == KeyEvent.VK_D) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            up = false;
        }
        if(code == KeyEvent.VK_S) {
            down = false;
        }
        if(code == KeyEvent.VK_A) {
            left = false;
        }
        if(code == KeyEvent.VK_D) {
            right = false;
        }
    }

}
