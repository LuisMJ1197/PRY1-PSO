/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.Kernel;
import machine.io.Display;
import machine.io.IODeviceListener;
import machine.processor.Core;

/**
 *
 * @author Luism
 */
public class INT extends Instruction implements IODeviceListener {
    private static final int PRINT = 9;
    private static final int FINISH = 20;
    private static final int READ = 10;
    private final int value;
    private boolean done = false;
    
    public INT(Core cpu, int value) {
        this.height = -1;
        this.value = value;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        switch (value) {
            case PRINT:
                ((Display) Kernel.getInstance().getDisplay()).addKey(core.getPCB().getDx().getValue());
                Kernel.getInstance().getDisplay().setBusy(Integer.parseInt(core.getPCB().getPid().getValue()));
                this.movePc(1);
                break;
            case READ:
                Kernel.getInstance().getKeyboard().setBusy(Integer.parseInt(core.getPCB().getPid().getValue()));
                this.movePc(1);
                break;
            case FINISH:
                this.core.abortProcess();
                done = true;
                break;
        }
        return true;
    }

    @Override
    public void callback(String result) {
        switch (value) {
            case PRINT:
                done = true;
                break;
            case READ:
                done = true;
                if (Integer.parseInt(result) > 255) {
                    this.core.abortProcess();
                } else {
                    this.core.getPCB().getDx().setValue(result);
                }
                break;
        }
    }
    
    @Override
    public boolean isDone() {
        return this.done;
    }
}
