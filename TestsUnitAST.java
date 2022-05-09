package org.ioopm.calculator.testing;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase; 
import static org.junit.Assert.assertEquals; 

public class TestsUnitAST extends TestCase {
  
  private Environment env = new Environment();
  final EvaluationVisitor evaluator = new EvaluationVisitor();

// *** PRIMARY CHECK TEST *** 
  
  @Test
  public void testOk() {
    String str= "Junit is working fine";
    assertEquals("Junit is working fine", str);
  }

// *** TEST FOR getValue() ***

  @Test
  public void testGetValueConstant1() {
    Constant zero = new Constant(0);
    assertEquals(0, zero.getValue(), 0);
  }
  
// *** TESTS FOR isConstant() *** 
  
  @Test
  public void testIsConstant1() {
    Constant one = new Constant(1);
    assertEquals(true, one.isConstant());
  }
  
  @Test
  public void testIsConstant2() {
    Variable x = new Variable("x");
    assertEquals(false, x.isConstant());
  }
    
    
  @Test
  public void testIsConstant3() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);  
    Addition add1 = new Addition(five, one);
    assertEquals(false, add1.isConstant());
  }
    
  @Test
  public void testIsConstant4() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);  
    Subtraction sub1 = new Subtraction(five, one);
    assertEquals(false, sub1.isConstant());
  }
    
  @Test
  public void testIsConstant5() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);  
    Division div1 = new Division(five, one);
    assertEquals(false, div1.isConstant());
  }
    
  @Test
  public void testIsConstant6() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);  
    Multiplication mult1 = new Multiplication(five, one);
    assertEquals(false, mult1.isConstant());
  }
  
  @Test
  public void testIsConstant7() {
    Variable x = new Variable("x");
    Constant one = new Constant(1);  
    Assignment ass1 = new Assignment(one, x);
    assertEquals(false, ass1.isConstant());
  }
  
  @Test
  public void testIsConstant8() {
    Constant one = new Constant(1);  
    Sin sin1 = new Sin(one);
    assertEquals(false, sin1.isConstant());
  }
    
  @Test
  public void testIsConstant9() {
    Constant one = new Constant(1);  
    Cos cos1 = new Cos(one);
    assertEquals(false, cos1.isConstant());
  }
    
  
  @Test
  public void testIsConstant10() {
    Constant one = new Constant(1);  
    Exp exp1 = new Exp(one);
    assertEquals(false, exp1.isConstant());
  }
  
  @Test
  public void testIsConstant11() {
    Constant one = new Constant(1);  
    Log log1 = new Log(one);
    assertEquals(false, log1.isConstant());
  }
  
  @Test
  public void testIsConstant12() {
    Constant one = new Constant(1);  
    Negation neg1 = new Negation(one);
    assertEquals(false, neg1.isConstant());
  }

// *** TESTS FOR getName() ***
  
  @Test
  public void testGetName1() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Addition fivePlusOne = new Addition(five, one);
    assertEquals("+", fivePlusOne.getName());
  }
  
  @Test
  public void testGetName2() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Subtraction fiveMinusOne = new Subtraction(five, one);
    assertEquals("-", fiveMinusOne.getName());
  }
  
  @Test
  public void testGetName3() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Multiplication fiveTimesOne = new Multiplication(five, one);
    assertEquals("*", fiveTimesOne.getName());
  }
  
  @Test
  public void testGetName4() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Division fiveDivideOne = new Division(five, one);
    assertEquals("/", fiveDivideOne.getName());
  }
  
  @Test
  public void testGetName5() {
    Constant five = new Constant(5);
    Variable x = new Variable("x");
    Assignment ass1 = new Assignment(five, x);
    assertEquals("=", ass1.getName());
  }
  
  @Test
  public void testGetName6() {
    Constant one = new Constant(1);  
    Sin sin1 = new Sin(one);
    assertEquals("Sin", sin1.getName());
  }
  
  @Test
  public void testGetName7() {
    Constant one = new Constant(1);  
    Cos cos1 = new Cos(one);
    assertEquals("Cos", cos1.getName());
  }

  @Test
  public void testGetName8() {
    Constant one = new Constant(1);  
    Exp exp1 = new Exp(one);
    assertEquals("Exp", exp1.getName());
  }
  
  @Test
  public void testGetName9() {
    Constant one = new Constant(1);  
    Log log1 = new Log(one);
    assertEquals("Log", log1.getName());
  }
  
  @Test
  public void testGetName10() {
    Constant one = new Constant(1);  
    Negation neg1 = new Negation(one);
    assertEquals("-", neg1.getName());
  }
  
