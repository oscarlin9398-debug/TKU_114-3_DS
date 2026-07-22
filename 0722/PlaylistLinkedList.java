public class PlaylistLinkedList {

    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean containsId(String id) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addLast(String id, String title) {
        if (containsId(id)) {
            System.out.println("【錯誤】新增失敗！歌曲代碼 " + id + " 已存在於播放清單中。");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(id, title);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("【成功】已新增歌曲：[" + id + "] " + title);
        return true;
    }

    public PlaylistNode searchById(String id) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("【警告】播放清單為空，無法執行刪除操作。");
            return false;
        }

        if (head.id.equals(id)) {
            System.out.println("【刪除】成功移除第一首歌曲：[" + head.id + "] " + head.title);
            head = head.next;
            size--;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;

        while (current != null) {
            if (current.id.equals(id)) {
                previous.next = current.next;
                System.out.println("【刪除】成功移除歌曲：[" + current.id + "] " + current.title);
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("【警告】找不到歌曲代碼 [" + id + "]，無法刪除。");
        return false;
    }

    public void printPlaylist() {
        System.out.println("\n========== 🎵 目前播放清單 (共 " + size + " 首) ==========");
        if (head == null) {
            System.out.println("（播放清單目前是空的）");
            System.out.println("==================================================");
            return;
        }

        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". [" + current.id + "] " + current.title);
            current = current.next;
            index++;
        }
        System.out.println("==================================================");
    }
}
