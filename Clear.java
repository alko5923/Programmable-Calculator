package org.ioopm.calculator.ast;
import java.util.*;

/**
 * Clears all variables from an environment.
 */
public class Clear extends Command {
    
    private static final Clear theInstance = new Clear();
    
    private Clear() {
        super("Clear", "Clear all");
    }
    
    public static Clear instance() {
        return theInstance;
    }
    
    public void clear(Environment vars) {
        vars.clear();
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
   
}