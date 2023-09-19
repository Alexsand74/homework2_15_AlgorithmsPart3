package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Homework will be here!");
        IntegerList integerList = new IntegerListImpl();
        integerList.add(4);   // 0
        integerList.add(10);  // 1
        integerList.add(5);   // 2
        integerList.add(-8);  // 3
        integerList.add(-15); // 4
        integerList.add(7);   // 5
        integerList.add(286); // 6
        System.out.println(integerList);
        Integer a = 10;
        integerList.remove(a);
        System.out.println(integerList);
        integerList.remove(0);
        System.out.println(integerList);
        integerList.add(3, 5);
        System.out.println(integerList);
        System.out.println(integerList.contains(10));
        System.out.println(integerList.contains(11));
    }

}