package ibrahim.example.beesinernatinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExchangeRecyclerViewAdapter extends RecyclerView.Adapter<ExchangeRecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<Currency> exchangeRates;
    private double usd;

    public ExchangeRecyclerViewAdapter(ArrayList<Currency> rates, double usd) {

        this.exchangeRates = rates;
        this.usd = usd;

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_exchange, parent,false);
//        CustomViewHolder viewHolder = new CustomViewHolder(view);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //view.setLayoutParams(params);
        //CustomViewHolder viewHolder = new CustomViewHolder(view);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Currency exchangeRate = exchangeRates.get(position);

        holder.countryName.setText(exchangeRate.getCountry());

        // Just list countries name if usd = -1
        if (usd != -1) {
            holder.currencyName.setText(exchangeRate.getName());
            double rate = Math.round(usd * exchangeRate.getRate() );
            holder.exchangeRate.setText(exchangeRate.getSign() + String.valueOf(rate));
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
        //protected double usd; // = Double.parseDouble((String) usdText.getText());

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);


            countryName = itemView.findViewById(R.id.countryNameTextView);
            currencyName = itemView.findViewById(R.id.currencyNameTextView);
            exchangeRate = itemView.findViewById(R.id.exchangeRateTextView);
        }
    }
}