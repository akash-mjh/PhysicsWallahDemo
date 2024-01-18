package com.physicswallahdemo

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyCustomWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Perform operations when the widget is updated
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        // Create a RemoteViews object and set it as the widget's view
        val views = RemoteViews(context.packageName, R.layout.widget_layout)

        val viewModel = AppViewModelProvider.getMainViewModel()

        viewModel.currentDateTime.observeForever { currentTime ->
            views.setTextViewText(R.id.time_date_text, currentTime)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

      /*  viewModel.currentQuote.observeForever { currentQuote ->
            views.setTextViewText(R.id.txtQuote, currentQuote.text)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }*/

        viewModel.currentDayofWeek.observeForever { dayOfWeek ->
            // Use when statement to perform actions based on the day of the week
            when (dayOfWeek) {
                monday -> {
                    views.setInt(
                        R.id.txtSunday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotMonday, ".")
                }

                tuesday -> {
                    views.setInt(
                        R.id.txtMonday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotTuesday, ".")
                }

                wednesday -> {
                    views.setInt(
                        R.id.txtTuesday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotWednesday, ".")
                }

                thursday -> {
                    views.setInt(
                        R.id.txtWednesday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotThursday, ".")
                }

                friday -> {
                    views.setInt(
                        R.id.txtThursday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotFriday, ".")
                }

                saturday -> {
                    views.setInt(
                        R.id.txtFriday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotSaturday, ".")
                }

                sunday -> {
                    views.setInt(
                        R.id.txtSaturday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                    views.setTextViewText(R.id.dotSunday, ".")
                }

                else -> {
                    views.setTextViewText(R.id.dotMonday, "")
                    views.setTextViewText(R.id.dotTuesday, "")
                    views.setTextViewText(R.id.dotWednesday, "")
                    views.setTextViewText(R.id.dotThursday, "")
                    views.setTextViewText(R.id.dotFriday, "")
                    views.setTextViewText(R.id.dotSaturday, "")
                    views.setTextViewText(R.id.dotSunday, "")
                    views.setInt(
                        R.id.txtMonday,
                        "setBackgroundResource",
                        R.drawable.highlight_circle_bg
                    )
                }
            }

            // Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)

        }
    }

    companion object{
        const val monday = "Monday"
        const val tuesday = "Tuesday"
        const val wednesday = "Wednesday"
        const val thursday = "Thursday"
        const val friday = "Friday"
        const val saturday = "Saturday"
        const val sunday = "Sunday"
    }
}
