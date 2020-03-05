package view;

import mathematics.operable.Fraction;
import mathematics.operable.OperableNumber;
import model.AgeRange;
import model.PopulationModel;

import javax.swing.*;
import java.awt.*;

public class PopulationBuilder extends JFrame {
    JPanel ageRangePanel;
    JTextField ageRangeField;
    JSpinner populationSpinner;
    JSpinner birthrateSpinner;
    JSpinner survivalRateSpinner;
    JSpinner indexSpinner;
    PopulationModel model;

    public PopulationBuilder() {
        // Set model
        model = new PopulationModel();

        // Set frame properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Population builder");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create objects
        JLabel title = new JLabel("Population Builder");
        JLabel populationLabel = new JLabel("Starting pop");
        JLabel birthrateLabel = new JLabel("Birth rate");
        JLabel survivalRateLabel = new JLabel("Survival rate");
        JLabel simulateLabel = new JLabel("Simulate populations");
        JLabel simulateYearsLabel = new JLabel("Years to simulate");
        JButton simulateButton = new JButton("Simulate");
        JButton addRangeButton = new JButton("Add Range");
        JSpinner simulateYearsSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 1000, 1));
        populationSpinner = new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1));
        ageRangeField = new JTextField();
        birthrateSpinner = new JSpinner(new SpinnerNumberModel(1.000, 0.000, Double.POSITIVE_INFINITY, 0.1));
        survivalRateSpinner = new JSpinner(new SpinnerNumberModel(1.000, 0.000, Double.POSITIVE_INFINITY, 0.1));
        indexSpinner = new JSpinner();
        ageRangePanel = new JPanel();
        JPanel rangePanel = new JPanel();
        JPanel addRangePanel = new JPanel();
        JPanel dataPanel = new JPanel();
        JPanel centrePanel = new JPanel();
        JPanel simulatePanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        // Set object properties
        title.setFont(new Font("Monotxt_IV25", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));

        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        dataPanel.setLayout(new GridLayout(2, 3));
        addRangePanel.setLayout(new GridLayout(1, 2));
        simulatePanel.setLayout(layout);

        simulateLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        simulatePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        rangePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        ageRangePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        rangePanel.setLayout(new BoxLayout(rangePanel, BoxLayout.PAGE_AXIS));
        rangePanel.setMaximumSize(new Dimension(500, 640));

        setAgeRangePanel();

        simulateLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        simulateYearsLabel.setHorizontalAlignment(JLabel.CENTER);
        simulateLabel.setHorizontalAlignment(JLabel.CENTER);

        populationLabel.setLabelFor(populationSpinner);
        birthrateLabel.setLabelFor(birthrateSpinner);
        survivalRateLabel.setLabelFor(survivalRateSpinner);
        simulateYearsLabel.setLabelFor(simulateYearsSpinner);

        // Set GridBagLayout
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        layout.setConstraints(simulateLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        layout.setConstraints(simulateYearsLabel, constraints);

        constraints.gridy = 2;
        layout.setConstraints(simulateYearsSpinner, constraints);

        constraints.gridheight = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        layout.setConstraints(simulateButton, constraints);

        // Add listeners
        addRangeButton.addActionListener(e -> {
            String range = ageRangeField.getText();
            OperableNumber population = new Fraction(Integer.parseInt(populationSpinner.getValue().toString()));
            OperableNumber birthRate = new Fraction(Math.round(Double.parseDouble(birthrateSpinner.getValue().toString())
                    * 1000) / 1000.0);
            OperableNumber survivalRate = new Fraction(Math.round(Double.parseDouble(survivalRateSpinner.getValue().toString())
                    * 1000) / 1000.0);
            AgeRange ageRange = new AgeRange(range, population, birthRate, survivalRate);
            model.addAgeRange(Integer.parseInt(indexSpinner.getValue().toString()) - 1, ageRange);
            setAgeRangePanel();
        });
        simulateButton.addActionListener(e -> {
            SimulatorFrame simulator = new SimulatorFrame(model, Integer.parseInt(simulateYearsSpinner.getValue().toString()));
            simulator.setVisible(true);
        });

        // Add objects
        simulatePanel.add(simulateLabel);
        simulatePanel.add(simulateYearsLabel);
        simulatePanel.add(simulateYearsSpinner);
        simulatePanel.add(simulateButton);

        dataPanel.add(populationLabel);
        dataPanel.add(birthrateLabel);
        dataPanel.add(survivalRateLabel);
        dataPanel.add(populationSpinner);
        dataPanel.add(birthrateSpinner);
        dataPanel.add(survivalRateSpinner);

        addRangePanel.add(addRangeButton);
        addRangePanel.add(indexSpinner);

        rangePanel.add(ageRangeField);
        rangePanel.add(dataPanel);
        rangePanel.add(addRangePanel);

        centrePanel.add(rangePanel);
        centrePanel.add(ageRangePanel);

        add(title, BorderLayout.PAGE_START);
        add(centrePanel);
        add(simulatePanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        PopulationBuilder frame = new PopulationBuilder();
        frame.setVisible(true);
    }

    public void setAgeRangePanel() {
        setAgeRangePanel(model.getAgeRanges().size() + 1);
    }

    public void setAgeRangePanel(int index) {
        indexSpinner.setModel(new SpinnerNumberModel(index, 1, model.getAgeRanges().size() + 1, 1));
        for (Component c : ageRangePanel.getComponents()) {
            ageRangePanel.remove(c);
        }
        ageRangePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < model.getAgeRanges().size(); ++i) {
            // Create buttons
            JPanel panel = new JPanel(new GridLayout(3, 1));
            JLabel ageRange = new JLabel(model.getAgeRanges().get(i).getRange());
            JButton removeButton = new JButton("Remove");
            JButton changeButton = new JButton("Change");

            // Set properties
            panel.setBackground(new Color(85, 85, 85, 94));

            ageRange.setHorizontalAlignment(JLabel.CENTER);

            removeButton.setName(Integer.toString(i));
            changeButton.setName(Integer.toString(i));

            // Add listeners
            removeButton.addActionListener(e -> {
                model.getAgeRanges().remove(Integer.parseInt(removeButton.getName()));
                setAgeRangePanel();
            });
            changeButton.addActionListener(e -> {
                AgeRange range = model.getAgeRanges().get(Integer.parseInt(changeButton.getName()));
                ageRangeField.setText(range.getRange());
                populationSpinner.setValue(range.getStartingPopulation().intValue());
                birthrateSpinner.setValue(range.getBirthRate().doubleValue());
                survivalRateSpinner.setValue(range.getSurvivalRate().doubleValue());
                model.getAgeRanges().remove(Integer.parseInt(changeButton.getName()));
                setAgeRangePanel(Integer.parseInt(changeButton.getName()) + 1);
            });

            // Add objects
            panel.add(ageRange);
            panel.add(removeButton);
            panel.add(changeButton);

            // Add panel
            ageRangePanel.add(panel);
        }
        ageRangePanel.revalidate();
        ageRangePanel.repaint();
    }
}
