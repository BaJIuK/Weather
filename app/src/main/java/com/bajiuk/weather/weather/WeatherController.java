package com.bajiuk.weather.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ViewModelController;
import com.bajiuk.weather.utils.BundleBuilder;
import com.bajiuk.weather.weather.di.DaggerWeatherComponent;
import com.bajiuk.weather.weather.di.WeatherComponent;
import javax.inject.Inject;

public class WeatherController extends ViewModelController<WeatherComponent, WeatherViewModel>
    implements WeatherView {

  private static final String KEY_CITY = "K_CITY";

  @Inject WeatherPresenter presenter;
  @BindView(R.id.location) TextView locationText;
  @BindView(R.id.temperature) TextView temperatureText;
  @BindView(R.id.progress) ProgressBar progressBar;
  @BindView(R.id.error_layout) View errorLayout;
  @BindView(R.id.error_text) TextView errorText;
  @BindView(R.id.error_button) TextView errorButton;

  private String cityName;

  public WeatherController(String cityName) {
    this(new BundleBuilder(new Bundle()).putString(KEY_CITY, cityName).build());
  }

  public WeatherController(Bundle bundle) {
    super(bundle);
    cityName = bundle.getString(KEY_CITY);
    getComponent().inject(this);
    presenter.attach(this);
  }

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_weather, container, false);
  }

  @Override protected void loadData() {
    presenter.load(cityName);
  }

  @Override protected WeatherComponent buildComponent() {
    return DaggerWeatherComponent.builder()
        .applicationComponent(WeatherApplication.getAppComponent())
        .build();
  }

  @Override public void showContent() {
    showLoading();
    showData();
    showError();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detach();
  }

  @OnClick(R.id.error_button) protected void onErrorClicked() {
    if (data.getErrorType() == WeatherViewModel.ErrorType.NETWORK) {
      presenter.load(cityName);
    }
  }

  private void showLoading() {
    boolean visible = (data.getState() == WeatherViewModel.State.LOADING);
    progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

  private void showError() {
    boolean visible = (data.getState() == WeatherViewModel.State.ERROR);
    errorLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    if (visible) {
      boolean isConnection = data.getErrorType() == WeatherViewModel.ErrorType.NETWORK;
      String messageText = getActivity().getString(
          isConnection ? R.string.error_connection : R.string.error_location);
      String buttonText =
          getActivity().getString(isConnection ? R.string.error_retry : R.string.error_request);
      errorText.setText(messageText);
      errorButton.setText(buttonText);
    }
  }

  private void showData() {
    boolean visible = (data.getState() == WeatherViewModel.State.LOADED);
    temperatureText.setVisibility(visible ? View.VISIBLE : View.GONE);
    locationText.setVisibility(visible ? View.VISIBLE : View.GONE);
    locationText.setText(data.getLocation());
    temperatureText.setText(data.getFormattedTemperature());
  }

  public static WeatherController getControllerForCity(String name, WeatherController instance) {
    if (instance != null && instance.cityName.equals(name)) {
      return instance;
    } else {
      return new WeatherController(name);
    }
  }
}
