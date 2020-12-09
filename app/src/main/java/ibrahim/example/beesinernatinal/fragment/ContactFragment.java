package ibrahim.example.beesinernatinal.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.R;

/**
 * <h1>App for BeesInernatinal</h1>
 * <h2>Android Project of MAD305 Course</h2>
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author Wusiman Yibuulayin
 * @version 1.0
 * @since 2020-11-20

 */
public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int PREMISSION_CALL_PHONE = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * <h1>App for BeesInernatinal</h1>
     * <h2>Android Project of MAD305 Course</h2>
     *
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     *
     * @author Wusiman Yibuulayin
     * @version 1.0
     * @since 2020-11-20
     *
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
        view = inflater.inflate(R.layout.fragment_contact, container, false);


        return view;
    }


    /**
     * <h1>App for BeesInernatinal</h1>
     * <h2>Android Project of MAD305 Course</h2>
     *
     *  OnClickListener class implemets {@link android.view.View.OnClickListener}<br>
     *  get call number as string in constructor method<br>
     *  open phone app with call number in onClick method<br>
     *
     * @author Wusiman Yibuulayin
     * @version 1.0
     * @since 2020-11-20
     *
     */
    class PhoneCall implements View.OnClickListener{
        private String callNumber;

        /**
         * Constructor to get the call number
         * @param callNumber
         */
        public PhoneCall(String callNumber) {
            this.callNumber = callNumber;
            //this.view = view;
        }

        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Permission NOT Granted", Snackbar.LENGTH_LONG);
                snackbar.show();

                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        getActivity(),
                        Manifest.permission.CALL_PHONE
                )){

                } else {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[] {Manifest.permission.CALL_PHONE},
                            PREMISSION_CALL_PHONE
                    );
                }
            } else {

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

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));

        // Call three different number with Intent
        TextView callTextView = view.findViewById(R.id.callTextView);
        callTextView.setTextSize(textSize);
        callTextView.setOnClickListener(new PhoneCall(callTextView.getText().toString()));
        TextView salesTextView = view.findViewById(R.id.salesTextView);
        salesTextView.setTextSize(textSize);
        salesTextView.setOnClickListener(new PhoneCall(salesTextView.getText().toString()));
        TextView accountingTextView = view.findViewById(R.id.accountingTextView);
        accountingTextView.setTextSize(textSize);
        accountingTextView.setOnClickListener(new PhoneCall(accountingTextView.getText().toString()));


        // SMS to a phone number Intent
        TextView smsTextView = view.findViewById(R.id.smsTextView);
        smsTextView.setTextSize(textSize);
        smsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                //sendIntent.setData(sms);
                sendIntent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(sendIntent, ""));
                startActivity(sendIntent);
            }
        });

        // Send Email Intent
        TextView emailTextView = view.findViewById(R.id.emailTextView);
        emailTextView.setTextSize(textSize);
        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SENDTO);
                Uri email = Uri.parse("mailto:");
                String[] emailAddress = {getResources().getString(R.string.text_email_address)};

                i.setData(email);
                i.putExtra(Intent.EXTRA_EMAIL,emailAddress);


                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot end an email", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        // Fax textView
        TextView faxTextView = view.findViewById(R.id.faxTextView);
        faxTextView.setTextSize(textSize);

        // Open address on map Intent
        TextView addressTextView = view.findViewById(R.id.addressTextView);
        addressTextView.setTextSize(textSize);
        addressTextView.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri location = Uri.parse("geo:0.0?q=42.3189817,-83.0435235(BEES International)");
            i.setData(location);

            if(i.resolveActivity(getActivity().getPackageManager()) != null){
                startActivity(i);
            } else {
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Cannot open the map", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        TextView dealerTextView = view.findViewById(R.id.dealerButton);
        dealerTextView.setTextSize(textSize);
        dealerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.action_nav_contact_to_dealerFragment);
            }
        });

        ImageView twitterImage = view.findViewById(R.id.twitterImage);
        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri twitter = Uri.parse("twitter://user?screen_name=Torabek403");
                Uri twitterWeb = Uri.parse("https://twitter.com/Torabek403");
                i.setData(twitter);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    webIntent.setData(twitterWeb);

                    if(webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(webIntent);
                    }else {

                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Cannot open twitter app", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

            }
        });

        ImageView facebookImage = view.findViewById(R.id.facebookImage);
        facebookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri twitter = Uri.parse("fb://www.facebook.com/BEES-International-102895001694749");
                Uri twitterWeb = Uri.parse("fb://page/102895001694749");
                i.setData(twitter);

                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                } else {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    webIntent.setData(twitterWeb);

                    if(webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(webIntent);
                    }else {
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Cannot open twitter app", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            }

        });
    }
}