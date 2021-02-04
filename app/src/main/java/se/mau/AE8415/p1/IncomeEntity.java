package se.mau.AE8415.p1;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class IncomeEntity {

    private String name;

    private int date;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String category;

    private int amount;

    public IncomeEntity(String name, String category, int amount, int date){
        this.amount=amount;
        this.name=name;
        this.category=category;
        this.date= date;
        this.id=0;
    }

    public String getName(){
        return this.name;
    }
    public int getDate(){
        return this.date;
    }
    public int getAmount(){
        return this.amount;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
