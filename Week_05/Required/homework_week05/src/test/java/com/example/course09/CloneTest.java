package com.example.course09;

public class CloneTest implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest test = new CloneTest();
        Object o = test.clone();
        System.out.println(33);
    }
}
