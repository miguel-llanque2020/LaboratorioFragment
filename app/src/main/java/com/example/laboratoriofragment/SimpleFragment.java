package com.example.laboratoriofragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    OnFragmentInteractionListener mListener;
    private static final String CHOICE = "choice";
    Button btnvotar;
    TextView t1,t2,tvresul;
    int voto1=0,voto2=0;

    public SimpleFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_simple, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        btnvotar=(Button)rootView.findViewById(R.id.btnvotar);
        t1=(TextView)rootView.findViewById(R.id.tvoto1);
        t2=(TextView)rootView.findViewById(R.id.tvoto2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES: // User chose "Yes."
                        mRadioButtonChoice = YES;


                        break;
                    case NO: // User chose "No."
                        mRadioButtonChoice = NO;


                        break;
                    default: // No choice made.
                        mRadioButtonChoice = NONE;


                        break;
                }
            }
        });
        if (getArguments().containsKey(CHOICE)) {
            // A choice was made, so get the choice.
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            // Check the radio button choice.
            if (mRadioButtonChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }
        btnvotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int estado=0;
                int checnasal = radioGroup.getCheckedRadioButtonId();
                if (checnasal==-1 ){
                    estado=0;
                }
                else {
                    if (  checnasal == R.id.radio_button_yes  ){

                        voto1=voto1+1;
                        t1.setText(""+voto1+" votos");
                    }
                    if (checnasal == R.id.radio_button_no){

                        voto2=voto2+1;
                        t2.setText(""+voto2 +" votos");
                    }
                }

                if (voto1>voto2){
                    String re="pelicula 1 Tiene mas Votos ";

                    mListener.onRadioButtonChoice(re);
                }
                else if (voto1==voto2){

                    String r="Empates";
                    mListener.onRadioButtonChoice(r);
                }
                else    {

                    String a ="pelicula 2 Tiene mas Votos";
                    mListener.onRadioButtonChoice(a);
                }
            }
        });
        return rootView;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }

    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }
    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(String choice);
    }

}