package com.istimeless.timecacheclient.command;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.nio.charset.StandardCharsets;

public class ClientCommand {

    private static Channel channel;

    public static void setChannel(Channel channel) {
        ClientCommand.channel = channel;
    }

    public ClientCommand() {

    }

    public void sendMessage(String message) {
       channel.writeAndFlush(Unpooled.copiedBuffer(message, StandardCharsets.UTF_8));
    }
}
