package se.mau.AE8415.p1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */

    public class DataFragment extends Fragment {
        private String activeFragment;

        public DataFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public void setActiveFragment(String tag) {
            activeFragment = tag;
        }

        public String getActiveFragment() {
            return activeFragment;
        }
    }