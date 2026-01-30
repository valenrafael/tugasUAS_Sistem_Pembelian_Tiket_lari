import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DashboardView {
    private VBox view;

    public DashboardView(Main mainApp, String username) {
        view = new VBox(20);
        view.setPadding(new Insets(20));

        Label welcome = new Label("Selamat Datang, " + username);
        ListView<String> eventList = new ListView<>();
        eventList.getItems().addAll("Bandung kota 10K", "Bandung Marathon", "Banndung jalan");

        Button nextBtn = new Button("Daftar Event");
        nextBtn.setOnAction(e -> {
            String selected = eventList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mainApp.showRegistration(username, selected);
            }
        });

        view.getChildren().addAll(welcome, new Label("Pilih Event:"), eventList, nextBtn);
    }

    public VBox getView() { return view; }
}