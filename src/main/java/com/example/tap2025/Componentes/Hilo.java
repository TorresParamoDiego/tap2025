package com.example.tap2025.Componentes;


import javafx.scene.control.ProgressBar;

public class Hilo extends Thread {
    private ProgressBar ruta;
    public Hilo(String nombre,ProgressBar pgb) {
        super(nombre);
        ruta=pgb;
    }
    @Override
    public void run() {
        super.run();
        double avance=0;
        while (avance<1){
            avance+=Math.random()*0.01;
            this.ruta.setProgress(avance);
            try {
                sleep((long) (Math.random()*500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
Metodo anterior de run
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
*/