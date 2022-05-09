package org.ioopm.calculator.ast;

// *** The parent class of all commands. ***

public abstract class Command extends SymbolicExpression {
    
    public Command(String name, Object... subExpressions) {
        super(name, subExpressions);
    }
    
    public boolean isCommand() { 
        
        return true;
    }
    
}