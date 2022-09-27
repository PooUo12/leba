package client.validators;


import utils.exceptions.IncorrectInputInScriptException;
import utils.sendingUtils.Request;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Class that parse scripts from file
 */
public class ScriptParser {
    private final CommandParser commandParser;
    private int i = 0;

    public ScriptParser(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    /**
     * @param scriptName name of the script file
     */
    public List<Request> parse(String scriptName) {
        List<Request> requests = new ArrayList<>();
        try {
            i++;
            if (i >= 5) {
                throw new RuntimeException("Impossible to use so many inner scripts");
            }
            String line;
            BufferedInputStream buff = new BufferedInputStream(new FileInputStream(scriptName));
            Scanner s = new Scanner(buff);

            while (s.hasNext()) {
                line = s.nextLine();
                if (line.equals("execute_script " + scriptName)) {
                    System.out.println("Impossible to use same script from script");
                    break;
                }
                try {
                    List<Request> reqs = commandParser.launchCommand(line.split(" "));
                    requests.addAll(reqs);
                } catch (IncorrectInputInScriptException e){
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file name");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return requests;
    }
}
