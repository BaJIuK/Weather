package com.bajiuk.weather.home;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bajiuk.weather.R;
import com.bajiuk.weather.base.ButterKnifeController;

public class HomeController<String> extends ButterKnifeController {

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_weather, container, false);
  }

  @Override protected Object buildComponent() {
    return "OK";
  }
}
