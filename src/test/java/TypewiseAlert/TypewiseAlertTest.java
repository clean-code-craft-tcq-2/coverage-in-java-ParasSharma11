package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TypewiseAlertTest 
{
    @Test
    public void infersBreachAsPerLimits()
    {
      assertTrue(TypewiseAlert.inferBreach(12, 20, 30) ==
        TypewiseAlert.BreachType.TOO_LOW);
    }
    
    @Test
    public void testClassifyTemperatureBreach() {
        assertTrue(TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.PASSIVE_COOLING,
                                                           50) == TypewiseAlert.BreachType.TOO_HIGH);
        assertTrue(TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.PASSIVE_COOLING,
                                                           -1) == TypewiseAlert.BreachType.TOO_LOW);
        assertTrue(TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.PASSIVE_COOLING,
                                                           30) == TypewiseAlert.BreachType.NORMAL);
        
    }
}
