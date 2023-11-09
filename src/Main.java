import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        System.out.println("Система управления библиотекой");

        while (true) {
            int roleChoice = selectRole(scanner);

            if (roleChoice == 1) {
                adminMenu(scanner, library);
            } else if (roleChoice == 2) {
                studentMenu(scanner, library);
            } else if (roleChoice == 3) {
                break;
            } else {
                System.out.println("Неверный выбор роли. Попробуйте снова");
            }
        }
    }

    private static int selectRole(Scanner scanner) {
        System.out.print("Выберите роль (1 - админ, 2 - студент, 3 - выйти): ");
        return scanner.nextInt();
    }

    private static void adminMenu(Scanner scanner, Library library) {
        boolean isAdminMenu = true;

        while (isAdminMenu) {
            System.out.println("""
                    Меню админа:
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
                case 1 -> addStudent(scanner, library);
                case 2 -> addBook(scanner, library);
                case 3 -> issueBookToStudent(scanner, library);
                case 4 -> returnBook(scanner, library);
                case 5 -> library.displayStudents();
                case 6 -> library.displayBooks();
                case 7 -> isAdminMenu = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова");
            }
        }
    }

    private static void studentMenu(Scanner scanner, Library library) {
        System.out.print("Введите id студента: ");
        int studentLogin = scanner.nextInt();
        Student student = library.getStudentById(studentLogin);

        if (student != null) {
            boolean isStudentMenu = true;

            while (isStudentMenu) {
                System.out.printf("""
                        Меню студента %s:
                        1. Взять книгу
                        2. Вернуть книгу
                        3. Список книг
                        4. Выйти в меню выбора роли
                        """, student.name);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        library.displayBooks();
                        issueBookToStudent(scanner, library, student);
                    }
                    case 2 -> returnBook(library, student);
                    case 3 -> displayBorrowedBook(student);
                    case 4 -> isStudentMenu = false;
                }
            }
        } else {
            System.out.println("Студент с таким id не найден. Попробуйте снова");
        }
    }

    private static void addStudent(Scanner scanner, Library library) {
        System.out.print("Введите имя студента: ");
        String studentName = scanner.next();
        System.out.print("Введите id студента: ");
        int studentId = scanner.nextInt();

        Student student = new Student(studentName, studentId);
        library.addStudent(student);
    }

    private static void addBook(Scanner scanner, Library library) {
        System.out.print("Введите название книги: ");
        scanner.nextLine();
        String bookTitle = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String bookAuthor = scanner.nextLine();
        System.out.print("Введите id книги: ");
        int bookId = scanner.nextInt();

        Book book = new Book(bookTitle, bookAuthor, bookId);
        library.addBook(book);
    }

    private static void issueBookToStudent(Scanner scanner, Library library) {
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
    }

    public static void returnBook(Scanner scanner, Library library) {
        System.out.print("Введите id студента: ");
        int studentIdToReturn = scanner.nextInt();

        Student studentToReturn = library.getStudentById(studentIdToReturn);
        if (studentToReturn != null) {
            library.acceptReturnedBook(studentToReturn);
        } else {
            System.out.println("Студент не найден");
        }
    }

    private static void issueBookToStudent(Scanner scanner, Library library, Student student) {
        System.out.print("Введите id книги: ");
        int bookIdToIssue = scanner.nextInt();

        Book bookToIssue = library.getBookById(bookIdToIssue);
        if (bookToIssue != null) {
            library.issueBook(bookToIssue, student);
        } else {
            System.out.println("Книга не найдена");
        }
    }

    public static void returnBook(Library library, Student student) {
        library.acceptReturnedBook(student);
    }

    public static void displayBorrowedBook(Student student) {
        if (student.borrowedBook != null) {
            student.borrowedBook.displayInfo();
        } else {
            System.out.println("Нет одолженных книг");
        }
    }
}