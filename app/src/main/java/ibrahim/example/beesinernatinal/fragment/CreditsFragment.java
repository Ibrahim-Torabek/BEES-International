package ibrahim.example.beesinernatinal.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreditsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreditsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public CreditsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreditsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreditsFragment newInstance(String param1, String param2) {
        CreditsFragment fragment = new CreditsFragment();
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
        view = inflater.inflate(R.layout.fragment_credits, container, false);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));

        TextView creditIconsText = view.findViewById(R.id.creditIconsText);
        creditIconsText.setTextSize(textSize);
        creditIconsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.flaticon.com/");
                Intent i = new Intent(Intent.ACTION_VIEW,webpage);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot open the browser", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        TextView creditFlagsText = view.findViewById(R.id.creditFlagsText);
        creditFlagsText.setTextSize(textSize);
        creditFlagsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.flaticon.com/authors/freepik");
                Intent i = new Intent(Intent.ACTION_VIEW,webpage);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot open the browser", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        TextView creditImagesText = view.findViewById(R.id.creditImagesText);
        creditImagesText.setTextSize(textSize);
        creditImagesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://pixabay.com/");
                Intent i = new Intent(Intent.ACTION_VIEW,webpage);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot open the browser", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }
}