package org.ioopm.calculator.ast;
import java.util.LinkedList;

// *** Represents the call to a function. *** 

public class FunctionCall extends SymbolicExpression {
    
    private String identifier;
    private LinkedList<SymbolicExpression> funcArgs;
    private int priority;
    
    public FunctionCall(String identifier, LinkedList<SymbolicExpression> _funcArgs) {
        super("");
        this.identifier = identifier;
        this.funcArgs = _funcArgs;
        this.priority = 100;
        super.setPriority(priority);
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(identifier);
        sb.append("(");
        for (int i = 0; i < funcArgs.size(); i++) {
            sb.append(funcArgs.get(i).toString());
            if (i + 1 < funcArgs.size()) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    public LinkedList<SymbolicExpression> getArgs() {
        return this.funcArgs;
    }
    
    public String getName() {
        return this.identifier;
    }
    
    public boolean isFuncCall() {
        return true;
    }
    
}