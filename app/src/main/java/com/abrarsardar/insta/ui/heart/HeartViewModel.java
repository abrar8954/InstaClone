package com.abrarsardar.insta.ui.heart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HeartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public HeartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is heart fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}