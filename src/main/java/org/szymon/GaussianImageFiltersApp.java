package org.szymon;

import org.szymon.components.AreaSelectionRadioGroup;
import org.szymon.components.ImageCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GaussianImageFiltersApp extends Frame {

    private ImageCanvas canvas;
    private JPanel menuPanel;
    private Checkbox blur3Checkbox;
    private Checkbox blur5Checkbox;
    private Checkbox sharpen3Checkbox;

    private Checkbox wholeImageAreaCheckbox;
    private Checkbox circleAreaCheckbox;
    private Checkbox polygonalArea;

    private JSlider radiusSlider;

    private final int MIN_RADIUS = 0;
    private final int MAX_RADIUS = 200;
    private final int INITIAL_RADIUS = 50;


    public GaussianImageFiltersApp() {
        setupLayout();
        setupListeners();

    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setVisible(true);

        canvas = new ImageCanvas(800, 800);
        add(canvas, BorderLayout.CENTER);

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, 800));

        var areaSelectionRG = new AreaSelectionRadioGroup(
                MIN_RADIUS,
                MAX_RADIUS,
                INITIAL_RADIUS,
                (radius) -> System.out.println("Current radius is " + radius)
        );


        menuPanel.add(areaSelectionRG);


        // kernel selection checkboxes setup
        var kernelSelectionCheckboxGroup = new CheckboxGroup();
        blur3Checkbox = new Checkbox("Gaussian Blur 3x3", kernelSelectionCheckboxGroup, true);
        blur5Checkbox = new Checkbox("Gaussian Blur 5x5", kernelSelectionCheckboxGroup, false);
        sharpen3Checkbox = new Checkbox("Gaussian Sharpen 3x3", kernelSelectionCheckboxGroup, false);
        var kernelSelection = new JPanel();
        kernelSelection.setLayout(new BoxLayout(kernelSelection, BoxLayout.Y_AXIS));
        kernelSelection.add(new Label("Matrix filter selection"));
        kernelSelection.add(blur3Checkbox);
        kernelSelection.add(blur5Checkbox);
        kernelSelection.add(sharpen3Checkbox);
        menuPanel.add(kernelSelection);


        add(menuPanel, BorderLayout.LINE_END);
    }

    private void setupListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        blur5Checkbox.addItemListener(e -> System.out.println("Gaussian blur 5x5 selected"));
        blur3Checkbox.addItemListener(e -> System.out.println("Gaussian blur 3x3 selected"));
        sharpen3Checkbox.addItemListener(e -> System.out.println("Gaussian sharpen 3x3 selected"));

        wholeImageAreaCheckbox.addItemListener(e -> System.out.println("Whole area selected"));
        circleAreaCheckbox.addItemListener(e -> radiusSlider.setEnabled(true));
        polygonalArea.addItemListener(e -> System.out.println("Custom polygonal area selected"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GaussianImageFiltersApp::new);
    }
}