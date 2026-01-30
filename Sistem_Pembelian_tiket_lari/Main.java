import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        showLogin();
    }

    public void showLogin() {
        LoginView loginView = new LoginView(this);
        stage.setScene(new Scene(loginView.getView(), 400, 500));
        stage.setTitle("Runner App - Login");
        stage.show();
    }

    public void showDashboard(String username) {
        DashboardView dashboard = new DashboardView(this, username);
        stage.setScene(new Scene(dashboard.getView(), 500, 600));
        stage.setTitle("Runner App - Dashboard");
    }

    public void showRegistration(String username, String eventName) {
        RegistrationView regView = new RegistrationView(this, username, eventName);
        stage.setScene(new Scene(regView.getView(), 500, 600));
    }

    public static void main(String[] args) {
        launch(args);
    }
}