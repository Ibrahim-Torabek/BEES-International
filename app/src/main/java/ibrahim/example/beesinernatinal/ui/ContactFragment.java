package ibrahim.example.beesinernatinal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        MainActivity.fab.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        TextView callTextView = view.findViewById(R.id.callTextView);
        callTextView.setOnClickListener(new PhoneCall(callTextView.getText().toString()));
        TextView salesTextView = view.findViewById(R.id.salesTextView);
        salesTextView.setOnClickListener(new PhoneCall(salesTextView.getText().toString()));
        TextView accountingTextView = view.findViewById(R.id.accountingTextView);
        accountingTextView.setOnClickListener(new PhoneCall(accountingTextView.getText().toString()));

        return view;
    }

    class PhoneCall implements View.OnClickListener{
        private String callNumber;
        //private View view;

        public PhoneCall(String callNumber) {
            this.callNumber = callNumber;
            //this.view = view;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri call = Uri.parse("tel:" + callNumber);
            i.setData(call);

            if(i.resolveActivity(getActivity().getPackageManager()) != null){
                startActivity(i);
            } else {
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot make a call", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }
}