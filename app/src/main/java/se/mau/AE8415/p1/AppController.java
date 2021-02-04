package se.mau.AE8415.p1;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;


public class AppController {

    private MainActivity mainActivity;
    private IncomeFragment incomeFragment;
    private OverviewFragment overviewFragment;
    private Fragment activeFragment;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String user;
    private UserLogin userLogin;
    private OutcomeFragment outcomeFragment;
    private AppDatabase db;
    private ResultViewer resultViewer;
    private IncomeDAO dao;
    private IncomeInfo incomeInfo;
    private int totalBalance;
    private OutcomeInfo outcomeInfo;


    public AppController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        incomeFragment = new IncomeFragment();
        overviewFragment = new OverviewFragment();
        userLogin = new UserLogin();
        userLogin.setController(this);
        incomeFragment.setController(this);
        overviewFragment.setController(this);
        outcomeFragment = new OutcomeFragment();
        outcomeFragment.setController(this);
        resultViewer = new ResultViewer();
        resultViewer.setController(this);
        incomeInfo = new IncomeInfo();
        incomeInfo.setController(this);
        outcomeInfo = new OutcomeInfo();
        outcomeInfo.setController(this);
        totalBalance=0;

        mainActivity.setFragment(overviewFragment);
        //checkPreferences();
        //viewOverView();
        activeFragment = overviewFragment;

        preferences = PreferenceManager.getDefaultSharedPreferences(this.mainActivity);
        editor = preferences.edit();
        Log.d("lol","lol");
        db = AppDatabase.getInstance(mainActivity);
        dao = db.incomeDAO();
        new BalanceFetcher(overviewFragment,dao).execute();

