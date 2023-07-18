package io.github.afernandezv.converter.controllers;

import io.github.afernandezv.converter.enums.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConverterAppController<T> implements Initializable {
    // Menu Icons
    @FXML
    private Button currencyButton;

    @FXML
    private Button lengthButton;

    @FXML
    private Button massButton;

    @FXML
    private Button temperatureButton;

    // Controls
    @FXML
    private TextField valueText;

    @FXML
    private ChoiceBox<T> fromSelect;

    @FXML
    private ChoiceBox<T> toSelect;

    @FXML
    private Button convertButton;

    @FXML
    private Label headerText;

    @FXML
    private Label resultText;

    private Category category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restrictDecimalNumbers();

        currencyButton.setOnAction(actionEvent -> {
            category = Category.CURRENCY;
            initControls();
        });

        lengthButton.setOnAction(actionEvent -> {
            category = Category.LENGTH;
            initControls();
        });

        massButton.setOnAction(actionEvent -> {
            category = Category.MASS;
            initControls();
        });

        temperatureButton.setOnAction(actionEvent -> {
            category = Category.TEMPERATURE;
            initControls();
        });
    }

    private void initControls(){
        setHeaderText();
        populateChoiceBox();
    }

    private void setHeaderText(){
        headerText.setText("Conversor de " + this.category.getDescription());
    }

    private void populateChoiceBox(){
        List<T> itemsList = switch (this.category.name()) {
            case "CURRENCY" -> CurrencyType.getTypes();
            case "LENGTH" -> LengthUnit.getUnits();
            case "MASS" -> MassUnit.getUnits();
            default -> TemperatureUnit.getUnits();
        };
        ObservableList<T> items = FXCollections.observableList(itemsList);
        fromSelect.setItems(items);
        toSelect.setItems(items);
        fromSelect.getSelectionModel().selectFirst();
        toSelect.getSelectionModel().select(1);
    }

    public void restrictDecimalNumbers(){
        this.valueText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("-?([0-9]*)?(\\.[0-9]*)?")){
                    valueText.setText(oldValue);
                }
            }
        });
    }
}