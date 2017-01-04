package com.unidadcoronaria.prestaciones.app;

import com.unidadcoronaria.domain.model.DeviceMessage;

import java.util.List;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public interface MessageView extends View {

    void onMessageListReceived(List<DeviceMessage> messageList);

    void onMessageSendReceived(DeviceMessage message);
}
