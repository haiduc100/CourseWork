package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursework.databinding.FragmentExpensesBinding;

public class ExpensesFragment extends Fragment {
    private FragmentExpensesBinding binding;
    private  Database database;
    private ExpensesAdapter adapter;
    private MainViewModel MainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireActivity().setTitle("List Expenses");
        binding = FragmentExpensesBinding.inflate(inflater,container,false);
        MainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        database = RoomHelper.initDatabase(getContext());

        MainViewModel.setDatabase(database);
        int tripId = getArguments().getInt("id");
        MainViewModel.getExpensesOfTrip(tripId).observe(getViewLifecycleOwner(),expensesEntities -> {
            adapter = new ExpensesAdapter(expensesEntities);

            adapter.setListener((view, position) -> {
                Bundle bundle = new Bundle();// send data
                int expId = expensesEntities.get(position).getId();
                bundle.putInt("id",expId);
                Navigation.findNavController(getView()).navigate(R.id.action_expensesFragment_to_editExpensesFragment,bundle);
            });
            binding.recyclerview.setAdapter(adapter);
            binding.recyclerview.setLayoutManager(
                    new LinearLayoutManager(getActivity())
            );
            binding.recyclerview.addItemDecoration(
                    new DividerItemDecoration(
                            getContext(),
                            DividerItemDecoration.HORIZONTAL
                    )
            );
        });
        RecyclerView rv = binding.recyclerview;
        rv.setHasFixedSize(true);
        rv.addItemDecoration(
                new DividerItemDecoration(
                        getContext(),
                        (new LinearLayoutManager(getContext()).getOrientation())
                )
        );
        binding.fabAddExpenses.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", tripId);
            Navigation
                    .findNavController(getView())
                    .navigate(R.id.action_expensesFragment_to_createExpensesFragment, bundle);
        });
        return binding.getRoot();
    }
}