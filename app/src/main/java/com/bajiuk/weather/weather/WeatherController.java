package com.bajiuk.weather.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ButterKnifeController;
import com.bajiuk.weather.utils.BundleBuilder;
import com.bajiuk.weather.weather.di.DaggerWeatherComponent;
import com.bajiuk.weather.weather.di.WeatherComponent;
import javax.inject.Inject;

public class WeatherController extends ButterKnifeController<WeatherComponent>
    implements WeatherView {

  public static final String KEY_CITY = "K_CITY";

  @Inject WeatherPresenter presenter;
  @BindView(R.id.location) TextView locationText;
  @BindView(R.id.temperature) TextView temperatureText;

  private String cityName;

  public WeatherController(String cityName) {
    this(new BundleBuilder(new Bundle()).putString(KEY_CITY, cityName).build());
  }

  public WeatherController(Bundle bundle) {
    super(bundle);
    cityName = bundle.getString(KEY_CITY);
  }

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_weather, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    getComponent().inject(this);
  }

  @Override protected void onAttach(@NonNull View view) {
    super.onAttach(view);
    presenter.attach(this);
    presenter.load(cityName);
  }

  @Override protected void onDetach(@NonNull View view) {
    presenter.detach();
    super.onDetach(view);
  }

  @Override protected WeatherComponent buildComponent() {
    return DaggerWeatherComponent.builder()
        .applicationComponent(WeatherApplication.getAppComponent())
        .build();
  }

  @Override public void setData(WeatherData data) {
    temperatureText.setText("" + (data.getTemperature() - 273.15) + "\u2103");
    locationText.setText(data.getLocation());
  }
}
