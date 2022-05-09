package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 
import java.io.IOException;

public class TestsScope extends TestCase {
  
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

// *** TEST FOR SCOPE toString() ***

  @Test
  public void testScope1() {
    Variable x = new Variable("x");
    Constant one = new Constant(1);
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    Scope s2 = new Scope(a2);
    assertEquals("{1.0 = x}", s1.toString());
  }
 
  @Test
  public void testScope2() {
    //{1 = x} + {1 = x} Scope(Addition)
    Constant one = new Constant(1);
    Constant oneone = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(oneone, x);
    Scope s1 = new Scope(a1);
    Scope s2 = new Scope(a2);
    Addition add1 = new Addition(s1, s2);
    assertEquals("{1.0 = x} + {1.0 = x}", add1.toString());
  }
  
  @Test
  public void testScope3() {
    // {{1 = x} = x}
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    Assignment a2 = new Assignment(s1, x);
    Scope s2 = new Scope(a2);
    assertEquals("{{1.0 = x} = x}", s2.toString());
  }
  
  @Test
  public void testScope4() {
    //{(2 = x) + {1 = x}}
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(two, x);
    Scope s1 = new Scope(a1);
    Addition add1 = new Addition(a2, s1);
    Scope s2 = new Scope(add1);
    assertEquals("{(2.0 = x) + {1.0 = x}}", s2.toString());
  }
  
  
  @Test
  public void testScope5() {
    //(1 = x) + {(2 + x = x) + {3 + x = x}}
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Constant three = new Constant(3);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Addition add1 = new Addition(two, x); // 2.0+x
    Assignment a2 = new Assignment(add1, x); //2.0+x=x
    Addition add2 = new Addition(three, x); //3.0+x
    Assignment a3 = new Assignment(add2, x); //3.0+x=x
    Scope s1 = new Scope(a3);
    Addition add3 = new Addition(a2, s1); //2.0+x=x + {3+x=x}
    Scope s2 = new Scope(add3);
    Addition add4 = new Addition(a1, s2);
    assertEquals("(1.0 = x) + {(2.0 + x = x) + {3.0 + x = x}}", add4.toString());
  }
  
  
  @Test
  public void testScope6() {
    //{{1.0 = x} = x} = y
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Variable y = new Variable("y");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Scope s1 = new Scope(a1); // {1.0=x}
    Assignment a2 = new Assignment(s1, x); //{1.0=x}=x
    Scope s2 = new Scope(a2); //{{1.0=x}=x}
    Assignment a3 = new Assignment(s2, y); //{{1.0=x}=x}=y
    assertEquals("{{1.0 = x} = x} = y", a3.toString());
  }
  
  
// *** TESTS for SCOPE PARSER *** 
  
  @Test
  public void testScope7() throws IOException {
    Variable x = new Variable("x");
    Constant one = new Constant(1);
    Assignment a1 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    String input = "{1.0 = x}";
    result = p.parse(input, env);
    assertEquals(result.toString(), s1.toString());
  }
  
  @Test
  public void testScope8() throws IOException {
    //{1 = x} + {1 = x} Scope(Addition)
    Constant one = new Constant(1);
    Constant oneone = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(oneone, x);
    Scope s1 = new Scope(a1);
    Scope s2 = new Scope(a2);
    Addition add1 = new Addition(s1, s2);
    String input = "{1.0 = x} + {1.0 = x}";
    result = p.parse(input, env);
    assertEquals(result.toString(), add1.toString());
  }
  
  
  @Test
  public void testScope9() throws IOException {
    // {{1 = x} = x}
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    Assignment a2 = new Assignment(s1, x);
    Scope s2 = new Scope(a2);
    String input = "{{1.0 = x} = x}";
    result = p.parse(input, env);
    assertEquals(result.toString(), s2.toString());
  }
  
  @Test
  public void testScope10() throws IOException {
    //{(2 = x) + {1 = x}}
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(two, x);
    Scope s1 = new Scope(a1);
    Addition add1 = new Addition(a2, s1);
    Scope s2 = new Scope(add1);
    String input = "{(2.0 = x) + {1.0 = x}}";
    result = p.parse(input, env);
    assertEquals(result.toString(), s2.toString());
  }
  
