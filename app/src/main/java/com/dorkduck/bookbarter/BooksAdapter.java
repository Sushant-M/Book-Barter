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
    ArrayList<String> ourList = new ArrayList<>();

    public BooksAdapter(ArrayList<String> list){
        this.ourList = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView bookName;

        public MyViewHolder(View itemView) {
            super(itemView);
            bookName = (TextView) itemView.findViewById(R.id.book_name);
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
        String name = ourList.get(position);
        holder.bookName.setText(name);
    }

    @Override
    public int getItemCount() {
        if(ourList!=null){
            return ourList.size();
        }
        return 0;
    }
}
