package ibrahim.example.beesinernatinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARGS_PRODUCTS = "products";
    private static final String ARGS_PRODUCT_NAME = "product_name";
    private static final String ARGS_PRODUCT_IMAGE = "product_image";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProductType product;
    private String productName;
    private int productImage;

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
    public static FactFragment newInstance(String productName, int productImage) {
        FactFragment fragment = new FactFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_PRODUCT_NAME,  productName);
        args.putInt(ARGS_PRODUCT_IMAGE,  productImage);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fact, container, false);

        TextView productNameTextView = view.findViewById(R.id.prodcutNameTextView);
        productNameTextView.setText(productName);

        ImageView productImageView = view.findViewById(R.id.productImageView);
        productImageView.setImageResource(productImage);

        return view;
    }
}