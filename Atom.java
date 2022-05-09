package org.ioopm.calculator.ast;

/**
 * The basic building parts of an expression. Superclass of variables and constants.
 */
public abstract class Atom extends SymbolicExpression {
    
    public Atom(String name, Object... subExpressions) {
        super(name, subExpressions);
    }
    

        
}