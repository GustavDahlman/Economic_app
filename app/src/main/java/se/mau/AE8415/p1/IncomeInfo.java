package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import se.mau.ag6511.newsp3.AppController;
//import se.mau.ag6511.newsp3.IncomeEntity;
import se.mau.AE8415.p1.R;

public class IncomeInfo extends Fragment {
    private TextView title;
    private TextView date;
    private TextView category;
    private TextView amount;
    private AppController controller;
    private IncomeEntity entity;
    private Button delete;

    public IncomeInfo(){

    }
    public void setController(AppController controller){
        this.controller=controller;
    }
    public void setEntity(IncomeEntity entity){
        this.entity=entity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.income_info, container, false);
        title=view.findViewById(R.id.info_incomeTitle);
        date=view.findViewById(R.id.info_incomeDate);
        category=view.findViewById(R.id.info_incomeCategory);
        amount=view.findViewById(R.id.info_incomeAmount);
        delete=view.findViewById(R.id.deleteIncome);
        title.setText(entity.getName());
        category.setText(entity.getCategory());
        date.setText(Integer.toString(entity.getDate()));
        amount.setText(Integer.toString(entity.getAmount())+" kr");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.deleteTransaction(entity);

            }
        });


        return view;
    }
}
