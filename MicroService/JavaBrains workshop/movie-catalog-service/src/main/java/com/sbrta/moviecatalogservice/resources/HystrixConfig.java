/*
package com.sbrta.moviecatalogservice.resources;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @author SbrTa
 * @since 8/4/2020  1:11 AM
 *//*


@Configuration
public class HystrixConfig {

    @Autowired
    public void configHystrixPlugin() {
        HystrixPlugins.getInstance().registerCommandExecutionHook(new HystrixCommandExecutionHook() {
            @Override
            public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
                if (commandInstance instanceof HystrixInvokableInfo) {
                    HystrixInvokableInfo invokableInfo = (HystrixInvokableInfo) commandInstance;
                    //do something with specfied exception here
                    System.out.println("\n\nHYSTRIX EXCEPTION :: " + e.getMessage() + "\n\n");
                }
                return e;
            }
        });
    }
}*/
