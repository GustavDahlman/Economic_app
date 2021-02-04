package se.mau.AE8415.p1;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class IncomeFragment extends Fragment {

    private Button submitBtn;
    private TextInputLayout editDate;
    private TextInputLayout editName;
    private TextInputLayout editAmount;
    private Spinner editCategory;
    private AppController controller;

    private String name;
    private String category;
    private int date;
    private int amount;


    public IncomeFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.income_form, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {
        submitBtn = view.findViewById(R.id.submitIncome);
        editName = view.findViewById(R.id.incomeName);
        editDate = view.findViewById(R.id.incomeDate);
        editAmount = view.findViewById(R.id.incomeAmount);
        editCategory = view.findViewById(R.id.spinner);
        submitBtn.setOnClickListener(submitListener);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),R.array.categories,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCategory.setAdapter(arrayAdapter);


    }
    public void setController(AppController controller){
        this.controller=controller;
    }

    public void submit(){
        name = editName.getEditText().getText().toString();
        category = editCategory.getSelectedItem().toString();

        //date = Integer.parseInt(editDate.getEditText().getText().toString());

        date = convertDate(editDate.getEditText().getText().toString());
        amount = Integer.parseInt(editAmount.getEditText().getText().toString());

        controller.submitIncome(name,category,date,amount);
        //controller.submitOutCome(name,category,date,amount);

    }

    private View.OnClickListener submitListener = new View.OnClickListener() {
        public void onClick(View v) {

            submit();

            Toast.makeText(getActivity(), name+" added",
                    Toast.LENGTH_LONG).show();
            controller.viewOverView();

        }
    };

    private int convertDate(String date){
        String formatedString = date.replace("/","");
        return Integer.parseInt(formatedString);
    }
}
