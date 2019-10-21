package classes;

import action.Action;
import databases.BookShelf;
import databases.RecordShelf;
import excetions.BorrowedOutException;
import excetions.NoSuchBookException;
import excetions.NotBorrowedException;
import excetions.YetBorrowedException;

import java.util.Scanner;

public class Student extends User {
    public Student(String id, String name) {
        super(id,name);
    }

    @Override
    public void menu() {
        System.out.println("**  同学，请选择 **");
        System.out.println("** 0. 退出 **");
        System.out.println("** 1. 查阅图书 **");
        System.out.println("** 2. 借阅图书 **");
        System.out.println("** 3. 归还图书 **");
        System.out.println("** 4. 查询借阅情况 **");
    }

    @Override
    public boolean input() throws NoSuchBookException {
        Scanner scanner = new Scanner(System.in);
        int selected = scanner.nextInt();
        scanner.nextLine();
        switch (selected) {
            case 0: return true;
            case 1:
                queryBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook(); break;
            case 4:
                //searchBook();break;
        }

        return false;
    }

    private void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要归还书籍的 ISBN:");
        String ISBN = scanner.nextLine();
        User user = User.getCurrentUser();
        try {
            BookShelf bookShelf = BookShelf.getInstance();
            Book book = bookShelf.search(ISBN);
            RecordShelf recordShelf = RecordShelf.getInstance();
            recordShelf.remove(user, ISBN);
            book.returnBook();
            System.out.printf("《%s》归还成功！%n", book.getTitle());
        } catch (NoSuchBookException e) {
            System.out.println("错误！没有这本书");
        }
    }

    private void borrowBook() throws NoSuchBookException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要借阅书籍的 ISBN:");
        String ISBN = scanner.nextLine();
        User user = User.getCurrentUser();

        try {
            Book book = Action.borrowBook(user, ISBN);
            System.out.printf("《%s》借阅成功！%n", book.getTitle());
        }catch (NoSuchBookException e){
            System.out.println("错误！没有这本书");
        }catch (BorrowedOutException e){
            System.out.println("错误！这本书已经全被借完了");
        }catch (YetBorrowedException e){
            System.out.println("错误！这本书已经你已经借过了");
        }
    }


}
