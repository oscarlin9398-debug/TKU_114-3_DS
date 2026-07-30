public class StableMergeSort {
    public static void main(String[] args) {
        Record[] records = {
            new Record("Amy", 90),
            new Record("Ben", 80),
            new Record("Cara", 90),
            new Record("Dan", 80)
        };

        mergeSort(records);

        for (Record record : records) {
            System.out.println(record);
        }
    }

    public static void mergeSort(Record[] values) {
        Record[] temp = new Record[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        Record[] values,
        Record[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    private static void merge(
        Record[] values,
        Record[] temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (values[i].getScore() >= values[j].getScore()) {
                temp[k++] = values[i++];
            } else {
                temp[k++] = values[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        while (j <= right) {
            temp[k++] = values[j++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}

class Record {
    private String name;
    private int score;

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}
