package com.mycompany.simplecalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display, expressionField;
    private String operator = "";
    private double firstNumber = 0;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel to hold expression and result
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        // Expression display
        expressionField = new JTextField();
        expressionField.setEditable(false);
        expressionField.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(expressionField);

        // Result display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(display);

        add(topPanel, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            try {
                firstNumber = Double.parseDouble(display.getText());
                operator = command;
                expressionField.setText(firstNumber + " " + operator);
                display.setText("");
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (command.equals("=")) {
            try {
                double secondNumber = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "-": result = firstNumber - secondNumber; break;
                    case "*": result = firstNumber * secondNumber; break;
                    case "/": result = secondNumber != 0 ? firstNumber / secondNumber : 0; break;
                }
                expressionField.setText(firstNumber + " " + operator + " " + secondNumber + " =");
                display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Error");
                expressionField.setText("");
            }
        } else if (command.equals("C")) {
            display.setText("");
            expressionField.setText("");
            operator = "";
            firstNumber = 0;
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}

