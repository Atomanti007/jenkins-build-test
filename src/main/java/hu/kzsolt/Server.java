package hu.kzsolt;

import com.google.inject.Guice;

public class Server {

    public static void main(String[] args) {

        System.out.println("Test");
        Guice.createInjector();
    }
}
