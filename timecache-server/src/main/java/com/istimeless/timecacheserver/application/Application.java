package com.istimeless.timecacheserver.application;

import com.istimeless.timecachecommon.properties.PropertiesManager;
import com.istimeless.timecachecommon.properties.TimeCacheProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Application {

    private final TimeCacheProperties properties;

    public Application () {
        this.properties = PropertiesManager.getProperties();
    }



    public void runs() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            System.out.println("server start...");
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_BACKLOG, properties.getMaxConnections())
                    .childHandler(new ApplicationChannelInitializer())
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(properties.getPort()).sync();
            System.out.println("server start at port:" + properties.getPort());
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            System.out.println("server shutdown...");
        }
    }

    public static void run() {
        new Application().runs();
    }
}
