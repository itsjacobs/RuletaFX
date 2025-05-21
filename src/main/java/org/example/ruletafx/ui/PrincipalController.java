package org.example.ruletafx.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.ruletafx.domain.Casilla;
import org.example.ruletafx.domain.Tablero;
import org.example.ruletafx.service.gestionApuestas;
import org.example.ruletafx.service.gestionApuestasImplementacion;

import java.util.Optional;
import java.util.Random;

public class PrincipalController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea tableroText;
    @FXML
    private Button btnApostarNumero, btnApostarColor, btnApostarPar, btnApostarMayor, btnApostarFila, btnApostarDocena, btnApostarHuerfanos, btnTerminarApuesta;

    private gestionApuestas servicio = new gestionApuestasImplementacion();
    private Tablero tablero = new Tablero();
    private boolean sesionIniciada = false;

    public void inicializarSesion(String usuario) {
        setApuestasEnabled(true);
        sesionIniciada = true;
        welcomeText.setText("Bienvenido, " + usuario);
    }

    @FXML
    public void initialize() {
        setApuestasEnabled(true);
        pintarTablero();
    }
    @FXML
    private GridPane tableroGrid;

    private void setApuestasEnabled(boolean enabled) {
        btnApostarNumero.setDisable(!enabled);
        btnApostarColor.setDisable(!enabled);
        btnApostarPar.setDisable(!enabled);
        btnApostarMayor.setDisable(!enabled);
        btnApostarFila.setDisable(!enabled);
        btnApostarDocena.setDisable(!enabled);
        btnApostarHuerfanos.setDisable(!enabled);
        btnTerminarApuesta.setDisable(!enabled);
    }

    private void pintarTablero() {
        tableroGrid.getChildren().clear();
        Casilla[][] matriz = tablero.getTablero();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                Label label = new Label(String.valueOf(matriz[i][j].getNumero()));
                label.setMinSize(30, 30);
                label.setStyle("-fx-border-color: black; -fx-alignment: center;");
                tableroGrid.add(label, i, j);
            }
        }
    }
    @FXML
    protected void onApostarNumero() {
        TextInputDialog numeroDialog = new TextInputDialog();
        numeroDialog.setTitle("Apostar a Número");
        numeroDialog.setHeaderText("Introduce el número (0-36):");
        Optional<String> numeroResult = numeroDialog.showAndWait();
        numeroResult.ifPresent(numeroStr -> {
            try {
                int numero = Integer.parseInt(numeroStr);
                if (numero < 0 || numero > 36) throw new NumberFormatException();
                TextInputDialog dineroDialog = new TextInputDialog();
                dineroDialog.setTitle("Cantidad a Apostar");
                dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
                Optional<String> dineroResult = dineroDialog.showAndWait();
                dineroResult.ifPresent(dineroStr -> {
                    try {
                        double dinero = Double.parseDouble(dineroStr);
                        servicio.apostarNumero(numero, dinero, tablero);
                        showInfo("Apuesta realizada al número " + numero + " por " + dinero + "€");
                    } catch (NumberFormatException e) {
                        showError("Cantidad inválida.");
                    }
                });
            } catch (NumberFormatException e) {
                showError("Número inválido.");
            }
        });
    }

    @FXML
    protected void onApostarColor() {
        ChoiceDialog<String> colorDialog = new ChoiceDialog<>("Rojo", "Rojo", "Negro");
        colorDialog.setTitle("Apostar a Color");
        colorDialog.setHeaderText("Selecciona un color:");
        Optional<String> colorResult = colorDialog.showAndWait();
        colorResult.ifPresent(color -> {
            boolean esRojo = color.equals("Rojo");
            TextInputDialog dineroDialog = new TextInputDialog();
            dineroDialog.setTitle("Cantidad a Apostar");
            dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
            Optional<String> dineroResult = dineroDialog.showAndWait();
            dineroResult.ifPresent(dineroStr -> {
                try {
                    double dinero = Double.parseDouble(dineroStr);
                    servicio.apostarColor(esRojo, dinero, tablero);
                    showInfo("Apuesta realizada al color " + color + " por " + dinero + "€");
                } catch (NumberFormatException e) {
                    showError("Cantidad inválida.");
                }
            });
        });
    }

    @FXML
    protected void onApostarPar() {
        ChoiceDialog<String> parDialog = new ChoiceDialog<>("Par", "Par", "Impar");
        parDialog.setTitle("Apostar a Par/Impar");
        parDialog.setHeaderText("Selecciona Par o Impar:");
        Optional<String> parResult = parDialog.showAndWait();
        parResult.ifPresent(paridad -> {
            boolean esPar = paridad.equals("Par");
            TextInputDialog dineroDialog = new TextInputDialog();
            dineroDialog.setTitle("Cantidad a Apostar");
            dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
            Optional<String> dineroResult = dineroDialog.showAndWait();
            dineroResult.ifPresent(dineroStr -> {
                try {
                    double dinero = Double.parseDouble(dineroStr);
                    servicio.apostarPar(esPar, dinero, tablero);
                    showInfo("Apuesta realizada a " + paridad + " por " + dinero + "€");
                } catch (NumberFormatException e) {
                    showError("Cantidad inválida.");
                }
            });
        });
    }

    @FXML
    protected void onApostarMayor() {
        ChoiceDialog<String> mayorDialog = new ChoiceDialog<>("Mayor (19-36)", "Mayor (19-36)", "Menor (1-18)");
        mayorDialog.setTitle("Apostar a Mayor/Menor");
        mayorDialog.setHeaderText("Selecciona una opción:");
        Optional<String> mayorResult = mayorDialog.showAndWait();
        mayorResult.ifPresent(opcion -> {
            boolean esMayor = opcion.startsWith("Mayor");
            TextInputDialog dineroDialog = new TextInputDialog();
            dineroDialog.setTitle("Cantidad a Apostar");
            dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
            Optional<String> dineroResult = dineroDialog.showAndWait();
            dineroResult.ifPresent(dineroStr -> {
                try {
                    double dinero = Double.parseDouble(dineroStr);
                    servicio.apostarMayor(esMayor, dinero, tablero);
                    showInfo("Apuesta realizada a " + opcion + " por " + dinero + "€");
                } catch (NumberFormatException e) {
                    showError("Cantidad inválida.");
                }
            });
        });
    }

    @FXML
    protected void onApostarFila() {
        ChoiceDialog<String> filaDialog = new ChoiceDialog<>("Fila 1", "Fila 1", "Fila 2", "Fila 3");
        filaDialog.setTitle("Apostar a Fila");
        filaDialog.setHeaderText("Selecciona una fila:");
        Optional<String> filaResult = filaDialog.showAndWait();
        filaResult.ifPresent(fila -> {
            int filaNum = Integer.parseInt(fila.split(" ")[1]);
            TextInputDialog dineroDialog = new TextInputDialog();
            dineroDialog.setTitle("Cantidad a Apostar");
            dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
            Optional<String> dineroResult = dineroDialog.showAndWait();
            dineroResult.ifPresent(dineroStr -> {
                try {
                    double dinero = Double.parseDouble(dineroStr);
                    servicio.apostarFila(filaNum, dinero, tablero);
                    showInfo("Apuesta realizada a " + fila + " por " + dinero + "€");
                } catch (NumberFormatException e) {
                    showError("Cantidad inválida.");
                }
            });
        });
    }

    @FXML
    protected void onApostarDocena() {
        ChoiceDialog<String> docenaDialog = new ChoiceDialog<>("Docena 1", "Docena 1", "Docena 2", "Docena 3");
        docenaDialog.setTitle("Apostar a Docena");
        docenaDialog.setHeaderText("Selecciona una docena:");
        Optional<String> docenaResult = docenaDialog.showAndWait();
        docenaResult.ifPresent(docena -> {
            int docenaNum = Integer.parseInt(docena.split(" ")[1]);
            TextInputDialog dineroDialog = new TextInputDialog();
            dineroDialog.setTitle("Cantidad a Apostar");
            dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
            Optional<String> dineroResult = dineroDialog.showAndWait();
            dineroResult.ifPresent(dineroStr -> {
                try {
                    double dinero = Double.parseDouble(dineroStr);
                    servicio.apostarDocena(docenaNum, dinero, tablero);
                    showInfo("Apuesta realizada a " + docena + " por " + dinero + "€");
                } catch (NumberFormatException e) {
                    showError("Cantidad inválida.");
                }
            });
        });
    }

    @FXML
    protected void onApostarHuerfanos() {
        TextInputDialog dineroDialog = new TextInputDialog();
        dineroDialog.setTitle("Cantidad a Apostar");
        dineroDialog.setHeaderText("¿Cuánto dinero quieres apostar?");
        Optional<String> dineroResult = dineroDialog.showAndWait();
        dineroResult.ifPresent(dineroStr -> {
            try {
                double dinero = Double.parseDouble(dineroStr);
                servicio.apostarHuerfanos(true,dinero, tablero);
                showInfo("Apuesta realizada a Huerfanos por " + dinero + "€");
            } catch (NumberFormatException e) {
                showError("Cantidad inválida.");
            }
        });
    }

    @FXML
    protected void onTerminarApuesta() {
        Random rand = new Random();
        int fila = rand.nextInt(12);
        int col = rand.nextInt(3);
        Casilla ganador = tablero.getTablero()[fila][col];
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText("¡Apuesta finalizada!");
        alert.setContentText("La casilla ganadora es: " + ganador);
        alert.showAndWait();
    }

    private void showInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Apuesta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void showError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
