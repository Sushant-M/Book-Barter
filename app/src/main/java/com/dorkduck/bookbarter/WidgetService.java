package com.dorkduck.bookbarter;

import android.appwidget.AppWidgetManager;
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

import java.util.ArrayList;

/**
 * Created by sushant on 7/6/17.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);


        return new RemoteViewsFactory() {
            final String TAG = getClass().getSimpleName();
            ArrayList<String> ourList = new ArrayList<>();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("books");

            @Override
            public void onCreate() {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<ArrayList<String>> type = new GenericTypeIndicator<ArrayList<String>>(){};
                        ourList  = dataSnapshot.getValue(type);
                        if(ourList != null) {
                            Log.d(TAG, ourList.get(0));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onDataSetChanged() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                Log.d("ATTENTION HERE",String.valueOf(ourList.size()));
                return ourList.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                Log.d(TAG,"HELLO WIDGET");
                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.simple_book_view);
                remoteViews.setTextViewText(R.id.book_name,ourList.get(position));
                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                Log.d(TAG,"Something must be working.");
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
