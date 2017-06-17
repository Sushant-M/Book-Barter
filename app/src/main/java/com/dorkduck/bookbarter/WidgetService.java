package com.dorkduck.bookbarter;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;

import static com.dorkduck.bookbarter.MainActivity.MYPREF;

/**
 * Created by sushant on 7/6/17.
 */

public class WidgetService extends RemoteViewsService {

    final String TAG = getClass().getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return new RemoteViewsFactory() {
            ArrayList<String> ourList = new ArrayList<>();

            @Override
            public void onCreate() {
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

                for (int i = 0; i < sharedPreferences.getInt("StringArrayLength",0); i++) {
                    ourList.add(sharedPreferences.getString("StringArrayElement" +i, ""));
                }
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
                Log.d("LOL","HELLO WIDGET");
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
                Log.d("LOL","Something must be working.");
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
