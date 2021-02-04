package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class OutcomeFragment extends Fragment {

    private Button submit;
    private EditText editTitle;
    private String title;
    private String category;
    private Spinner editCategory;
    private int amount;
    private EditText editAmount;
    private EditText editDate;
    private int date;
    private ImageView img;
    private AppController controller;


    public OutcomeFragment(){

    }

    public void setController(AppController controller){
        this.controller=controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.outcome_form, container, false);
        //initializeComponents(view);

        editTitle=view.findViewById(R.id.outcomeName);
        editAmount=view.findViewById(R.id.outcomeAmount);
        editDate=view.findViewById(R.id.outcomeDate);
        editCategory=view.findViewById(R.id.spinner2);
        submit = view.findViewById(R.id.submitOutcome);
        submit.setOnClickListener(clickListener);
        img = view.findViewById(R.id.form_img);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),R.array.categoriesO,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCategory.setAdapter(arrayAdapter);
        editCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("bills")){
                    img.setImageResource(R.mipmap.bills);
                }
                else if(adapterView.getItemAtPosition(i).toString().equals("food")){
                    img.setImageResource(R.mipmap.food);
                }
                else if(adapterView.getItemAtPosition(i).toString().equals("technology")){
                    img.setImageResource(R.mipmap.technology);
                }
                else if(adapterView.getItemAtPosition(i).toString().equals("activites")){
                    img.setImageResource(R.mipmap.fotboll);
                }
                else {
                    img.setImageResource(R.mipmap.misc);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void submit(){
        title = editTitle.getText().toString();
        category = editCategory.getSelectedItem().toString();

        //date = Integer.parseInt(editDate.getText().toString());

        date = convertDate(editDate.getText().toString());
        amount = Integer.parseInt(editAmount.getText().toString());

        //controller.submitIncome(name,category,date,amount);
        controller.submitOutCome(title,category,date,amount);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {

            //submit Entry
            submit();
            Toast.makeText(getActivity(), title+" added",
                    Toast.LENGTH_LONG).show();
            controller.viewOverView();


        }
    };

    private int convertDate(String date){
        String formatedString = date.replace("/","");
        return Integer.parseInt(formatedString);
    }
}
