import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        System.out.println("Система управления библиотекой");
        while (true) {
            System.out.println("""
                    Меню:
                    1. Добавить студента
                    2. Добавить книгу
                    3. Вручить книгу студенту
                    4. Возврат книги
                    5. Список студентов
                    6. Список книг 
                    7. Выйти """);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Введите имя студента: ");
                    String studentName = scanner.next();
                    System.out.print("Введите id студента: ");
                    int studentId = scanner.nextInt();
                    Student student = new Student();
                    student.name = studentName;
                    student.idCard = studentId;
                    library.addStudent(student);
                    break;
                case 2:
                    System.out.print("Введите название книги: ");
                    scanner.nextLine();
                    String bookTitle = scanner.nextLine();
                    System.out.print("Введите автора книги: ");
                    String bookAuthor = scanner.nextLine();
                    System.out.print("Введите id книги: ");
                    int bookId = scanner.nextInt();
                    Book book = new Book();
                    book.title = bookTitle;
                    book.author = bookAuthor;
                    book.id = bookId;
                    library.addBook(book);
                    break;
                case 3:
                    System.out.print("Введите id студента: ");
                    int studentIdToIssue = scanner.nextInt();
                    System.out.print("Введите id книги: ");
                    int bookIdToIssue = scanner.nextInt();
                    Student studentToIssue = library.getStudentById(studentIdToIssue);
                    Book bookToIssue = library.getBookById(bookIdToIssue);
                    if (studentToIssue != null && bookToIssue != null) {
                        library.issueBook(bookToIssue, studentToIssue);
                    } else {
                        System.out.println("Студент или книга не найдены");
                    }
                    break;
                case 4:
                    System.out.print("Введите id студента: ");
                    int studentIdToReturn = scanner.nextInt();
                    Student studentToReturn = library.getStudentById(studentIdToReturn);
                    if (studentToReturn != null) {
                        library.acceptReturnedBook(studentToReturn);
                    } else {
                        System.out.println("Студент не найден");
                    }
                    break;
                case 5:
                    library.displayStudents();
                    break;
                case 6:
                    library.displayBooks();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова");
            }
        }
    }
}