package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserInterface extends Application {

    private BluetoothTransmitter bluetoothTransmitter;
    private ObservableList<Route> allRoutes = FXCollections.observableArrayList();
    private ObservableList<Route> allRoutesCoded = FXCollections.observableArrayList();
    private ArrayList<String> currentRouteUser;
    private ArrayList<String> currentRouteCode;
    private String routeNameCurrent;
    private String uploadedRoute;

    public UserInterface () {
        this.bluetoothTransmitter = new BluetoothTransmitter();
        this.currentRouteUser = new ArrayList<>();
        this.currentRouteCode = new ArrayList<>(); //Wordt aangemaakt voor toevoegen
        this.routeNameCurrent = "";
        this.uploadedRoute = "";
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Boebot Groep C3");

        BorderPane borderPaneInfo = new BorderPane();
        VBox allVbox = new VBox();
        HBox nameHbox = new HBox();
        VBox infoVBox = new VBox();
        GridPane buttonGrid = new GridPane();
        HBox selectHbox = new HBox();
        ComboBox routeCombo = new ComboBox();
        HBox allInfoTop = new HBox();
        VBox nameAndRoute = new VBox();
        HBox selectRow = new HBox();
        VBox bottomRow = new VBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefHeight(400);
        borderPane.setPrefWidth(650);

        String font = "Verdana";
        int size = 20;

        Label welcome = new Label("Welcome to C3's routeplanner!");
        welcome.setFont(Font.font(font, 18));
        welcome.setTextFill(Color.DARKRED);

        Label step1 = new Label("Stap 1.  Enter your route using the buttons on the right.");
        Label step2 = new Label("Stap 2.  Give the route a name.");
        Label step3 = new Label("Stap 3.  Press add route!");
        Label emptyLabel = new Label("");

        step1.setFont(Font.font(font, 13));
        step2.setFont(Font.font(font, 13));
        step3.setFont(Font.font(font, 13));

        Button addRoute = new Button("Add route");
        addRoute.setFont(Font.font(font, 13));

        Label name = new Label("Name: ");
        name.setFont(Font.font(font, 13));

        TextField nameField = new TextField();

        Label routeCurrent = new Label("Route: ");
        name.setFont(Font.font(font, 13));

        TextField routeCurrentText = new TextField();
        routeCurrentText.setPrefWidth(450);

        Button emergency = new Button("Emergency Break");
        emergency.setFont(Font.font(font, FontWeight.BOLD, 16));
        emergency.setTextFill(Color.DARKRED);
        emergency.setMaxWidth(Double.MAX_VALUE);
        emergency.setMaxHeight(Double.MAX_VALUE);

        Button left = new Button("<");
        left.setFont(Font.font(font, FontWeight.BOLD, size));
        left.setTextFill(Color.DARKGREEN);
        left.setMaxWidth(Double.MAX_VALUE);
        left.setMaxHeight(Double.MAX_VALUE);

        Button right = new Button(">");
        right.setFont(Font.font(font, FontWeight.BOLD, size));
        right.setTextFill(Color.DARKGREEN);
        right.setMaxWidth(Double.MAX_VALUE);
        right.setMaxHeight(Double.MAX_VALUE);

        Button up = new Button("^");
        up.setFont(Font.font(font, FontWeight.BOLD, size));
        up.setTextFill(Color.DARKGREEN);
        up.setMaxWidth(Double.MAX_VALUE);
        up.setMaxHeight(Double.MAX_VALUE);

        Button destination = new Button("Stop");
        destination.setFont(Font.font(font, FontWeight.BOLD, 13));
        destination.setMaxWidth(Double.MAX_VALUE);
        destination.setMaxHeight(Double.MAX_VALUE);

        buttonGrid.add(left, 0, 1);
        buttonGrid.add(right, 2, 1);
        buttonGrid.add(up, 1, 0);
        buttonGrid.add(destination, 1, 1);

        Label select = new Label("Select Route: ");
        select.setFont(Font.font(font, 14));

        Button upload = new Button("Upload!");
        upload.setFont(Font.font(font, 13));

        TableView routesTable = new TableView();
        routesTable.setMaxHeight(200);

        routesTable.setEditable(true);

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory( new PropertyValueFactory<Route, String>("name"));

        TableColumn directionsColumn = new TableColumn("Route");
        directionsColumn.setPrefWidth(500);
        directionsColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("routeString"));

        routesTable.getColumns().addAll(nameColumn, directionsColumn);

        addRoute.setOnAction(event -> {
            this.currentRouteCode.add("D");
            this.allRoutes.add(new Route (nameField.getText(), this.routeNameCurrent));
            this.allRoutesCoded.add(new Route(nameField.getText(), this.currentRouteCode));

            routesTable.setItems(this.allRoutes);
            routeCombo.getItems().add(nameField.getText());

            this.currentRouteCode.clear();
            this.currentRouteUser.clear();
            this.routeNameCurrent = "";
            routeCurrentText.setText(this.routeNameCurrent);
            nameField.setText("");
        });

        left.setOnAction(event -> {
            this.uploadedRoute += "l";
            this.currentRouteUser.add("Left");
            this.routeNameCurrent += "Left" + ", ";
            this.currentRouteCode.add("L");
            routeCurrentText.setText(this.routeNameCurrent);
        });

        right.setOnAction(event -> {
            this.uploadedRoute += "r";
            this.currentRouteUser.add("Right");
            this.routeNameCurrent += "Right" + ", ";
            this.currentRouteCode.add("R");
            routeCurrentText.setText(this.routeNameCurrent);
        });

        up.setOnAction(event -> {
            this.uploadedRoute += "f";
            this.currentRouteUser.add("Forward");
            this.routeNameCurrent += "Forward" + ", ";
            this.currentRouteCode.add("F");
            routeCurrentText.setText(this.routeNameCurrent);
        });

        destination.setOnAction(event -> {
            this.uploadedRoute += "s";
            this.currentRouteUser.add("Stop");
            this.routeNameCurrent += "Stop" + ", ";
            this.currentRouteCode.add("S");
            routeCurrentText.setText(this.routeNameCurrent);
        });

        upload.setOnAction(event -> {
            for (Route element : this.allRoutesCoded) {
                if (element.getName().equals(routeCombo.getValue())) {

                    setUploadedRoute(element.getRouteArray());
                }
            }
            this.bluetoothTransmitter.setRoute(this.uploadedRoute);
            this.bluetoothTransmitter.transmitRoute();
        });

        emergency.setOnAction(event -> {
            this.bluetoothTransmitter.transmitEmergency();
        });

        nameHbox.getChildren().addAll(name,nameField, routeCurrent, routeCurrentText);
        infoVBox.getChildren().addAll(welcome, step1,step2,step3,emptyLabel);
        selectHbox.getChildren().addAll(select, routeCombo);

        allInfoTop.getChildren().addAll(infoVBox, buttonGrid);
        nameAndRoute.getChildren().addAll(nameHbox, addRoute);

        nameHbox.setSpacing(7);
        allInfoTop.setSpacing(100);
        nameAndRoute.setSpacing(5);

        borderPaneInfo.setCenter(allInfoTop);
        borderPaneInfo.setBottom(nameAndRoute);

        bottomRow.getChildren().addAll(selectHbox, upload);
        selectRow.getChildren().addAll(bottomRow, emergency);
        selectRow.setSpacing(450);

        allVbox.getChildren().addAll(borderPaneInfo,routesTable, selectRow);
        allVbox.setSpacing(10);

        Scene scene = new Scene(allVbox, 800, 430);
        stage.setScene(scene);
        stage.show();
    }

    public String getRoute () {
        return this.uploadedRoute;
    }

    public void setUploadedRoute(String uploadedRoute) {
        System.out.println(uploadedRoute);
        this.uploadedRoute = uploadedRoute;
    }
}
