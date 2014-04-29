/*
 * GoogleGlassFirstSteps_Demo4
 * Copyright (C) 2014.  Rayco Araña (http://raycoarana.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.raycoarana.glass.demo4;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.RecognizerIntent;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;

import java.util.ArrayList;

public class Demo4Service extends Service {

    private static final String LIVE_CARD_TAG = "demo_4_card";

    private LiveCard mLiveCard;
    private RemoteViews mRemoteViews;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<String> voiceResults = intent.getExtras().getStringArrayList(RecognizerIntent.EXTRA_RESULTS);

        StringBuilder userContent = new StringBuilder();
        for (String voiceToken : voiceResults) {
            userContent.append(voiceToken);
            userContent.append(" ");
        }

        boolean haveToPublish = false;
        if (mLiveCard == null) {
            mLiveCard = new LiveCard(this, "simple-card");
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, new Intent(this, MenuActivity.class), 0));
            mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_of_my_livecard);
            haveToPublish = true;
        }

        mRemoteViews.setTextViewText(R.id.my_card_content, userContent);
        mLiveCard.setViews(mRemoteViews);

        if(haveToPublish) {
            mLiveCard.publish(LiveCard.PublishMode.REVEAL);
        } else {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }
}
