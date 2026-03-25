package com.java.experiment3;

//Manager.java
public class Manager extends Employee {
 double bonus;

 public Manager(String name, int age, double baseSalary, double bonus) {
     super(name, age, baseSalary);
     this.bonus = bonus;
 }

 // Method Overriding - Calculate salary including bonus
 @Override
 public double calculateSalary() {
     return baseSalary + bonus;
 }
}
