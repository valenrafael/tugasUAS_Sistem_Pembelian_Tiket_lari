package com.example.uas1;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class DashboardView {
    private Main mainApp;
    private String userName;
    private boolean isAdmin;
    private BorderPane root;
    private Label lblSaldo, lblStatus;

    public DashboardView(Main mainApp, String userName, boolean isAdmin) {
        this.mainApp = mainApp;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.root = new BorderPane();
        initLayout();
    }

    private void initLayout() {
        // --- SIDEBAR ---
        VBox sidebar = new VBox(15);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(220);
        Button btnOut = new Button("LOGOUT");
        btnOut.getStyleClass().add("btn-buy");
        btnOut.setMaxWidth(Double.MAX_VALUE);
        btnOut.setOnAction(e -> mainApp.logout());
        sidebar.getChildren().addAll(new Label("RUNNER APP"), new Separator(), btnOut);

        // --- HEADER ---
        HBox header = new HBox(15);
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_RIGHT);
        lblSaldo = new Label("💰 Saldo: Rp " + mainApp.getBalance());
        lblSaldo.getStyleClass().add("label-saldo");
        header.getChildren().addAll(new Label("User: " + userName), lblSaldo);

        // --- CONTENT ---
        VBox content = new VBox(20);
        content.setPadding(new Insets(25));

        // Status Card
        VBox statusCard = new VBox(5);
        statusCard.getStyleClass().add("card");
        lblStatus = new Label("STATUS: " + mainApp.getPaymentStatus());
        lblStatus.setStyle("-fx-font-weight: bold; -fx-text-fill: #e67e22;");
        statusCard.getChildren().addAll(new Label("Informasi Pendaftaran:"), lblStatus);
        content.getChildren().add(statusCard);

        // KHUSUS ADMIN
        if (isAdmin) {
            VBox adminBox = new VBox(10);
            adminBox.setStyle("-fx-background-color: #dfe6e9; -fx-padding: 15; -fx-background-radius: 10;");
            ListView<String> listTrx = new ListView<>();
            listTrx.getItems().addAll(mainApp.getAllTransactions());
            listTrx.setPrefHeight(100);

            Button btnVerify = new Button("Verifikasi Semua Peserta");
            btnVerify.getStyleClass().add("btn-buy");
            btnVerify.setOnAction(e -> {
                mainApp.verifyPayment();
                lblStatus.setText("STATUS: " + mainApp.getPaymentStatus());
                lblStatus.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            });
            adminBox.getChildren().addAll(new Label("🛠 ADMIN PANEL (Riwayat & Verifikasi)"), listTrx, btnVerify);
            content.getChildren().add(adminBox);
        }

        // GRID EVENT
        GridPane grid = new GridPane();
        grid.setHgap(20); grid.setVgap(20);
        TextField promoField = new TextField(); promoField.setPromptText("Kode Promo (LARI10)");

        String[] events = {"Jakarta 10K", "Bali Marathon"};
        double[] prices = {250000, 500000};

        for (int i = 0; i < events.length; i++) {
            final String name = events[i]; final double price = prices[i];
            VBox card = new VBox(10); card.getStyleClass().add("card"); card.setAlignment(Pos.CENTER);
            Button btn = new Button("Beli Tiket"); btn.getStyleClass().add("btn-buy");
            btn.setOnAction(e -> {
                mainApp.buyTicket(name, price, promoField.getText(), userName);
                lblSaldo.setText("💰 Saldo: Rp " + mainApp.getBalance());
            });
            card.getChildren().addAll(new Rectangle(120, 60), new Label(name), new Label("Rp " + price), btn);
            grid.add(card, i, 0);
        }

        content.getChildren().addAll(new Label("Promo:"), promoField, new Separator(), grid);
        root.setTop(header); root.setLeft(sidebar); root.setCenter(new ScrollPane(content));
    }

    public BorderPane getLayout() { return root; }
}