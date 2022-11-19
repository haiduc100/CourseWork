package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coursework.databinding.FragmentCreateExpensesBinding;

public class CreateExpensesFragment extends Fragment {
    private FragmentCreateExpensesBinding binding;
    private Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateExpensesBinding.inflate(inflater,container,false);
        database = RoomHelper.initDatabase(getContext());

        int id = getArguments().getInt("id");
        TripEntities tripEntities = database.tripDAO().getTripByID(id);

        binding.btnAExpense.setOnClickListener(view ->
        {
            String amount = binding.txtAmount.getText().toString();
            String date = binding.txtDate.getText().toString();
            String type= binding.txtType.getText().toString();
            String desc = binding.txtDesc.getText().toString();

            ExpensesEntities expenses = new ExpensesEntities(tripEntities, type,amount,date,desc);
            expenses.setTripEntities(tripEntities);

            database.expensesDAO().insert(expenses);
            requireActivity().onBackPressed();


        });

        return binding.getRoot();
    }
}