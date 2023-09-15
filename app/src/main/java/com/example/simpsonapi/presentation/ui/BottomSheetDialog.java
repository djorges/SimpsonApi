package com.example.simpsonapi.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpsonapi.R;
import com.example.simpsonapi.databinding.BottomSheetDialogBinding;
import com.example.simpsonapi.presentation.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetDialogBinding binding;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = BottomSheetDialogBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Init Views
        BottomSheetDialogArgs args = BottomSheetDialogArgs.fromBundle(getArguments());
        String quote = args.getQuote();
        String character = args.getCharacter();

        binding.sheetQuote.setText(quote);
        binding.sheetBtnCopy.setOnClickListener(view1 -> {
            viewModel.copyQuoteIntoClipboard(quote);
            Toast.makeText(requireContext(), R.string.sheet_msg_copied, Toast.LENGTH_SHORT).show();
        });
        binding.sheetBtnShare.setOnClickListener(view1 -> {
            //Create Chooser
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, quote);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
        binding.sheetBtnSave.setOnClickListener(view1 -> {
            viewModel.insertQuote(quote,character)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        Toast.makeText(requireContext(), R.string.sheet_msg_saved, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(requireContext(), R.string.sheet_msg_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        });
    }
}
