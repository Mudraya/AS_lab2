package com.example.appfragment;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Button button = (Button) view.findViewById(R.id.update_button);
        // задаем обработчик кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail(v);
            }
        });

        Button buttonC = (Button) view.findViewById(R.id.cancel);
        // задаем обработчик кнопки
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void updateDetail(View v) {

        String selItem;
        RadioGroup radGrpF = (RadioGroup) getView().findViewById(R.id.radioFaculties);
        RadioGroup radGrpY = (RadioGroup) getView().findViewById(R.id.radioYear);
        int selectedIdF = radGrpF.getCheckedRadioButtonId();
        int selectedIdY = radGrpY.getCheckedRadioButtonId();
        if (selectedIdF != -1 && selectedIdY != -1 ) {
            RadioButton radioBtnF = (RadioButton) getView().findViewById(selectedIdF);
            RadioButton radioBtnY = (RadioButton) getView().findViewById(selectedIdY);
            selItem = "You are " + radioBtnY.getText().toString() + "-year student, who study in " + radioBtnF.getText().toString();
        }
        else { selItem = "Select something from both lists"; }
        mListener.onFragmentInteraction(selItem);
    }

    public void cancel(View v) {
        RadioGroup radGrpF = (RadioGroup)getView().findViewById(R.id.radioFaculties);
        RadioGroup radGrpY = (RadioGroup)getView().findViewById(R.id.radioYear);
        radGrpF.clearCheck();
        radGrpY.clearCheck();
    }

}
