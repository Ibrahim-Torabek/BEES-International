package ibrahim.example.beesinernatinal.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import ibrahim.example.beesinernatinal.R;

/**
 * <h1>App for BeesInernatinal</h1>
 * <h2>Android Project of MAD305 Course</h2>
 *
 * @author Wusiman Yibuulayin
 * @version 1.0
 * @since 2020-11-20
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link DealerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DealerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DealerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DealerFragment newInstance(String param1, String param2) {
        DealerFragment fragment = new DealerFragment();
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
        View view = inflater.inflate(R.layout.fragment_dealer, container, false);

        // Set up country spinner adapter
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.currency_country,
                android.R.layout.simple_spinner_dropdown_item
        );
        countryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Spinner countrySpinner = view.findViewById(R.id.countrySpinner);
        countrySpinner.setAdapter(countryAdapter);

        // Set up category spinner adapter
        String[] temp = {"Hello","World"};
        ArrayList<String> a = new ArrayList(Arrays.asList(temp));
        a.add(0,"All");


        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.categories_name,
                android.R.layout.simple_spinner_dropdown_item
        );
        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        String allCategories = "All";
        CharSequence all = allCategories;
        //categoryAdapter.remove("Hand Tools");

        Spinner categorySpinner = view.findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(categoryAdapter);


        Button emailButton = view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText subject = view.findViewById(R.id.subjectEditText);
                EditText emailBody = view.findViewById(R.id.askDealerEditText);

                String country = countrySpinner.getSelectedItem().toString();
                // country name may contains spaces, remove all spaces to use it in emial address.
                String countryEmail = country.replaceAll("\\s+", "");

                String category = categorySpinner.getSelectedItem().toString();


                Intent i = new Intent(Intent.ACTION_SENDTO);
                Uri email = Uri.parse("mailto:");
                String[] emailAddress = { countryEmail + "@beesinternational.ca"};
                String[] emailAddress_BCC = {"admin@beesinternational.ca"};

                i.setData(email);
                i.putExtra(Intent.EXTRA_EMAIL,emailAddress);
                i.putExtra(Intent.EXTRA_SUBJECT,category + ": " + subject.getText());
                i.putExtra(Intent.EXTRA_TEXT, "Dear dealer of " + country + ":\n\n" + emailBody.getText());
                i.putExtra(Intent.EXTRA_BCC, emailAddress_BCC);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot send an email to report", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });
        return view;
    }
}