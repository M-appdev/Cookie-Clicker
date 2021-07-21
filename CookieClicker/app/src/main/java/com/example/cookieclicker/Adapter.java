package com.example.cookieclicker;

import android.content.Context;
import android.media.Image;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private RecyclerViewClickListener listener;

    int[] opcije = {1, 3, 5, 10, 30, 60};

    public Adapter(int[] opcije,RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_selection_design, parent, false);
        ViewHolder holder = new ViewHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position)  {
        holder.textView.setText("Timer for " + opcije[position] + " seconds");
        holder.button.setText("Start");

    }

    @Override
    public int getItemCount() {
        return opcije.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private Button button;
        private RecyclerViewClickListener listener;

        public ViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            this.listener=listener;
            textView = itemView.findViewById(R.id.tv_5s);
            button = itemView.findViewById(R.id.btn_5s);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onButtonClick(v, getAdapterPosition());
        }
    }


    public interface RecyclerViewClickListener {
        void onButtonClick(View v, int position);
    }
}
