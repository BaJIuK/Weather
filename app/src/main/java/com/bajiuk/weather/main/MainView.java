/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bajiuk.weather.main;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bajiuk.weather.R;
import com.bajiuk.weather.weather.WeatherView;

public class MainView extends LinearLayout {

  @BindView(R.id.text) TextView textView;
  @BindView(R.id.weather) WeatherView weatherView;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    //presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    //presenter.dropView(this);
    super.onDetachedFromWindow();
  }

  public void show(CharSequence stuff) {
    textView.setText(stuff);
  }

}
