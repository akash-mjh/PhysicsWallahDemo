package com.physicswallahdemo

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle

class WidgetConfigActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Replace MyCustomWidget::class.java with your actual widget class
        val widgetClass = MyCustomWidget::class.java
        val widgetManager = AppWidgetManager.getInstance(this)
        val widgetId = widgetManager.getAppWidgetIds(
            ComponentName(this, widgetClass)
        ).firstOrNull()

        // Launch widget configuration screen for the specific widget
        if (widgetId != null) {
            val resultValue = Intent()
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            setResult(RESULT_OK, resultValue)
            val configureIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE)
            configureIntent.component = ComponentName(this, widgetClass)
            configureIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            startActivityForResult(configureIntent, 0)
        }

        finish()
    }
}