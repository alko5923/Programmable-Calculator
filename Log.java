package org.ioopm.calculator.ast;

// *** Represents the logarithmic operation. ***

public class Log extends Unary {
    SymbolicExpression value;
    
     public Log(SymbolicExpression a) {
        super("Log", a);
        this.value = a;
    }
    
    public String getName() {
        return "Log";
        
    }
    
    public boolean equals(Object other) {
        if (other instanceof Log) {
            return this.equals((Log) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Log other) {
        return this.getName().equals(other.getName()) && this.value.equals(other.value);
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
}