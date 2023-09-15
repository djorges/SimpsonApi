package com.example.simpsonapi.presentation.ui;

import static java.util.Collections.emptyList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simpsonapi.R;
import com.example.simpsonapi.databinding.FragmentQuoteBinding;
import com.example.simpsonapi.domain.Quote;
import com.example.simpsonapi.presentation.adapter.QuoteAdapter;
import com.example.simpsonapi.presentation.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class QuoteFragment extends Fragment {
    private FragmentQuoteBinding binding;
    private QuoteAdapter adapter;
    private MainViewModel viewModel;

    private final Observer<List<Quote>> listObserver = new Observer<List<Quote>>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull List<Quote> quotes) {
            adapter.setQuotes(quotes);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            adapter.setQuotes(emptyList());
        }

        @Override
        public void onComplete() {

        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setup recyclerview
        adapter = new QuoteAdapter();
        binding.quoteRecyclerList.setAdapter(adapter);
        binding.quoteRecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Init ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.getAllQuotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listObserver);

        //Swipe gesture callback
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Quote deletedQuote = adapter.getItem(position);

                //Delete item
                adapter.removeItem(position);
                adapter.notifyItemRemoved(position);

                //Show snackbar
                Snackbar.make(view, R.string.quote_msg_deleted, Snackbar.LENGTH_LONG)
                        .setAction(R.string.quote_msg_undo, v -> {
                            //Undo item delete
                            adapter.addItem(position, deletedQuote);
                            adapter.notifyItemInserted(position);
                        })
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                //Delete when timeout
                                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                    viewModel.deleteQuote(deletedQuote)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe();
                                }
                            }
                            @Override
                            public void onShown(Snackbar snackbar) {
                            }
                        })
                        .show();
            }
        }).attachToRecyclerView(binding.quoteRecyclerList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}