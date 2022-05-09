package org.ioopm.calculator.ast;
import java.util.*;

// *** Enables the evaluation of all symbolic expressions through the visitor interface. ***

public class EvaluationVisitor implements Visitor {
    LinkedList<Environment> envStack = new LinkedList<Environment>();
    public HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env) {
        envStack.addFirst(env);
        functions = null;
        return topLevel.accept(this);
    }
    
    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env, 
    HashMap<String, FunctionDeclaration> _functions) {
        envStack.addFirst(env);
        functions = _functions;
        return topLevel.accept(this);
    }
    

    // This method gets called from Addition.accept(Visitor v) -- you should
    // be able to see from the eval() methods how these should behave (i.e., 
    // compare this method with your Addition::eval() and Symbolic.addition) 
    public SymbolicExpression visit(Addition n) {
        // Visit the left hand side and right hand side subexpressions
        SymbolicExpression left = n.valueLeft.accept(this);
        SymbolicExpression right = n.valueRight.accept(this);
        // When we come back here, the visitor has visited all subexpressions, 
        // meaning left and right point to newly created trees reduced to 
        // the extent possible (best case -- both are constants)

        // If subexpressions are fully evaluated, replace them in
        // the tree with a constant whose value is the sub of the
        // subexpressions, if not, simply construct a new addition
        // node from the new subexpressions
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }
    
    public SymbolicExpression visit(Assignment n) {
        SymbolicExpression left = n.getLhs().accept(this);
        if  (n.getRhs() instanceof NamedConstant) {
            throw new IllegalExpressionException("Not allowed to change fixed constants!");
        } 
        else if (n.getRhs() instanceof Variable) {
            envStack.getFirst().put((Variable) n.getRhs(), left);
            return left;
        } 
        else {
            throw new RuntimeException("Must have something assigned to '=");
        }
    }
    
    public SymbolicExpression visit(Constant n) {
        
        return n; 
        
    }
    
    public SymbolicExpression visit(Cos n) throws IllegalExpressionException {
        
        SymbolicExpression arg = n.value.accept(this);
        if (arg.isConstant()) {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
        
    }
    
    public SymbolicExpression visit(Clear n) {
        
        throw new RuntimeException("Commands cannot be evaluated!");
        
    }
    
    public SymbolicExpression visit(Division n) {
        
        SymbolicExpression left = n.valueLeft.accept(this);
        SymbolicExpression right = n.valueRight.accept(this);
        
        if (left.isConstant() && right.isConstant()) {
            if (right.getValue() == 0) throw new DivisionByZeroException();
            return new Constant(left.getValue() / right.getValue());
        } else {
            return new Division(left, right);
        }
        
    }
    
    public SymbolicExpression visit(Exp n) throws IllegalExpressionException {
        SymbolicExpression arg = n.value.accept(this);
        if (arg.isConstant()) {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg); // different from Johan's version
        }
    }
    
    public SymbolicExpression visit(EqualTo n) {
        SymbolicExpression value1 = n.getValue1().accept(this);
        SymbolicExpression value2 = n.getValue2().accept(this);
        
        if(value1.getValue() == value2.getValue()) {
            return n.getIsTrue().accept(this);
        }
        
        else {
            return n.getIsFalse().accept(this);
        }
    }

    
    public SymbolicExpression visit(FunctionCall n) {
        /*
        Now, how to implement arguments to a function? Well, we know the internal names 
        of the parameters from the function definition, and we know the values of the arguments 
        from the function call. For each parameter p bound to the argument a we are going to prepend a
        a = p to the function body Sequence.*/
        String name = n.getName();
        // create a stack of environments
        // push new env to stack
        // add arguments to top of stack
        // accept all statements in func body
        // pop environment before returning
        if (functions.containsKey(name)) {
            Environment funcEnv = new Environment();
            envStack.addFirst(funcEnv);
            FunctionDeclaration function = functions.get(name);
            LinkedList<SymbolicExpression> parameters = function.getParameters();
            LinkedList<SymbolicExpression> args = n.getArgs();
            int numberOfArgs = args.size();
            for (int i = 0; i < numberOfArgs; i+=1) {
                SymbolicExpression paramVar = parameters.get(i);
                SymbolicExpression argVar = args.get(i);
                SymbolicExpression value = argVar.accept(this);
                if (value.isConstant() == false) {
                    throw new RuntimeException("Not a constant!" + argVar + "=" + value);
                }
                Assignment newValue = new Assignment(value, paramVar);
                
                newValue.accept(this);

            }
            SymbolicExpression value = null;
            LinkedList<SymbolicExpression> funcBody = function.getFuncBody();
            for(int i = 0; i < funcBody.size(); i+=1) {
            
            value = funcBody.get(i).accept(this);
            }
            envStack.removeFirst();
            return value;
        }
        
        return n;
    }
    
    public SymbolicExpression visit(FunctionDeclaration n) { 
        
        throw new RuntimeException("Wrong eval call!");
    }
    
    public SymbolicExpression visit(GreaterThan n) {
        SymbolicExpression value1 = n.getValue1().accept(this);
        SymbolicExpression value2 = n.getValue2().accept(this);
        
        if(value1.getValue() > value2.getValue()) {
            return n.getIsTrue().accept(this);
        }
        
        else {
            return n.getIsFalse().accept(this);
        }
    }
    
    public SymbolicExpression visit(GreaterThanOrEqualTo n) {
        SymbolicExpression value1 = n.getValue1().accept(this);
        SymbolicExpression value2 = n.getValue2().accept(this);
        
        if(value1.getValue() >= value2.getValue()) {
            return n.getIsTrue().accept(this);
        }
        
        else {
            return n.getIsFalse().accept(this);
        }
    }
    
    
    public SymbolicExpression visit(LessThan n) {
        SymbolicExpression value1 = n.getValue1().accept(this);
        SymbolicExpression value2 = n.getValue2().accept(this);
        
        if(value1.getValue() < value2.getValue()) {
            return n.getIsTrue().accept(this);
        }
        
        else {
            return n.getIsFalse().accept(this);
        }
    }
    
    
    public SymbolicExpression visit(LessThanOrEqualTo n) {
        SymbolicExpression value1 = n.getValue1().accept(this);
        SymbolicExpression value2 = n.getValue2().accept(this);
        
        if(value1.getValue() <= value2.getValue()) {
            return n.getIsTrue().accept(this);
        }
        
        else {
            return n.getIsFalse().accept(this);
        }
    }
    
    
    
    public SymbolicExpression visit(Log n) throws IllegalExpressionException {
        SymbolicExpression arg = n.value.accept(this);
        if (arg.isConstant()) {
            return new Constant(Math.log(arg.getValue()));
        } else {
            return new Log(arg); // different from Johan's version
        }
    }
    
    public SymbolicExpression visit(Multiplication n) {
        SymbolicExpression left = n.valueLeft.accept(this);
        SymbolicExpression right = n.valueRight.accept(this);
        
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() * right.getValue());
        } else {
            return new Multiplication(left, right);
        }
    }
    
    public SymbolicExpression visit(NamedConstant n)
    {
        return new Constant(n.getValue());
    }
    
    public SymbolicExpression visit(Negation n) {
        SymbolicExpression arg = n.value.accept(this);
        if (arg.isConstant()) {
            return new Constant((-1)*(arg.getValue()));
            
        } 
        else {
            return new Negation(arg);
        }
    }
    
    public SymbolicExpression visit(Nuke n)
    {
        
        double upperBoundary = n.value.getValue();
        System.out.println(upperBoundary);
        Random rd = new Random();
        Environment env2 = envStack.getFirst();
        for (Variable variable : env2.keySet()) {
            env2.put(variable, new Constant(rd.nextDouble()*upperBoundary));
        }
        return new Constant(0);
    }
    
    public SymbolicExpression visit(Quit n) {
        throw new RuntimeException("Commands cannot be evaluated!");
    }
    
    
    public SymbolicExpression visit(Scope n) {
        Environment localEnv = new Environment();
        //localEnv.putAll(envStack.getFirst());
        envStack.addFirst(localEnv);
        SymbolicExpression expression = n.getExpression().accept(this); 
        envStack.removeFirst();
        return expression;
    }
    
    public SymbolicExpression visit(Sin n) throws IllegalExpressionException {
        
        SymbolicExpression arg = n.value.accept(this);
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
        
    }
    
    public SymbolicExpression visit(Subtraction n) {
        SymbolicExpression left = n.valueLeft.accept(this);
        SymbolicExpression right = n.valueRight.accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() - right.getValue());
        } else {
            return new Subtraction(left, right);
        }
    }
    
    public SymbolicExpression visit(Variable n) {
        // find the variable somewhere in the stack
        // create a temp stack
        // check the first env in stack, if the var is there, return it
        // otherwise push env to temp stack
        // when you have found a var then pop and push back envs from temp stack to ordinary stack
        for(Environment env : envStack) {
            SymbolicExpression value = env.get(n);
            if (value != null) {
                return value;
            }
        }
        
        return n;
        
    }
    
    public SymbolicExpression visit(Vars n) {
        
        throw new RuntimeException("Commands cannot be evaluated!");
    }

}
