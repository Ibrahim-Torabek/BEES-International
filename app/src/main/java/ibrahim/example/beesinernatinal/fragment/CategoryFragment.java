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
 *
 * @author  Wusiman Yibulayin (Ibrahim)
 * @version 1.0
 * @since   2020-11-20
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PRODCUTS = "PRODUCTS";

    //ArrayList<ProductType> products = new ArrayList<>();
    ArrayList<CategoryType> categories = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // I need to use the view in other overrided method of this fragment.
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
        MainActivity.fab.hide();
        return view;
    }

    /**
     * Override onResume method to effect the settings of text size.
     */
    @Override
    public void onResume() {
        super.onResume();

        // List Categories in the ListView
        CustomListViewAdapter adapter = new CustomListViewAdapter(getContext(),categories);

        ListView categoryList = view.findViewById(R.id.categoryListView);
        categoryList.setAdapter(adapter);

        categoryList.setOnItemClickListener((parent, view1, position, id) -> {

            ArrayList<ProductType> products = categories.get(position).getProducts();

            // Get animation settings from preferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            boolean isAnimated = preferences.getBoolean("animation",true);

            Bundle args = new Bundle();
            args.putSerializable(ARG_PRODCUTS,products);

            // Set navigation options builder to set animation of the navigation controller
            NavOptions.Builder navBuilder = new NavOptions.Builder();
            // Set navigation to the navigation controller if animation settings turned on.
            if(isAnimated){
                navBuilder.setEnterAnim(R.anim.in);
                navBuilder.setExitAnim(R.anim.out);
                navBuilder.setPopEnterAnim(R.anim.back_in);
                navBuilder.setPopExitAnim(R.anim.back_out);
            }

            NavController navController = Navigation.findNavController(view);

            navController.navigate(R.id.action_nav_category_to_productFragment, args,navBuilder.build());
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

                // Set the category's icon.
                ImageView iconImage = convertView.findViewById(R.id.iconImageView);
                iconImage.setImageResource(getItem(position).getIconResource());

                // Get text size
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));
                //System.out.println(textSize);

                // Set category's name
                TextView categoryList = convertView.findViewById(R.id.categoryListTextView);
                categoryList.setText(getItem(position).getName());
                categoryList.setTextSize(textSize);

                // Set product amount of the category.
                TextView categoryCount = convertView.findViewById(R.id.categoryCountTextView);
                categoryCount.setText(String.valueOf(getItem(position).getItems()));
                categoryCount.setTextSize(textSize);
            }

            return convertView;
        }


    }

    /**
     * Initialize Product list objects into their related category.
     * Initialize Category list objects into an arrayList. </br>
     * All attributes of the categories' and products' information stored in String.xml file.
     * This function will get all attributes from string file as array and assign them into the created category
     * object ArrayList.
     *
     * @return Arraylist<Currency>
     */
    private void initProducts(){
        // Get all categories and their icons.
        String[] categoryNames = getResources().getStringArray(R.array.categories_name);
        TypedArray categoryIcons = getResources().obtainTypedArray(R.array.categories_icon);

        // create a category list array.
        if(categoryNames.length == categoryIcons.length()) {
            for(int i =0 ; i < categoryNames.length; i++){
                categories.add(new CategoryType(categoryNames[i], categoryIcons.getResourceId(i,-1)));
            }
        }

        // Get all products' name, description, image, price.
        String[] productNames = getResources().getStringArray(R.array.products_name);
        String[] productDescription = getResources().getStringArray(R.array.products_description);
        TypedArray productImage = getResources().obtainTypedArray((R.array.products_image));
        String[] productPrice = getResources().getStringArray(R.array.products_pricee);

        // categories_item list is a list to determine how much items that the categories have.
        // This array is listed each category has how much items.
        int[] categoriesItem = getResources().getIntArray(R.array.categories_item);
        int productCount = 0;

        // Put products into their category by categories_item array.
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

    }
}