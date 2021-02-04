package se.mau.AE8415.p1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton login_button;
    TextView textView;
    //private Controller controller;
    private FragmentManager fm;
    private AppController appController;
    //private SharedPreferences preferences;
    //private SharedPreferences.Editor editor;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().logOut();
        fm = getSupportFragmentManager();
        //controller = new Controller(this);
        appController = new AppController(this);
        setContentView(R.layout.activity_main);
        //preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //editor = preferences.edit();
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }



    @Override
    public void onBackPressed() {
       // if (controller.backPressed()) {
       //     super.onBackPressed();
       // }
        //super.onBackPressed();
        appController.viewOverView();
    }

    public Fragment getFragment(String tag) {
        return fm.findFragmentByTag(tag);
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(fragment, tag);
        transaction.commit();
    }
}
