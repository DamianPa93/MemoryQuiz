package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LexiconLogic {
    public static List<Lexicon> lexiconList = new ArrayList<Lexicon>();
    
    /*  *Emergency /test/ question filler
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
    */
    
    //                                DB Test
    //--------------------------------------------------------------------------
    
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/MemoryQuizStarterDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public static void fillLexicon(){
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String sql = ("SELECT * FROM Vocabulary;");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String wordA = rs.getString("wordA");
                String wordB = rs.getString("wordB");
                lexiconList.add(new Lexicon(wordA,wordB,id));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    //--------------------------------------------------------------------------
    
    static int roll(int n){
       Random rand = new Random(); 
       return rand.nextInt(n);
    }
    
    public static Lexicon rollValue(List<Lexicon> list){
        int n = roll(list.size());
        return list.get(n);
    }
    
    public static List<Lexicon> rollQuiz(int n){
        List<Lexicon>temp = new ArrayList<Lexicon>(lexiconList);
        List<Lexicon> quizList = new ArrayList<Lexicon>();
        int rnd;
        for(int i = 0; i<n; i++){
            rnd = roll(temp.size()); //roll number from 0 to temp.size()
            quizList.add(temp.get(rnd)); //add to final quizList random object from temp List
            temp.remove(rnd); //remove object so it wont repeat next time
        }
        return quizList; //return rolled quiz list
    }
    
    public static List<Lexicon> getList(){
        return lexiconList;
    }
}