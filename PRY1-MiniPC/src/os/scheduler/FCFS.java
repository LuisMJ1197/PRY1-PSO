/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.scheduler;

import java.util.Random;
import machine.Kernel;
import os.process.PCB;
import queue.IQueue;

/**
 *
 * @author Luism
 */
public class FCFS implements Scheduler {
    private IQueue<PCB> readyQueue;
    
    public FCFS(IQueue<PCB> readyQueue) {
        this.readyQueue = readyQueue;

    }
    
    @Override
    public void insert(PCB processPCB) {
        this.readyQueue.enqueue(processPCB);
        processPCB.getStatus().setValue(PCB.READY);
    }

    @Override
    public void remove(PCB processPCB) {
        if (processPCB==null) {
        } else {
            processPCB.getStatus().setValue(PCB.TERMINATED);
        }
    }

    @Override
    public void resume(PCB processPCB) {
        processPCB.getStatus().setValue(PCB.RUNNING);
    }

    @Override
    public void suspend(PCB processPCB) {
        processPCB.getStatus().setValue(PCB.WAITING);
    }

    @Override
    public PCB choose_next(int cpu) {
        PCB pcbR = null;
        for (PCB pcb: this.readyQueue.getList()) {
            if (Integer.parseInt(pcb.getCpuNumber().getValue()) == cpu) {
                pcbR = pcb;
                pcbR.getStatus().setValue(PCB.RUNNING);
                pcbR.getStartTime().setValue(Integer.toString(Kernel.getInstance().getProcessor().getProcessTime()));
                break;
            }
        }
        this.readyQueue.getList().remove(pcbR);
        return pcbR;
    }
    
}
