package ibrahim.example.beesinernatinal.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.pojo.Currency;
import ibrahim.example.beesinernatinal.pojo.ProductType;
import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FactFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author Wusiman Yibuulayin
 * @version 1.0
 * @since 2020-11-20
 */
public class FactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARGS_PRODUCTS = "products";
    private static final String ARGS_PRODUCT_NAME = "product_name";
    private static final String ARGS_PRODUCT_IMAGE = "product_image";
    private static final String ARGS_PRODUCT_DESCRIPTION = "product_description";
    private static final String ARGS_PRODUCT_PRICE = "product_price";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProductType product;
    private String productName;
    private int productImage;
    private String productDescription;
    private double productPrice;

    private View view;

    public FactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FactFragment newInstance(String name, int imageSource, String description, double price) {
        FactFragment fragment = new FactFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_PRODUCT_NAME,  name);
        args.putInt(ARGS_PRODUCT_IMAGE,  imageSource);
        args.putString(ARGS_PRODUCT_DESCRIPTION,  description);
        args.putDouble(ARGS_PRODUCT_PRICE,  price);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //product = (ProductType) getArguments().getSerializable(ARGS_PRODUCTS);
            productName = getArguments().getString(ARGS_PRODUCT_NAME);
            productImage = getArguments().getInt(ARGS_PRODUCT_IMAGE);
            productDescription = getArguments().getString(ARGS_PRODUCT_DESCRIPTION);
            productPrice = getArguments().getDouble(ARGS_PRODUCT_PRICE);
        }


    }

    /**
     * Override onResume method to effect the settings of text size.
     */
    @Override
    public void onResume() {
        super.onResume();
        showPrice();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fact, container, false);



        //showPrice();


        return view;
    }

    private void showPrice(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));
        String currencyName = preferences.getString("currency","USD");


        TextView productNameTextView = view.findViewById(R.id.prodcutNameTextView);
        productNameTextView.setText(productName);
        productNameTextView.setTextSize(textSize + 6);


        ImageView productImageView = view.findViewById(R.id.productImageView);
        productImageView.setImageResource(productImage);

        TextView productDescriptionTextView = view.findViewById(R.id.productDescriptionTextView);
        productDescriptionTextView.setText(productDescription);
        productDescriptionTextView.setTextSize(textSize);


        TextView productPriceTextView = view.findViewById(R.id.productPriceTextView);
        Currency current = MainActivity.exchangeRates.get(0);
        double exchageRate = 1;
        String currency = "$";



        for (Currency c :
                MainActivity.exchangeRates) {
            if(c.getName().equals(currencyName)){
                exchageRate = c.getRate();
                currency = c.getSign();
                break;
            }
        }

        String price = new DecimalFormat("#.##").format((productPrice * exchageRate));
        productPriceTextView.setText(currency + price);
        productPriceTextView.setTextSize(textSize + 8);
    }
}