package org.ioopm.calculator.ast;

/**
 * Pre-defined non-mutual constants like Pi, e, etc.. 
 */
public class NamedConstant extends Constant {
    
    private final double value; 
    private final String name;
    
    public NamedConstant(String name, double x) {
        super(x);
        value = x;
        this.name = name;
        
    }

    
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof NamedConstant) {
            return this.equals((NamedConstant) other);
        } 
        else {
            return false;
        }
        
    }
    
    public boolean equals(NamedConstant other) {
        return this.value == other.value && this.name == other.name;
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
    @Override 
    public boolean isNamedConstant() {
        
        return true;
    }


    
}