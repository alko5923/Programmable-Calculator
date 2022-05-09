package org.ioopm.calculator.ast;
import java.lang.Math;

// *** Represents the sine operation. ***

public class Nuke extends Unary {
    
    SymbolicExpression value;
    
    public Nuke(SymbolicExpression a) {
        super("Unary", a);
        this.value = a;
    }
    
    public String getName() {
        return "Unary";
        
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
        
    }
    
}