package ru.otus.l71.structural.adapter;

/**
 * Created by tully.
 * <p>
 * PolarPlotter coordinated to CartesianPlotter coordinated adapter.
 */
public class Polar2CartesianAdapter implements PolarPlotter {

    private final CartesianPlotter cartesianPlotter;

    Polar2CartesianAdapter(CartesianPlotter cartesianPlotter) {
        this.cartesianPlotter = cartesianPlotter;
    }

    @Override
    public void setPoint(double r, double theta) {
        cartesianPlotter.setPoint(r * Math.cos(theta), r * Math.sin(theta));
    }
}
