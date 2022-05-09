package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 
import java.io.IOException;

public class TestsConditionals extends TestCase {
  
  CalculatorParser p = new CalculatorParser();
  private Environment env = new Environment();
  SymbolicExpression result; 
  final EvaluationVisitor evaluator = new EvaluationVisitor();
  

// *** PRIMARY CHECK TEST *** 
  
  @Test
  public void testOk() {
    String str= "Junit is working fine";
    assertEquals("Junit is working fine", str);
  }

// *** TEST FOR CONDITIONALS ***

  @Test
  public void testConditional1() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Constant num1 = new Constant(42);
    Constant num2 = new Constant(4711);
    Scope isTrue = new Scope(num1); 
    Scope isFalse = new Scope(num2);
    LessThan xy = new LessThan(x3, y4, isTrue, isFalse);
    assertTrue(xy.toString().equals("if 3.0 = x < 4.0 = y {42.0} else {4711.0}"));
    
  }
  
  @Test
  public void testConditional2() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Constant num1 = new Constant(42);
    Constant num2 = new Constant(4711);
    Scope isTrue = new Scope(num1); 
    Scope isFalse = new Scope(num2);
    LessThan xy = new LessThan(x3, y4, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional3() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Constant num1 = new Constant(42);
    Constant num2 = new Constant(4711);
    Scope isTrue = new Scope(num1); 
    Scope isFalse = new Scope(num2);
    LessThan xy = new LessThan(y4, x3, isTrue, isFalse);
    Constant evaluation = new Constant(4711);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional4() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    GreaterThan xy = new GreaterThan(y4, x3, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional5() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    GreaterThan xy = new GreaterThan(x3, y4, isTrue, isFalse);
    Constant evaluation = new Constant(4711);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional6() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    EqualTo xy = new EqualTo(x3, y4, isTrue, isFalse);
    Constant evaluation = new Constant(4711);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional7() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Constant one = new Constant(1);
    Subtraction sub = new Subtraction(new Constant(4), new Constant(1));
    Assignment x3 = new Assignment(three, x);
    Assignment y3 = new Assignment(sub, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    EqualTo xy = new EqualTo(x3, y3, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional8() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Constant one = new Constant(1);
    Subtraction sub = new Subtraction(new Constant(4), new Constant(1));
    Assignment x3 = new Assignment(three, x);
    Assignment y3 = new Assignment(sub, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    LessThanOrEqualTo xy = new LessThanOrEqualTo(x3, y3, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional9() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Constant one = new Constant(1);
    Subtraction sub = new Subtraction(new Constant(4), new Constant(2));
    Assignment x3 = new Assignment(three, x);
    Assignment y2 = new Assignment(sub, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    LessThanOrEqualTo xy = new LessThanOrEqualTo(x3, y2, isTrue, isFalse);
    Constant evaluation = new Constant(4711);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional10() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    LessThanOrEqualTo xy = new LessThanOrEqualTo(x3, y4, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional11() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Constant one = new Constant(1);
    Subtraction sub = new Subtraction(new Constant(4), new Constant(1));
    Assignment x3 = new Assignment(three, x);
    Assignment y3 = new Assignment(sub, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    GreaterThanOrEqualTo xy = new GreaterThanOrEqualTo(x3, y3, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional12() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Constant one = new Constant(1);
    Subtraction sub = new Subtraction(new Constant(4), new Constant(2));
    Assignment x3 = new Assignment(three, x);
    Assignment y2 = new Assignment(sub, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    GreaterThanOrEqualTo xy = new GreaterThanOrEqualTo(x3, y2, isTrue, isFalse);
    Constant evaluation = new Constant(42);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
  @Test
  public void testConditional13() throws IOException {
    Variable x = new Variable("x");
    Constant three = new Constant(3);
    Variable y = new Variable("y");
    Constant four = new Constant(4);
    Assignment x3 = new Assignment(three, x);
    Assignment y4 = new Assignment(four, y);
    Scope isTrue = new Scope(new Constant(42)); 
    Scope isFalse = new Scope(new Constant(4711));
    GreaterThanOrEqualTo xy = new GreaterThanOrEqualTo(x3, y4, isTrue, isFalse);
    Constant evaluation = new Constant(4711);
    assertTrue(evaluator.evaluate(xy, env).equals(evaluation));
  }
  
}
