package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursework.databinding.FragmentEditExpensesBinding;

public class EditExpensesFragment extends Fragment {

private Database database;
private FragmentEditExpensesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditExpensesBinding.inflate(inflater,container,false);
        database = RoomHelper.initDatabase(getContext());

        int id = getArguments().getInt("id");
        ExpensesEntities exp = database.expensesDAO().getExpensesByID(id);

        binding.txtAmount.setText(exp.getAmount());
        binding.txtDate.setText(exp.getTime());
        binding.txtDesc.setText(exp.getNote());
        binding.txtType.setText(exp.getType());

        binding.btnAExpense.setOnClickListener(view -> {
            exp.setAmount(binding.txtAmount.getText().toString());
            exp.setTime(binding.txtDate.getText().toString());
            exp.setNote(binding.txtDesc.getText().toString());
            exp.setType(binding.txtType.getText().toString());

            database.expensesDAO().update(exp);
           requireActivity().onBackPressed();
        });
        binding.btnDelete.setOnClickListener(view -> {
            database.expensesDAO().deleteExpensesByID(id);
            requireActivity().onBackPressed();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}