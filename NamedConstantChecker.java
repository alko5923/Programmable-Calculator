package org.ioopm.calculator.ast;

// *** A checker for assignment to named constants (not allowed!).***

public class NamedConstantChecker implements Visitor {
    
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
        return a; // No need to create a new tree
    }
    
    // When we hit an assignment, make sure to check!
    public SymbolicExpression visit(Assignment a) {
        
        a.getRhs().accept(this);
        
        if (a.getRhs().isNamedConstant()) { 
            System.out.println("Error, assignments to namedConstants:\n" + a);
            throw new IllegalExpressionException("Error, assignments to namedConstants:\n" + a);
        }
        if (a.getRhs().toString().equals("Cos") || a.getRhs().toString().equals("Sin")) {
            System.out.println("Can't define Sin or Cos as a variable.");
            throw new IllegalExpressionException("Can't define as a variable.");
            }
        if (a.getRhs().toString().equals("if") || a.getRhs().toString().equals("else")) {
            System.out.println("Can't use if and else as variables.");
            throw new IllegalExpressionException("Can't use if and else as variables.");
        }
        
        return a;
    }
    
    
    public SymbolicExpression visit(Conditional n) { // TODO?
        
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
        // TODO
        return n;
    }
    
    public SymbolicExpression visit(FunctionDeclaration n) {
        // TODO
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
    
    public SymbolicExpression visit(Nuke n)
    {
        return n; 
    }
    
    public SymbolicExpression visit(Negation a) {
        a.value.accept(this);
        return a; // No need to create a new tree
    }
    
    public SymbolicExpression visit(Quit a) {
        return a;
    }
    
    public SymbolicExpression visit(Scope n) {
        
        n.getExpression().accept(this); 
        return n;
        
    }
    
    public SymbolicExpression visit(Sin a) {
        a.value.accept(this);
        return a;
    }
    
    public SymbolicExpression visit(Subtraction a) {
        a.valueLeft.accept(this);
        a.valueRight.accept(this);
        return a; // No need to create a new tree
    }
    
    public SymbolicExpression visit(Variable a) {
        return a;
    }
    
    public SymbolicExpression visit(Vars a) {
        return a;
    }
}
