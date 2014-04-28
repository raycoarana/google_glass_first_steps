/*
 * GoogleGlassFirstSteps_Demo3
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

package com.raycoarana.glass.demo3;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class CardsActivity extends Activity implements AdapterView.OnItemClickListener {

    private CardScrollView mCardScrollView;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mCardScrollView = new CardScrollView(this);
        mCardScrollView.setAdapter(mCardScrollAdapter);
        mCardScrollView.setOnItemClickListener(this);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
    }

    CardScrollAdapter mCardScrollAdapter = new CardScrollAdapter() {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public int getPosition(Object o) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View cardView;
            switch (i) {
                case 0:
                    cardView = getFirstCardView();
                    break;
                case 1:
                    cardView = getSecondCardView();
                    break;
                case 2:
                    cardView = getThirdCardView();
                    break;
                default: //case 3:
                    cardView = getFourthCardView();
                    break;
            }
            return cardView;
        }

    };

    private View getFirstCardView() {
        Card card = new Card(this);
        card.setText("Tarjeta simple de texto");
        return card.getView();
    }

    private View getSecondCardView() {
        Card card = new Card(this);
        card.setText("Tarjeta con imagen a la izquierda");
        card.setImageLayout(Card.ImageLayout.LEFT);
        card.addImage(R.drawable.audi);
        return card.getView();
    }

    private View getThirdCardView() {
        Card card = new Card(this);
        card.setText("Tarjeta con imagen al fondo");
        card.setImageLayout(Card.ImageLayout.FULL);
        card.addImage(R.drawable.opel);
        return card.getView();
    }

    private View getFourthCardView() {
        Card card = new Card(this);
        card.setText("Tarjeta con texto al pie y varias imagenes");
        card.setFootnote("Texto pie de página");
        card.setImageLayout(Card.ImageLayout.LEFT);
        card.addImage(R.drawable.audi);
        card.addImage(R.drawable.opel);
        return card.getView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            mAudioManager.playSoundEffect(Sounds.DISALLOWED);
        } else {
            mAudioManager.playSoundEffect(Sounds.TAP);
            this.openOptionsMenu();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_read_aloud:
                onMenuReadAloud();
                break;
            case R.id.menu_share:
                onMenuShare();
                break;
            case R.id.menu_close:
                onMenuClose();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void onMenuReadAloud() {
        Log.i("DEMO3", "onMenuReadAloud()");
    }

    private void onMenuShare() {
        Log.i("DEMO3", "onMenuShare()");
    }

    private void onMenuClose() {
        Log.i("DEMO3", "onMenuClose()");
    }

}
