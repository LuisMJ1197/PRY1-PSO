/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import machine.io.IODevice;
import machine.io.IODeviceControllerListener;
import machine.io.Keyboard;

/**
 *
 * @author Luism
 */
public class DisplayConstroller implements KeyListener, IODeviceControllerListener {
    private JTextArea display;
    private IODevice keyboard;
    private JLabel processID;
    private boolean enabled = false;
    
    public DisplayConstroller(JTextArea keyboard, JLabel processID) {
        this.display = keyboard;
        this.processID = processID;
        this.keyboard.setControllerListener(this);
        this.display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.enabled) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.keyboard.reset();
                this.display.setText("");
                this.enabled = false;
            } else {
                char keyChar = e.getKeyChar();
                if (keyChar >= '0' && keyChar <= '9') {
                    ((Keyboard) this.keyboard).addKey(Character.toString(keyChar));
                }
                this.display.setText(((Keyboard) this.keyboard).getText());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }

    @Override
    public void start(int processID) {
        this.processID.setText(Integer.toString(processID));
        this.enabled = true;
    }
}
