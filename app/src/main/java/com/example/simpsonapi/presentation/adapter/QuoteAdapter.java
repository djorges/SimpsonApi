package com.example.simpsonapi.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpsonapi.R;
import com.example.simpsonapi.domain.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder>{
    private List<Quote> quotes = new ArrayList<>();

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public QuoteAdapter() {}

    public Quote getItem(int position) {
        return quotes.get(position);
    }

    public void removeItem(int position) {
        quotes.remove(position);
    }

    public void addItem(int position, Quote quote) {
        quotes.add(position, quote);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemSubTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle =(TextView) itemView.findViewById(R.id.quote_item_title);
            itemSubTitle = (TextView) itemView.findViewById(R.id.quote_item_subtitle);
        }
    }
    @NonNull
    @Override
    public QuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_quote, parent, false);

        return new QuoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.ViewHolder holder, int position) {
       Quote quote = quotes.get(position);
        //Setup item view
        holder.itemTitle.setText(quote.getQuote());
        holder.itemSubTitle.setText(quote.getCharacter());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
