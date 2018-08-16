package model;

public class Lexicon {
    public Lexicon(){
        
    }
    
    public Lexicon(String word, String translation){
        this.word = word;
        this.translation = translation;
    }
    
    public Lexicon(String word, String translation, int id){
        this.word = word;
        this.translation = translation;
        this.id = id;
    }
    
    public String word;
    public String translation;
    public int useAmount;
    public int id;
}
