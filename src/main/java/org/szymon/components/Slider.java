package org.szymon.components;

import javax.swing.*;
import java.util.Hashtable;

public class Slider extends JSlider {
    private final int minValue;
    private final int maxValue;

    public Slider(int minValue, int maxValue, int initValue) {
        super(minValue, maxValue, initValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        setupLayout();
    }

    private void setupLayout() {
        var difference = maxValue - minValue;
        var ticks = 4;
        var tick = difference / ticks;
        var hashtable = new Hashtable<Integer, JLabel>();
        for (int i = 0; i < ticks; i++) {
            hashtable.put(minValue + i * tick, new JLabel(Integer.toString(minValue + i * tick)));
        }
        hashtable.put(maxValue, new JLabel(Integer.toString(maxValue)));
        setLabelTable(hashtable);
        setPaintLabels(true);
        setMajorTickSpacing(tick);
        setPaintTicks(true);
    }
}
