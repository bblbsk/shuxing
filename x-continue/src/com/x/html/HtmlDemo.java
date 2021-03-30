package com.x.html;

import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;

public class HtmlDemo {

    public static void main(String[] args) {
        HtmlParser htmlParser = new HtmlParserImpl();
        htmlParser.loadURI("https://www.baidu.com");
        // html 是我的html代码
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        imageRenderer.saveImage("/tmp/" + System.currentTimeMillis() + ".png");


    }
}
