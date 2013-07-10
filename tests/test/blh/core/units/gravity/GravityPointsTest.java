package test.blh.core.units.gravity;

import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class GravityPointsTest {
    
@Test
    public void testDouble() {
        GravityPoints actual = new GravityPoints(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testSpecificGravity() {
        GravityPoints actual = new GravityPoints(new SpecificGravity(1.061));
        GravityPoints expected = new GravityPoints(61);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testToSpecificGravity() {
        SpecificGravity actual = new GravityPoints(57).toSpecificGravity();
        SpecificGravity expected = new SpecificGravity(1.057);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}