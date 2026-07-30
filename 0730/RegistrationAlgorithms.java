import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    public static void sortByIdAscending(List<Registration> list, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortByIdAscending(list, left, mid);
        sortByIdAscending(list, mid + 1, right);
        mergeByIdAscending(list, left, mid, right);
    }

    private static void mergeByIdAscending(List<Registration> list, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<Registration> leftList = new ArrayList<>();
        List<Registration> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(list.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).getRegId().compareTo(rightList.get(j).getRegId()) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static int binarySearchById(List<Registration> list, String targetId) {
        if (list == null || list.isEmpty() || targetId == null) {
            return -1;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = list.get(mid).getRegId().compareTo(targetId);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static List<Registration> sequentialSearchByName(List<Registration> list, String name) {
        List<Registration> results = new ArrayList<>();
        if (list == null || name == null) {
            return results;
        }

        for (Registration reg : list) {
            if (reg.getName().equalsIgnoreCase(name)) {
                results.add(reg);
            }
        }
        return results;
    }
}