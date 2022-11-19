package com.example.coursework;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.databinding.ExpensesListItemBinding;
import com.example.coursework.databinding.ListItemBinding;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder> {


    @NonNull
    @Override
    public ExpensesAdapter.ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.expenses_list_item, parent, false);
        return new ExpensesAdapter.ExpensesViewHolder(view);
    }

    public ExpensesAdapter(List<ExpensesEntities> listExpense) {
        this.listExpense = listExpense;
    }


    private ListItemListener listener;
    private List<ExpensesEntities> listExpense;

    public interface ListItemListener {
        void onItemClick(View view, int position);
    }
    public void setListener(ExpensesAdapter.ListItemListener listener) {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ExpensesViewHolder holder, int position) {
        ExpensesEntities expenses = listExpense.get(position);
        holder.bindData(expenses);
    }

    @Override
    public int getItemCount() {
        return listExpense.size();
    }

    public class ExpensesViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final ExpensesListItemBinding itemBinding;

        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ExpensesListItemBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }
        public void bindData(ExpensesEntities expenses) {
            itemBinding.amountTitle.setText(expenses.getAmount());
            itemBinding.datetimeTitle.setText(expenses.getTime());
            itemBinding.typeTitle.setText(expenses.getType());
        }
        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAbsoluteAdapterPosition());

        }
    }
}