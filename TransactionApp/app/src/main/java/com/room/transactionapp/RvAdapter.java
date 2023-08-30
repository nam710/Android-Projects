package com.room.transactionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.room.transactionapp.databinding.RvRowBinding;

public class RvAdapter extends ListAdapter<TxEntity,RvAdapter.ViewHolder> {

    public RvAdapter(){
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<TxEntity> CALLBACK = new DiffUtil.ItemCallback<TxEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TxEntity oldItem, @NonNull TxEntity newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TxEntity oldItem, @NonNull TxEntity newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getAmount().equals(newItem.getAmount());
        }
    };

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TxEntity note = getItem(position);
        holder.binding.tvTitle.setText(note.getTitle());
        holder.binding.tvAmount.setText(note.getAmount());
    }
    public TxEntity getTx(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RvRowBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RvRowBinding.bind(itemView);
        }
    }
}
