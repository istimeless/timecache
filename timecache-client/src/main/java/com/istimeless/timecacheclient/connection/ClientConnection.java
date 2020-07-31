package com.istimeless.timecacheclient.connection;

import com.istimeless.timecacheclient.handler.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnection {

    private static final Logger log = LoggerFactory.getLogger(ClientConnection.class);

    private String host;

    private Integer port;

    private Channel channel;

    public ClientConnection(String host, Integer port) {
        this.host = host;
        this.port = port;
        connect();
    }

    private void connect() {
        log.info("Starting TimeCache client");
        long start = System.currentTimeMillis();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();
            long end = System.currentTimeMillis();
            log.info("Started TimeCache client in {} seconds", (end - start) / 1000.0);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
