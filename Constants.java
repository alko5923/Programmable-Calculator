package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * A static object class containing a HashMap of pre-defined constants.
 */
public class Constants {
    private static final Constants theInstance = new Constants();
    public static final HashMap<String, Double> namedConstants = new HashMap<>();
    private Constants() {}
    
    public static Constants instance() {
        return theInstance;
    }

    static {
        Constants.namedConstants.put("pi", Math.PI);
        Constants.namedConstants.put("e",  Math.E);
        Constants.namedConstants.put("Answer",  42.0);
        Constants.namedConstants.put("L",  6.022140857*Math.pow(10,23));
    }
}