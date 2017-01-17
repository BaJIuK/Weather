package com.bajiuk.weather.fab;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.bajiuk.weather.R;
import com.bajiuk.weather.WeatherApplication;
import com.bajiuk.weather.base.ButterKnifeController;
import com.bajiuk.weather.fab.di.DaggerFabComponent;
import com.bajiuk.weather.fab.di.FabComponent;
import javax.inject.Inject;

/**
 * Created by Valentin on 18.01.2017.
 */

public class FabController extends ButterKnifeController<FabComponent> {

  @Inject FabPresenter presenter;

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.view_add_city, container, false);
  }

  @Override protected void onViewBound(@NonNull View view) {
    super.onViewBound(view);
    getComponent().inject(this);
  }

  @Override protected FabComponent buildComponent() {
    return DaggerFabComponent.builder()
        .applicationComponent(WeatherApplication.getAppComponent())
        .build();
  }

  @OnClick(R.id.fab) protected void onAddCity() {
    showDialog();
  }

  protected void showDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Title");
    final EditText input = new EditText(getActivity());
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("OK",
        (dialog, which) -> presenter.addCity(input.getText().toString()));
    builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
    builder.show();
  }
}
