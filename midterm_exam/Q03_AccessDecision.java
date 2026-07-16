public class Q03_AccessDecision {
    public static void main(String[] args) {
        System.out.println(decideAccess(20, true, false, false));
        System.out.println(decideAccess(20, false, true, false));
        System.out.println(decideAccess(17, true, true, false));
        System.out.println(decideAccess(30, true, true, true));
        System.out.println(decideAccess(130, true, true, false));
    }

    public static String decideAccess(
            int age,
            boolean member,
            boolean hasInvitation,
            boolean suspended
    ) {
        if (age < 0 || age > 120) {
            return "INVALID";
        }
        if (suspended) {
            return "DENIED";
        }
        if (age >= 18 && (member || hasInvitation)) {
            return "ALLOWED";
        }
        return "REVIEW";
    }
}
