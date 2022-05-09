package org.ioopm.calculator.ast;
import java.util.List;
import java.util.ArrayList;

// *** A visitor that checks for reassignment of variables 
//     (not allowed in the same expression). ***

public class ReassignmentChecker implements Visitor {
    
    ArrayList<String> variables = new ArrayList<String>();
    
    
    public boolean check(SymbolicExpression exp) {
        
        try {
            exp.accept(this);
            return true; 
        }
        
        catch (IllegalExpressionException e) {
            return false;
        }
    }
    
    public SymbolicExpression visit(Addition a) 
    {
        a.valueLeft.accept(this);
        a.valueRight.accept(this);
        return a;
        
    }
    
    // When we hit an assignment, make sure to check!
    public SymbolicExpression visit(Assignment a) {
        
        if (a.getRhs() instanceof Variable) {
                if (variables.contains(a.getRhs().getName())) {
                    System.out.println("Error, the variable " + a.getRhs().getName() + " is reassigned.");
                    throw new IllegalExpressionException("Error, the variable is reassigned.");
                }
            
            variables.add(a.getRhs().getName());
            }
        return a;
    }
    
    
    public SymbolicExpression visit(Conditional n) { // TODO??
        
        return n;
        
    }
    
    public SymbolicExpression visit(Constant a) {
        return a;
    }
    
    public SymbolicExpression visit(Cos a) {
        a.value.accept(this);
        return a;
    }
    
    public SymbolicExpression visit(Clear a) {
        return a;
    }
    
    public SymbolicExpression visit(Division a) {
        a.valueLeft.accept(this);
        a.valueRight.accept(this);
        return a; // No need to create a new tree
    }
    
    public SymbolicExpression visit(Exp a) {
        a.value.accept(this);
        return a;
    }
    
    public SymbolicExpression visit(EqualTo n) {
        n.getValue1().accept(this);
        n.getValue2().accept(this);
        return n;
    }
    
    
    public SymbolicExpression visit(FunctionCall n) {
       
        return n;
    }
    
    public SymbolicExpression visit(FunctionDeclaration n) {
      
        return n;
    }
    
    
    public SymbolicExpression visit(GreaterThan n) {
        n.getValue1().accept(this);
        n.getValue2().accept(this);
        return n;
    }
    
    public SymbolicExpression visit(GreaterThanOrEqualTo n) {
        n.getValue1().accept(this);
        n.getValue2().accept(this);
        return n;
    }
    
    public SymbolicExpression visit(LessThan n) {
        n.getValue1().accept(this);
        n.getValue2().accept(this);
        return n;
    }
    
    public SymbolicExpression visit(LessThanOrEqualTo n) {
        n.getValue1().accept(this);
        n.getValue2().accept(this);
        return n;
    }
    
    public SymbolicExpression visit(Log a) {
        a.value.accept(this);
        return a;
    }
    
    public SymbolicExpression visit(Multiplication a) {
        a.valueLeft.accept(this);
        a.valueRight.accept(this);
        return a; // No need to create a new tree
    }
    
    public SymbolicExpression visit(NamedConstant a) {
        return a;
    }
    
    public SymbolicExpression visit(Negation a) {
        a.value.accept(this);
        return a; // No need to create a new tree
    }
    
    public SymbolicExpression visit(Nuke n)
    {
        return n; 
    }
    
    public SymbolicExpression visit(Quit n) {
        return n;
    }
    
    public SymbolicExpression visit(Scope n) {
        
        ArrayList<String> oldVars = variables;
        variables = new ArrayList<String>();
        SymbolicExpression expression = n.getExpression().accept(this);
        variables = oldVars;
        return expression;
    }
    
    public SymbolicExpression visit(Sin a) {
        a.value.accept(this);
        return a;
    }
    
    public SymbolicExpression visit(Subtraction n) {
        n.valueLeft.accept(this);
        n.valueRight.accept(this);
        return n; // No need to create a new tree
    }
    
    public SymbolicExpression visit(Variable n) {
        return n;
    }
    
    public SymbolicExpression visit(Vars n) {
        return n;
    }
}