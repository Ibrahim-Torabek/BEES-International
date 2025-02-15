package ibrahim.example.beesinernatinal.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.pojo.ProductType;
import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author Wusiman Yibuulayin
 * @version 1.0
 * @since 2020-11-20
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PRODCUTS = "PRODUCTS";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<ProductType> products;

    private View view;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        view = inflater.inflate(R.layout.fragment_product, container, false);


        return view;
    }

    /**
     * Override onResume method to effect the settings of text size.
     */
    @Override
    public void onResume() {
        super.onResume();
        products = (ArrayList<ProductType>) getArguments().getSerializable(ARG_PRODCUTS);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getChildFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.productContent);
        viewPager.setAdapter(adapter);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(preferences.getBoolean("animation",true))
            viewPager.setPageTransformer(true, new RotateDownTransformer());
        else
            viewPager.setPageTransformer(true, null);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter{
        //private int itemCount = 0;
        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //itemCount = products.size()
            int viewPosition = position % products.size();
            return FactFragment.newInstance(
                    products.get(viewPosition).getName(),
                    products.get(viewPosition).getImageSource(),
                    products.get(viewPosition).getDescription(),
                    products.get(viewPosition).getPrice()
            );
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
    }
}