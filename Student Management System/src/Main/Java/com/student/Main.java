package com.student;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Student> table = new TableView<>();
    private ObservableList<Student> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        // ===== Sidebar =====
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #1f2a40;");
        sidebar.setPrefWidth(180);

        Label logo = new Label("SMS");
        logo.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        sidebar.getChildren().addAll(logo,
                createMenuButton("Dashboard"),
                createMenuButton("Students"),
                createMenuButton("Courses"),
                createMenuButton("Logout"));

        // ===== Top Bar =====
        Label title = new Label("Student Management System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        HBox topBar = new HBox(title);
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: #f5f6fa;");

        // ===== Input Fields =====
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField courseField = new TextField();
        courseField.setPromptText("Course");

        Button addBtn = new Button("Add Student");

        HBox form = new HBox(10, idField, nameField, courseField, addBtn);
        form.setPadding(new Insets(15));

        // ===== Table =====
        TableColumn<Student, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

        table.getColumns().addAll(idCol, nameCol, courseCol);
        table.setItems(data);

        // ===== Button Action =====
        addBtn.setOnAction(e -> {
            if (!idField.getText().isEmpty() &&
                !nameField.getText().isEmpty() &&
                !courseField.getText().isEmpty()) {

                data.add(new Student(
                        idField.getText(),
                        nameField.getText(),
                        courseField.getText()
                ));

                table.refresh();

                idField.clear();
                nameField.clear();
                courseField.clear();
            } else {
                showAlert("Please fill all fields!");
            }
        });

        VBox mainContent = new VBox(topBar, form, table);
        mainContent.setStyle("-fx-background-color: #ecf0f1;");

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }

    private Button createMenuButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        return btn;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}