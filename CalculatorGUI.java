package Coralde.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField displayField;
    private Calculator calculator;

    public CalculatorGUI() {
        setTitle("Amazing Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50));

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setBackground(new Color(30, 30, 30)); 
        displayField.setForeground(Color.WHITE); 
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "C", "", "", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "x", "="
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        calculator = new Calculator(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            
            String expression = displayField.getText();
            
            String[] parts = expression.split("(?<=[-+*/])|(?=[-+*/])");
            if (parts.length == 3) {
                double firstOperand = Double.parseDouble(parts[0]);
                char operator = parts[1].charAt(0);
                double secondOperand = Double.parseDouble(parts[2]);
                
                calculator.setFirstOperand(firstOperand);
                calculator.setOperator(operator);
                calculator.setSecondOperand(secondOperand);
                
                double result = calculator.calculate();
                displayField.setText(Double.toString(result));
            } else {
                
                displayField.setText("Error");
            }
        } else if (command.equals("C")) { 
            displayField.setText(""); 
        } else {
            
            displayField.setText(displayField.getText() + command);
        }
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE); 
        button.setFont(new Font("Arial", Font.BOLD, 20)); 
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100))); 
        button.setFocusPainted(false); 
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
