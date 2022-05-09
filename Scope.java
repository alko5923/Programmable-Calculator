package org.ioopm.calculator.ast;

// *** Represents scope of a variable/function. ***

public class Scope extends Unary {
    
    private SymbolicExpression expression;
    private int priority;
    
    public Scope(SymbolicExpression expression) {
        super("", expression);
        this.expression = expression;
        this.priority = 75;
        super.setPriority(priority);
    }
    
    public SymbolicExpression getExpression() {
        return expression;
    }
    
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    @Override
    public String toString() {
        if (expression == null) {
            return "";
        }
        
        return ("{" + expression + "}");
    }
    
    public String getName() {
        return "";
    }
    
}