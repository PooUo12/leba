import commands.*;
import managers.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "Lab";


            CollectionManager collectionManager = new CollectionManager();
            RouteAsker routeAsker = new RouteAsker(collectionManager, userScanner);
            FileManager fileManager = new FileManager("file");
            CommandManager commandManager = new CommandManager(
                    new AddCommand(collectionManager, routeAsker),
                    new AddIfMaxCommand(collectionManager, routeAsker),
                    new ClearCommand(collectionManager),
                    new ExecuteScriptCommand(),
                    new ExitCommand(),
                    new InfoCommand(collectionManager),
                    new SaveCommand(fileManager, collectionManager),
                    new ShowCommand(collectionManager),
                    new UpdateCommand(collectionManager, routeAsker),
                    new HelpCommand(),
                    new RemoveByIdCommand(collectionManager),
                    new RemoveGreaterCommand(collectionManager, routeAsker),
                    new AverageDistanceCommand(collectionManager, routeAsker),
                    new MaxByFromCommand(collectionManager),
                    new FilterByDistanceCommand(collectionManager, routeAsker),
                    new HeadCommand(collectionManager)
            );



            if(args.length == 0){
                Console.printLn("Using default filename: " + envVariable);
                collectionManager.setCollection(fileManager.readCollection());
            } else if(args.length > 1){
                Console.printError("More arguments than expected! (" + args.length  +", 1 expected)");
                commandManager.exit("");
            }
            Console console = new Console(commandManager, userScanner, routeAsker);
            console.interactiveMode();
        }
    }
}