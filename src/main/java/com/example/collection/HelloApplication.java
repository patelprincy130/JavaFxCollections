package com.example.collection;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS", 25));
        txtNote.setFill(Color.GREEN);

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Enter name");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(85);

        // ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "SkyBlue",
                "Lavender",
                "Pink",
                "Red",
                "Purple");
        listList.setItems(items);
        listList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listList.setMaxSize(300, 300);

        // ObservableList ChangeListener
        items.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                txtNote.setText("ListView Successfully changed");
            }
        });

        // Validation function
        EventHandler<ActionEvent> validateAndAdd = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String input = fldAdd.getText();
                if (input.isEmpty()) {
                    txtNote.setText("Name cannot be empty");
                    txtNote.setFill(Color.RED);
                } else if (!Character.isUpperCase(input.charAt(0))) {
                    txtNote.setText("Name must start with an uppercase letter");
                    txtNote.setFill(Color.RED);
                } else if (input.length() < 5) {
                    txtNote.setText("Name must be at least 5 characters long");
                    txtNote.setFill(Color.RED);
                } else {
                    items.add(input);
                    txtNote.setText("Added: " + input);
                    txtNote.setFill(Color.GREEN);
                }
                listList.getSelectionModel().clearSelection();
            }
        };

        // Button Add
        btnAdd.setOnAction(validateAndAdd);

        // Button Remove
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String selectedItem = listList.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    items.remove(selectedItem);
                    txtNote.setText("Removed: " + selectedItem);
                    txtNote.setFill(Color.GREEN);
                } else {
                    txtNote.setText("Select an item to remove");
                    txtNote.setFill(Color.RED);
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        // Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnRemove);

        // Root Node
        BorderPane root = new BorderPane();
        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("JavaFX 8 - FXCollections");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
