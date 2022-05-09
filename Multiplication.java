package org.ioopm.calculator.ast;

// *** Represents multiplication of Symbolic Expressions. ***

public class Multiplication extends Binary {
    
    private int priority;
    SymbolicExpression valueLeft;
    SymbolicExpression valueRight;
    
    public Multiplication(SymbolicExpression v1, SymbolicExpression v2) {
        super("*", v1, v2);
        this.priority = 100;
        super.setPriority(priority);
        this.valueLeft = v1;
        this.valueRight = v2;
    }
    
    public String getName() {
        return "*";
        
    }
    
    public boolean equals(Object other) {
        if (other instanceof Multiplication) {
            return this.equals((Multiplication) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Multiplication other) {
        return (this.getName() == other.getName() && this.valueLeft.equals(other.valueLeft) && this.valueRight.equals(other.valueRight));
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    

    
}