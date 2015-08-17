package com.self.hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;


/**
 * Created by shaojieyue on 8/16/15.
 */
public class ObservableCommandHelloWorld extends HystrixObservableCommand {
    private String name;

    public ObservableCommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        this.name = name;
    }

    @Override
    protected Observable construct() {
        return null;
    }
}
