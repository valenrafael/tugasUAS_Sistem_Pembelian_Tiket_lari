package com.example.uas1;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegistrationView {
    private Main mainApp;
    private VBox layout;

    public RegistrationView(Main mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("root");

        Button btnBack = new Button("← Back to Login");
        btnBack.setOnAction(e -> mainApp.showLogin());

        Label title = new Label("DAFTAR AKUN");
        title.getStyleClass().add("card-title");

        TextField userIn = new TextField(); userIn.setPromptText("Username");
        Button btnReg = new Button("DAFTAR");
        btnReg.getStyleClass().add("btn-buy");
        btnReg.setOnAction(e -> {
            mainApp.showAlert("Sukses", "Registrasi Berhasil!", Alert.AlertType.INFORMATION);
            mainApp.showLogin();
        });

        layout.getChildren().addAll(btnBack, title, userIn, new PasswordField(), btnReg);
    }
    public VBox getLayout() { return layout; }
}