package com.example.fragment_1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicFragment extends Fragment {


    private static final int easy = 0;
    private static final int med = 1;
    private static final int hard = 2;

    public BasicFragment() {
        // Required empty public constructor
    }

    public static BasicFragment newInstance()
    {
        return new BasicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_basic
        ,container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group1);

        // Handler event from radioButton
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch (index)
                {
                    case easy :
                        textView.setText(R.string.easy_message);
                        break;
                    case med :
                        textView.setText(R.string.medium_message);
                        break;
                    case hard :
                        textView.setText(R.string.high_message);
                        break;
                    default:    break;
                }
            }
        });
        return rootView;
    }

}
