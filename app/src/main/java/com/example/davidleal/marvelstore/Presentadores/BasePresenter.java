package com.example.davidleal.marvelstore.Presentadores;

import com.example.davidleal.marvelstore.Interfaces.IBaseView;

import rx.Subscription;


/**
 * Created by DavidLeal
 */
public class BasePresenter {

    public IBaseView mBaseView;
    public Subscription observableSubscription;

    public BasePresenter() {
    }

    public void cancelarObservables() {
        if (observableSubscription != null && !observableSubscription.isUnsubscribed()) {
            observableSubscription.unsubscribe();
        }
    }
}
