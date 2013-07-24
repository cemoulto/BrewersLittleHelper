package org.blh.core.units;

/**
 * On the form 0.xyzw. For xy.zw% use Percentage.
 * @author thinner
 */
public class Factor extends Unit<Double> {

    public Factor(double value) {
        super(value);
    }

    public Factor(Percentage value) {
        super(value.value() / 100);
    }

    public Percentage asPercentage() {
        return new Percentage(this.value * 100);
    }
}