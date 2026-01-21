import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
public class CurrencyConverter {

    public static void main(String[] args) {
        // create frame
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // label and textfield for amount
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 30, 80, 25);
        frame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(130, 30, 150, 25);
        frame.add(amountField);

        // combo box source
        String[] currencies = {"USD", "EUR", "INR"};
        JComboBox<String> fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(50, 70, 100, 25);
        frame.add(fromCurrency);

        // combo box target
        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(180, 70, 100, 25);
        frame.add(toCurrency);

        // convert button
        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(130, 110, 100, 30);
        frame.add(convertBtn);

        // result label
        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(50, 160, 300, 25);
        frame.add(resultLabel);

        frame.setVisible(true);

        // conversion logic
        convertBtn.addActionListener((ActionEvent e) -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();
                double result = convert(amount, from, to);
                
                DecimalFormat df = new DecimalFormat("#.##");
                resultLabel.setText("Result: " + df.format(result) + " " + to);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });
    }

    // simple fixed conversion rates
    public static double convert(double amount, String from, String to) {
        double rate = 1;

        // example rates (fixed)
        double usdToInr = 83.0;
        double usdToEur = 0.93;
        double eurToUsd = 1.07;
        double eurToInr = 89.0;
        double inrToUsd = 1.0 / 83.0;
        double inrToEur = 1.0 / 89.0;

        if (from.equals("USD") && to.equals("INR")) rate = usdToInr;
        if (from.equals("USD") && to.equals("EUR")) rate = usdToEur;

        if (from.equals("EUR") && to.equals("USD")) rate = eurToUsd;
        if (from.equals("EUR") && to.equals("INR")) rate = eurToInr;

        if (from.equals("INR") && to.equals("USD")) rate = inrToUsd;
        if (from.equals("INR") && to.equals("EUR")) rate = inrToEur;

        if (from.equals(to)) rate = 1;

        return amount * rate;
    }
}
