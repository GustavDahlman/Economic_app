package se.mau.AE8415.p1;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;



@Dao
public interface IncomeDAO {

    @Insert
    public long insertIncome(IncomeEntity income);



    @Update
    public int updateIncome(IncomeEntity income);

    @Delete
    public int deleteIncome(IncomeEntity income);

    @Query("SELECT* from incomeEntity ORDER BY date DESC")
    public List<IncomeEntity> getAllIncomes();

    @Query("SELECT* FROM incomeEntity WHERE date BETWEEN :mindate AND :maxdate ORDER BY date DESC")
    public List<IncomeEntity> getTransactionsByDate(int mindate, int maxdate);

    @Query("SELECT SUM(amount) FROM  incomeEntity")
    public int getSum();

    @Query("SELECT* FROM incomeEntity WHERE amount>0")
    public List<IncomeEntity> getIncomes ();

    @Query("SELECT* FROM incomeentity WHERE amount<0")
    public List<IncomeEntity> getOutcomes();

    @Query("SELECT SUM(amount) FROM  incomeEntity WHERE date BETWEEN :mindate AND :maxdate")
    public int getSumBtDate(int mindate, int maxdate);

    @Query("SELECT* FROM incomeentity WHERE amount<0 AND date BETWEEN :mindate AND :maxdate ORDER BY DATE DESC")
    public List<IncomeEntity> getOutcomesByDate(int mindate, int maxdate);

    @Query("SELECT* FROM incomeentity WHERE amount>0 AND date BETWEEN :mindate AND :maxdate ORDER BY DATE DESC")
    public List<IncomeEntity> getIncomesByDate(int mindate, int maxdate);

    @Query("SELECT SUM(amount) FROM  incomeEntity WHERE amount>0 AND date BETWEEN :mindate AND :maxdate")
    public int getIncomeSumBtDate(int mindate, int maxdate);

    @Query("SELECT SUM(amount) FROM  incomeEntity WHERE amount>0 AND date BETWEEN :mindate AND :maxdate")
    public int getOutcomeSumBtDate(int mindate, int maxdate);

    @Query("SELECT SUM(amount) FROM  incomeEntity WHERE amount>0")
    public int getIncomeSum();

    @Query("SELECT SUM(amount) FROM  incomeEntity WHERE amount<0")
    public int getOutcomeSum();



}
