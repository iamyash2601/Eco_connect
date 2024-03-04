package com.example.eco_connect.ui.eco_quest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EcoQuestViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public EcoQuestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ecoquest fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}