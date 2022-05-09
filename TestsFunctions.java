package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class TestsFunctions extends TestCase {
  
  CalculatorParser p = new CalculatorParser();
  private Environment env = new Environment();
  SymbolicExpression result; 
  final EvaluationVisitor evaluator = new EvaluationVisitor();
  FunctionBody funcBody = new FunctionBody();
  LinkedList<SymbolicExpression> params = new LinkedList<SymbolicExpression>();
  LinkedList<SymbolicExpression> args = new LinkedList<SymbolicExpression>();
  HashMap<String, FunctionDeclaration> funcList = new HashMap<String, FunctionDeclaration>();
  

// *** PRIMARY CHECK TEST *** 
  
  @Test
  public void testOk() {
    String str= "Junit is working fine";
    assertEquals("Junit is working fine", str);
  }

// *** TEST FOR CONDITIONALS ***

  @Test
  public void testFunction1() {
      Variable x = new Variable("x");
      Variable y = new Variable("y");
      Assignment x5 = new Assignment(new Constant(5), x);
      Assignment y7 = new Assignment(new Constant(7), y);
      Scope ifTrue = new Scope(new Constant(42));
      Scope ifFalse = new Scope(new Constant(4711));
      LessThan xy = new LessThan(x5, y7, ifTrue, ifFalse);
      funcBody.add(xy);
      params.add(x);
      params.add(y);
      args.add(x);
      args.add(y);
      FunctionDeclaration max = new FunctionDeclaration("max", funcBody, params);
      FunctionCall callMax = new FunctionCall("max", args);
      String result = "function max(x, y) " + "\n" + "if 5.0 = x < 7.0 = y {42.0} else {4711.0}";
      assertEquals(max.toString(), result);
  }
  

  @Test
  public void testFunction2() {
      Variable n = new Variable("n");
      Variable m = new Variable("m");
      Constant one = new Constant(1);
      Assignment nm1 = new Assignment(new Subtraction(n, one), m);
      args.add(m);
      Scope ifTrue = new Scope(new Multiplication(new FunctionCall("factorial", args), n)); 
      Scope ifFalse = new Scope(one);
      GreaterThan nm2 = new GreaterThan(n, one, ifTrue, ifFalse); 
      funcBody.add(nm1);
      funcBody.add(nm2);
      params.add(n);
      FunctionDeclaration factorial = new FunctionDeclaration("factorial", funcBody, params);
      String result = "function factorial(n) " + "\n" + "n - 1.0 = m" + "\n" + 
      "if n > 1.0 {factorial(m) * n} else {1.0}" + "\n";
      assertEquals(factorial.toString(), result);
  }
  
}
