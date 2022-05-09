package org.ioopm.calculator.ast;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * An environment is a HashMap using variables as keys and symbolic expressions as values.
 */
public class Environment extends HashMap<Variable, SymbolicExpression> {
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Variables: ");
        TreeSet<Variable> vars = new TreeSet<>(this.keySet());
        Iterator<Variable> iter = vars.iterator();
        while (iter.hasNext()) {
            Variable v = iter.next();
            if (v.getName() != "ans") {
                
                sb.append(v.getName());
                sb.append(" = ");
                sb.append(this.get(v));
                sb.append(", ");
            }
        }
        
        int index = sb.lastIndexOf(","); // find the index of the last comma to remove
        if (index != -1) { // condition to check if a comma even exists in the string builder
            sb.deleteCharAt(index); // delete the last comma
            sb.deleteCharAt(index); // delete the last whitespace
        }
        
         
        return sb.toString();
    }
    

}

