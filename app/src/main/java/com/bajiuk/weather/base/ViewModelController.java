package com.bajiuk.weather.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public abstract class ViewModelController<T, M extends MvpViewModel>
    extends ButterKnifeController<T> implements MvpView<M> {

  private static final String KEY_DATA = "K_DATA";
  protected M data;

  public ViewModelController(){
  }
  public ViewModelController(Bundle bundle) {
    super(bundle);
  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(KEY_DATA, data);
  }

  @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    data = (M) savedInstanceState.getSerializable(KEY_DATA);
  }

  @Override public void setData(M data) {
    this.data = data;
    if (isAttached() && hasData()) {
      showContent();
    }
  }

  protected boolean hasData() {
    return data != null && !data.isEmpty();
  }

  @Override protected void onAttach(@NonNull View view) {
    super.onAttach(view);
    if (hasData()) {
      showContent();
    } else {
      loadData();
    }
  }

  protected abstract void loadData();
}
