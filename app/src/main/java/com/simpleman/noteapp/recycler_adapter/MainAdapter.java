package com.simpleman.noteapp.recycler_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleman.noteapp.R;
import com.simpleman.noteapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements Filterable {

    private Context context;
    private List<Note> noteList;
    private List<Note> fullNoteList;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Note> noteList , ItemClickListener itemClickListener) {
        this.noteList = noteList;
        fullNoteList = new ArrayList<>(noteList);
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_title, tv_note, tv_date;
        CardView cardView;
        ItemClickListener itemClickListener;

        MainViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemCLickListener(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new MainViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
        holder.cardView.setBackgroundColor(note.getColor());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface ItemClickListener {
        void onItemCLickListener(View view, int position);
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Note> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(fullNoteList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Note item : fullNoteList){
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            noteList.clear();
            noteList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
