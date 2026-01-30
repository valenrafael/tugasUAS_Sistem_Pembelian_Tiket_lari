package com.example.uas1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Main extends Application {
    private Stage stage;
    private double userBalance = 1000000;
    private String paymentStatus = "PENDING (Menunggu Verifikasi Admin)";
    private ArrayList<String> allTransactions = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        showLogin();
    }

    private void applyCSS(Scene scene) {
        try {
            String cssPath = getClass().getResource("/com/example/uas1/style.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.out.println("CSS tidak ditemukan!");
        }
    }

    public void showLogin() {
        stage.setScene(new Scene(new LoginView(this).getLayout(), 400, 450));
        applyCSS(stage.getScene());
        stage.setTitle("Runner App - Login");
        stage.show();
    }

    public void showRegistration() {
        stage.setScene(new Scene(new RegistrationView(this).getLayout(), 400, 550));
        applyCSS(stage.getScene());
    }

    public void showDashboard(String userName) {
        boolean isAdmin = userName.equalsIgnoreCase("admin");
        stage.setScene(new Scene(new DashboardView(this, userName, isAdmin).getLayout(), 1100, 750));
        applyCSS(stage.getScene());
        stage.centerOnScreen();
    }

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Keluar");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) showLogin();
    }

    public void buyTicket(String event, double price, String promo, String buyer) {
        double finalPrice = promo.equalsIgnoreCase("LARI10") ? price * 0.9 : price;
        if (userBalance >= finalPrice) {
            userBalance -= finalPrice;
            allTransactions.add(buyer + " membeli " + event + " (Rp " + finalPrice + ")");
            generateReceipt(buyer, event, finalPrice);
        } else {
            showAlert("Gagal", "Saldo tidak cukup!", Alert.AlertType.ERROR);
        }
    }

    private void generateReceipt(String buyer, String event, double price) {
        String fileName = "Struk_" + buyer + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("========== RUNNER APP RECEIPT ==========\n");
            writer.write("Nama Pembeli : " + buyer + "\n");
            writer.write("Event        : " + event + "\n");
            writer.write("Harga        : Rp " + price + "\n");
            writer.write("Status       : TERBAYAR\n");
            writer.write("========================================\n");
            showAlert("Berhasil", "Tiket dibeli! Struk tersimpan: " + fileName, Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert("Error", "Gagal mencetak struk.", Alert.AlertType.ERROR);
        }
    }

    public void verifyPayment() { this.paymentStatus = "LUNAS / TERVERIFIKASI ✅"; }
    public void showAlert(String t, String c, Alert.AlertType type) { new Alert(type, c).showAndWait(); }
    public double getBalance() { return userBalance; }
    public String getPaymentStatus() { return paymentStatus; }
    public ArrayList<String> getAllTransactions() { return allTransactions; }

    public static void main(String[] args) { launch(args); }
}