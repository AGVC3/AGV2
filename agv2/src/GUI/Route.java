package GUI;

import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;

public class Route {

    private SimpleStringProperty name;
    private String routeArray;
    private SimpleStringProperty routeString;

    public Route (String name, ArrayList<String> routeArray) {
        this.name = new SimpleStringProperty(name);
        this.routeArray = "";
        for (String s : routeArray) {
            this.routeArray += s;
        }
        reverseString();
    }

    public Route (String name, String routeString) {
        this.name = new SimpleStringProperty(name);
        this.routeString = new SimpleStringProperty(routeString);
    }

//    public ArrayList<String> getInvertedRoute() {
//        ArrayList<String> reversedRoute = getRouteArray();
//        Collections.reverse(reversedRoute);
//        return reversedRoute;
//    }

    public void reverseString () {
        char[] routeChar = this.routeArray.toCharArray();
        String tempRoute = "q";
        for (int i = routeChar.length - 1; i >= 0; i--) {
            tempRoute += routeChar[i];
        }
        this.routeArray = tempRoute;
        System.out.println(routeArray);


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

    public String getRouteArray() {
        return routeArray;
    }

//    public SimpleObjectProperty<ArrayList<String>> routeArrayProperty() {
//        return routeArray;
//    }

//    public void setRouteArray(ArrayList<String> routeArray) {
//        this.routeArray.(routeArray);
//    }

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
