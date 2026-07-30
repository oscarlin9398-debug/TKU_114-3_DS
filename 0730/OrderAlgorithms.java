import java.util.ArrayList;
import java.util.List;

public class OrderAlgorithms {

    public static void sortByAmountDescending(List<Order> orders, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sortByAmountDescending(orders, left, mid);
        sortByAmountDescending(orders, mid + 1, right);
        mergeDescending(orders, left, mid, right);
    }

    private static void mergeDescending(List<Order> orders, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<Order> leftList = new ArrayList<>();
        List<Order> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) {
            leftList.add(orders.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(orders.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).getAmount() >= rightList.get(j).getAmount()) {
                orders.set(k, leftList.get(i));
                i++;
            } else {
                orders.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            orders.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            orders.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static List<Order> searchByCustomerName(List<Order> orders, String customerName) {
        List<Order> results = new ArrayList<>();
        if (orders == null || customerName == null) {
            return results;
        }

        for (Order order : orders) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                results.add(order);
            }
        }
        return results;
    }
}
