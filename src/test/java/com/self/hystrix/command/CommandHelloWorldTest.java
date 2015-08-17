package com.self.hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by shaojieyue on 8/17/15.
 */
public class CommandHelloWorldTest {
    public static void main(String[] args) throws Exception {
//        async();
//        sync();
        reqCache();
    }
    public static final void async() throws ExecutionException, InterruptedException {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("command");
        final Future<String> queue = commandHelloWorld.queue();
        System.out.println(queue.get());
    }

    public static final void sync() throws Exception {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("command");
        System.out.println(commandHelloWorld.execute());
    }

    public static final void reqCache(){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        CommandHelloWorld commandHelloWorld1 = new CommandHelloWorld("aa");
        CommandHelloWorld commandHelloWorld2 = new CommandHelloWorld("bb");
        CommandHelloWorld commandHelloWorld3 = new CommandHelloWorld("aa");
        commandHelloWorld1.execute();
        commandHelloWorld2.execute();
        commandHelloWorld3.execute();
        System.out.println(commandHelloWorld1.isResponseFromCache());
        System.out.println(commandHelloWorld2.isResponseFromCache());
        System.out.println(commandHelloWorld3.isResponseFromCache());
    }
}
