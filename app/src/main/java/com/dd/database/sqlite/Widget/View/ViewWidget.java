package com.dd.database.sqlite.Widget.View;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.dd.database.sqlite.DatabaseOpenHelper;
import com.dd.database.sqlite.R;
import com.dd.database.sqlite.Widget.Presenter.IPresenterWidget;
import com.dd.database.sqlite.Widget.Presenter.PresenterWidget;

public class ViewWidget extends AppWidgetProvider implements IViewWidget {

    IPresenterWidget iPresenterWidget;
    DatabaseOpenHelper openHelper;
    private static final String SYNC_CLICKED = "automaticWidgetSyncButtonClick";


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        openHelper = new DatabaseOpenHelper(context);

        iPresenterWidget = new PresenterWidget(this, openHelper);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        openHelper = new DatabaseOpenHelper(context);
        iPresenterWidget = new PresenterWidget(this, openHelper);


        if (SYNC_CLICKED.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            ComponentName watchWidget = new ComponentName(context, ViewWidget.class);
            remoteViews.setTextViewText(R.id.appwidget_text, iPresenterWidget.getRandomMakalFromDatabase());
            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        openHelper = new DatabaseOpenHelper(context);
        iPresenterWidget = new PresenterWidget(this, openHelper);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        //setting text to widget TextView
        views.setTextViewText(R.id.appwidget_text, iPresenterWidget.getRandomMakalFromDatabase());
        views.setOnClickPendingIntent(R.id.appwidget_text, getPendingSelfIntent(context, SYNC_CLICKED));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}