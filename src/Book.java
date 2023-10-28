public class Book {
    int id;
    String title;
    String author;

    public void displayInfo() {
        System.out.println("Книга - " + title + "; Автор: " + author + "; ID книги: " + id + "\n");
    }
}