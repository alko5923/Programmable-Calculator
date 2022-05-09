package org.ioopm.calculator.ast;
import java.lang.Math;

// *** Represents the sine operation. ***

public class Sin extends Unary {
    
    SymbolicExpression value;
    
    public Sin(SymbolicExpression a) {
        super("Sin", a);
        this.value = a;
    }
    
    public String getName() {
        return "Sin";
        
    }
    
    public boolean equals(Object other) {
        if (other instanceof Sin) {
            return this.equals((Sin) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Sin other) {
        return this.getName().equals(other.getName()) && this.value.equals(other.value);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}