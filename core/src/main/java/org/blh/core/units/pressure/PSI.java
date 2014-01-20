package org.blh.core.units.pressure;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:05 AM
 */
public class PSI extends NumericUnit {

    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(14.503773800);

    public PSI(BigDecimal value) {
        super(value);
    }

    public PSI(double value) {
        super(value);
    }

    public PSI(Bar value) {
        super(value.value().multiply(CONVERSION_FACTOR));
    }

    public Bar toBar() {
        return new Bar(this.value.divide(CONVERSION_FACTOR));
    }
}
