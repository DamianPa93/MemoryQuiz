package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LexiconLogic {
    public static List<Lexicon> lexiconList = new ArrayList<Lexicon>();
    
    public void fillLexicon(){
        lexiconList.add(new Lexicon("red","czerwony",0));
        lexiconList.add(new Lexicon("blue","niebieski",1));
        lexiconList.add(new Lexicon("pink","różowy",2));
        lexiconList.add(new Lexicon("yellow","żółty",3));
        lexiconList.add(new Lexicon("purple","fioletowy",4));
        lexiconList.add(new Lexicon("orange","pomarańczowy",5));
        lexiconList.add(new Lexicon("white","biały",6));
        lexiconList.add(new Lexicon("black","czarny",7));
        lexiconList.add(new Lexicon("green","zielony",8));
        lexiconList.add(new Lexicon("brown","brązowy",9));
    }
    
    static int roll(int n){
       Random rand = new Random(); 
       return rand.nextInt(n);
    }
    
    public static Lexicon rollValue(List<Lexicon> list){
        int n = roll(list.size());
        return list.get(n);
    }
    
    public static List<Lexicon> rollQuiz(){
        List<Lexicon> quizList = new ArrayList<Lexicon>();
        int[] randoms = new int[3];
        randoms[0] = roll(lexiconList.size());
        do{
            randoms[1] = roll(lexiconList.size());
        } while (randoms[1] == randoms[0]);
        do{
            randoms[2] = roll(lexiconList.size());
        } while ((randoms[2] == randoms[0]) || (randoms[2] == randoms[1]));
        
        for(int i = 0; i<3; i++){
            quizList.add(lexiconList.get(randoms[i]));
        }
        return quizList;
    }
    
    public static List<Lexicon> getList(){
        return lexiconList;
    }
}
