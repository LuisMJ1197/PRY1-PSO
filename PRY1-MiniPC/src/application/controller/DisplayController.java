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
import machine.Kernel;
import machine.io.Display;
import machine.io.IODevice;
import machine.io.IODeviceControllerListener;
import machine.io.Keyboard;

/**
 *
 * @author Luism
 */
public class DisplayController implements KeyListener, IODeviceControllerListener {
    private JTextArea displayTA;
    private IODevice display;
    private JLabel processID;
    private boolean enabled = false;
    
    public DisplayController(JTextArea displayTA, JLabel processID) {
        this.displayTA = displayTA;
        this.processID = processID;
        this.display = Kernel.getInstance().getDisplay();
        this.display.setControllerListener(this);
        this.displayTA.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.enabled) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.display.reset();
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
        this.displayTA.setText(this.display.getEntry());
        this.enabled = true;
    }
    
    public void reset() {
        this.displayTA.setEnabled(false);
        this.enabled = false;
    }
}
