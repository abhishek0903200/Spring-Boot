package com.demo.first;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {
    public  void run(String args) throws Exception{
        System.out.println("Application has startedusinh command line runner");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
