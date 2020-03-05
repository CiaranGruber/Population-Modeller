package view;

import graphing.model.scatter.ScatterGraph;
import graphing.view.ScatterGraphDrawer;
import model.PopulationModel;
import model.Simulator;
import position.ScaledDimension;
import position.ScaledPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class SimulatorFrame extends JFrame {
    private ScatterGraph generatedModel;

    public SimulatorFrame(PopulationModel model, int years) {
        // Set frame properties
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Simulation (" + years + " years)");
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Create objects
        generatedModel = Simulator.generateGraph(model, years);
        JButton quitButton = new JButton("Close");

        // Set properties
        quitButton.setMinimumSize(new Dimension(100, 50));

        // Add event listeners
        quitButton.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        // Add objects
        add(quitButton, BorderLayout.PAGE_END);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ScaledDimension dimension = new ScaledDimension(new ScaledPosition(0.1, 0.1), 0.8, 0.8);
        dimension = dimension.getScaledDimension(getWidth(), getHeight());
        ScatterGraphDrawer.draw((Graphics2D) g, generatedModel, dimension);
    }
}
