/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.ArrayList;
import machine.Kernel;

/**
 *
 * @author Luism
 */
public class OperatingSystem {
    private Loader loader = new Loader();
    private MCompiler compiler = new MCompiler();
    private Kernel kernel = Kernel.getInstance();
    private static OperatingSystem instance = new OperatingSystem();
    
    private OperatingSystem() {
    }
    
    public static OperatingSystem getInstance() {
        return OperatingSystem.instance;
    }
    
    public String loadPrograms(ArrayList<Program> programs) {
        String errorMsg = "";
        ArrayList<Program> notAcceptedPrograms = new ArrayList<>();
        for (Program program: programs) {
            this.compiler.compileProgram(program);
            if (!program.isAdmitted()) {
                notAcceptedPrograms.add(program);
                errorMsg += program.getError() + "\n";
            }
        }
        programs.removeAll(notAcceptedPrograms);
        errorMsg += this.loader.loadPrograms(programs);
        return errorMsg;
    }
    
    public void cleanMemory() {
        Kernel.getInstance().getMainMemory().clean();
        Kernel.getInstance().getDiskMemory().clean();
    }
}
