class Myclass {
    int value;
    String condition;
}

public class assignment6a {
    public static void modifyObject(Myclass x) {
        x.value = 10;
        x.condition = "After Modify";
        // x = new Myclass();
        // x.value = 10;
    }

    public static void main(String[] args) {
        Myclass obj = new Myclass();
        obj.value = 5;
        obj.condition = "Before Modify";

        System.out.println(obj.value);
        System.out.println(obj.condition);
        modifyObject(obj);

        System.out.println("obj value after modification: " + obj.value);
        System.out.println("obj value after modification: " + obj.condition);

    }
}
