package com.dorkduck.bookbarter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sushant on 30/5/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {
    ArrayList<String> our_list = new ArrayList<>();

    public BooksAdapter(ArrayList<String> list){
        this.our_list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView book_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            book_name = (TextView) itemView.findViewById(R.id.book_name);
        }
    }

    @Override
    public BooksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_book_view,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = our_list.get(position);
        holder.book_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return our_list.size();
    }
}
