package org.ioopm.calculator.ast;

// *** Represents the "less than or equal to" conditional statement: if a <= b ***

public class LessThanOrEqualTo extends Conditional {
    
    private SymbolicExpression value1;
    private SymbolicExpression value2;
    private SymbolicExpression isTrue;
    private SymbolicExpression isFalse;
    
    public LessThanOrEqualTo(SymbolicExpression value1, SymbolicExpression value2, SymbolicExpression isTrue, 
    SymbolicExpression isFalse) {
        super("", value1, value2, isTrue, isFalse);
        this.value1 = value1;
        this.value2 = value2;
        this.isTrue = isTrue;
        this.isFalse = isFalse;
    }
    
    @Override
    public SymbolicExpression accept(Visitor v) {
        return v.visit(this);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if ");
        sb.append(value1.getRhs());
        sb.append(" <= ");
        sb.append(value2.getRhs());
        sb.append(" ");
        sb.append(isTrue);
        sb.append(" else ");
        sb.append(isFalse);
        return sb.toString();
    }
    
    public SymbolicExpression getValue1() {
        return this.value1;
    }
    
    public SymbolicExpression getValue2() {
        return this.value2;
    }
    
    public SymbolicExpression getIsTrue() {
        return this.isTrue;
    }
    
    public SymbolicExpression getIsFalse() {
        return this.isFalse;
    }
}