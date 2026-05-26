public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mail Server!");
        MailServer mSrc = new MailServer();
        mSrc.start();
    }
}
