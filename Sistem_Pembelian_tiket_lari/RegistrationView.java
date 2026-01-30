import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegistrationView {
    private GridPane view;

    public RegistrationView(Main mainApp, String username, String eventName) {
        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setHgap(10); view.setVgap(10);

        view.add(new Label("User: " + username), 0, 0);
        view.add(new Label("Event: " + eventName), 0, 1);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Nomor WhatsApp");
        view.add(new Label("No. HP:"), 0, 2);
        view.add(phoneField, 1, 2);

        Button submitBtn = new Button("Bayar Sekarang");
        submitBtn.setOnAction(e -> {
            System.out.println("Data tersimpan!");
            mainApp.showDashboard(username);
        });

        view.add(submitBtn, 1, 4);
    }

    public GridPane getView() { return view; }
}