package com.self.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

import java.util.Date;

/**
 * Created by shaojieyue on 8/16/15.
 */
public class CommandHelloWorld extends HystrixCommand<String> {
    private String name;
    private static final Setter cachedSetter =
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorld"));
    public CommandHelloWorld(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        super(cachedSetter);
        this.name = name;
    }

    /**
     * a real example would do work like a network call here
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        if(System.currentTimeMillis()%2==0){
            throw new RuntimeException("ex");
        }
        return "hello "+name+" at "+new Date();
    }

    /**
     *You can support graceful degradation
     * in a Hystrix command by adding a fallback method
     * @return
     */
    @Override
    protected String getFallback() {
        return "call fail.";
    }

    @Override
    protected String getCacheKey() {
        return name;
    }
}
