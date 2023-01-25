public class Partition {
    public int index;
    public int totalSize;
    public boolean fragmented;
    public boolean compacted;
    public Process ps;

    public Partition(int s , int i){
        totalSize = s;
        index = i;
        fragmented = false;
        compacted = false;
        ps = null;
    }
}
