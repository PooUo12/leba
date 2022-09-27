package server;

import server.collection.CollectionManager;
import server.commands.*;
import utils.sendingUtils.Request;
import utils.sendingUtils.Response;

import java.util.ArrayList;
import java.util.List;

public class CommandDefiner {
    CollectionManager collectionManager;
    public CommandDefiner(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    public Response define(Request request) {
        Response response = null;
        List<Object> params = new ArrayList<>();

        switch (request.getName()) {
            case "help":
                HelpCommand help = new HelpCommand(collectionManager);
                response = help.execute(params);
                break;
            case "info":
                InfoCommand infoCommand = new InfoCommand(collectionManager);
                response = infoCommand.execute(params);
                break;
            case "show":
                ShowCommand showCommand = new ShowCommand(collectionManager);
                response = showCommand.execute(params);
                break;
            case "add":
                AddCommand addCommand = new AddCommand(collectionManager);
                params.add(request.getArg());
                response = addCommand.execute(params);
                break;
            case "update_id":
                UpdateCommand updateCommand = new UpdateCommand(collectionManager);
                List<Object> args = (ArrayList) request.getArg();
                params.add(args.get(0));
                params.add(args.get(1));
                response = updateCommand.execute(params);
                break;
            case "remove_by_id":
                RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(collectionManager);
                params.add(request.getArg());
                response = removeByIdCommand.execute(params);
                break;
            case "clear":
                ClearCommand clearCommand = new ClearCommand(collectionManager);
                response = clearCommand.execute(params);
                break;
            case "head":
                HeadCommand headCommand = new HeadCommand(collectionManager);
                response = headCommand.execute(params);
                break;
            case "add_if_max":
                AddIfMaxCommand addIfMaxCommand = new AddIfMaxCommand(collectionManager);
                params.add(request.getArg());
                response = addIfMaxCommand.execute(params);
                break;
            case "remove_greater":
                RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand(collectionManager);
                params.add(request.getArg());
                response = removeGreaterCommand.execute(params);
                break;
            case "average_of_distance":
                AverageDistanceCommand averageDistanceCommand = new AverageDistanceCommand(collectionManager);
                response = averageDistanceCommand.execute(params);
                break;
            case "max_by_from":
                MaxByFromCommand maxByFromCommand = new MaxByFromCommand(collectionManager);
                response = maxByFromCommand.execute(params);
                break;
            case "filter_by_distance":
                FilterByDistanceCommand filterByDistanceCommand = new FilterByDistanceCommand(collectionManager);
                params.add(request.getArg());
                response = filterByDistanceCommand.execute(params);
        }
        return response;
    }
}
