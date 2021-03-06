package org.ioopm.calculator.ast;

// *** Interface for the Visitor pattern ***

public interface Visitor {

    public SymbolicExpression visit(Addition n);
    public SymbolicExpression visit(Assignment n);
    public SymbolicExpression visit(Constant n);
    public SymbolicExpression visit(Cos n);
    public SymbolicExpression visit(Clear n);
    public SymbolicExpression visit(Division n);
    public SymbolicExpression visit(Exp n);
    public SymbolicExpression visit(EqualTo n);
    public SymbolicExpression visit(FunctionCall n);
    public SymbolicExpression visit(FunctionDeclaration n);
    public SymbolicExpression visit(GreaterThan n);
    public SymbolicExpression visit(GreaterThanOrEqualTo n);
    public SymbolicExpression visit(LessThan n);
    public SymbolicExpression visit(LessThanOrEqualTo n);
    public SymbolicExpression visit(Log n);
    public SymbolicExpression visit(Multiplication n);
    public SymbolicExpression visit(NamedConstant n);
    public SymbolicExpression visit(Nuke n);
    public SymbolicExpression visit(Negation n);
    public SymbolicExpression visit(Quit n);
    public SymbolicExpression visit(Scope n);
    public SymbolicExpression visit(Sin n);
    public SymbolicExpression visit(Subtraction n);
    public SymbolicExpression visit(Variable n);
    public SymbolicExpression visit(Vars n);
}
