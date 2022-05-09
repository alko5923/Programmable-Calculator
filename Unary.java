package org.ioopm.calculator.ast;

/**
 * A superclass for unary functions.
 */
public abstract class Unary extends SymbolicExpression {
    
    private SymbolicExpression argument;
    
    public Unary(String name, SymbolicExpression argument) {
        super(name, argument);
        this.argument = argument;
    }
    
    public String toString() {
        String printOut = "";
        if (this.getName() == "-") {
            printOut = this.getName() + this.argument;
        } else {
            printOut = (this.getName() + "(" + this.argument + ")");
        }
        
        return printOut;
    }
    
    
}