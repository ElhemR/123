package elhem.app.rebhielhem.quizapp;
import android.app.Activity;

public class Question extends Activity {

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String RANK1;
    private String RANK2;
    private String RANK3;
    private Boolean asked;

    public Question() {
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        RANK1="";
        RANK2="";
        RANK3="";
        asked=false;
    }

    public Question(String qUESTION, String oPTA, String oPTB, String oPTC) {
        QUESTION = qUESTION;
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        asked=false;


    }

    public int getID() {
        return ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }
    public String getOPTB() {
        return OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }



    public void setID(int id) {
        ID = id;
    }

    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }

    public void setOPTA(String oPTA) {
        OPTA = oPTA;
    }

    public void setOPTB(String oPTB) {
        OPTB = oPTB;
    }

    public void setOPTC(String oPTC) {
        OPTC = oPTC;
    }


    public String getRANK1() {
        return RANK1;
    }

    public void setRANK1(String RANK1) {
        this.RANK1 = RANK1;
    }

    public String getRANK2() {
        return RANK2;
    }

    public void setRANK2(String RANK2) {
        this.RANK2 = RANK2;
    }

    public String getRANK3() {
        return RANK3;
    }

    public void setRANK3(String RANK3) {
        this.RANK3 = RANK3;
    }

    public Boolean getAsked() {
        return asked;
    }

    public void setAsked(Boolean asked) {
        this.asked = asked;
    }
}

