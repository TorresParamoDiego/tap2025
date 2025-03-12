package com.example.tap2025.Componentes;


import java.util.Random;

public class Hilo extends Thread {
    public Hilo(String nombre) {
        super(nombre);
    }
    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 10; i++) {
            try {
                sleep(new Random().nextLong(0,3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("El corredor "+this.getName()+" llego al kilometro "+ i);
        }
    }
}
