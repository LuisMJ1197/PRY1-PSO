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
import os.process.PCB;

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
    
    public String getProcessID() {
        return this.core.getPCB().getPid().getValue();
    }
    
    @Override
    public boolean execute() {
        switch (value) {
            case PRINT:
                this.core.getPCB().getListOfIO().setValue("00010000");
                ((Display) Kernel.getInstance().getDisplay()).addKey(core.getPCB().getDx().getValue());
                Kernel.getInstance().getDisplay().setBusy(Integer.parseInt(core.getPCB().getPid().getValue()), this);
                break;
            case READ:
                this.core.getPCB().getListOfIO().setValue("00000002");
                Kernel.getInstance().getKeyboard().setBusy(Integer.parseInt(core.getPCB().getPid().getValue()), this);
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
                this.core.getPCB().getStatus().setValue(PCB.RUNNING);
                this.movePc(1);
                break;
            case READ:
                this.movePc(1);
                done = true;
                if (result.isEmpty() || Integer.parseInt(result) > 255) {
                    this.core.abortProcess();
                } else {
                    this.core.getPCB().getStatus().setValue(PCB.RUNNING);
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
