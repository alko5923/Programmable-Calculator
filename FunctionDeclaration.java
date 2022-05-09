package org.ioopm.calculator.ast;
import java.util.LinkedList;

// *** Represents the declaration/definition of a function. ***

public class FunctionDeclaration extends SymbolicExpression {
    
    private String funcName;
    private LinkedList<SymbolicExpression> funcParameters;
    private FunctionBody funcBody;
    
    public FunctionDeclaration(String funcName, FunctionBody funcBody, LinkedList<SymbolicExpression> _funcParameters) {
        super("");
        this.funcName = funcName;
        this.funcParameters = _funcParameters;
        this.funcBody = funcBody;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    
    public LinkedList<SymbolicExpression> getParameters() {
        return this.funcParameters;
    }
    
    public FunctionBody getFuncBody() {
        return this.funcBody;
    }
    
    public String getName() {
        return this.funcName;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("function ");
        sb.append(funcName);
        sb.append("(");
        for(int i = 0; i < this.funcParameters.size(); i+=1) {
            sb.append(this.funcParameters.get(i).toString());
            if (i + 1 < funcParameters.size()) {
                sb.append(", ");
            }
        }
        
        sb.append(") ");
        sb.append("\n");
        for (int i = 0; i < this.getFuncBody().size(); i+=1) {
            sb.append(funcBody.get(i).toString());
            if (funcBody.size() > 1) { 
                sb.append("\n");
                
            }
        }
        return sb.toString();
    }
    
    public boolean isFuncDeclaration() {
        return true;
    }
    
    
}