import java.util.concurrent.CopyOnWriteArrayList;

public class Assignment5F {
    public static void main(String[] args) {
        // Create a copyWriteArrayList and add three elements
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Robin Hood");
        list.add("Ada Wong");
        list.add("Charlie Casper");

        // Start to iterate CopyOnWriteArrayList
        for (String s : list) {
            System.out.println("Iterating: " + s);

            // Modify CopyOnWriteArrayList while iterating
            if (s.equals("Ada Wong")) {
                list.set(1, "Ada Wong Great");
                list.add("four");
                System.out.println("List modified inside iteration");
            }
        }

        System.out.println("Final list: " + list);
    }
}
