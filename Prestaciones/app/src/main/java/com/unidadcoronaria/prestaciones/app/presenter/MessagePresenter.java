package com.unidadcoronaria.prestaciones.app.presenter;

import android.content.Context;

import com.unidadcoronaria.domain.usecase.GetMessageUseCase;
import com.unidadcoronaria.prestaciones.app.MessageView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MessagePresenter extends BasePresenter<MessageView>  {

    private Context mContext;
    private GetMessageUseCase mGetMessageUseCase;

    public MessagePresenter(MessageView view, Context context) {
        super(view);
        this.mContext = context;
        this.mGetMessageUseCase = new GetMessageUseCase();
    }

    public void getList(){
        view.showLoading();
        mGetMessageUseCase.execute(mContext);
    }

    @Subscribe
    public void onMessageListReceived(GetMessageUseCase.SuccessResponse response){
        view.onMessageListReceived(response.getMessageList());
        view.hideLoading();
    }
}
