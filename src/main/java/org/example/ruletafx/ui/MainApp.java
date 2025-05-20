package org.example.ruletafx.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MainApp extends Application {
    private void abrirVentanaPrincipal(String usuario) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/ruletafx/ui/principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PrincipalController controller = fxmlLoader.getController();
            controller.inicializarSesion(usuario);
            Stage stage = new Stage();
            stage.setTitle("Ruleta FX");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) {
        Button loginBtn = new Button("Iniciar sesión");
        Button registerBtn = new Button("Registrarse");

        loginBtn.setOnAction(e -> {
            if (mostrarDialogoLogin()) {
                primaryStage.close();
                abrirVentanaPrincipal();
            }
        });

        registerBtn.setOnAction(e -> {
            if (mostrarDialogoRegistro()) {
                primaryStage.close();
                abrirVentanaPrincipal();
            }
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(loginBtn, registerBtn);
        root.setStyle("-fx-padding: 40; -fx-alignment: center;");
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Bienvenido");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean mostrarDialogoLogin() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Iniciar Sesión");
        idDialog.setHeaderText("Introduce tu ID de usuario");
        Optional<String> idResult = idDialog.showAndWait();
        if (idResult.isEmpty() || idResult.get().isBlank()) return false;

        TextInputDialog passDialog = new TextInputDialog();
        passDialog.setTitle("Iniciar Sesión");
        passDialog.setHeaderText("Introduce tu contraseña");
        Optional<String> passResult = passDialog.showAndWait();
        if (passResult.isEmpty() || passResult.get().isBlank()) return false;

        // Aquí deberías validar el login con tu servicio
        return true; // Simula login correcto
    }

    private boolean mostrarDialogoRegistro() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Registro");
        idDialog.setHeaderText("Introduce tu ID de usuario");
        Optional<String> idResult = idDialog.showAndWait();
        if (idResult.isEmpty() || idResult.get().isBlank()) return false;

        TextInputDialog nombreDialog = new TextInputDialog();
        nombreDialog.setTitle("Registro");
        nombreDialog.setHeaderText("Introduce tu nombre");
        Optional<String> nombreResult = nombreDialog.showAndWait();
        if (nombreResult.isEmpty() || nombreResult.get().isBlank()) return false;

        TextInputDialog passDialog = new TextInputDialog();
        passDialog.setTitle("Registro");
        passDialog.setHeaderText("Introduce tu contraseña");
        Optional<String> passResult = passDialog.showAndWait();
        if (passResult.isEmpty() || passResult.get().isBlank()) return false;

        // Aquí deberías registrar el usuario con tu servicio
        return true; // Simula registro correcto
    }

    private void abrirVentanaPrincipal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/ruletafx/ui/principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Ruleta FX");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
