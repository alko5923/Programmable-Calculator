package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.*; 
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 

public class TestsIntegrationAST extends TestCase {

// *** Creating an Environment HashMap ***
  
  private Environment env = new Environment();
  final EvaluationVisitor evaluator = new EvaluationVisitor();

// *** PRIMARY CHECK TEST *** 
  
  @Test
  public void testOk() {
    String str = "Junit is working fine";
    assertEquals("Junit is working fine", str);
  }

// *** TESTS for ADDITION

  @Test
  public void testAddition1()
  {
    Constant c1 = new Constant(2);
    Constant c2 = new Constant(3);
    Constant c3 = new Constant(4);
    Constant c4 = new Constant(5);
    Addition a1 = new Addition(c1, c2);
    Addition a2 = new Addition(c3, c4);
    Addition a3 = new Addition(a1, a2);
    assertEquals("14.0", evaluator.evaluate(a3, env).toString());
  }
  
  @Test
  public void testAddition2() 
  {
    Constant c1 = new Constant(2);
    Constant c2 = new Constant(3);
    Constant c3 = new Constant(4);
    Variable v1 = new Variable("x");
    Addition a1 = new Addition(c1, c2);
    Addition a2 = new Addition(c3, v1);
    Addition a3 = new Addition(a1, a2);
    assertEquals("5.0 + 4.0 + x", evaluator.evaluate(a3, env).toString());
  }


  @Test
  public void testAddition3()  
  {
    Constant c1 = new Constant(4);
    Constant c2 = new Constant(5);
    Negation c3 = new Negation(c2);
    Variable v1 = new Variable("x");
    Addition a1 = new Addition(c1, c3);
    Addition a2 = new Addition(v1, a1);
    assertEquals("x + -1.0", evaluator.evaluate(a2, env).toString());
  }


  @Test
  public void testAddition4() 
  {
    Constant c1 = new Constant(5);
    Negation c2 = new Negation(c1);
    Variable v1 = new Variable("x");
    Addition a1 = new Addition(v1, c2);
    assertEquals("x + -5.0", evaluator.evaluate(a1, env).toString());
  }
  
  
  @Test
  public void testAddition5()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Addition a1 = new Addition(v1, v2);
    assertEquals("x + y", evaluator.evaluate(a1, env).toString());
  }
  

// *** TESTS for ASSIGNMENT
  
  @Test
  public void testAssignment1()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Constant c4 = new Constant(5);
    Assignment ass1 = new Assignment(c4 , v2);
    Addition a1 = new Addition(v1, ass1);
    assertEquals("x + 5.0", evaluator.evaluate(a1, env).toString());
  }
  
  
  @Test
  public void testAssignment2()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Constant c1 = new Constant(2);
    Constant c4 = new Constant(4);
    Assignment ass1 = new Assignment(c4 , v2);
    Division d1 = new Division(ass1, c1);
    Negation n1 = new Negation(d1);
    assertEquals("-2.0", evaluator.evaluate(n1, env).toString());
  }

// *** TESTS for TRIGONOMETRY

  @Test
  public void testTrigonometrical1()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Constant c1 = new Constant(2);
    Constant c4 = new Constant(Math.PI / 2);
    Assignment ass1 = new Assignment(c1 , v2);
    Sin sin1 = new Sin(c4);
    Multiplication m1 = new Multiplication(sin1, ass1);
    Negation n1 = new Negation(m1);
    assertEquals("-2.0", evaluator.evaluate(n1, env).toString());
  }

  @Test
  public void testTrigonometrical2()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Constant c4 = new Constant(Math.PI / 2);
    Assignment ass1 = new Assignment(c4 , v2);
    Cos cos1 = new Cos(ass1);
    Negation n1 = new Negation(cos1);
    assertEquals(0.0, evaluator.evaluate(n1, env).getValue(), 0.0001);
  }


  @Test
  public void testTrigonometrical3()
  {
    Variable v1 = new Variable("x");
    Variable v2 = new Variable("y");
    Constant c1 = new Constant(2);
    Constant c4 = new Constant(Math.PI / 2);
    Assignment ass1 = new Assignment(c1 , v2);
    Sin sin1 = new Sin(c4);
    Multiplication m1 = new Multiplication(sin1, ass1);
    Negation n1 = new Negation(m1);
    assertEquals("-2.0", evaluator.evaluate(n1, env).toString());
  }
  
  
