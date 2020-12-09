package ibrahim.example.beesinernatinal.adapter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.R;
import ibrahim.example.beesinernatinal.pojo.Currency;

public class ExchangeRecyclerViewAdapter extends RecyclerView.Adapter<ExchangeRecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<Currency> exchangeRates;
    private double usd;
    private int textSize;

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

        Currency exchangeRate = exchangeRates.get(position);

        holder.countryName.setText(exchangeRate.getCountry());
        holder.countryName.setTextSize(textSize - 8);

        // Just list countries name if usd = -1
        if (usd != -1) {
            holder.currencyName.setText(exchangeRate.getName());
            holder.currencyName.setTextSize(textSize);

            double rate = Math.round(usd * exchangeRate.getRate() *100 ) / 100;
            holder.exchangeRate.setText(exchangeRate.getSign() + String.valueOf(rate));
            holder.exchangeRate.setTextSize(textSize);

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