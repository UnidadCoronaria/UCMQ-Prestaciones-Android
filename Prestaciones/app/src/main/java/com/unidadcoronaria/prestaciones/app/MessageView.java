package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.Message;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public interface MessageView extends View {

    void onMessageListReceived(List<Message> messageList);
}
