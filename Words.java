//defines Words class and uses comparable

public class Words implements comparable<Words> {
    private String text;

    \\constructor

    public Words(string text) {
        this.text = text.toLowerCase();
    }
    // getter method
    public String getText(){
        return text;
    }
    @override 
    public int compareTo(Words other){
        return this.text.compareTo(other.text);
    }
}