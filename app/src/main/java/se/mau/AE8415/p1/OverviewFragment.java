package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import se.mau.AE8415.newsp3.R;


public class OverviewFragment extends Fragment {

    private Button incomeBtn;
    private  Button outcomeBtn;
    private Button incomeList;
    private Button outcomeList;
    private Button userBtn;
    private TextView user;
    private TextView balance;
    private AppController controller;

    public OverviewFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        //initializeComponents(view);
        user = view.findViewById(R.id.userTv);
        balance = view.findViewById(R.id.balanceTv);
        incomeBtn = view.findViewById(R.id.income_button);
        outcomeBtn = view.findViewById(R.id.outcome_button);
        incomeList = view.findViewById(R.id.income_list);
        //outcomeList = view.findViewById(R.id.outcome_list);
        userBtn = view.findViewById(R.id.change_user);
        incomeList.setOnClickListener(clickListener);
        //outcomeList.setOnClickListener(clickListener);
        userBtn.setOnClickListener(clickListener);
        incomeBtn.setOnClickListener(clickListener);
        outcomeBtn.setOnClickListener(clickListener);
        setUserTv(controller.getUser());
        //setBalanceTv(controller.getTotalBalance());
        controller.getTotalBalance();

        return view;
    }

    public void setController(AppController controller){
        this.controller=controller;
    }
    public void setUserTv(String userString){
        user.setText(userString);
    }
    public void setBalanceTv(int amount){
        balance.setText(amount+" kr");
        if(amount>0){
            balance.setTextColor(getResources().getColor(R.color.colorGreen));
        }
        else if(amount<0){
            balance.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.income_button:
                    controller.viewIncomeForm();
                    break;
                case R.id.outcome_button:
                    //setBalanceTv(100);
                    //controller.setUser("Gustav");
                    //controller.checkPreferences();

                    controller.viewOutcomeForm();
                    break;
                case  R.id.change_user:
                    controller.viewUserLogin();
                    break;
                case R.id.income_list:
                    controller.viewResultViewer();
            }





        }
    };


}
