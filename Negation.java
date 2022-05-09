package org.ioopm.calculator.ast;

// *** Represents negative integers. ***

public class Negation extends Unary {
    
    SymbolicExpression value;
    
    public Negation(SymbolicExpression a) {
        super("-", a);
        this.value = a;
    }
    
    public String getName() {
        return "-";
    }
    
    @Override
    public boolean isNegative() {
        return true;
    }
    
   public String toString() {
        return ("-" + String.valueOf(this.value));
    }
    
    public boolean equals(Object other) {
        if (other instanceof Negation) {
            return this.equals((Negation) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Negation other) {
        return (this.getName().equals(other.getName()) && value.equals(other.value));
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}