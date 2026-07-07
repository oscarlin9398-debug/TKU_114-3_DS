public class DrinkMenu {
    public static void main(String[] args) {
        int option = 3;

        switch (option) {
            case 1:
                System.out.println("Black tea");
                break;
            case 2:
                System.out.println("Green tea");
                break;
            case 3:
                System.out.println("Coffee");
                break;
            default:
                System.out.println("Unknown option");
        }
    }
}
