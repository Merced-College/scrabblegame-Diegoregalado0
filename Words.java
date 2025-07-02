//Diego Regalado
// CPSC-39
// 7-1-2025

//defines Words class and uses comparable

public class Words implements Comparable<Words> {
    private String text;

    // constructor

    public Words(String text) {
        this.text = text.toLowerCase();
    }
    // getter method
    public String getText(){
        return text;
    }
    @Override 
    public int compareTo(Words other){
        return this.text.compareTo(other.text);
    }
}