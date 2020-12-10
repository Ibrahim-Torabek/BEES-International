package ibrahim.example.beesinernatinal.adapter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.R;
import ibrahim.example.beesinernatinal.pojo.Currency;


/**
 * <h1>App for BEES International</h1>
 * <h2>Android Project of MAD305 Course</h2>
 * <p>This is the recycler view adapter to display currency exchange rates.</p>
 * <p>
 *
 * @author  Wusiman Yibulayin (Ibrahim)
 * @version 1.0
 * @since   2020-11-20
 *
 * @see AppCompatActivity
 *
 */
public class ExchangeRecyclerViewAdapter extends RecyclerView.Adapter<ExchangeRecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<Currency> exchangeRates;
    private double usd;
    private int textSize;

    /**
     * This is the constructor of class
     * @param rates All currnecy list to display.
     * @param usd usd amount to calculate the exchanges of each currency
     * @param textSize display text size that to be set in setting fragment.
     */
    public ExchangeRecyclerViewAdapter(ArrayList<Currency> rates, double usd, int textSize) {

        this.exchangeRates = rates;
        this.usd = usd;
        this.textSize = textSize + 8;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_exchange, parent,false);


        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        // get the currency
        Currency exchangeRate = exchangeRates.get(position);

        // place currency's country name to countryName textView, and set the text size.
        holder.countryName.setText(exchangeRate.getCountry());
        holder.countryName.setTextSize(textSize - 8);

        // Just list countries name if usd = -1. Because, This list will be reused in About Us fragmnet.
        if (usd != -1) {
            // Display currency name: CAD, EUR etc.
            holder.currencyName.setText(exchangeRate.getName());
            holder.currencyName.setTextSize(textSize);

            // Calculate and display the exchange rate
            double rate = Math.round(usd * exchangeRate.getRate() * 100 ) / 100;
            holder.exchangeRate.setText(exchangeRate.getSign() + String.valueOf(rate));
            holder.exchangeRate.setTextSize(textSize);

            // Display the flag. Because The size of image is different than text size, I multiplied 3 times of text size
            // So that, the flag size will be changed when we set the text size in the settings fragment.
            holder.countryFlag.setImageResource(exchangeRate.getFlag());
            ViewGroup.LayoutParams layoutParams = holder.countryFlag.getLayoutParams();
            layoutParams.width = textSize * 3;
            layoutParams.height = textSize * 3;
            holder.countryFlag.setLayoutParams(layoutParams);


        }

    }

    @Override
    public int getItemCount() {
        if(exchangeRates != null){
            return exchangeRates.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{


        protected TextView countryName;
        protected TextView currencyName;
        protected TextView exchangeRate;
        protected ImageView countryFlag;
        //protected double usd; // = Double.parseDouble((String) usdText.getText());

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);


            countryName = itemView.findViewById(R.id.countryNameTextView);
            currencyName = itemView.findViewById(R.id.currencyNameTextView);
            exchangeRate = itemView.findViewById(R.id.exchangeRateTextView);
            countryFlag = itemView.findViewById(R.id.countryFalgImageView);
        }
    }
}