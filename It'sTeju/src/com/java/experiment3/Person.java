package com.java.experiment3;

//Person.java
public class Person {
 String name;
 int age;

 public Person(String name, int age) {
     this.name = name;
     this.age = age;
 }

 public void displayInfo() {
     System.out.println("Name: " + name + ", Age: " + age);
 }
}
