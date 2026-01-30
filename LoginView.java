package com.example.uas1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginView {
    private Main mainApp;
    private VBox layout;

    public LoginView(Main mainApp) {
        this.mainApp = mainApp;
        layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("RUNNER APP LOGIN");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("Username");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Button loginBtn = new Button("Masuk");
        loginBtn.setPrefWidth(200);
        loginBtn.getStyleClass().add("btn-buy"); // Menggunakan style tombol dari CSS

        Hyperlink regLink = new Hyperlink("Belum punya akun? Daftar di sini");
        regLink.setOnAction(e -> mainApp.showRegistration());

        loginBtn.setOnAction(e -> {
            if (!userField.getText().isEmpty()) {
                mainApp.showDashboard(userField.getText());
            }
        });

        layout.getChildren().addAll(title, userField, passField, loginBtn, regLink);
    }

    public VBox getLayout() { return layout; }
}