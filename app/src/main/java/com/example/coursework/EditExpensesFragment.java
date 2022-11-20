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
        requireActivity().setTitle("Edit Expense");
        binding = FragmentEditExpensesBinding.inflate(inflater,container,false);
        database = RoomHelper.initDatabase(getContext());

        int id = getArguments().getInt("id");
        ExpensesEntities exp = database.expensesDAO().getExpensesByID(id);

        binding.txtAmount.setText(exp.getAmount());
        binding.txtDate.setText(exp.getTime());
        binding.txtDesc.setText(exp.getNote());
        binding.txtType.setText(exp.getType());

        binding.btnAExpense.setOnClickListener(view -> {
            String amount = binding.txtAmount.getText().toString();
            String date = binding.txtDate.getText().toString();
            String type= binding.txtType.getText().toString();
            String desc = binding.txtDesc.getText().toString();
            if(type.length()==0){
                binding.txtType.requestFocus();
                binding.txtType.setError("Type can not be null");
            }else if(amount.length()==0){
                binding.txtAmount.requestFocus();
                binding.txtAmount.setError("Amount can not be null");
            }else if(date.length()==0){
                binding.txtDate.requestFocus();
                binding.txtDate.setError("Date can not be null");
            }else if(desc.length()==0){
                binding.txtDesc.requestFocus();
                binding.txtDesc.setError("Description can not be null");
            }else {
                exp.setAmount(amount);
                exp.setTime(date);
                exp.setNote(desc);
                exp.setType(type);
                database.expensesDAO().update(exp);
                requireActivity().onBackPressed();
            }

        });
        binding.btnDelete.setOnClickListener(view -> {
            database.expensesDAO().deleteExpensesByID(id);
            requireActivity().onBackPressed();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}