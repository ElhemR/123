package elhem.app.rebhielhem.quizapp;


/**
 * Created by rebhielhem on 9/15/17.
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "quizApp";
    // tasks table name
    private static final String TABLE_QUEST = "questBank";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        Question q1 = new Question("Countries by area in descending order", "Russia", "Canada","China");
        this.addQuestion(q1);
        Question q2 = new Question("Countries by population in descending order", "China", "India", "USA");
        this.addQuestion(q2);
        Question q3 = new Question("National capitals by population in descending order", "Beijing", "New Delhi", "Tokyo");
        this.addQuestion(q3);
        Question q4 = new Question("Arab countries by population in descending order", "Egypt", "Algeria", "Sudan");
        this.addQuestion(q4);
      Question q6= new Question("Countries by Human Development Index in descending order","Norway","Australia/Switzerland","Germany");
        this.addQuestion(q6);
         Question q7= new Question("Countries by oil use in descending order", "USA","China", "Japan");
         this.addQuestion(q7);
        Question q8= new Question("Continents by population in ascending order","Europe","Africa","Asia") ;
        this.addQuestion(q8);
        Question q9= new Question("Oceans by area in descending order","Pacific Ocean","Atlantic Ocean","Indian Ocean");
        this.addQuestion(q9);
        Question q10= new Question("List of countries by number of Nobel Prizes in descending order","USA","United Kingdom","Germany");
        this.addQuestion(q10);
        Question q11= new Question("Countries by land borders","China","Russia","Brazil");
        this.addQuestion(q11);
        Question q12= new Question("Countries by number of recognized official languages in descendinga order","Bolivia"," Zimbabwe","South Africa") ;
        this.addQuestion(q12);
        Question q13= new Question("Countries by number of public holidays","Sri Lanka","India","Colombia/Philippines");
         this.addQuestion(q13);
        Question q14= new Question("Countries by most frequency of hosting the winter olympic games","USA","France","Canada");
        this.addQuestion(q14);
        Question q15= new Question("Continents by number of countries","Africa","Asia","Europe");
        this.addQuestion(q15);
        Question q16= new Question("Flags in order of increasing number of stars","Brazil","Bosnia and Herzegovina","Vietnam");
        this.addQuestion(q16);
        Question q17= new Question("Flags in order of increasing number of colors","South Africa","Italy","Albania");
        this.addQuestion(q17);
        Question q18= new Question("Rank these companies according their foundation dates from the most to the least recent ","Apple","Microsoft","IBM");
        this.addQuestion(q18);
        Question q19= new Question("Rank these US Presidents in a chronological order ","George washington","George H.W. Bush","Bill Clinton");
        this.addQuestion(q19);
        Question q20= new Question("Rank these WW2 events in a chronological order","Germany invades Poland","Hitler tours Paris","Battle of Stalingrad begins");
        this.addQuestion(q20);
        Question q21= new Question("Rank these events in a chronological order","IBM creates the first floppy disk","E-mail was invented by  Ray Tomlinson","Apple introduces the Macintosh with mouse and window interface");
        this.addQuestion(q21);
        Question q22= new Question("Rank these wars in a chronological order","Turkish War of Independence ","Spanish Civil War","World War II ");
        this.addQuestion(q22);
        Question q23= new Question("Rank these wars in a chronological order","Vietnam War ","Gulf War ","War in Afghanistan");
        this.addQuestion(q23);
        Question q24= new Question("Rank these roman numerals from biggest to smallest","M ","C","X");
        this.addQuestion(q24);
        Question q25= new Question("Rank these planets from closest to farthest from the earth","Mars","Jupiter","Saturn");
        this.addQuestion(q25);
        Question q26= new Question("Rank these human organs according average weight from highest to lowest ","Skin","Liver","Brain");
        this.addQuestion(q26);
        Question q27= new Question("Rank these values from highest to lowest","1 cubic metre","2 kg","1 litre ");
        this.addQuestion(q27);
        Question q28= new Question("Rank these polygons according number of edges from biggest to lowest to highest","octagon","hendecagon","icosagon");
        this.addQuestion(q28);
        Question q29= new Question("Rank these temperatures from highest to lowest"," 32°F","5°C","373K");
        this.addQuestion(q29);
        Question q30= new Question("Rank these alphabets according number of letters from highest to lowest","Arabic","Spanish","English");
        this.addQuestion(q30);
        Question q31= new Question("Rank these movies according their release dates in a chronological order ","The Godfather Part II ","The Shawshank Redemption","Titanic");
        this.addQuestion(q31);
        Question q32= new Question("Rank these mass values from highest to lowest ","1 Tonne ","400 kilograms"," 200 pounds ");
        this.addQuestion(q32);








        // END
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();



        // Select All Query
        //String selectQuery = "SELECT  * FROM " + TABLE_QUEST +"ORDER BY RANDOM"+"("+") limit 1";
        dbase = this.getReadableDatabase();

        long cnt= DatabaseUtils.queryNumEntries(dbase, TABLE_QUEST);  //return number of questions
        Cursor cursor = dbase.rawQuery("SELECT * FROM questBank ORDER BY RANDOM() LIMIT "+cnt, null); // pick randomly all questions
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Integer[] arr = new Integer[3];
                for (int i =2 ; i < 5; i++) {
                    arr[i-2] = i;

                }
                Collections.shuffle(Arrays.asList(arr));
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setAsked(false);

                quest.setQUESTION(cursor.getString(1));
                quest.setRANK1(cursor.getString(2));
                quest.setRANK2(cursor.getString(3));
                quest.setRANK3(cursor.getString(4));
                quest.setOPTA(cursor.getString(arr[1]));
                quest.setOPTB(cursor.getString(arr[2]));
                quest.setOPTC(cursor.getString(arr[0]));


                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

}

