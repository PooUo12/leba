package server.commands;

public class CommandsList {
    public static final String ADD = "add {element}: add a new element to the collection\n" ;
    public static final String ADD_IF = "add_if_max {element}: add a new element to the collection if its value is greater than the value of the largest element in this collection\n";
    public static final String CLEAR = "clear: clear the collection\n";
    public static final String SCRIPT = "execute_script file_name: read and execute the script from the specified file. The script contains server.commands in the same form in which they are entered by the user in interactive mode.\n";
    public static final String EXIT = "exit: terminate program (without saving to file)\n";
    public static final String INFO =  "info: print information about the collection to the standard output stream (type, initialization date, number of elements, etc.)\n";
    public static final String SHOW = "show: print to standard output all elements of the collection in string representation\n";
    public static final String UPDATE = "update id {element}: update the value of the collection element whose id is equal to the given one\n";
    public static final String HELP = "help: display help on available server.commands\n";
    public static final String REMOVE_BY = "remove_by_id id: remove element from collection by its id\n";
    public static final String REMOVE_GREATER = "remove_greater {element}: remove all items from the collection that exceed the specified\n";
    public static final String AVERAGE = "average_of_distance: print the average value of the distance field for all items in the collection\n";
    public static final String MAX_FROM = "max_by_from: output any object from the collection whose field from value is the maximum\n";
    public static final String FILTER = "filter_by_distance distance: output elements whose distance field value is equal to the specified\n";
    public static final String HEAD = "head: first element of collection";
}
