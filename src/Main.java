import classes.User;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true){
            User currentUser = User.login();
            boolean isQuit;
                currentUser.menu();
                isQuit = currentUser.input();
                if(isQuit) {
                    break;
                }
        }
//        System.out.println("欢迎下次光临");
    }
}
