package com.bajiuk.weather.dialog.di;

import android.content.Context;
import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.dialog.CityDialogPresenter;
import dagger.Module;
import dagger.Provides;

@PerController @Module public class DialogModule {
  @PerController @Provides CityDialogPresenter provideDialogModule(Context context) {
    return new CityDialogPresenter(context);
  }
}
