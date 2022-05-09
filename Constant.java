package org.ioopm.calculator.ast;

// *** Represents constants. ***

public class Constant extends Atom {
    
    private double value; 
    
    public Constant(double x) {
        super("Constant", x);
        value = x;
    }
    
    @Override
    public boolean isConstant() {
        return true;
    }
    
    public double getValue() {
        return value;
    }
    
    public String toString() {
        return String.valueOf(this.value);
        
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Constant) {
            return this.equals((Constant) other);
        } 
        else {
            return false;
        }
        
    }
    
    public boolean equals(Constant other) {
        return this.value == other.value;
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}