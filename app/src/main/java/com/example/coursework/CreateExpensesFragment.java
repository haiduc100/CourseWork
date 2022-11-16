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
//        binding.btnAddExpense.setOnClickListener(view ->
//        {
//            String amount = binding.txtAmount.getText().toString();
//            String date = binding.txtDate.getText().toString();
//            String type= binding.txtType.getText().toString();
//            String desc = binding.txtDesc.getText().toString();
//
//            ExpensesEntities expenses = new ExpensesEntities(type,amount,date,desc);
//
//            database.expensesDAO().insert(expenses);
//
//            Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();
////
//        });

        binding.btnAExpense.setOnClickListener(v -> {
            String amount = binding.txtAmount.getText().toString();
            String date = binding.txtDate.getText().toString();
            String type= binding.txtType.getText().toString();
            String desc = binding.txtDesc.getText().toString();

           ExpensesEntities expenses = new ExpensesEntities(type,amount,date,desc);

           database.expensesDAO().insert(expenses);
           requireActivity().onBackPressed();
        });


        return inflater.inflate(R.layout.fragment_create_expenses, container, false);
    }
}