/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import java.util.ArrayList;
import java.util.Random;
import os.process.PCB;
import os.scheduler.FCFS;
import os.scheduler.Scheduler;
import queue.IQueue;
import queue.MQueue;

/**
 *
 * @author Luism
 */
public class Processor {
    public static final int CPU_CORE1 = 1;
    public static final int CPU_CORE2 = 2;
    private final Core core1;
    private final Core core2;
    private ArrayList<os.process.Process> processList = new ArrayList<os.process.Process>();
    private IQueue<PCB> readyQueue = new MQueue();
    private IQueue<os.process.Process> waitingQueue = new MQueue();
    private Scheduler scheduler;
    private int time = 0;
    
    public Processor() {
        this.core1 = new Core(CPU_CORE1);
        this.core2 = new Core(CPU_CORE2);
        this.startReadyQueue();
    }
    
    public int getProcessTime() {
        return this.time;
    }
    
    public void setProcessList(ArrayList<os.process.Process> processList) {
        this.processList = processList;
    }
    
    public void addProcess(os.process.Process process) {
        this.processList.add(process);
    }
    
    public void start() {
        this.startReadyQueue();
    }
    
    private void assignCPU(os.process.Process proc) {
        proc.getPcb().getCpuNumber().setValue(Integer.toString(new Random().nextInt(2) + 1));
    }
    
    private void startReadyQueue() {
        this.processList.forEach((proc) -> {
            if (proc.getPcb().getStatus().getValue().equals(PCB.NEW)) {
                this.waitingQueue.enqueue(proc);
            } else {
                this.assignCPU(proc);
                this.readyQueue.enqueue(proc.getPcb());
            }
        });
        scheduler = new FCFS(this.readyQueue);
    }
    
    public ArrayList<os.process.Process> getProcessList() {
        return this.processList;
    }

    public boolean nextCycle() {
        if (this.core1.nextCycle() &&
            this.core2.nextCycle()) {
            return false;
        }
        this.time++;
        return true;
    }

    public Core getCore1() {
        return core1;
    }

    public Core getCore2() {
        return core2;
    }
    
    public PCB nextProcess(int core) {
        return this.scheduler.choose_next(core);
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }
    
}
