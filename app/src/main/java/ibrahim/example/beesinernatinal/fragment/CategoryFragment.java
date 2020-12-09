package ibrahim.example.beesinernatinal.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.pojo.CategoryType;
import ibrahim.example.beesinernatinal.MainActivity;
import ibrahim.example.beesinernatinal.pojo.ProductType;
import ibrahim.example.beesinernatinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //ArrayList<ProductType> products = new ArrayList<>();
    ArrayList<CategoryType> categories = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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

        initProducts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.fab.hide();

        CustomListViewAdapter adapter = new CustomListViewAdapter(getContext(),categories);

        ListView categoryList = view.findViewById(R.id.categoryListView);
        categoryList.setAdapter(adapter);

        categoryList.setOnItemClickListener((parent, view1, position, id) -> {
            ArrayList<ProductType> products = categories.get(position).getProducts();

            Bundle args = new Bundle();
            args.putSerializable("PRODUCTS",products);

            NavController navController = Navigation.findNavController(view);

            navController.navigate(R.id.action_nav_category_to_productFragment, args);
        });
    }

    public class CustomListViewAdapter extends ArrayAdapter<CategoryType>{
        public CustomListViewAdapter(@NonNull Context context, ArrayList<CategoryType> categories) {
            super(context, 0, categories);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.list_category, parent,false);

                ImageView iconImage = convertView.findViewById(R.id.iconImageView);
                iconImage.setImageResource(getItem(position).getIconResource());

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));
                //System.out.println(textSize);

                TextView categoryList = convertView.findViewById(R.id.categoryListTextView);
                categoryList.setText(getItem(position).getName());
                categoryList.setTextSize(textSize);


                TextView categoryCount = convertView.findViewById(R.id.categoryCountTextView);
                categoryCount.setText(String.valueOf(getItem(position).getItems()));
                categoryCount.setTextSize(textSize);
            }

            return convertView;
        }


    }

    private void initProducts(){
        //String[] categoryName = new String[getResources().getStringArray(R.array.categories).length];
        String[] categoryNames = getResources().getStringArray(R.array.categories_name);
        TypedArray categoryIcons = getResources().obtainTypedArray(R.array.categories_icon);

        if(categoryNames.length == categoryIcons.length()) {
            for(int i =0 ; i < categoryNames.length; i++){
                categories.add(new CategoryType(categoryNames[i], categoryIcons.getResourceId(i,-1)));
            }
        }

        String[] productNames = getResources().getStringArray(R.array.products_name);
        String[] productDescription = getResources().getStringArray(R.array.products_description);
        TypedArray productImage = getResources().obtainTypedArray((R.array.products_image));
        String[] productPrice = getResources().getStringArray(R.array.products_pricee);
        int[] categoriesItem = getResources().getIntArray(R.array.categories_item);
        int productCount = 0;


        for(int i =0; i < categories.size(); i++){
            for(int j = 0; j < categoriesItem[i]; j++){
                categories.get(i).setProduct(
                        new ProductType(
                                productNames[productCount],
                                productDescription[productCount],
                                productImage.getResourceId(productCount,-1),
                                Double.parseDouble(productPrice[productCount]))
                );
                productCount++;
            }
        }
//        for (CategoryType category :
//                categories) {
//            Random r = new Random();
//            for (int i = 0; i < r.nextInt(10) + 3; i++) {
//                ProductType productType = new ProductType("Drill", "Good",10);
//                //category.setProduct(productType);
//                if(category != null && productType != null) {
//                    category.setProduct(productType);
//                }
//                //category.setProduct(new ProductType("Drill", "Good", 0));
//            }
//            //category.setIconResource(R.drawable.ic_gardentools);
//            //category.setProduct(new ProductType("Drill", "Good",10));
//        }


    }
}