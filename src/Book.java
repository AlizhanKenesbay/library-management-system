public class Book {
    String title;
    String author;
    int id;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public void displayInfo() {
        System.out.println("Книга - " + title + "; Автор: " + author + "; ID книги: " + id + "\n");
    }
}