package se.mau.AE8415.p1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UserLogin extends Fragment {

    private EditText newUser;
    private Button applyUser;
    private AppController controller;
    private EditText newLastName;

    public UserLogin(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.user_login, container, false);
        //initializeComponents(view);
        newUser=view.findViewById(R.id.newName);
        applyUser=view.findViewById(R.id.applyName);
        newUser.setText(controller.getUser());
        applyUser.setOnClickListener(clickListener);
        newLastName=view.findViewById(R.id.newLastName);
        return view;
    }

    public void setController(AppController controller){
        this.controller=controller;

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {

            controller.setUser(newUser.getText().toString());
            controller.setLastName(newLastName.getText().toString());


        }
    };


}