        //     db = Room.databaseBuilder(mainActivity,
        //            AppDatabase.class, "database-name").build();
        //checkPreferences();
    }
    public void getTotalBalance(){
        new BalanceFetcher(overviewFragment,dao).execute();
    }

    public void setFragment(Fragment fragment) {
        mainActivity.setFragment(fragment);
    }

    public void viewIncomeForm() {
        mainActivity.setFragment(incomeFragment);
    }

    public void viewUserLogin() {
        mainActivity.setFragment(userLogin);
    }

    public void viewOutcomeForm() {
        mainActivity.setFragment(outcomeFragment);
    }

    public void viewResultViewer() {
        mainActivity.setFragment(resultViewer);
    }

    public void viewOutcomeInfo(IncomeEntity entity){
        outcomeInfo.setEntity(entity);
        mainActivity.setFragment(outcomeInfo);
    }

    public void viewIncomeInfo(IncomeEntity entity){
        incomeInfo.setEntity(entity);
        mainActivity.setFragment(incomeInfo);
    }

    public void viewOverView() {
        mainActivity.setFragment(overviewFragment);
        checkPreferences();
    }

    public void checkPreferences() {
        user = preferences.getString("username", "stranger");
        //Toast.makeText(this.mainActivity,user,Toast.LENGTH_LONG);
        overviewFragment.setUserTv(user);
        if (user.equals("stranger")) {
            //ask for name
            //setFragment(incomeFragment);

        }
    }
    public void getOutcomes() {
        new OutcomeFetcher(dao,resultViewer).execute();
    }
    public void getOutcomes(int startDate, int finishDate) {
        new OutcomeFetcher(dao,resultViewer,startDate,finishDate).execute();
    }

    public String getUser() {
        user = preferences.getString("username", "stranger");
        return user;
    }

    public void setUser(String User) {
        editor.putString("username", User);
        editor.commit();
    }
    public void setLastName(String lastname){
        editor.putString("lastname", lastname);
        editor.commit();
    }

    public void submitIncome(String title, String category, int date, int amount) {
        new SubmitIncomeTask(dao).execute(new IncomeEntity(title,category,amount,date));

    }

    public void submitOutCome(String title, String category, int date, int amount) {
        new SubmitIncomeTask(dao).execute(new IncomeEntity(title,category,amount*-1,date));
    }
    public void fetchIncomes(){
        new IncomeFetcher(dao,resultViewer).execute();
    }
    public void fetchIncomes(int startDate, int finishDate){
        new IncomeFetcher(dao,resultViewer,startDate,finishDate).execute();
    }

    private static class SubmitIncomeTask extends AsyncTask<IncomeEntity, Void, Void> {

        IncomeDAO dao;
        public SubmitIncomeTask(IncomeDAO dao){this.dao=dao;}
        @Override
        protected Void doInBackground(IncomeEntity... IncomeEntities) {

            dao.insertIncome(IncomeEntities[0]);
            return null;
        }

        protected void onPostExecute() {

        }
        // Here is doInBackground etc. as you did before
    }

    public void getIncomes() {
        new TransactionFetcher(dao,resultViewer).execute();

    }
    public void getTransactionsByDate(int startDate, int finishDate){
        new TransactionFetcher(dao,resultViewer,startDate,finishDate).execute();
    }


    private static class TransactionFetcher extends AsyncTask<Void, Void, List<IncomeEntity>> {
        private IncomeDAO dao;
        private List<IncomeEntity> list;
        private ResultViewer resultViewer;
        private OverviewFragment overviewFragment;
        private boolean dateSearchOn;
        private int startDate;
        private int finishDate;
        private int balance;
        private boolean viewInfo;
        private String tag;

        public TransactionFetcher(IncomeDAO dao, ResultViewer resultViewer){
            this.dao=dao;
            this.resultViewer=resultViewer;
            dateSearchOn=false;
            viewInfo=false;
            tag="both";
        }
        public TransactionFetcher(IncomeDAO dao, ResultViewer resultViewer, int startDate, int finishDate){
            this.dao=dao;
            this.resultViewer=resultViewer;
            this.startDate=startDate;
            this.finishDate=finishDate;
            viewInfo=false;
            dateSearchOn=true;
            tag="both";

        }

        @Override
        protected List<IncomeEntity> doInBackground(Void... voids) {
            Log.d("step0","step0");
            if(viewInfo){
                balance = dao.getSum();
            }

            if(dateSearchOn){
                //list = dao.getIncomesByDate(startDate, finishDate);
                list = dao.getTransactionsByDate(startDate, finishDate);
                balance = dao.getSumBtDate(startDate,finishDate);
                return list;
            }
            else {
                list = dao.getAllIncomes();
                balance = dao.getSum();
                Log.d("step1.5", "step1.5");
                return list;
            }
        }

        @Override
        protected void onPostExecute(List<IncomeEntity> entities) {
            //setFragment(overviewFragment);
            Log.d("step1","step1");
            resultViewer.convertEntities(list);
            resultViewer.setBalance(balance);


            Log.d("step2","step2");
        }



        // Here is doInBackground etc. as you did before
    }

    private static class BalanceFetcher extends AsyncTask<Void,Void,Void>{
        private OverviewFragment overviewFragment;
        private IncomeDAO dao;
        private int balance;
        public BalanceFetcher(OverviewFragment overview,IncomeDAO dao){
            overviewFragment=overview;
            this.dao=dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            balance=dao.getSum();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            overviewFragment.setBalanceTv(balance);

        }
    }

    private static class OutcomeFetcher extends AsyncTask<Void, Void, List<IncomeEntity>> {
        private IncomeDAO dao;
        private List<IncomeEntity> list;
        private ResultViewer resultViewer;
        private OverviewFragment overviewFragment;
        private boolean dateSearchOn;
        private int startDate;
        private int finishDate;
        private int balance;
        private boolean viewInfo;
        private String tag;

        public OutcomeFetcher(IncomeDAO dao,ResultViewer resultViewer){
            this.dao=dao;
            this.resultViewer=resultViewer;
            dateSearchOn=false;
            viewInfo=false;
            tag="both";
        }
        public OutcomeFetcher(IncomeDAO dao,ResultViewer resultViewer, int startDate, int finishDate){
            this.dao=dao;
            this.resultViewer=resultViewer;
            this.startDate=startDate;
            this.finishDate=finishDate;
            viewInfo=false;
            dateSearchOn=true;
            tag="both";

        }

        @Override
        protected List<IncomeEntity> doInBackground(Void... voids) {
            Log.d("step0","step0");
            if(viewInfo){
                balance = dao.getSum();
            }

            if(dateSearchOn){
                //list = dao.getIncomesByDate(startDate, finishDate);
                list = dao.getOutcomesByDate(startDate, finishDate);
                balance = dao.getOutcomeSumBtDate(startDate,finishDate);
                return list;
            }
            else {
                list = dao.getOutcomes();
                balance = dao.getOutcomeSum();
                Log.d("step1.5", "step1.5");
                return list;
            }
        }

        @Override
        protected void onPostExecute(List<IncomeEntity> entities) {
            //setFragment(overviewFragment);
            Log.d("step1","step1");
            resultViewer.convertEntities(list);
            resultViewer.setBalance(balance);


            Log.d("YO","YO");
        }



        // Here is doInBackground etc. as you did before
    }
    private static class IncomeFetcher extends AsyncTask<Void, Void, List<IncomeEntity>> {
        private IncomeDAO dao;
        private List<IncomeEntity> list;
        private ResultViewer resultViewer;
        private OverviewFragment overviewFragment;
        private boolean dateSearchOn;
        private int startDate;
        private int finishDate;
        private int balance;
        private boolean viewInfo;
        private String tag;

        public IncomeFetcher(IncomeDAO dao,ResultViewer resultViewer){
            this.dao=dao;
            this.resultViewer=resultViewer;
            dateSearchOn=false;
            viewInfo=false;
            tag="both";
        }
        public IncomeFetcher(IncomeDAO dao,ResultViewer resultViewer, int startDate, int finishDate){
            this.dao=dao;
            this.resultViewer=resultViewer;
            this.startDate=startDate;
            this.finishDate=finishDate;
            viewInfo=false;
            dateSearchOn=true;
            tag="both";

        }

        @Override
        protected List<IncomeEntity> doInBackground(Void... voids) {
            Log.d("step0","step0");
            if(viewInfo){
                balance = dao.getSum();
            }

            if(dateSearchOn){
                //list = dao.getIncomesByDate(startDate, finishDate);
                Log.d("bajs","bajs");
                list = dao.getIncomesByDate(startDate, finishDate);
                balance = dao.getIncomeSumBtDate(startDate,finishDate);
                return list;
            }
            else {
                list = dao.getIncomes();
                //balance = dao.getSum();
                dao.getIncomeSum();
                Log.d("kiss", "kiss");
                return list;
            }
        }

        @Override
        protected void onPostExecute(List<IncomeEntity> entities) {
            //setFragment(overviewFragment);
            Log.d("step1","step1");
            resultViewer.convertEntities(list);
            resultViewer.setBalance(balance);


            Log.d("YO","YO");
        }
        // Here is doInBackground etc. as you did before
    }

    private static class deleter extends AsyncTask<Void,Void,Void>{
        private OverviewFragment overviewFragment;
        private ResultViewer resultViewer;
        private IncomeDAO dao;
        private IncomeEntity entity;
        private int balance;
        private AppController c;
        public deleter(AppController controller,IncomeDAO dao, IncomeEntity entity){
            this.resultViewer=resultViewer;
            c=controller;
            this.dao=dao;
            this.entity=entity;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //balance=dao.getSum();
            dao.deleteIncome(entity);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //overviewFragment.setBalanceTv(balance);
            c.viewResultViewer();


        }
    }
    public void deleteTransaction(IncomeEntity entity){
        new deleter(this,dao,entity).execute();
    }
}




