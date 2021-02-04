package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OutcomeInfo extends Fragment {
    private TextView title;
    private TextView date;
    private TextView category;
    private TextView amount;
    private AppController controller;
    private IncomeEntity entity;
    private ImageView img;
    private Button delete;

    public OutcomeInfo(){

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

        View view = inflater.inflate(R.layout.outcome_info, container, false);
        title=view.findViewById(R.id.info_outcomeTitle);
        date=view.findViewById(R.id.info_outcomeDate);
        category=view.findViewById(R.id.info_outcomeCategory);
        amount=view.findViewById(R.id.info_outcomeAmount);
        img = view.findViewById(R.id.info_img);
        delete = view.findViewById(R.id.deleteOutcome);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.deleteTransaction(entity);
            }
        });

        title.setText(entity.getName());
        category.setText(entity.getCategory());
        date.setText(Integer.toString(entity.getDate()));
        amount.setText(Integer.toString(entity.getAmount())+" kr");
        if(entity.getCategory().equals("activites")){
            img.setImageResource(R.mipmap.fotboll);
        }
        else if(entity.getCategory().equals("bills")){
            img.setImageResource(R.mipmap.bills);
        }
        else if(entity.getCategory().equals("technology")){
            img.setImageResource(R.mipmap.technology);
        }
        else if(entity.getCategory().equals("food")){
            img.setImageResource(R.mipmap.food);
        }
        else {
            img.setImageResource(R.mipmap.misc);
        }
        return view;
    }

}
