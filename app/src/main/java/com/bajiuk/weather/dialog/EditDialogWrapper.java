package com.bajiuk.weather.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import com.bajiuk.weather.R;

public class EditDialogWrapper {

  public static final String DIALOG_SHOWING = "D_SHOWING";
  public static final String DIALOG_TEXT = "D_TEXT";

  private int title;
  private String text = "";
  private boolean isShowing = false;
  private DialogListener listener;

  public EditDialogWrapper(int title, DialogListener listener) {
    this.title = title;
    this.listener = listener;
  }

  public void onSaveInstanceState(Bundle state) {
    state.putBoolean(DIALOG_SHOWING, isShowing);
    state.putString(DIALOG_TEXT, text);
  }

  public void onRestoreInstanceState(Bundle state) {
    text = state.getString(DIALOG_TEXT);
    isShowing = state.getBoolean(DIALOG_SHOWING);
  }

  public void onResume(Context context) {
    if (isShowing) {
      show(context);
    }
  }

  public void show(Context context) {
    isShowing = true;
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(title);
    final EditText input = new EditText(context);
    input.setText(text);
    input.post(() -> input.setSelection(text.length()));
    input.setSelection(input.getText().length());
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    input.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        text = s.toString();
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
    builder.setView(input);
    builder.setPositiveButton(R.string.ok, (dialog, which) -> {
      if (listener != null) {
        listener.onOkDialog(input.getText().toString());
      }
    });
    builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());
    builder.setOnDismissListener(dialog -> {
      isShowing = false;
      text = "";
    });
    builder.show();
  }

  public interface DialogListener {
    void onOkDialog(String text);
  }
}
