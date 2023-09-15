package com.example.simpsonapi.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simpsonapi.R;
import com.example.simpsonapi.domain.Character;
import com.example.simpsonapi.presentation.ui.CharacterFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder>{
    private Context context;
    private List<Character> characters = new ArrayList<>();

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public CharacterAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView itemCard;
        public ImageView itemImg;
        public TextView itemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemCard = (CardView) itemView.findViewById(R.id.character_item_card);
            itemImg = (ImageView) itemView.findViewById(R.id.character_item_img);
            itemTitle =(TextView)itemView.findViewById(R.id.quote_item_title);
        }
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        Character character = characters.get(position);

        //Setup item view
        Glide
            .with(context)
            .load(character.getImg())
            .centerInside()
            .into(holder.itemImg);
        holder.itemTitle.setText(character.getCharacter());
        holder.itemCard.setOnClickListener(view -> {
            //Show BottomSheetDialog
            Navigation.findNavController(holder.itemView).navigate(
                CharacterFragmentDirections.actionCharacterFragmentToBottomSheetDialog(character.getQuote(), character.getCharacter())
            );
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
