public class LoginCheck {
    public static void main(String[] args) {
        String username = "jerry";
        String password = "8787";

        String inputUsername = "jerry";
        String inputPassword = "8787";

        boolean isLoginSuccess = username.equals(inputUsername) && password.equals(inputPassword);

        System.out.println("Login Success: " + isLoginSuccess);
    }
}
