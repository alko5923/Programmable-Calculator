package org.ioopm.calculator.ast;

// *** Represents a variable. ***

public class Variable extends Atom implements Comparable<Variable> {
    
    private String identifier;
    
    public Variable(final String str) {
        super("Variable", str);
        identifier = str;
    }
    
    public String toString() {
        return this.identifier;
        
    }
    
    @Override
    public String getName() {
        return this.identifier;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return this.equals((Variable) other);
        } 
        else {
            return false;
        }
    }

    public boolean equals(Variable other) {
        return this.identifier.equals(other.identifier);
    }
    
    public int hashCode() {
        return identifier.hashCode();
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    
    public int compareTo (Variable otherVar) {
        return ((this.getName()).compareTo(otherVar.getName()));
    }

}
