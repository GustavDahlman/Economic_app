package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


public class ResultViewer extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppController controller;
    private TextInputLayout startDate;
    private TextInputLayout finishDate;
    private TextView balance;
    ArrayList<IncomeItem> items = new ArrayList<>();
    private Button btn;
    private RadioGroup group;
    private RadioButton radioIncome;
    private RadioButton radioOutcome;
    private RadioButton radioBoth;

    public ResultViewer() {

    }

    public void setController(AppController controller) {
        this.controller = controller;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.recycler_fragment, container, false);
        /**
        items = new ArrayList<>();
        items.add(new IncomeItem("Misc", "+500.00", "18/12/01"));
        items.add(new IncomeItem("Salary", "+11500.00", "18/11/14"));
        items.add(new IncomeItem("Misc", "+500.00", "18/10/11"));
        items.add(new IncomeItem("Misc", "+100.00", "18/10/01"));
        items.add(new IncomeItem("Salary", "+11500.00", "18/9/14"));
        items.add(new IncomeItem("Misc", "+500.00", "18/12/01"));
        items.add(new IncomeItem("Salary", "+11500.00", "18/11/14"));
        items.add(new IncomeItem("Misc", "+500.00", "18/10/11"));

        recyclerView = view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new TransactionAdapter(items);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
         */
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        btn = view.findViewById(R.id.applyFilter);
        btn.setOnClickListener(ApplyFilter);
        balance = view.findViewById(R.id.balance);
        startDate=view.findViewById(R.id.startDate);
        finishDate=view.findViewById(R.id.finishDate);
        group=view.findViewById(R.id.radioGroup);
        controller.getIncomes();
        if(this.getActivity().getResources().getConfiguration().orientation==ORIENTATION_LANDSCAPE){
            //change orientation
        }




        return view;
    }

    public void initRV(){

    }
    public void setBalance(int amount){
        balance.setText(Integer.toString(amount)+" kr");
        if(amount<0){
            balance.setTextColor(getResources().getColor(R.color.colorRed));
        }
        if(amount>0){
            balance.setTextColor(getResources().getColor(R.color.colorGreen));
        }
    }

    public void convertEntities(List<IncomeEntity> entities) {
        items = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            items.add(new IncomeItem(entities.get(i)));

        }
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new TransactionAdapter(items,controller);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




    }
    private int formatDate(String string){
        //string.replace("/","");
        String newString = string;
        newString = newString.replace("/","");
        //newString.replace("/","");
        Log.d("skit",newString);
        return Integer.parseInt(newString);
    }

    private View.OnClickListener ApplyFilter = new View.OnClickListener() {
        public void onClick(View v) {

            //convertEntities(controller.getIncomes());
            controller.getIncomes();
            if(group.getCheckedRadioButtonId()==R.id.radioIncomes){
                if (TextUtils.isEmpty(startDate.getEditText().getText().toString())){
                    Log.d("radioIncome","radioIncome");
                    controller.fetchIncomes();
                }
                else{
                    //controller.fetchIncomes(Integer.parseInt(startDate.getEditText().getText().toString()),
                    //        Integer.parseInt(finishDate.getEditText().getText().toString()));
                    controller.fetchIncomes(formatDate(startDate.getEditText().getText().toString()),
                            formatDate(finishDate.getEditText().getText().toString()));
                }

            }
            else if(group.getCheckedRadioButtonId()==R.id.radioOutcomes){
                if (TextUtils.isEmpty(startDate.getEditText().getText().toString())){
                    Log.d("radioOutcome","radioOutcome");
                    controller.getOutcomes();
                }
                else{
                    //controller.getOutcomes(Integer.parseInt(startDate.getEditText().getText().toString()),
                    //        Integer.parseInt(finishDate.getEditText().getText().toString()));

                    controller.getOutcomes(formatDate(startDate.getEditText().getText().toString()),
                            formatDate(finishDate.getEditText().getText().toString()));
                }

            }
            else {
                //controller.getIncomes();
                if (TextUtils.isEmpty(startDate.getEditText().getText().toString())){
                    controller.getIncomes();
                }
                else{
                    //controller.getTransactionsByDate(Integer.parseInt(startDate.getEditText().getText().toString()),
                    //        Integer.parseInt(finishDate.getEditText().getText().toString()));

                    controller.getTransactionsByDate(formatDate(startDate.getEditText().getText().toString()),
                            formatDate(finishDate.getEditText().getText().toString()));
                }

            }



        }
    };

}