// *** TESTS FOR isCommand() ***
  
  @Test
  public void testIsCommand1() {
    Vars command = Vars.instance();  
    assertEquals(true, command.isCommand());
  }
  
  @Test
  public void testIsCommand2() {
    Quit command = Quit.instance();  
    assertEquals(true, command.isCommand());
  }
  
  @Test
  public void testIsCommand3() {
    Clear command = Clear.instance();  
    assertEquals(true, command.isCommand());
  }
  
  @Test
  public void testIsCommand4() {
    Constant one = new Constant(1);  
    Sin sin1 = new Sin(one);
    assertEquals(false, sin1.isCommand());
  }
  
  @Test
  public void testIsCommand5() {
    Constant one = new Constant(1);  
    Cos cos1 = new Cos(one);
    assertEquals(false, cos1.isCommand());
  }
  
  @Test
  public void testIsCommand6() {
    Constant one = new Constant(1);  
    Negation neg1 = new Negation(one);
    assertEquals(false, neg1.isCommand());
  }
  
  @Test
  public void testIsCommand7() {
    Constant one = new Constant(1);  
    Exp exp1 = new Exp(one);
    assertEquals(false, exp1.isCommand());
  }
  
  @Test
  public void testIsCommand8() {
    Constant one = new Constant(1);  
    Log log1 = new Log(one);
    assertEquals(false, log1.isCommand());
  }
  
  @Test
  public void testIsCommand9() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Addition fivePlusOne = new Addition(five, one);
    assertEquals(false, fivePlusOne.isCommand());
  }
  
  @Test
  public void testIsCommand10() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Subtraction fiveMinusOne = new Subtraction(five, one);
    assertEquals(false, fiveMinusOne.isCommand());
  }
  
  @Test
  public void testIsCommand11() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Multiplication fiveTimesOne = new Multiplication(five, one);
    assertEquals(false, fiveTimesOne.isCommand());
  }
  
  @Test
  public void testIsCommand12() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Division fiveDivideOne = new Division(five, one);
    assertEquals(false, fiveDivideOne.isCommand());
  }
  
  @Test
  public void testIsCommand13() {
    Constant five = new Constant(5);
    Variable x = new Variable("x");
    Assignment ass1 = new Assignment(five, x);
    assertEquals(false, ass1.isCommand());
  }
  
  @Test
  public void testIsCommand14() {
    Constant five = new Constant(5);
    assertEquals(false, five.isCommand());
  }
  
  @Test
  public void testIsCommand15() {
    Variable x = new Variable("x");
    assertEquals(false, x.isCommand());
  }
  
