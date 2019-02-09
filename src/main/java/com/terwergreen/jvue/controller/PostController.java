package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章
 *
 * @author Terwer
 * @version 1.0
 * 2019/2/1 12:04
 **/
@Controller
public class PostController {
    @Autowired
    private VueRenderer vueRenderer;

    @RequestMapping("/post/{id}.html")
    public String post(Model model, @PathVariable String id) {
        // 设置路由上下文
        Map<String, Object> context = new HashMap<>();
        context.put("url", String.format("/post/%s.html", id));

        Map<String, Object> resultMap = vueRenderer.renderContent(context);
        model.addAllAttributes(resultMap);
        return "index";
    }
}