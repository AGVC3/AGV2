package GUI;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

    private SimpleStringProperty name;
    private SimpleObjectProperty<ArrayList<String>> routeArray;
    private SimpleStringProperty routeString;

    public Route (String name, ArrayList<String> routeArray) {
        this.name = new SimpleStringProperty(name);
        this.routeArray = new SimpleObjectProperty<>(routeArray);
    }

    public Route (String name, String routeString) {
        this.name = new SimpleStringProperty(name);
        this.routeString = new SimpleStringProperty(routeString);

    }

    public ArrayList<String> getInvertedRoute() {
        ArrayList<String> reversedRoute = getRouteArray();
        Collections.reverse(reversedRoute);
        return  reversedRoute;
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ArrayList<String> getRouteArray() {
        return routeArray.get();
    }

    public SimpleObjectProperty<ArrayList<String>> routeArrayProperty() {
        return routeArray;
    }

    public void setRouteArray(ArrayList<String> routeArray) {
        this.routeArray.set(routeArray);
    }

    public String getRouteString() {
        return routeString.get();
    }

    public SimpleStringProperty routeStringProperty() {
        return routeString;
    }

    public void setRouteString(String routeString) {
        this.routeString.set(routeString);
    }
}
