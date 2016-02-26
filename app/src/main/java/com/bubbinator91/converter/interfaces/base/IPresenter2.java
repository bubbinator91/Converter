package com.bubbinator91.converter.interfaces.base;

import java.util.List;

public interface IPresenter2<V extends IView2> {
    void onResume();

    void onPause();

    void registerView(V view);

    void unregisterView();

    void updateModel(List<String> valuesList);
}
