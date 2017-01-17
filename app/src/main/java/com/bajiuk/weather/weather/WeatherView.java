package com.bajiuk.weather.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bajiuk.weather.R;
import com.bajiuk.weather.utils.DaggerService;
import javax.inject.Inject;

public class WeatherView extends LinearLayout {

  @Inject Weather.Presenter presenter;
  @BindView(R.id.temperature) TextView temperatureText;
  @BindView(R.id.location) TextView locationText;

  public WeatherView(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<Weather.Component>getDaggerComponent(context, Weather.Component.class.getName()).inject(this);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    presenter.takeView(this);
  }

  @Override protected void onDetachedFromWindow() {
    presenter.dropView(this);
    super.onDetachedFromWindow();
  }

  public void setLocation(String location) {
    locationText.setText(location);
  }

  public void setTemperature(String temperature) {
    temperatureText.setText(temperature);
  }
}
