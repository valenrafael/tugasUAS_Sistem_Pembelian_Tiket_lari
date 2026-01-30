import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginView {
    private VBox view;

    public LoginView(Main mainApp) {
        view = new VBox(15);
        view.setPadding(new Insets(40));
        view.setAlignment(Pos.CENTER);

        Label title = new Label("RUNNER SYSTEM");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("Username");

        // Menambahkan kolom Password
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        loginBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            // Validasi sederhana (Contoh: user 'admin', pass '123')
            if (username.equals("admin") && password.equals("123")) {
                mainApp.showDashboard(username);
            } else if (username.isEmpty() || password.isEmpty()) {
                showError("Input Kosong", "Username dan Password harus diisi!");
            } else {
                showError("Login Gagal", "Username atau Password salah!");
            }
        });

        view.getChildren().addAll(title, userField, passField, loginBtn);
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public VBox getView() { return view; }
}