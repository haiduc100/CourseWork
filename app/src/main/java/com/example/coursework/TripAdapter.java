package com.example.coursework;

import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coursework.databinding.ListItemBinding;
import java.util.List;

public class TripAdapter
  extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {

  @NonNull
  @Override
  public TripAdapter.TripViewHolder onCreateViewHolder(
    @NonNull ViewGroup parent,
    int viewType
  ) {
    View view = LayoutInflater
      .from(parent.getContext())
      .inflate(R.layout.list_item, parent, false);
    return new TripViewHolder(view);
  }

  public TripAdapter(List<TripEntities> listTrip) {
    this.listTrip = listTrip;
  }

  private ListItemListener listener;
  private List<TripEntities> listTrip;

  public interface ListItemListener {
    void onItemClick(View view, int position);
  }

  public void setListener(ListItemListener listener) {
    this.listener = listener;
  }

  @Override
  public void onBindViewHolder(
    @NonNull TripAdapter.TripViewHolder holder,
    int position
  ) {
    TripEntities trip = listTrip.get(position);
    holder.bindData(trip);
  }

  @Override
  public int getItemCount() {
    return listTrip.size();
  }

  public class TripViewHolder
    extends RecyclerView.ViewHolder
    implements View.OnClickListener {

    private final ListItemBinding itemBinding;

    public TripViewHolder(@NonNull View itemView) {
      super(itemView);
      itemBinding = ListItemBinding.bind(itemView);
      itemView.setOnClickListener(this);
    }

    public void bindData(TripEntities trip) {
      itemBinding.destinatonTitle.setText(trip.getDestination());
      itemBinding.dayTitle.setText(trip.getStartDate());
      itemBinding.budgetTitle.setText(trip.getBudget());
    }

    @Override
    public void onClick(View view) {
      listener.onItemClick(view, getAbsoluteAdapterPosition());
    }
  }
}
