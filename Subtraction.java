package org.ioopm.calculator.ast;

// *** Represents the subtraction operation. ***

public class Subtraction extends Binary {
    
    private int priority;
    SymbolicExpression valueLeft;
    SymbolicExpression valueRight;

    
    public Subtraction(SymbolicExpression v1, SymbolicExpression v2) {
        super("-", v1, v2);
        this.priority = 50;
        super.setPriority(priority);
        this.valueLeft = v1;
        this.valueRight = v2;        
    }
    
    public String getName() {
        return "-";
    }
    
    public boolean equals(Object other) {
        if (other instanceof Subtraction) {
            return this.equals((Subtraction) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Subtraction other) {
        return (this.getName().equals(other.getName()) && this.valueLeft.toString().equals(other.valueLeft.toString()) && this.valueRight.toString().equals(other.valueRight.toString()));
        
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}