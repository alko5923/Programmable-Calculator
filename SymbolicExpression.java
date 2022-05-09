package org.ioopm.calculator.ast; /// could place this in parser *for now*
import java.util.HashMap;

// *** Represents a symbolic expression. ***

public abstract class SymbolicExpression {
    private String name; 
    private String[] subExpressions;
    private int priority;
    /// The second argument allows us to pass in 0 or more arguments
    public SymbolicExpression(String name, Object... subExpressions) {
        this.name = name;
        this.priority = 0;
        this.subExpressions = new String[subExpressions.length];
        for (int i = 0; i < subExpressions.length; ++i) {
            this.subExpressions[i] = subExpressions[i].toString();
        }
    }

    /// Returns e.g., "Constant(42)" if name is "Constant" and subExpressions is ["42"]
    public String toString(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        for (int i = 1; i < this.subExpressions.length; ++i) {
            sb.append(this.subExpressions[i]);
            if (i + 1 < subExpressions.length) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    public boolean isConstant() {
        
        return false;
    }
    
    public boolean isNamedConstant() {
        
        return false;
    }
    
    public String getName() {
        throw new RuntimeException("getName() called on expression with no operator");
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int setPriority(int p) {
        return this.priority = p;
    }
    
    public boolean isNegative() {
        return false;
    }
    
    public double getValue() {
 
        throw new RuntimeException("getValue() called on expression which is not a constant.");
    }
    
    public boolean isCommand() { 
        
        return false;
    }
    
    public boolean isAddition() {
        
        return false;
    }

    public abstract SymbolicExpression accept(Visitor v);
    
    public SymbolicExpression getRhs() {
        throw new RuntimeException("getRhs() called on an expression without a rhs.");
    }
    
    public boolean isFuncDeclaration() {
        return false;
    }
    
    public boolean isFuncCall() {
        return false;
    }

}