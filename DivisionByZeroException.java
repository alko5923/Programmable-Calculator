package org.ioopm.calculator.ast;

class DivisionByZeroException extends ArithmeticException {
    DivisionByZeroException() {
        super("Division by zero!");
    }
}