package ibrahim.example.beesinernatinal.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.Currency;
import ibrahim.example.beesinernatinal.ExchangeRecyclerViewAdapter;
import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.R;

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
        TextView usdText = view.findViewById(R.id.usdText);
        double usd;

        usd = Double.parseDouble((String) usdText.getText());
        ArrayList<Currency> exchangeRates = initExchange();

        exchangeRecycleView.setAdapter(new ExchangeRecyclerViewAdapter(exchangeRates, usd));

        return view;
    }

    private ArrayList<Currency> initExchange(){
        String[] currency_name = getResources().getStringArray(R.array.currency_name);
        String[] currency_country = getResources().getStringArray(R.array.currency_country);
        String[] currency_rate = getResources().getStringArray(R.array.currency_rate);
        String[] currency_sign = getResources().getStringArray(R.array.currency_sign);

        ArrayList<Currency> currencies = new ArrayList<>();

        for (int i = 1; i < currency_country.length; i++){
            currencies.add(new Currency(
                    currency_name[i],
                    currency_country[i],
                    currency_sign[i],
                    Double.parseDouble(currency_rate[i])
                    )
            );
        }

        return currencies;
    }
}