// *** TESTS FOR getPriority() ***

  @Test
  public void testGetPriority1() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Addition fivePlusOne = new Addition(five, one);
    assertEquals(50, fivePlusOne.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority2() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Subtraction sub1 = new Subtraction(five, one);
    assertEquals(50, sub1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority3() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Multiplication fiveTimesOne = new Multiplication(five, one);
    assertEquals(100, fiveTimesOne.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority4() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Division fiveDivideOne = new Division(five, one);
    assertEquals(100, fiveDivideOne.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority5() {
    Constant five = new Constant(5);
    Variable x = new Variable("x");
    Assignment ass1 = new Assignment(five, x);
    assertEquals(0, ass1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority6() {
    Constant one = new Constant(1);  
    Sin sin1 = new Sin(one);
    assertEquals(0, sin1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority7() {
    Constant one = new Constant(1);  
    Cos cos1 = new Cos(one);
    assertEquals(0, cos1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority8() {
    Constant one = new Constant(1);  
    Log log1 = new Log(one);
    assertEquals(0, log1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority9() {
    Constant one = new Constant(1);  
    Exp exp1 = new Exp(one);
    assertEquals(0, exp1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority10() {
    Constant one = new Constant(1);  
    Negation neg1 = new Negation(one);
    assertEquals(0, neg1.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority11() {
    Vars command = Vars.instance();  
    assertEquals(0, command.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority12() {
    Quit command = Quit.instance();  
    assertEquals(0, command.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority13() {
    Clear command = Clear.instance();  
    assertEquals(0, command.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority14() {
    Constant one = new Constant(1);   
    assertEquals(0, one.getPriority(), 0);
  }
  
  @Test
  public void testGetPriority15() {
    Variable y = new Variable("y");   
    assertEquals(0, y.getPriority(), 0);
  }
  
// *** TESTS for toString() ***

  @Test
  public void testToString1() {
    Constant one = new Constant(1);
    assertEquals("1.0", one.toString());
  }
  
  @Test
  public void testToString2() {
    Variable y = new Variable("y");   
    assertEquals("y", y.toString());
  }
  
  @Test
  public void testToString3() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Addition fivePlusOne = new Addition(five, one);
    assertEquals("5.0 + 1.0", fivePlusOne.toString());
  }
  
  @Test
  public void testToString4() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Subtraction sub1 = new Subtraction(five, one);
    assertEquals("5.0 - 1.0", sub1.toString());
  }
  
  @Test
  public void testToString5() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Multiplication fiveTimesOne = new Multiplication(five, one);
    assertEquals("5.0 * 1.0", fiveTimesOne.toString());
  }
  
  @Test
  public void testToString6() {
    Constant five = new Constant(5);
    Constant one = new Constant(1);
    Division fiveDivideOne = new Division(five, one);
    assertEquals("5.0 / 1.0", fiveDivideOne.toString());
  }
  
  @Test
  public void testToString7() {
    Constant five = new Constant(5);
    Variable x = new Variable("x");
    Assignment ass1 = new Assignment(five, x);
    assertEquals("5.0 = x", ass1.toString());
  }
  
  @Test
  public void testToString8() {
    Constant one = new Constant(1);  
    Sin sin1 = new Sin(one);
    assertEquals("Sin(1.0)", sin1.toString());
  }
  
  @Test
  public void testToString9() {
    Constant one = new Constant(1);  
    Cos cos1 = new Cos(one);
    assertEquals("Cos(1.0)", cos1.toString());
  }
  
  @Test
  public void testToString10() {
    Constant one = new Constant(1);  
    Log log1 = new Log(one);
    assertEquals("Log(1.0)", log1.toString());
  }
  
  @Test
  public void testToString11() {
    Constant one = new Constant(1);  
    Exp exp1 = new Exp(one);
    assertEquals("Exp(1.0)", exp1.toString());
  }
  
  @Test
  public void testToString12() {
    Constant one = new Constant(1);  
    Negation neg1 = new Negation(one);
    assertEquals("-1.0", neg1.toString());
  }

// *** TESTS for equals() ***
  
  @Test
  public void testEquals1() {
    Constant one1 = new Constant(1);
    Constant one2 = new Constant(1);
    assertEquals(true, one1.equals(one2));
  }
  
  @Test
  public void testEquals2() {
    Variable y1 = new Variable("y");
    Variable y2 = new Variable("y");
    assertEquals(true, y1.equals(y2));
  }
  
  @Test
  public void testEquals3() {
    Constant zero1 = new Constant(0);
    Constant zero2 = new Constant(0);
    Sin sin1 = new Sin(zero1);
    Sin sin2 = new Sin(zero2);
    assertEquals(true, sin1.equals(sin2));
  }
  
  @Test
  public void testEquals4() {
    Constant zero1 = new Constant(0);
    Constant zero2 = new Constant(0);
    Cos cos1 = new Cos(zero1);
    Cos cos2 = new Cos(zero2);
    assertEquals(true, cos1.equals(cos2));
  }
  
  @Test
  public void testEquals5() {
    Constant zero1 = new Constant(0);
    Constant zero2 = new Constant(0);
    Log log1 = new Log(zero1);
    Log log2 = new Log(zero2);
    assertEquals(true, log1.equals(log2));
  }
  
  @Test
  public void testEquals6() {
    Constant zero1 = new Constant(0);
    Constant zero2 = new Constant(0);
    Exp exp1 = new Exp(zero1);
    Exp exp2 = new Exp(zero2);
    assertEquals(true, exp1.equals(exp2));
  }
  
  @Test
  public void testEquals7() {
    Constant one1 = new Constant(1);
    Constant one2 = new Constant(1);
    Negation neg1 = new Negation(one1);
    Negation neg2 = new Negation(one2);
    assertEquals(true, neg1.equals(neg2));
  }
  
  @Test
  public void testEquals8() {
    Constant one1 = new Constant(1);
    Constant five1 = new Constant(5);
    Constant one2 = new Constant(1);
    Constant five2 = new Constant(5);
    Addition add1 = new Addition(one1, five1);
    Addition add2 = new Addition(one2, five2);
    assertEquals(true, add1.equals(add2));
  }
  
  @Test
  public void testEquals9() {
    Constant one1 = new Constant(1);
    Constant five1 = new Constant(5);
    Constant one2 = new Constant(1);
    Constant five2 = new Constant(5);
    Subtraction sub1 = new Subtraction(one1, five1);
    Subtraction sub2 = new Subtraction(one2, five2);
    assertEquals(true, sub1.equals(sub2));
  }
  
  @Test
  public void testEquals10() {
    Constant one1 = new Constant(1);
    Constant five1 = new Constant(5);
    Constant one2 = new Constant(1);
    Constant five2 = new Constant(5);
    Division div1 = new Division(one1, five1);
    Division div2 = new Division(one2, five2);
    assertEquals(true, div1.equals(div2));
  }
  
  @Test
  public void testEquals11() {
    Constant one1 = new Constant(1);
    Constant five1 = new Constant(5);
    Constant one2 = new Constant(1);
    Constant five2 = new Constant(5);
    Multiplication mult1 = new Multiplication(one1, five1);
    Multiplication mult2 = new Multiplication(one2, five2);
    assertEquals(true, mult1.equals(mult2));
  }
  
  @Test
  public void testEquals12() {
    Constant one1 = new Constant(1);
    Variable x1 = new Variable("x");
    Constant one2 = new Constant(1);
    Variable x2 = new Variable("x");
    Assignment ass1 = new Assignment(one1, x1);
    Assignment ass2 = new Assignment(one2, x2);
    assertEquals(true, ass1.equals(ass2));
  }

  
// *** TESTS for eval() ***

  @Test
  public void testEval1() {
    Constant one1 = new Constant(1);
    assertEquals("1.0", evaluator.evaluate(one1, env).toString());
  }
  
  @Test
  public void testEval2() {
    Variable x = new Variable("x");
    assertEquals("x", evaluator.evaluate(x, env).toString());
  }
  
  @Test
  public void testEval3() {
    Variable x = new Variable("x");
    Constant one = new Constant(1);
    Assignment ass1 = new Assignment(one, x);
    assertEquals("1.0", evaluator.evaluate(ass1, env).toString());
  }
  
  @Test
  public void testEval4() {
    Constant one = new Constant(1);
    Constant two =  new Constant(2);
    Addition add1 = new Addition(one,two);
    assertEquals("3.0" , evaluator.evaluate(add1, env).toString());
  }
  
  @Test
  public void testEval5() {
    Constant one1 = new Constant(1);
    Constant two =  new Constant(2);
    Subtraction subb1 = new Subtraction(one1,two);
    assertEquals("-1.0" , evaluator.evaluate(subb1, env).toString());
  }
  
  @Test
  public void testEval6() {
    Constant one1 = new Constant(5);
    Constant two =  new Constant(3);
    Multiplication mul1 = new Multiplication(one1,two);
    assertEquals("15.0" , evaluator.evaluate(mul1, env).toString());
  }
  
  @Test
  public void testEval7() {
    Constant one1 = new Constant(1);
    Constant two =  new Constant(2);
    Division div1 = new Division(one1,two);
    assertEquals("0.5" , evaluator.evaluate(div1, env).toString());
  }
  
  @Test
  public void testEval8() {
    Constant pi = new Constant(Math.PI);
    Cos cos1 = new Cos(pi);
    assertEquals("-1.0" , evaluator.evaluate(cos1, env).toString());
  }
  
  @Test
  public void testEval9() {
    Constant one1 = new Constant(5);
    Exp exp1 = new Exp(one1);
    assertEquals("148.4131591025766" , evaluator.evaluate(exp1, env).toString());
  }
  
  @Test
  public void testEval10() {
    Constant one1 = new Constant(Math.E);
    Log log1 = new Log(one1);
    assertEquals("1.0" , evaluator.evaluate(log1, env).toString());
  }
  
  @Test
  public void testEval11() {
    Constant one1 = new Constant(-5);
    Negation neg1 = new Negation(one1);
    assertEquals("5.0" , evaluator.evaluate(neg1, env).toString());
  }
  
  @Test
  public void testEval12() {
    Constant pi = new Constant(Math.PI);
    Sin sin1 = new Sin(pi);
    assertEquals(0.0, evaluator.evaluate(sin1, env).getValue(), 0.0001);
  }
  
  @Test
  public void testEval13() {
    Constant zero = new Constant(0);
    Variable x = new Variable("x");
    Assignment a1 = new Assignment(zero, x);
    Sin sin1 = new Sin(a1);
    
    assertEquals(0.0, evaluator.evaluate(sin1, env).getValue(), 0.0001);
  }

}