# OOP Principal in Java:

## Four Principal in Java

#### Encapsulation

Encapsulation is one of the concept that to operate a data into a single unit by using a modifier such as **public**, **private**, or **protected** to control the access.

```java
public class Ninja {
    private String name;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String attack(){
        return "Attack another Player"
    }
}
```

#### Inheritance

Inheritance is one of the most used technique to inherit all the behaviour that super class have. There is a Super Class and Sub Class, super class will inherite all the behaviour and sub class is the one who got all the behaviour from the super class.

```java
public class NinjaEquip extends Ninja {
    private String Weapon;

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String name) {
        this.Weapon = Weapon;
    }

    public void Display(){
       System.out.println(Ninja.name);
       System.out.println(getWeapon());

    }
}
```

#### Polymorphism

Polymorphism allows objects of different types to be treated as objects of a common superclass. In Java, you can achieve polymorphism through method overriding and method overloading.

```java
public class Ninja {
    private String name;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String attack(){
        return "Attack another Player"
    }
}

public class NinjaEquip extends Ninja {
    private String Weapon;

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String name) {
        this.Weapon = Weapon;
    }

    public String attack(){
        return "Attack another Player with" + this.getWeapon();
    }
    public void Display(){
       System.out.println(Ninja.name);
       System.out.println(getWeapon());
    }
}
```

#### Abstraction

Abstraction is the process of hiding the implementation details and showing only the important features of the object.

```java
public abstract class Ninja {
    public abstract double damage();
}

public class NinjaEquip extends Ninja {
    private String Weapon;

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String name) {
        this.Weapon = Weapon;
    }

    public String attack(){
        return "Attack another Player with" + this.getWeapon();
    }
    public void Display(){
       System.out.println(Ninja.name);
       System.out.println(getWeapon());
    }

    public double damage(){
        return 50;
    }
}
```
