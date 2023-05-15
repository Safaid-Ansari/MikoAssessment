import java.util.HashMap;
import java.util.Map;

public class AssemblyInterpreter {
	
	private Map<String, Integer> registers;

    public AssemblyInterpreter() {
        registers = new HashMap<>();
    }

    public void execute(String program) {
        String[] lines = program.split("\n");
        for (String line : lines) {
            if (line.startsWith("MV")) {
                processMV(line);
            } else if (line.startsWith("ADD")) {
                processAdd(line);
            } else if (line.startsWith("SHOW")) {
                processShow(line);
            }
        }
    }

    private void processMV(String line) {
        String[] parts = line.split("[\\s,]+");
        String register = parts[1];
        int value = Integer.parseInt(parts[2].substring(1));
        registers.put(register, value);
    }

    private void processAdd(String line){
        String[] parts = line.split("[\\s,]+");
        String register = parts[1];
        int value;
        if (parts[2].startsWith("#")) {
            value = Integer.parseInt(parts[2].substring(1));
        } else {
            value = registers.getOrDefault(parts[2], 0);
        }
        registers.put(register, registers.get(register) + value);
    }

    private void processShow(String line) {
        String[] parts = line.split("[\\s,]+");
        String register = parts[1];
        System.out.println(register + ": " + registers.get(register));
    }
    
    public static void main(String[] args) {
    	String program = 
    			"MV REG1,#2000\n" +
                "MV REG2,#4000\n" +
                "ADD REG1, REG2\n" +
                "ADD REG1,600\n" +
                "SHOW REG1";

        AssemblyInterpreter interpreter = new AssemblyInterpreter();
        interpreter.execute(program);
    }
}