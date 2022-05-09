package org.ioopm.calculator;

import java.io.*;
import java.util.*;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;


/*
 *** A symbolic calculator, input from the terminal. ***
*/

public class Calculator {
    
    //  *** Statistics counters ***
        static private int expressionCounter = 0;
        static private int successCounter = 0;
        
    //  *** Initializing data structures ***
    static HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
    static Environment vars = new Environment(); 
    
    
    // *** Scanner ***
    static private Scanner sc = new Scanner(System.in);
    public static CalculatorParser p = new CalculatorParser();
    
    public static void main(String [] args) {
        
        SymbolicExpression result; // result of each input from user
        EvaluationVisitor evaluator = new EvaluationVisitor();
        vars.put(new Variable("ans"), new Constant(0)); // creates default value for ans
        Vars.instance().setVariables(vars);
        
        System.out.println("Welcome to the parser!");
        
        
        while (true) {
            Environment oldVars = (Environment) vars.clone(); // Backup old variables
            System.out.print("Please enter an expression: ");
            
            try {
                
                String line = sc.nextLine();
                result = p.parse(line, vars, functions);
                    
                if (result.isCommand()) {
                    if (result == Vars.instance()) {
                        System.out.println(oldVars);
                    }
                    if (result == Quit.instance()) {
                        System.out.print("Number of expressions entered: " + expressionCounter + "\n");
                        System.out.print("Number of expressions fully evaluated: " 
                        + successCounter + "\n"); 
                        System.out.println("Good bye!");
                        break;
                    }
                    if (result == Clear.instance()) {
                        Clear.instance().clear(vars);
                    }
                }
                
                else if (result.isFuncDeclaration()) {
                    functionMode((FunctionDeclaration)result, vars, functions);
                    }
                    
                else if (result.isFuncCall()) {
                    evaluateFunction((FunctionCall)result, vars, functions);
                }
                
                else {
                    if (checkStatic(result)) {
                        final SymbolicExpression evaluationResult = evaluator.evaluate(result, vars);
                        System.out.println(evaluationResult);
                        
                
                    if (!evaluationResult.equals("ans")) {
                            vars.put(new Variable("ans"), evaluationResult);
                            
                        if (evaluationResult.isConstant()) {
                            successCounter += 1;
                        }  
                    } 
                    expressionCounter += 1;
                    }
                }
    
            } 
            
            catch(SyntaxErrorException e) {
                System.out.print("Syntax Error: ");
                System.out.println(e.getMessage());
            } 
            catch(IOException e) {
                System.err.print("IO Exception: ");
                System.out.println(e.getMessage());
            } 
            catch(IllegalExpressionException e) {
                System.out.print("Illegal Expression: ");
                System.out.println(e.getMessage());
                vars = (Environment) oldVars.clone(); // Reset variables
            } 
            catch(RuntimeException e) {
                System.out.print("Runtime Error: ");
                System.out.println(e.getMessage());
            }  
        }
    }
    
    public static boolean checkStatic(SymbolicExpression result) {
        NamedConstantChecker ncc = new NamedConstantChecker();
        ReassignmentChecker rac = new ReassignmentChecker();
        return (ncc.check(result) && rac.check(result));
    }
    
    public static void functionMode(FunctionDeclaration func, Environment vars, 
    HashMap<String, FunctionDeclaration> functions) throws IOException {
        
        functions.put(func.getName(), func); // add the new function in the hashmap
        
        // set parameters into env to avoid getting error (Can't compare non-assigned variables)
        for (SymbolicExpression param : func.getParameters()) {
            vars.put(new Variable(param.toString()), new Constant(0));
        }
        
        String line = sc.nextLine();
        
        while(!line.equals("end"))  // add parsed line after line into function body until you read an "end"
        {
            SymbolicExpression result = p.parse(line, vars, functions);
            if (result.isCommand()) {
                throw new IllegalExpressionException("Can't have commands within functions!");
            }
            func.getFuncBody().add(result);
            line = sc.nextLine();
        }
        // remove parameter you set in before to avoid getting error (Can't compare non-assigned variables)
        for (SymbolicExpression param : func.getParameters()) {
            vars.remove(new Variable(param.toString()), new Constant(0));
        }
        System.out.println("Added a new function: ");
        System.out.println(func);
    }
    
    public static void evaluateFunction(FunctionCall result, Environment vars, 
    HashMap<String, FunctionDeclaration> functions) {
        
        if (result.getArgs().size() != functions.get(result.getName()).getParameters().size()) {
            throw new IllegalExpressionException("Number of arguments doesn't match number of parameters!");
        }
        final EvaluationVisitor evaluator = new EvaluationVisitor();
        final SymbolicExpression evaluationResult = evaluator.evaluate(result, vars, functions);
        System.out.println(evaluationResult);
        expressionCounter += 1;
        
        if (!evaluationResult.equals("ans")) {
            vars.put(new Variable("ans"), evaluationResult);
            if (evaluationResult.isConstant()) {
                successCounter += 1;
            }  
        } 
    }
    
}