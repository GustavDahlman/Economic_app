package se.mau.AE8415.p1;

import android.media.Image;

import se.mau.AE8415.p1.IncomeEntity;

public class IncomeItem {

    private String category;
    private  int amountz;
    private String amount;
    private String date;
    private Image img;
    private IncomeEntity entity;

    public IncomeItem(){
        category="misc";
        amount="+0.00";
        date="18/12/01";
    }

    public  IncomeItem(IncomeEntity entity){
        this.entity=entity;
        this.date=Integer.toString(entity.getDate());

        this.date=formatDate(entity.getDate());
        this.category=entity.getCategory();
        this.amount=Integer.toString(entity.getAmount());
        this.amountz=entity.getAmount();
    }
    public IncomeEntity getEntity(){
        return entity;
    }


    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public int getAmountz(){return amountz;}

    public Image getImg() {
        return img;
    }

    public String formatDate(int dateToFormat){
        String s = Integer.toString(dateToFormat);
        if(s.length()==6) {
            StringBuilder str = new StringBuilder(s);
            str.insert(2, "/");
            str.insert(5, "/");
            return str.toString();
        }
        else {
            return s;
        }
    }
}
