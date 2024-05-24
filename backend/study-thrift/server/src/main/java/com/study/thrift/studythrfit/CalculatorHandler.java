package com.study.thrift.studythrfit;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorHandler implements Calculator.Iface {
    @Override
    public int add(int num1, int num2) throws TException {
        return num1 + num2;
    }

    @Override
    public int subtract(int num1, int num2) throws TException {
        return num1 - num2;
    }
}
