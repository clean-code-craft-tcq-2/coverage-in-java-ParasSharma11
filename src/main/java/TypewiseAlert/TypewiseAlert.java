package TypewiseAlert;

import java.util.HashMap;
import java.util.Map;

public class TypewiseAlert {
    public enum BreachType {
        NORMAL, TOO_LOW, TOO_HIGH
    };

    public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
        if (value < lowerLimit) {
            checkAndAlert(AlertTarget.TO_EMAIL,BreachType.TOO_LOW);
            return BreachType.TOO_LOW;
        }
        if (value > upperLimit) {
            checkAndAlert(AlertTarget.TO_EMAIL,BreachType.TOO_HIGH);
            return BreachType.TOO_HIGH;
        }
        checkAndAlert(AlertTarget.TO_CONTROLLER,BreachType.NORMAL);
        return BreachType.NORMAL; 
    }

    public enum CoolingType {
        PASSIVE_COOLING, HI_ACTIVE_COOLING, MED_ACTIVE_COOLING
    };

    public static class WrapperToSetLowerAndUpper {
        public WrapperToSetLowerAndUpper(int lower, int  upper) {
           this.lower = lower;
           this.upper = upper;
        }

        public int getLower() { return this.lower ; }
        public int getHigher() { return this.upper; }
        private int lower;
        private int  upper;

    }
    static Map< CoolingType, WrapperToSetLowerAndUpper> MapforCoolingTypeValues = new HashMap<>();
    public static void populate(){
        MapforCoolingTypeValues.put( CoolingType.PASSIVE_COOLING, new WrapperToSetLowerAndUpper(0,35));
        MapforCoolingTypeValues.put( CoolingType.HI_ACTIVE_COOLING , new WrapperToSetLowerAndUpper(0,45));
        MapforCoolingTypeValues.put( CoolingType.MED_ACTIVE_COOLING , new WrapperToSetLowerAndUpper(0,40));
    }

    public static BreachType classifyTemperatureBreach(CoolingType coolingType, double temperatureInC) {
        populate();
        int lowerLimit = MapforCoolingTypeValues.get(coolingType).getLower();
        int upperLimit = MapforCoolingTypeValues.get(coolingType).getHigher();
        return inferBreach(temperatureInC, lowerLimit, upperLimit);
    }

    public enum AlertTarget {
        TO_CONTROLLER, TO_EMAIL
    };

    public static void checkAndAlert(AlertTarget alertTarget,  BreachType breachType) {

        switch (alertTarget) {
        case TO_CONTROLLER: 
            sendToController(breachType);
            break;
        case TO_EMAIL:
            sendToEmail(breachType);
            break;
        }
    }

    public static void sendToController(BreachType breachType) {
        int header = 0xfeed;
        System.out.printf("%d : %s\n", header, breachType);
    }

    public static void sendToEmail(BreachType breachType) {
        String recepient = "a.b@c.com";
        switch (breachType) {
        case TOO_LOW:
            System.out.printf("To: %s\n", recepient);
            System.out.println("Hi, the temperature is too low\n");
            break;
        case TOO_HIGH:
            System.out.printf("To: %s\n", recepient);
            System.out.println("Hi, the temperature is too high\n");
            break;
        }
    }
}
