package ibrahim.example.beesinernatinal.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import ibrahim.example.beesinernatinal.CategoryType;
import ibrahim.example.beesinernatinal.ProductType;
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

    ArrayList<ProductType> products = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Create Products List
        // Data is just for test.
        products.add(new ProductType("Hand Tools", "Drill", "Good", 0));
        products.add(new ProductType("Hand Tools", "Drill", "Good", 0));
        products.add(new ProductType("Hand Tools", "Drill", "Good", 0));
        products.add(new ProductType("Hand Tools", "Drill", "Good", 0));
        products.add(new ProductType("Hand Tools", "Drill", "Good", 0));
        products.add(new ProductType("Outdoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Outdoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Outdoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Outdoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Outdoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Indoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Indoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Indoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Indoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Indoor Tools", "Drill", "Good", 0));
        products.add(new ProductType("Power Tools", "Drill", "Good", 0));
        products.add(new ProductType("Power Tools", "Drill", "Good", 0));
        products.add(new ProductType("Power Tools", "Drill", "Good", 0));
        products.add(new ProductType("Power Tools", "Drill", "Good", 0));
        products.add(new ProductType("Power Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("Garden Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("WoodWorking Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));
        products.add(new ProductType("Air Tools", "Drill", "Good", 0));

        // Create Category list from products list
        ArrayList<CategoryType> categories = new ArrayList<>();

//        // Take all products' category from products list into categories ArrayList
//        for (ProductType product :
//                products) {
//            categories.add(new CategoryType(product.getCategory(),1));  // This means at least one item exist at that category
//        }
//
//        // Remove duplicated item in each category, and increase item count.
//        for(int i=0; i < categories.size(); i++){
//            for(int j=i+1; j < categories.size(); j++){
//                if(categories.get(i).getName() == categories.get(j).getName()){
//                    categories.get(i).setItems(categories.get(i).getItems() + 1);
//                    categories.remove(j);
//                    j--;
//                }
//            }
//        }

        // Create categories based on products
        for (ProductType product :products) {
            boolean flag=false;
            int indx=0;

            for(;indx<categories.size();indx++) {
                if(categories.get(indx).getName().equals(product.getCategory())) {
                    flag=true;
                    break;  // If category exist in arraylist, break
                }
            }
            if (flag) {  // If category exist in ArrayList, increase the item count
                categories.get(indx).setItems(categories.get(indx).getItems()+1);
            }else {  // If not exit, add the category name to the ArrayList
                categories.add(new CategoryType(product.getCategory(),1));
            }
        }

//        System.out.println(categories.size());
//        System.out.println(categories.get(0).getItems());

        CustomListViewAdapter adapter = new CustomListViewAdapter(getContext(),categories);

        ListView categoryList = view.findViewById(R.id.categoryListView);
        categoryList.setAdapter(adapter);

        return view;
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
                        .inflate(R.layout.category_list, parent,false);

                TextView categoryList = convertView.findViewById(R.id.categoryListTextView);
                categoryList.setText(getItem(position).getName());
                //System.out.println(getItem(position).getName());

                TextView categoryCount = convertView.findViewById(R.id.categoryCountTextView);
                categoryCount.setText(String.valueOf(getItem(position).getItems()));
            }

            return convertView;
        }
    }
}