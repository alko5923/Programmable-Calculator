package org.ioopm.calculator.ast;
import java.lang.Math;

// *** Represents the exponential operation. ***

public class Exp extends Unary {
    SymbolicExpression value;
    
    public Exp(SymbolicExpression a) {
        super("Exp", a);
        this.value = a;
    }
    
    public String getName() {
        return "Exp";
    }
    
    public boolean equals(Object other) {
        if (other instanceof Exp) {
            return this.equals((Exp) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Exp other) {
        return this.getName().equals(other.getName()) && this.value.equals(other.value);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
}
