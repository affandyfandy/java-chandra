class dog {
    private String color;
    private String name;
    private String breed;

    dog(String color, String name, String breed) {
        this.color = color;
        this.name = name;
        this.breed = breed;
    }

    public void waggingthetail() {
        System.out.println(this.name + " with the color of " + this.color + " and breed of " + this.breed
                + " has a behavior of wagging the tail.\n");
    }

    public void barking() {
        System.out.println(this.name + " with the color of " + this.color + " and breed of " + this.breed
                + " has a behavior of barking.\n");
    }

    public void eating() {
        System.out.println(this.name + " with the color of " + this.color + " and breed of " + this.breed
                + " has a behavior of eating.\n");
    }
}

public class Assignment1A {

    public static void main(String[] args) throws Exception {
        dog dog1 = new dog("Brown", "Jhonny", "Alaskan Mhalamut");
        dog dog2 = new dog("Black", "Doggo", "Chihuahua");
        dog dog3 = new dog("Yellow", "Ruff", "Pitbull");
        dog1.barking();
        dog2.eating();
        dog3.waggingthetail();
    }
}
