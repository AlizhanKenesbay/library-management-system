import java.util.ArrayList;

public class Library {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    public void issueBook(Book book, Student student) {
        student.borrowBook(book);
    }

    public void acceptReturnedBook(Student student) {
        if (student.borrowedBook != null) {
            student.returnBook();
        } else {
            System.out.println("У студента нет одолженных книг");
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.idCard == id) {
                return student;
            }
        }
        return null;
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.id == id) {
                return book;
            }
        }
        return null;
    }

    public void displayStudents() {
        System.out.println("Список студентов в библиотеке:");
        for (Student student : students) {
            student.displayInfo();
        }
    }

    public void displayBooks() {
        System.out.println("Список книг в библиотеке:");
        for (Book book : books) {
            book.displayInfo();
        }
    }
}