public class User {
    String name;
    int id;
    Book borrowedBook;

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
        System.out.println("Студент - " + name + "; idCard: " + id + "; Одолженная книга: ");
        if (borrowedBook != null) {
            borrowedBook.displayInfo();
        } else {
            System.out.println("Нет одолженных книг");
        }
    }
}
