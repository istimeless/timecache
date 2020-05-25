package com.istimeless.timecacheserver.application;

import com.istimeless.timecachecommand.CommandHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class ApplicationChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        String command = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("receive command: " + command);
        String result = CommandHandler.invokeCommand(command);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(result, StandardCharsets.UTF_8));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
