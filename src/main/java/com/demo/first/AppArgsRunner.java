package com.demo.first;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class AppArgsRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Option : "+ args.getOptionNames());
    }
}
