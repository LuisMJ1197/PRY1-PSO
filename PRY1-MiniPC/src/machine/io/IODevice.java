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
public interface IODevice {
    public boolean isBusy();
    
    public void setBusy(int value);
    
    public void reset();
    
    public void setControllerListener(IODeviceControllerListener listener);
    
    public void setListener(IODeviceListener listener);

    public String getEntry();
}
