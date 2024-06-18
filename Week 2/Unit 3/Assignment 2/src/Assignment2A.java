interface firstInterface {

    default void log(String string) {
        System.out.println("This method is default implementation from first Interface " + string);
    }
}

interface secondInterface {
    default void log(String string) {
        System.out.println("This method is default implementation from second Interface " + string);
    }

}

public class Assignment2A implements firstInterface, secondInterface {

    @Override
    public void log(String string) {
        firstInterface.super.log(string);
        secondInterface.super.log(string);
    }

    public static void main(String[] args) throws Exception {
        Assignment2A assignment = new Assignment2A();
        assignment.log("Interface");
    }

}
