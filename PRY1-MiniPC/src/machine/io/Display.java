/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.io;

/**
 *
 * @author Luism
 */
public class Display implements IODevice {
    private String entry = "";
    private String id = "0002";
    private boolean busy = false;
    private IODeviceControllerListener controllerListener;
    private int processID;
    private IODeviceListener listener;
    
    public Display() {
        
    }
    
    @Override
    public boolean isBusy() {
        return this.busy;
    }
    
    @Override
    public void setBusy(int processID) {
        this.busy = true;
        this.processID = processID;
        this.controllerListener.start(processID);
    }
    
    @Override
    public void reset() {
        this.listener.callback("");
        this.entry = "";
        this.busy = false;
        this.processID = -1;
    }
    
    public void addKey(String ch) {
        this.entry += ch + "\n";
    }
    
    public String getText() {
        return this.entry;
    }

    @Override
    public void setControllerListener(IODeviceControllerListener listener) {
        controllerListener.start(this.processID);
    }

    @Override
    public void setListener(IODeviceListener listener) {
        this.listener = listener;
    }
}
