package client.validators;

import utils.collections.Route;
import utils.exceptions.IncorrectInputInScriptException;
import utils.sendingUtils.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    private final RouteAsker routeAsker;
    private final ScriptParser scriptParser = new ScriptParser(this);

    public CommandParser(RouteAsker routeAsker){
        this.routeAsker = routeAsker;
    }

    public List<Request> launchCommand(String[] userCommand) throws IncorrectInputInScriptException {
        List<Request> requests = new ArrayList<>();
        Request request;
        switch (userCommand[0]) {
            case "":
                System.out.println("Input is empty");
                return null;
            case "help":
            case "clear":
            case "show":
            case "info":
            case "remove_by_id":
            case "max_by_from":
            case "head":
            case "average_of_distance":
                request = new Request(null, userCommand[0]);
                requests.add(request);
                break;
            case "add":
            case "add_if_max":
            case "remove_greater":
                Route route = new Route(
                        -1,
                        routeAsker.askName(),
                        routeAsker.askCoordinates(),
                        LocalDate.now(),
                        routeAsker.askLocationFrom(),
                        routeAsker.askLocationTo(),
                        routeAsker.askDistance()
                );

                request = new Request(route, userCommand[0]);
                requests.add(request);
                break;
            case "update_id":
                int id;
                try {
                    id = Integer.parseInt(userCommand[1]);
                } catch (NumberFormatException e){
                    System.out.println("id must be int");
                    return null;
                }
                Route routeToUpdate = new Route(
                        -1,
                        routeAsker.askName(),
                        routeAsker.askCoordinates(),
                        LocalDate.now(),
                        routeAsker.askLocationFrom(),
                        routeAsker.askLocationTo(),
                        routeAsker.askDistance()
                );
                List<Object> args = new ArrayList<>();
                args.add(routeToUpdate);
                args.add(id);
                request = new Request(args, userCommand[0]);
                requests.add(request);
                break;
            case "execute_script":
                requests = scriptParser.parse(userCommand[1]);
                break;
            case "filter_by_distance":
                request = new Request(routeAsker.askDistance(), userCommand[0]);
                requests.add(request);
                break;
            case "exit":
                System.exit(0);
            default:
                Console.printLn("Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки.");
        }

        return requests;
    }
}
