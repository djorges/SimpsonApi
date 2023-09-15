package com.example.simpsonapi.data.service;

import android.content.ClipData;
import android.content.ClipboardManager;

import javax.inject.Inject;

public class ClipboardService {
    private ClipboardManager clipboardManager;

    @Inject
    public ClipboardService(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public void copyQuoteToClipboard(String quote){
        ClipData data = ClipData.newPlainText("Quote", quote);
        clipboardManager.setPrimaryClip(data);
    }
}
