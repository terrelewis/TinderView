package com.tinderview.utils;

import android.content.Context;
import android.content.Intent;

public class IntentClass {

    /**
     * Create explicit intents
     */
    public void createIntent(Context from, Class to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }
}
