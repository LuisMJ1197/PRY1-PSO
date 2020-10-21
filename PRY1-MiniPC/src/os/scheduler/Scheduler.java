/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.scheduler;

import os.process.PCB;

/**
 *
 * @author Luism
 */
public interface Scheduler {
    public void insert(PCB process);
    public void remove(PCB process);
    public void resume(PCB process);
    public void suspend(PCB process);
    public PCB choose_next(int cpu);
}
