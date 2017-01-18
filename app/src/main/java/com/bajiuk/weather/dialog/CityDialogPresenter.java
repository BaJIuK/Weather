package com.bajiuk.weather.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;
import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.fab.FabPresenter;
import javax.inject.Inject;

public class CityDialogPresenter implements MvpPresenter<CityDialogView> {

  @Inject FabPresenter presenter;

  private Context context;

  public CityDialogPresenter(Context context) {
    this.context = context;
  }

  public void showDialog(String title) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(title);
    final EditText input = new EditText(context);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("OK",
                              (dialog, which) -> presenter.addCity(input.getText().toString()));
    builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
    builder.show();
  }

  @Override public void attach(CityDialogView view) {

  }

  @Override public void detach() {

  }
}
