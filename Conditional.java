package org.ioopm.calculator.ast;

// *** Represents conditional statements. ***

public abstract class Conditional extends SymbolicExpression {
    
    public Conditional(String name, Object... subExpressions) {
        super(name, subExpressions);
    }
    
}