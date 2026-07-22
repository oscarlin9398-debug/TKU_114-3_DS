public class PlaylistNode {

    String id;
    String title;
    PlaylistNode next;

    public PlaylistNode(String id, String title) {
        this.id = id;
        this.title = title;
        this.next = null;
    }
}
