package org.ioopm.calculator.ast;
import java.util.*;

/**
 * Prints out all variables of an environment.
 */
public class Vars extends Command {
    
    private static final Vars theInstance = new Vars();
    private Environment variables;
    
    private Vars() {
        super("Vars", "");
    }
    
    public static Vars instance() {
        return theInstance;
    }
    
    public String toString() {
        return "Vars";
    }
    
     /**
     * Set environment for Vars instance
     * @param vars the Environment in which the variables exist
     */   
    public void setVariables(Environment vars) {
        this.variables = vars;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
}