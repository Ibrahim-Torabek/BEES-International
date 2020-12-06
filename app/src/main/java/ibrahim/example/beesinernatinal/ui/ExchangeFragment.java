package ibrahim.example.beesinernatinal.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.Currency;
import ibrahim.example.beesinernatinal.ExchangeRecyclerViewAdapter;
import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.R;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExchangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExchangeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExchangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExchangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExchangeFragment newInstance(String param1, String param2) {
        ExchangeFragment fragment = new ExchangeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        MainActivity.fab.hide();

        RecyclerView exchangeRecycleView = view.findViewById(R.id.exchangeRecycleView);

        // Layout Manager
        //LinearLayout exchangeList = new LinearLayout(getContext());
        exchangeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set adapter
        TextView usdText = view.findViewById(R.id.usdEditText);
        double usd;

        usd = Double.parseDouble((String) usdText.getText().toString());
        exchangeRecycleView.setAdapter(new ExchangeRecyclerViewAdapter(MainActivity.exchangeRates, usd));

        // Change the exchage rate of currncies when usd Text changed
        usdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usdString = usdText.getText().toString();
                if(!usdString.isEmpty()) {
                    Double myusd = Double.parseDouble(usdString);
                    exchangeRecycleView.setAdapter(new ExchangeRecyclerViewAdapter(MainActivity.exchangeRates, myusd));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        usdText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v.getId() == R.id.usdEditText && !hasFocus) {

                    InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }
            }
        });

        return view;
    }

}