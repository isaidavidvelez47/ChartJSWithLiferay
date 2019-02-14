package co.com.colfondos.herramientapo.model;

/**
 * Class that models an object of type person
 * with common attributes
 */
public class Person {

    private String name;
    private String gender;
    private int age;
    private double salary;
    private double balance;

    public Person() {}

    public Person(String name, String gender, int age, double salary, double balance) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
