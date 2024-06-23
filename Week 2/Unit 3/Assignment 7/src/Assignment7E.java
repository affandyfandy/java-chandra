import java.util.ArrayList;
import java.util.List;

class Paginator<T> {
    private List<T> data;
    private int pageSize;
    private int totalItems;

    public Paginator(List<T> data, int pageSize) {
        this.data = data;
        this.pageSize = pageSize;
        this.totalItems = data.size();
    }

    public List<T> getPage(int pageNumber) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        return data.subList(startIndex, endIndex);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        this.totalItems = data.size();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}

public class Assignment7E {
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        // Create a list of objects (e.g., Person objects)
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 30));
        persons.add(new Person("Bob", 25));
        persons.add(new Person("Charlie", 35));
        persons.add(new Person("David", 28));
        persons.add(new Person("Eve", 32));

        // Initialize Paginator with list of persons and page size of 2
        Paginator<Person> paginator = new Paginator<>(persons, 2);

        // Get and print total pages
        int totalPages = paginator.getTotalPages();
        System.out.println("Total pages: " + totalPages);

        // Iterate over each page and print the contents
        for (int page = 1; page <= totalPages; page++) {
            List<Person> pageData = paginator.getPage(page);
            System.out.println("\nPage " + page + ":");
            for (Person person : pageData) {
                System.out.println(person.getName() + ", " + person.getAge());
            }
        }
    }
}
