package org.ioopm.calculator.ast;

// *** The addition of two expressions ***

public class Addition extends Binary {
    
    private int priority;
    SymbolicExpression valueLeft;
    SymbolicExpression valueRight;
    
    public Addition(SymbolicExpression v1, SymbolicExpression v2) {
        super("+", v1, v2);
        this.priority = 50;
        super.setPriority(priority);
        
        this.valueLeft = v1;
        this.valueRight = v2;
    }
    
    public String getName() {
        return ("+");
    }
    
    public boolean isAddition() {
        
        return true;
    }
    
    
    public boolean equals(Object other) {
        if (other instanceof Addition) {
            return this.equals((Addition) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Addition other) {
        return (this.getName().equals(other.getName()) && this.valueLeft.equals(other.valueLeft) && this.valueRight.equals(other.valueRight));
        
    };
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
}