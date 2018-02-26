package com.yang.testpiwik.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/2/11.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //    Logger log = Logger.getLogger(this.getClass().getName());
    private Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/index")
    public String list(HttpServletRequest request) {
        return "index";
    }


    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test(String hostPort) {
        log.info("test");
        return (hostPort != null && hostPort.length() != 0)
                ? test3(hostPort)
                : test2();
    }

    private Map<String, String> test3(String hostPort) {
        String url = (hostPort.split(",").length > 1)
                ? "http://" + hostPort.split(",")[0] + "/test?hostPort=" + hostPort.substring(hostPort.indexOf(",") + 1)
                : "http://" + hostPort + "/test";
        return test1(url);
    }

    private Map<String, String> test1(String url) {
        log.info("继续请求远程应用：" + url);
        log.info("PtxId: " + MDC.get("PtxId") + " | PspanId: " + MDC.get("PspanId"));
        Map<String, String> json = restTemplate.getForEntity(url, Map.class).getBody();
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("state", "success " + sdf.format(new Date()));
        dataMap.put("msg", (String) MDC.get("PtxId"));
        return dataMap;
    }

    public Map<String, String> test2() {
        log.info("直接处理请求！");
        log.info("PtxId: " + MDC.get("PtxId") + " | PspanId: " + MDC.get("PspanId"));
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("state", "success " + sdf.format(new Date()));
        dataMap.put("msg", (String) MDC.get("PtxId"));
        return dataMap;
    }
}
