package org.ioopm.calculator.ast;
import java.lang.Math;

// *** Represents the cosine operation. ***

public class Cos extends Unary {
    
    SymbolicExpression value;
    
    public Cos(SymbolicExpression a) {
        super("Cos", a);
        this.value = a;
    }

    public String getName() {
        return "Cos";
    }
    
    
    public boolean equals(Object other) {
        if (other instanceof Cos) {
            return this.equals((Cos) other);
         } 
        else {
            return false;
        }
    }

    public boolean equals(Cos other) {
        return this.getName().equals(other.getName()) && this.value.equals(other.value);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}