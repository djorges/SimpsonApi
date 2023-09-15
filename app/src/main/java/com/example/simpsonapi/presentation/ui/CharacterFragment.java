package com.example.simpsonapi.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.simpsonapi.databinding.FragmentCharacterBinding;
import com.example.simpsonapi.presentation.adapter.CharacterAdapter;
import com.example.simpsonapi.presentation.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentCharacterBinding binding;
    private CharacterAdapter adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setup ReciclerView
        adapter = new CharacterAdapter(getActivity());
        binding.characterRecyclerList.setAdapter(adapter);
        binding.characterRecyclerList.setLayoutManager(new GridLayoutManager(getActivity(),3));

        //Init ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel.getCharacterList().observe(getViewLifecycleOwner(), list -> {
            adapter.setCharacters(list);
            adapter.notifyDataSetChanged();
        });
        viewModel.fetchAllCharacters();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}