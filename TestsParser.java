package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.*; 
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 
import java.io.IOException;

public class TestsParser extends TestCase {

// *** Creating an Environment HashMap ***
  
  private Environment env = new Environment();
  CalculatorParser p = new CalculatorParser();
  SymbolicExpression result; 
  final EvaluationVisitor evaluator = new EvaluationVisitor();

// *** Setting the Before and After methods *** 

  @Before
  public void environmentInsert() {
    // 
  }
  
  @After
  public void environmentClean() {
    env.clear();
  }

// *** PRIMARY CHECK TEST *** 
  
  @Test
  public void testOk() {
    String str = "Junit is working fine";
    assertEquals("Junit is working fine", str);
  }

// *** TESTS for ... 

  @Test
  public void testConstant1() throws IOException
  {
    Constant c1 = new Constant(42);
    String input = c1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(c1));
  }
  
  @Test
  public void testNegation1() throws IOException
  {
    Constant c1 = new Constant(42);
    Negation n1 = new Negation(c1);
    String input = n1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(n1));
  }
  
  @Test
  public void testNegation2() throws IOException
  {
    Constant c1 = new Constant(42);
    Negation n1 = new Negation(c1);
    String input = "-42";
    result = p.parse(input, env);
    assertTrue(result.equals(n1));
  }
  
  @Test
  public void testUnary1() throws IOException
  {
    
    Sin sin1 = new Sin(new Constant(0));
    String input = sin1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(sin1));
  }
  
  @Test
  public void testUnary2() throws IOException
  {
    
    Cos cos1 = new Cos(new Constant(1));
    String input = cos1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(cos1));
  }
  
  @Test
  public void testUnary3() throws IOException
  {
    
    Log log1 = new Log(new Constant(1));
    String input = log1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(log1));
  }
  
  @Test
  public void testUnary4() throws IOException
  {
    
    Exp exp1 = new Exp(new Constant(1));
    String input = exp1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(exp1));
  }
  
  @Test
  public void testAddition1() throws IOException
  {
    Addition a1 = new Addition(new Constant(42), new Negation(new Constant(4711)));
    String input = a1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(a1));
  }
  
  @Test
  public void testAddition2() throws IOException
  {
    Addition a1 = new Addition(new Constant(42), new Constant(-42));
    String input = a1.toString();
    result = p.parse(input, env);
    Addition a2 = new Addition(new Constant(42), new Negation(new Constant(42)));
    assertTrue(result.equals(a2));
  }
  
  @Test
  public void testSubtraction1() throws IOException
  {
    Subtraction s1 = new Subtraction(new Constant(42), new Constant(-42));
    String input = s1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(s1));
  }
  
  @Test
  public void testSubtraction2() throws IOException
  {
    Subtraction s1 = new Subtraction(new Constant(0), new Constant(42));
    String input = s1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(s1));
  }
  
  @Test
  public void testMultiplication1() throws IOException
  {
    Multiplication m1 = new Multiplication(new Constant(0), new Constant(42));
    String input = m1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(m1));
  }
  
  @Test
  public void testMultiplication3() throws IOException
  {
    Multiplication m1 = new Multiplication(new Negation(new Constant(15)), new Constant(42));
    String input = m1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(m1));
  }
  
  @Test
  public void testDivision1() throws IOException
  {
    Division d1 = new Division(new Negation(new Constant(15)), new Constant(42));
    String input = d1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(d1));
  }
  
  @Test
  public void testDivision2() throws IOException
  {
    Division d1 = new Division(new Negation(new Constant(15)), new Constant(42));
    String input = d1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(d1));
  }
  
  @Test
  public void testAssignment1() throws IOException
  {
    Constant c1 = new Constant(42);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(c1, x);
    String input = a1.toString();
    result = p.parse(input, env);
    assertTrue(result.equals(a1));
  }
  
  @Test
  public void testAssignment2() throws IOException
  {
    Constant c1 = new Constant(42);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(c1, x);
    String input = "42 = x";
    result = p.parse(input, env);
    assertTrue(result.equals(a1));
  }
  
  @Test
  public void testAssignment3() throws IOException
  {
    Constant c1 = new Constant(42);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(c1, x);
    String input = "(42 = x)";
    result = p.parse(input, env);
    assertTrue(result.equals(a1));
  }
  
  @Test
  public void testAssignment4() throws IOException
  {
    try {
        Constant c1 = new Constant(42);
        Variable x = new Variable("x");
        Assignment a1 = new Assignment(c1, x);
        String input = "(42 = x";
        result = p.parse(input, env);
        assertTrue(result.equals(a1));
    }
    catch(SyntaxErrorException e) {
        System.out.print("Syntax Error: ");
        System.out.println(e.getMessage());
    }
  }
  
  @Test
  public void testCorner1() throws IOException
  {
    Variable x = new Variable("x");
    Negation n1 = new Negation(x);
    Negation n2 = new Negation(n1);
    Negation n3 = new Negation(n2);
    String input = evaluator.evaluate(n3, env).toString();
    result = p.parse(input, env);
    assertTrue(result.equals(n3));
  }
  
  @Test
  public void testCorner2() throws IOException
  {
    Variable x = new Variable("x");
    Negation n1 = new Negation(x);
    Negation n2 = new Negation(n1);
    Negation n3 = new Negation(n2);
    String input = "---x";
    result = p.parse(input, env);
    assertTrue(result.equals(n3));
  }
  
  
  @Test
  public void testMixed1() throws IOException
  {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    Constant c5 = new Constant(5);
    Constant c2 = new Constant(2);
    Constant c4 = new Constant(4);
    Negation c44 = new Negation(c4);
    Negation c22 = new Negation(c2); // -2
    
    Multiplication m1 = new Multiplication(c5, a); // 5.0 * a
    Addition a1 = new Addition(c44, b); // -4.0 + b
    Multiplication m2 = new Multiplication(c22, c); // (-2.0) * c
    Division d1 = new Division(c2, a1); // 2.0 / ((-4.0) + b)
    Addition a2 = new Addition(m1, d1); // 5.0 * a + 2.0 / ((-4.0) + b)
    Subtraction s1 = new Subtraction(a2, m2); // 5.0 * a + 2.0 / ((-4.0) + b) - (-2.0) * c
    
    String input = s1.toString();
    result = p.parse(input, env);
    
    assertTrue(result.equals(s1));
  }

}