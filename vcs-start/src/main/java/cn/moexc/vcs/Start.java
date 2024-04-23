package cn.moexc.vcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        try{
            SpringApplication.run(Start.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
