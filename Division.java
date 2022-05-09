package org.ioopm.calculator.ast;

// *** Represents the division of Symbolic Expressions. ***

public class Division extends Binary {
    
    private int priority;
    SymbolicExpression valueLeft;
    SymbolicExpression valueRight;
    
    public Division(SymbolicExpression v1, SymbolicExpression v2) {
        super("/", v1, v2);
        this.priority = 100;
        super.setPriority(priority);
        
        this.valueLeft = v1;
        this.valueRight = v2;
    }

    public String getName() {
        return "/";
    }
    
    public boolean equals(Object other) {
        if (other instanceof Division) {
            return this.equals((Division) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Division other) {
        
        return (this.getName() == other.getName() && this.valueLeft.equals(other.valueLeft) && this.valueRight.equals(other.valueRight));
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}