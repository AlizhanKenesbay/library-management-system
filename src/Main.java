import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean isAdminMenu;
        boolean isStudentMenu;
        Admin admin = new Admin();
        admin.name = "adm";
        admin.id = 1;

        System.out.println("Система управления библиотекой");
        while (true) {
            System.out.print("Выберите роль (1 - админ, 2 - студент, 3 - Выйти): ");
            int roleChoice = scanner.nextInt();

            if (roleChoice == 1) {
                isAdminMenu = true;
                while (isAdminMenu) {
                    System.out.println("""
                            Меню:
                            1. Добавить студента
                            2. Добавить книгу
                            3. Вручить книгу студенту
                            4. Возврат книги
                            5. Список студентов
                            6. Список книг 
                            7. Выйти в меню выбора роли
                            """);
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Введите имя студента: ");
                            String studentName = scanner.next();
                            System.out.print("Введите id студента: ");
                            int studentId = scanner.nextInt();
                            Student student = new Student();
                            student.name = studentName;
                            student.id = studentId;
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
                            isAdminMenu = false;
                            break;
                        default:
                            System.out.println("Неверный выбор. Попробуйте снова");
                    }
                }
            } else if (roleChoice == 2) {
                System.out.print("Введите id студента: ");
                int studentLogin = scanner.nextInt();
                Student student = library.getStudentById(studentLogin);

                if (student != null) {
                    isStudentMenu = true;
                    while (isStudentMenu) {
                        System.out.println("Здравствуйте, " + student.name);
                        System.out.println("""
                            Меню студента:
                            1. Взять книгу
                            2. Вернуть книгу
                            3. Список книг
                            4. Выйти в меню выбора роли
                            """);
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                library.displayBooks();
                                System.out.print("Введите id книги: ");
                                int bookIdToIssue = scanner.nextInt();
                                Book bookToIssue = library.getBookById(bookIdToIssue);
                                if (bookToIssue != null) {
                                    library.issueBook(bookToIssue, student);
                                } else {
                                    System.out.println("книга не найдена");
                                }
                                break;
                            case 2:
                                library.acceptReturnedBook(student);
                                break;
                            case 3:
                                System.out.println(student.borrowedBook.title);
                                break;
                            case 4:
                                isStudentMenu = false;
                                break;
                        }
                    }
                } else {
                    System.out.println("Студент с таким ID не найден. Попробуйте снова.");
                }
            } else if (roleChoice == 3) {
                break;
            } else {
                System.out.println("Неверный выбор роли. Попробуйте снова");
            }
        }
    }
}