// *** TESTS for MIXED NODES (checking the functioning of parentheses)
  
  @Test
  public void testMixed1()
  {
    Constant c1 = new Constant(3);
    Constant c2 = new Constant(5);
    Multiplication m1 = new Multiplication (c1, c2);
    Constant c3 = new Constant(2);
    Constant c4 = new Constant(4);
    Constant c5 = new Constant(1);
    Addition a1 = new Addition(c4, c5);
    Division d1 = new Division(c3, a1);
    Addition a2 = new Addition(m1, d1);
    assertEquals("15.4", evaluator.evaluate(a2, env).toString());
  }
  
  @Test
  public void testMixed2()
  {
    Variable x = new Variable("x");
    Constant c2 = new Constant(5);
    Multiplication m1 = new Multiplication (c2, x);
    Constant c3 = new Constant(2);
    Constant c4 = new Constant(4);
    Variable y = new Variable("y");
    Addition a1 = new Addition(c4, y);
    Division d1 = new Division(c3, a1);
    Addition a2 = new Addition(m1, d1);
    assertEquals("5.0 * x + 2.0 / (4.0 + y)", evaluator.evaluate(a2, env).toString());
  }
  
  @Test
  public void testMixed3()
  {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    Addition a1 = new Addition(a, b);
    Subtraction s1 = new Subtraction(c, d);
    Multiplication m1 = new Multiplication(a1, s1);
    assertEquals("(a + b) * (c - d)", evaluator.evaluate(m1, env).toString());
  }
  
  
  @Test
  public void testMixed4()
  {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    Addition a1 = new Addition(a, b);
    Multiplication m1 = new Multiplication(c, d);
    Multiplication m2 = new Multiplication(a1, m1);
    assertEquals("(a + b) * c * d", evaluator.evaluate(m2, env).toString());
  }
  
  @Test
  public void testMixed5()
  {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    Constant c5 = new Constant(5);
    Constant c2 = new Constant(2);
    Constant c4 = new Constant(4);
    Negation c22 = new Negation(c2); // -2
    
    Multiplication m1 = new Multiplication(c5, a); // 5.0 * a
    Addition a1 = new Addition(c4, b); // 4.0 + b
    Multiplication m2 = new Multiplication(c2, c); // 2.0 * c
    Division d1 = new Division(c2, a1); // 2.0 / (4.0 + b)
    Addition a2 = new Addition(m1, d1); // 5.0 * a + 2.0 / (4.0 + b)
    Subtraction s1 = new Subtraction(a2, m2); // 5.0 * a + 2.0 / (4.0 + b) - 2.0 * c
    Multiplication m3 = new Multiplication(c4, d); // 4.0 * d
    Addition a3 = new Addition(c22, m3); // -2.0 + 4.0 * d
    Division d2 = new Division(s1, a3); // 5.0 * a + 2.0 / (4.0 + b) - 2.0 * c / -2.0 + 4.0 * d
    
    assertEquals("(5.0 * a + 2.0 / (4.0 + b) - 2.0 * c) / (-2.0 + 4.0 * d)", evaluator.evaluate(d2, env).toString());
  }
  
  @Test
  public void testMixed6()
  {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    Constant c5 = new Constant(5);
    Constant c2 = new Constant(2);
    Constant c4 = new Constant(4);
    Negation c22 = new Negation(c2); // -2
    
    Multiplication m1 = new Multiplication(c5, a); // 5.0 * a
    Addition a1 = new Addition(c4, b); // 4.0 + b
    Multiplication m2 = new Multiplication(c2, c); // 2.0 * c
    Division d1 = new Division(c2, a1); // 2.0 / (4.0 + b)
    Addition a2 = new Addition(m1, d1); // 5.0 * a + 2.0 / (4.0 + b)
    Subtraction s1 = new Subtraction(a2, m2); // 5.0 * a + 2.0 / (4.0 + b) - 2.0 * c
    Addition a3 = new Addition(c4, d); // 4.0 + d
    Multiplication m3 = new Multiplication(c22, a3); // -2.0 * (4.0 + d)
    Division d2 = new Division(s1, m3); // 5.0 * a + 2.0 / (4.0 + b) - 2.0 * c / -2.0 + 4.0 * d
    
    assertEquals("(5.0 * a + 2.0 / (4.0 + b) - 2.0 * c) / -2.0 * (4.0 + d)", evaluator.evaluate(d2, env).toString());
  }
  
}