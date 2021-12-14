package com.santimattius.template.core.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(url: String) {
    if (!url.startsWith("http://") && !url.startsWith("https://")) return
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    this.startActivity(browserIntent)
}