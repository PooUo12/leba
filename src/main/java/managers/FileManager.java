package managers;

import collections.Coordinates;
import collections.LocationFrom;
import collections.LocationTo;
import collections.Route;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class FileManager {
    private final String envVariable;
    private final XStream xstream = new XStream(new StaxDriver());


    public FileManager(String envVariable) {
        this.envVariable = envVariable;
        //XStream = new XStream(new StaxDriver());


        xstream.alias("locationTo", LocationTo.class);
        xstream.alias("locationFrom", LocationFrom.class);
        xstream.alias("coordinates", Coordinates.class);
        xstream.alias("route", Route.class);
        xstream.addImplicitCollection(CollectionManager.class, "routeCollection");

        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(List.class);
        xstream.allowTypeHierarchy(String.class);
        xstream.ignoreUnknownElements();
    }


    public void writeCollection(LinkedList<Route> collection) {
//        if (System.getenv().get(envVariable) != null) {
            try (FileWriter collectionFileWriter = new FileWriter(("C:\\Users\\annau\\IdeaProjects\\lab5.2WORKPLS\\afile.xml"))) {
//            try (FileWriter collectionFileWriter = new FileWriter((System.getenv("envVariable")))) {

                String xml = xstream.toXML(new ArrayList<>(collection));
                collectionFileWriter.write(xml);

                Console.printLn("Collection was successfully added to the file!");
            } catch (IOException exception) {
                Console.printError("File is a directory or can't be opened!");
            }
//        } else Console.printError("System variable with download file not found!");
    }


    public LinkedList<Route> readCollection() {
        
//        if (System.getenv().get(envVariable) != null) {
//            try (Scanner collectionFileScanner = new Scanner(new File(System.getenv().get(envVariable)))) {
            try (Scanner collectionFileScanner = new Scanner(new File("C:\\Users\\annau\\IdeaProjects\\lab5.2WORKPLS\\afile.xml"))) {
                ArrayList<Route> collection;
                xstream.setMode(XStream.NO_REFERENCES);
                xstream.addPermission(NoTypePermission.NONE);
                xstream.addPermission(NullPermission.NULL);
                xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
                xstream.allowTypeHierarchy(List.class);
                xstream.allowTypeHierarchy(String.class);
                xstream.ignoreUnknownElements();
                xstream.allowTypes(new Class[]{ArrayList.class, Route.class});
                StringBuilder xml = new StringBuilder();
                while (collectionFileScanner.hasNextLine()) {
                    xml.append(collectionFileScanner.nextLine());
                }
                collection = (ArrayList<Route>) xstream.fromXML(xml.toString());
                LinkedList<Route> temp = new LinkedList<>();
                for (Route route: collection) {
                    temp.add(route);
                }
                Console.printLn("Collection was read successfully!");
                return temp;
            } catch (StreamException exception) {
                Console.printError("File is empty. EOF or Stream Exception!");
            } catch (FileNotFoundException exception) {
                Console.printError("File was not found!");
            } catch (NoSuchElementException exception) {
                Console.printError("File is empty!");
            } catch (NullPointerException exception) {
                Console.printError("Can't find a collection in file!");
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
//    }
            return new LinkedList<>();

    }
    /**
     * This function returns a string that describes the class
     *
     * @return The string "FileManager (class for working with files)"
     */
    @Override
    public String toString() {
        return "FileManager (class for working with files)";
    }
}