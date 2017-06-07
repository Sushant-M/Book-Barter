package com.dorkduck.bookbarter;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 7/6/17.
 */

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context = null;
    private int appWidgetID = 0;
    List<String> ourList;

    public ListProvider(Context context_var, Intent intent){
        context = context_var;
        appWidgetID =intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("books");
        GenericTypeIndicator<ArrayList<String>> type = new GenericTypeIndicator<ArrayList<String>>(){};
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>>  type = new GenericTypeIndicator<ArrayList<String>>(){};
                ourList = dataSnapshot.getValue(type);
                Log.d("INSIDE THE DAMN SERVICE",ourList.get(2));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ourList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.simple_book_view);
        views.setTextViewText(R.id.book_name,ourList.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
