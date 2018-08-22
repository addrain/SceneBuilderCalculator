/*
    File:   Main.java
    Name:   Adrian Kwok
    Date:   8/21/2018
    Desc:   This file handles the controls defined within the Scene.
            It implements addition, subtraction, multiplication, and division of doubles.
            Provides minimal capability to edit computational inputs such as modifying a double value.

            Other files required: Main.java, sample.fxml
 */

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class Controller {
    @FXML
    private Label equation;
    @FXML
    private TextField display;
    @FXML
    private Button clearCurrent;
    @FXML
    private Button delete;
    @FXML
    private Button posNeg;

    private String value = "";
    private double curr = 0;
    private double prev = 0;
    private String operation = null;

    @FXML
    void handleNumbers(ActionEvent event) {
        if(value.equals("=") || value.equals("+") || value.equals("-") || value.equals("/") || value.equals("x")){
            if(value.equals("="))
                equation.setText("");
            display.setText("0");
        }
        value = ((Button)event.getSource()).getText();
        if(value.equals(".") && display.getText().contains(".")) {
            return;
        }

        if(event.getSource() == posNeg) {
            curr = curr * -1;
            if(curr < 0)
                display.setText("-" + display.getText());
            else if(display.getText().length() != 1)
                display.setText(display.getText().substring(1, display.getText().length()));
        } else if(event.getSource() == clearCurrent) {
            display.setText("0");
        } else if(event.getSource() == delete) {
            if(display.getText().length() == 1)
                display.setText("0");
            else
                display.setText(display.getText().substring(0, display.getText().length() - 1));
            if(display.getText().equals("-"))
                display.setText("0");
        } else if(display.getText().equals("0")) {
            if (value.equals("."))
                display.setText("0" + value);
            else
                display.setText(value);
        } else {
            display.setText(display.getText() + value);
        }
        curr = Double.parseDouble(display.getText());
        System.out.println("prev: " + prev + "\ncurr: " + curr);
    }

    @FXML
    void handleOperations(ActionEvent event){
        value = ((Button)event.getSource()).getText();
        double print = curr;
        if(operation != null){
            if(operation.equals("=")){
                equation.setText("");
            }
            switch(operation){
                case "+":
                    curr = prev + curr;
                    displayResult(curr);
                    break;
                case "-":
                    curr = prev - curr;
                    displayResult(curr);
                    break;
                case "/":
                    if(curr != 0) {
                        curr = prev / curr;
                        displayResult(curr);
                    } else {
                        equation.setText("Error: Cannot divide by zero");
                        return;
                    }
                    break;
                case "x":
                    curr = prev * curr;
                    displayResult(curr);
                    break;
            }
        }
        operation = value;
        equation.setText(equation.getText() + " " + print + " " + operation);
        prev = curr;
    }


    private void displayResult(double result) {
        DecimalFormat df = new DecimalFormat("#.####");

        if(result % 1 == 0) {
            display.setText(Integer.toString((int)result));
        } else {
            display.setText(df.format(result));
        }
        operation = null;
    }

    @FXML
    void handleClearAll() {
        display.setText("0");
        equation.setText("");
        curr = 0;
        prev = 0;
        operation = null;
    }
}