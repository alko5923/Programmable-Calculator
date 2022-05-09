package org.ioopm.calculator.ast;

// *** Assigns an expression to a variable ***

public class Assignment extends Binary {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;
    
    public Assignment(SymbolicExpression v1, SymbolicExpression v2) {
        super("=", v1, v2);
        
        this.lhs = v1;
        this.rhs = v2;
        
    }
    
    public String getName() {
        return "=";
    }
    
    
    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return this.equals((Assignment) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Assignment other) {
        return (this.getName().equals(other.getName()) && this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs));
        
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
    public SymbolicExpression getLhs() {
        return this.lhs;
    }
    
    public SymbolicExpression getRhs() {
        return this.rhs;
    }
    
    
}