package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    ObservableList<Event> eventList;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Instantiate eventList
        eventList = FXCollections.observableArrayList();

        //Event comparison tool
        ComboBox<Event> firstEvent = new ComboBox<Event>(eventList);
        ComboBox<Event> secondEvent = new ComboBox<>(eventList);
        firstEvent.onActionProperty().set(e -> {
            //update first event
            //attempt to recalculate relationship
        });
        secondEvent.onActionProperty().set(e -> {
            //update second event
            //attempt to recalculate relationship
        });
        Text separator = new Text(" - ");

        //Event Comparator HBox
        HBox eventComparison = new HBox();
        eventComparison.setPadding(new Insets(5));
        eventComparison.setSpacing(5);
        eventComparison.getChildren().addAll(firstEvent, separator, secondEvent);

        //Event comparison display
        Text firstEventDisplay = new Text();
        firstEventDisplay.setTextAlignment(TextAlignment.CENTER);
        Text eventRelationship = new Text();
        eventRelationship.setTextAlignment(TextAlignment.CENTER);
        Text secondEventDisplay = new Text();
        secondEventDisplay.setTextAlignment(TextAlignment.CENTER);
        HBox relationshipDisplay = new HBox();
        relationshipDisplay.getChildren().addAll(firstEventDisplay, eventRelationship, secondEventDisplay);

        //Right VBox
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setSpacing(5);
        rightPanel.setAlignment(Pos.TOP_LEFT);
        rightPanel.setMinSize(300,0);
        rightPanel.getChildren().addAll(eventComparison, relationshipDisplay);

        //# of processes input
        Label processesLabel = new Label("Number of processes to simulate:");
        TextField processesInput = new TextField();

        //# of events input
        Label eventsLabel = new Label("Target number of events:");
        TextField eventsInput = new TextField();

        //Start simulation button
        Button startSimulation = new Button("Run Simulation");

        //Simulation
        startSimulation.setOnAction(e -> {
            eventList = RunSimulation(eventList, Integer.parseInt(processesInput.getText()),Integer.parseInt(eventsInput.getText()));
        });

        //Update displays on change selection
        firstEvent.setOnAction(event -> UpdateDisplays());
        secondEvent.setOnAction(event -> UpdateDisplays());

        //Left Panel
        VBox leftPanel = new VBox();
        leftPanel.setPadding(new Insets(5));
        leftPanel.setSpacing(5);
        leftPanel.getChildren().addAll(processesLabel, processesInput, eventsLabel, eventsInput, startSimulation);

        BorderPane root = new BorderPane();
        root.leftProperty().set(leftPanel);
        root.rightProperty().set(rightPanel);

        Scene window = new Scene(root, 700, 500);
        primaryStage.setTitle("Vector Simulator");
        primaryStage.setScene(window);
        primaryStage.show();
    }

    public static ObservableList<Event> RunSimulation(ObservableList<Event> eventList, int processes, int events){
        List<Process> processList = new ArrayList<>();
        for(int i=0;i<processes;i++){
            processList.add(new Process(i, processes));
        }
        while(eventList.size() < events){
            for(int i=0;i<processList.size();i++){
                Random random = new Random();
                random.ints(1, 0,processes*10);
                int randomNumber = random.nextInt(processes*10);
                if(randomNumber < processes && randomNumber >= 0){
                    if(randomNumber != i){
                        processList.get(i).SendMessage(processList.get(randomNumber), eventList);
                    }
                }
            }
        }
        return eventList;
    }

    public void UpdateDisplays(){
        System.out.println("selection changed");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
