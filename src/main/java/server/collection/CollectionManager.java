package server.collection;


import org.apache.logging.log4j.Logger;
import server.LoggerHolder;
import utils.collections.Route;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;


/**
 * The server.collection.CollectionManager class is a singleton class that manages the collection of all the utils.collections
 * in the application
 */
public class CollectionManager {

    static final Logger rootLogger = LoggerHolder.getInstance();


    @XStreamImplicit
    private LinkedList<Route> routeCollection;
    private final LocalDate creationDate;
    private final FileManager fileManager = new FileManager();

    public CollectionManager() {
        routeCollection = new LinkedList<>();
        creationDate = LocalDate.now();
    }

    /**
     * Get the creation date of the object
     *
     * @return The creation date of the collection.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }


    /**
     * This function returns the collection of routes
     *
     * @return The LinkedList of Route objects.
     */
    public LinkedList<Route> getCollection() {
        return routeCollection;
    }

    /**
     * The setCollection function sets the routeCollection field to the value of the
     * routeCollection parameter
     *
     * @param routeCollection The collection of Route that the user is a member of.
     */
    public void setCollection(LinkedList<Route> routeCollection) {
        this.routeCollection = routeCollection;
    }


    /**
     * Given an id, return the route with that id
     *
     * @param id The id of the route to be retrieved.
     * @return the response of right execution.
     */
    public Route getById(int id) {
        for (Route route : routeCollection) {
            if (route.getId() == id) return route;
        }
        return null;
    }

    /**
     * Replace the route with the given id with the new value
     *
     * @param id       The id of the route to be replaced.
     * @param newValue The new value to be set.
     */
    public void replaceById(int id, Route newValue) {
        newValue.setId(id);
        routeCollection
                .stream()
                .filter(route -> route.getId() == id)
                .findFirst()
                .ifPresent(route -> routeCollection.set(routeCollection.indexOf(route), newValue));
        filter();
        rootLogger.info("Element replaced");
    }

    public void save(){
        fileManager.writeCollection(routeCollection);
    }

    public void filter(){
        Collections.sort(routeCollection);
    }



    /**
     * Add a route to the collection of routes
     *
     * @param route The route to add to the collection.
     */
    public void addToCollection(Route route) {
        routeCollection.add(route);
        filter();
        rootLogger.info("Element added");
    }


    /**
     * Remove a route from the collection
     *
     * @param route The route to be removed from the collection.
     */
    public void removeFromCollection(Route route) {
        routeCollection.remove(route);
        rootLogger.info("Element removed");
    }


    /**
     * Clear the collection of all the routes
     */
    public void clearCollection() {
        routeCollection.clear();
        rootLogger.info("Collection cleared");
    }


    /**
     * Given a collection of routes objects, return the maximum id value of the collection.
     * If the collection is empty, return 0
     *
     * @return The id of the route that was just added.
     */
    public int generateNewIdForCollection() {
        int id = routeCollection.stream()
                .mapToInt(Route::getId)
                .filter(route -> route >= 0)
                .max().orElse(0);
        rootLogger.info("New id generated");
        return id + 1;
    }

    /**
     * This function returns a string that contains information about the collection
     *
     * @return The string "Type - " + routeCollection.getClass() + "\n" +
     * "Creation date - " + getCreationDate() + "\n" +
     * "Amount of elements - " + routeCollection.size();
     */
    public String infoAboutCollection() {
        return "Type - " + routeCollection.getClass() + "\n" +
                "Creation date - " + getCreationDate() + "\n" +
                "Amount of elements - " + routeCollection.size();
    }
    /**
     * Remove a route from the collection if it exists
     *
     * @param id The id of the route to remove.
     */
    public void removeByIdFromCollection(int id) {
        routeCollection.stream()
                .filter(route -> route.getId() == id)
                .findFirst()
                .ifPresent(this::removeFromCollection);
    }

}