public class PlaylistSystem {

    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("--- 1. 邊界測試：空串列狀態列印與刪除 ---");
        playlist.printPlaylist();
        playlist.removeById("S001");

        System.out.println("\n--- 2. 測試尾端新增歌曲 (addLast) ---");
        playlist.addLast("S001", "晴天");
        playlist.addLast("S002", "七里香");
        playlist.addLast("S003", "稻香");
        playlist.addLast("S004", "告白氣球");
        playlist.printPlaylist();

        System.out.println("\n--- 3. 測試代碼防重機制 (Unique ID Check) ---");
        playlist.addLast("S002", "重複的七里香");

        System.out.println("\n--- 4. 測試依代碼搜尋歌曲 (Search) ---");
        PlaylistNode found = playlist.searchById("S003");
        if (found != null) {
            System.out.println("【搜尋結果】找到歌曲：[" + found.id + "] " + found.title);
        } else {
            System.out.println("【搜尋結果】未找到指定歌曲。");
        }

        PlaylistNode notFound = playlist.searchById("S999");
        if (notFound == null) {
            System.out.println("【搜尋結果】代碼 S999 不存在（正確處理）。");
        }

        System.out.println("\n--- 5. 測試刪除第一首歌曲 (Head) ---");
        playlist.removeById("S001");
        playlist.printPlaylist();

        System.out.println("\n--- 6. 測試刪除最後一首歌曲 (Tail) ---");
        playlist.removeById("S004");
        playlist.printPlaylist();

        System.out.println("\n--- 7. 測試刪除不存在的歌曲 ---");
        playlist.removeById("S888");

        System.out.println("\n--- 8. 邊界測試：續刪至清單完全清空 ---");
        playlist.removeById("S002");
        playlist.removeById("S003");
        playlist.printPlaylist();
    }
}
