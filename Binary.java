package org.ioopm.calculator.ast;

// *** Represents all binary expressions, meaning expression with a left and a right
//     operator. ***

public abstract class Binary extends SymbolicExpression {
    public SymbolicExpression lhs = null;
    public SymbolicExpression rhs = null;
    private int priority;
    
    public Binary(String name, SymbolicExpression lhs, SymbolicExpression rhs) { 
        super(name, lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
        super.setPriority(this.priority);
         
    } 
    

    public String toString() {
        
        StringBuilder tmp = new StringBuilder();
        
        if ((this.lhs instanceof Atom == false) && (this.lhs.getPriority() < this.getPriority())) {
            
            tmp.append("(");
            tmp.append(this.lhs);
            tmp.append(")");
            
        }
        
        else {
            tmp.append(this.lhs);
            
        }
        
        tmp.append(" ");
        tmp.append(this.getName());
        tmp.append(" ");
        
        if ((this.rhs instanceof Atom == false) && (this.rhs.getPriority() < this.getPriority())) {
            
            tmp.append("(");
            tmp.append(this.rhs);
            tmp.append(")");
            
        }
        
        else {
            
            tmp.append(this.rhs);
            
        }
        
        
        return tmp.toString();
    } 

}
