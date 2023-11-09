public class Student {
    String name;
    int id;
    Book borrowedBook;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void borrowBook(Book book) {
        if (borrowedBook == null) {
            borrowedBook = book;
            System.out.println(name + " взял книгу:");
            borrowedBook.displayInfo();
        } else {
            System.out.println(name + " должен вернуть книгу, прежде чем взять новую");
        }
    }

    public void returnBook() {
        System.out.println(name + " вернул книгу:");
        borrowedBook.displayInfo();
        borrowedBook = null;
    }

    public void displayInfo() {
        System.out.println("Студент - " + name + "; id: " + id + "; Одолженная книга: ");
        if (borrowedBook != null) {
            borrowedBook.displayInfo();
        } else {
            System.out.println("Нет одолженных книг");
        }
    }
}