package org.szymon.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AreaSelectionRadioGroup extends JPanel {
    private final int minRadius;
    private final int maxRadius;
    private Slider radiusSlider;
    private final Consumer<Integer> onRadiusChanged;


    public AreaSelectionRadioGroup(
            int minRadius,
            int maxRadius,
            int initRadius,
            Consumer<Integer> onRadiusChanged
    ) {
        super();
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.onRadiusChanged = onRadiusChanged;
        setupLayout(initRadius);
    }

    private void setupLayout(int initRadius) {
        var areaSelectionCheckboxGroup = new CheckboxGroup();
        var wholeImageAreaCheckbox = new Checkbox("Whole image", areaSelectionCheckboxGroup, true);
        var circleAreaCheckbox = new Checkbox("Circle area", areaSelectionCheckboxGroup, false);
        var polygonalArea = new Checkbox("Polygonal area", areaSelectionCheckboxGroup, false);
        var areaSelectionGroup = new JPanel();
        areaSelectionGroup.setLayout(new BoxLayout(areaSelectionGroup, BoxLayout.Y_AXIS));
        areaSelectionGroup.add(new Label("Filter area"));
        areaSelectionGroup.add(wholeImageAreaCheckbox);
        areaSelectionGroup.add(circleAreaCheckbox);

        radiusSlider = new Slider(minRadius, maxRadius, initRadius);
        areaSelectionGroup.add(radiusSlider);

        areaSelectionGroup.add(polygonalArea);
        add(areaSelectionGroup);

        setupListeners();
    }

    private void setupListeners() {
        if (radiusSlider != null) {
            radiusSlider.addChangeListener(e -> onRadiusChanged.accept(radiusSlider.getValue()));
        }
    }
}
