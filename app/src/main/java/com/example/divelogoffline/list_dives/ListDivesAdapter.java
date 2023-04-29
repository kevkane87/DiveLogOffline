package com.example.divelogoffline.list_dives;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divelogoffline.Dive;
import com.example.divelogoffline.databinding.ItemDiveBinding;

public class ListDivesAdapter  extends ListAdapter<Dive, ListDivesAdapter.ViewHolder> {

    public ListDivesAdapter(@NonNull DiffUtil.ItemCallback<Dive> diffCallback) {
        super(diffCallback);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ItemDiveBinding binding;

        private ViewHolder(ItemDiveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Dive item) {
            binding.itemDate.setText(item.date);
            binding.itemDiveTitle.setText(item.diveTitle);
            binding.itemDiveSite.setText(item.diveSite);
            binding.itemBottomTime.setText(Integer.toString(item.bottomTime));
            binding.itemMaxDepth.setText(Integer.toString(item.maxDepth));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemDiveBinding binding = ItemDiveBinding.inflate(
               LayoutInflater.from(parent.getContext()),
               parent,
               false);

       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dive current = getItem(position);
        holder.bind(current);
    }

    public Dive getCurrentDive(int position) {
        return getItem(position);
    }

    static class DiveDiff extends DiffUtil.ItemCallback<Dive> {

        @Override
        public boolean areItemsTheSame(@NonNull Dive oldItem, @NonNull Dive newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dive oldItem, @NonNull Dive newItem) {
            return oldItem.id == newItem.id;
        }
    }
}