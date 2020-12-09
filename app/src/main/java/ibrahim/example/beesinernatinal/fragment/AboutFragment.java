package ibrahim.example.beesinernatinal.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ibrahim.example.beesinernatinal.adapter.ExchangeRecyclerViewAdapter;
import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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
        //MainActivity.fab.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));

        // Countries list area:
        RecyclerView countryRecycleView = view.findViewById(R.id.countryRecycleView);
        countryRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        countryRecycleView.setAdapter(new ExchangeRecyclerViewAdapter(MainActivity.exchangeRates, -1,textSize));

        countryRecycleView.setVisibility(RecyclerView.VISIBLE);

        TextView aboutBodyTextView = view.findViewById(R.id.aboutBodyTextView);
        aboutBodyTextView.setTextSize(textSize);

        TextView aboutLocationText = view.findViewById(R.id.aboutLocationText);
        aboutLocationText.setTextSize(textSize);

        TextView aboutCreationText = view.findViewById(R.id.aboutCreationText);
        aboutCreationText.setTextSize(textSize);

        TextView aboutMemberText = view.findViewById(R.id.aboutMemberText);
        aboutMemberText.setTextSize(textSize);

        TextView aboutEmployeeText = view.findViewById(R.id.aboutEmployeeText);
        aboutEmployeeText.setTextSize(textSize);

    }
}