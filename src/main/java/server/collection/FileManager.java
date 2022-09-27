package server.collection;

import org.apache.logging.log4j.Logger;
import server.LoggerHolder;
import utils.collections.Coordinates;
import utils.collections.LocationFrom;
import utils.collections.LocationTo;
import utils.collections.Route;
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
import java.util.*;


public class FileManager {
    private final XStream xstream = new XStream(new StaxDriver());
    static final Logger rootLogger = LoggerHolder.getInstance();


    public FileManager() {

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
            try (FileWriter collectionFileWriter = new FileWriter((System.getenv("file")))) {

                String xml = xstream.toXML(new ArrayList<>(collection));
                collectionFileWriter.write(xml);

                rootLogger.info("Collection was successfully added to the file!");
            } catch (IOException exception) {
                rootLogger.error("File is a directory or can't be opened!");
            }
    }


    public LinkedList<Route> readCollection() {
        
            try (Scanner collectionFileScanner = new Scanner(new File(System.getenv("file")))) {
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
                return temp;
            } catch (StreamException exception) {
                rootLogger.error("File is empty. EOF or Stream Exception!");
            } catch (NoSuchElementException exception) {
                rootLogger.error("File is empty!");
            } catch (NullPointerException exception) {
                rootLogger.error("Can't find a collection in file!");
            } catch (IllegalStateException exception) {
                rootLogger.error("Unexpected error!");
                System.exit(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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