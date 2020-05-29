package com.istimeless.timecacheserver.application;

import com.istimeless.timecachecommon.properties.PropertiesManager;
import com.istimeless.timecachecommon.properties.TimeCacheProperties;
import com.istimeless.timecachecore.container.Container;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final TimeCacheProperties properties;

    public Application () {
        this.properties = PropertiesManager.getProperties();
    }

    public static void run() {
        new Application().runServer();
    }

    public void runServer() {
        log.info("Starting TimeCache Server");
        long start = System.currentTimeMillis();
        Container.init();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_BACKLOG, properties.getMaxConnections())
                    .childHandler(new ApplicationChannelInitializer())
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(properties.getPort()).sync();
            log.info("TimeCache server started on port: {}", properties.getPort());
            long end = System.currentTimeMillis();
            log.info("Started TimeCache server in {} seconds", (end - start) / 1000.0);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.info("TimeCache server shutdown");
        }
    }
}
