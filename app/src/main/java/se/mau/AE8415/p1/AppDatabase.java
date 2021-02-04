package se.mau.AE8415.p1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


@Database(entities = {IncomeEntity.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract IncomeDAO incomeDAO();

    public static synchronized AppDatabase getInstance(Context context){
        Log.d("check","check1");

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,"database")
                    .fallbackToDestructiveMigration()
                    .build();
            return instance;
        }
        else {return instance;}
    }
}
