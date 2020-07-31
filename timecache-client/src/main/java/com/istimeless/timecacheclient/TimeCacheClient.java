package com.istimeless.timecacheclient;

import com.istimeless.timecacheclient.command.ClientCommand;
import com.istimeless.timecacheclient.command.StringOperate;
import com.istimeless.timecacheclient.connection.ClientConnection;

public class TimeCacheClient<T> {

    private StringOperate<T> stringOperate;

    public TimeCacheClient(String host, Integer port) {
        ClientConnection connection = new ClientConnection(host, port);
        ClientCommand.setChannel(connection.getChannel());
    }

    public StringOperate<T> stringOperate() {
        if (stringOperate == null) {
            stringOperate = new StringOperate<>();
        }
        return stringOperate;
    }
}
