package managers;

import collections.Route;

import java.util.Iterator;
import java.util.List;

public class Validator {
    List<Route> route;

    public Validator(List<Route> route) {
        this.route = route;
    }

    public List<Route> validate(){
        for(Iterator<Route> iterator = route.iterator(); iterator.hasNext(); ){
            Route route = iterator.next();
            if(route.getId() <= 0) iterator.remove();
            if(route.getName() == null || route.getName().equals("")) iterator.remove();
            if(route.getCoordinates() == null) iterator.remove();
            if(route.getCreationDate() == null) iterator.remove();
            if(route.getLocationTo() == null) iterator.remove();
            if(route.getDistance() <= 1) iterator.remove();

            if(route.getCoordinates() != null){
                if(route.getCoordinates().getX() > 99) iterator.remove();}

            if(route.getCoordinates() != null){
                if(route.getCoordinates().getY() == null) iterator.remove();
            }

            if(route.getCoordinates() == null){
                assert false;
                if(route.getCoordinates().getY() < -523) iterator.remove();
            }
            if(route.getLocationTo().getName() == null) iterator.remove();
            if(route.getLocationFrom().getName().equals("")) iterator.remove();
        }
        return route;
    }

}