  @Test
  public void testScope11() throws IOException {
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Constant three = new Constant(3);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Addition add1 = new Addition(two, x); // 2.0+x
    Assignment a2 = new Assignment(add1, x); //2.0+x=x
    Addition add2 = new Addition(three, x); //3.0+x
    Assignment a3 = new Assignment(add2, x); //3.0+x=x
    Scope s1 = new Scope(a3);
    Addition add3 = new Addition(a2, s1); //2.0+x=x + {3+x=x}
    Scope s2 = new Scope(add3);
    Addition add4 = new Addition(a1, s2);
    String input = add4.toString();
    result = p.parse(input, env);
    assertTrue(result.toString().equals(input));
  }
  
  @Test
  public void testScope12() throws IOException {
    //{{1.0 = x} = x} = y
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Variable y = new Variable("y");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Scope s1 = new Scope(a1); // {1.0=x}
    Assignment a2 = new Assignment(s1, x); //{1.0=x}=x
    Scope s2 = new Scope(a2); //{{1.0=x}=x}
    Assignment a3 = new Assignment(s2, y); //{{1.0=x}=x}=y
    String input = a3.toString();
    result = p.parse(input, env);
    assertTrue(result.toString().equals(input));
  }
  
// *** TESTS FOR SCOPE EVALUATION THROUGH PARSER ***

  @Test
  public void testScope13() throws IOException {
    // {1.0 = x}
    Variable x = new Variable("x");
    Constant one = new Constant(1);
    Assignment a1 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    String input = s1.toString();
    result = p.parse(input, env);
    assertTrue(evaluator.evaluate(result, env).equals(one));
  }
  
  @Test
  public void testScope14() throws IOException {
    //{1.0 = x} + {1.0 = x} Scope(Addition)
    Constant one = new Constant(1);
    Constant oneone = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(oneone, x);
    Scope s1 = new Scope(a1);
    Scope s2 = new Scope(a2);
    Addition add1 = new Addition(s1, s2);
    String input = add1.toString();
    result = p.parse(input, env);
    Constant evaluation = new Constant(2);
    assertTrue(evaluator.evaluate(result, env).equals(evaluation));
  }
  
  
  @Test
  public void testScope15() throws IOException {
    // {{1 = x} = x}
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Scope s1 = new Scope(a1);
    Assignment a2 = new Assignment(s1, x);
    Scope s2 = new Scope(a2);
    String input = s2.toString();
    result = p.parse(input, env);
    assertTrue(evaluator.evaluate(result, env).equals(one));
  }
  
  @Test
  public void testScope16() throws IOException {
    //{(2 = x) + {1 = x}}
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x);
    Assignment a2 = new Assignment(two, x);
    Scope s1 = new Scope(a1);
    Addition add1 = new Addition(a2, s1);
    Scope s2 = new Scope(add1);
    String input = s2.toString();
    result = p.parse(input, env);
    Constant evaluation = new Constant(3);
    assertTrue(evaluator.evaluate(result, env).equals(evaluation));
  }
  
  @Test
  public void testScope17() throws IOException {
    // (1.0 = x) + {(2.0 + x = x) + {3.0 + x = x}}
    Constant one = new Constant(1);
    Constant two = new Constant(2);
    Constant three = new Constant(3);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Addition add1 = new Addition(two, x); // 2.0+x
    Assignment a2 = new Assignment(add1, x); //2.0+x=x
    Addition add2 = new Addition(three, x); //3.0+x
    Assignment a3 = new Assignment(add2, x); //3.0+x=x
    Scope s1 = new Scope(a3);
    Addition add3 = new Addition(a2, s1); //2.0+x=x + {3+x=x}
    Scope s2 = new Scope(add3);
    Addition add4 = new Addition(a1, s2);
    String input = add4.toString();
    result = p.parse(input, env);
    Constant evaluation = new Constant(10);
    System.out.println(evaluator.evaluate(result, env));
    
    assertTrue(evaluator.evaluate(result, env).equals(evaluation));
  }
  
  @Test
  public void testScope18() throws IOException {
    //{{1.0 = x} = x} = y
    Constant one = new Constant(1);
    Variable x = new Variable("x");
    Variable y = new Variable("y");
    Assignment a1 = new Assignment(one, x); // 1.0=x
    Scope s1 = new Scope(a1); // {1.0=x}
    Assignment a2 = new Assignment(s1, x); //{1.0=x}=x
    Scope s2 = new Scope(a2); //{{1.0=x}=x}
    Assignment a3 = new Assignment(s2, y); //{{1.0=x}=x}=y
    String input = a3.toString();
    result = p.parse(input, env);
    assertTrue(evaluator.evaluate(result, env).equals(one));
  }
}
