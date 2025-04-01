package controller;
import java.util.concurrent.Semaphore;
import java.util.Random;
public class cooking extends Thread{
	private final int id;
	private final String name;
	private final long cookingtime;
	private static Semaphore semaforo = new Semaphore(1);
	private static final Random random = new Random();
	
	public cooking(int id) {
		this.id = id;
		if(id %2 == 0) {
			this.name = "Lasanha à Bolonhesa";
		} else {
			this.name = "Sopa de Cebola";
		}
		this.cookingtime = (id%2 ==0)? 600 + random.nextInt(601): 500 + random.nextInt(301);
		
	}
	
	private void progress() throws InterruptedException{
		long breaks = cookingtime / 100;
		for(int i = 1; i <= breaks ; i++) {
			Thread.sleep(100);
            System.out.println("Prato " + id + " (" + name + ") - Cozimento: " + (i * 100 / breaks) + "% concluído.");
        }
	}
	
	private void delivery() throws InterruptedException{
		semaforo.acquire();
		System.out.println("Prato " + id + " (" + name + ") sendo entregue...");
		Thread.sleep(500);
		System.out.println("Prato " + id + " (" + name + ") entregue!");
		semaforo.release();
	}
	@Override
	public void run() {
		try {
			System.out.println("Prato " + id + " (" + name + ") iniciando cozimento.");
            progress();
            System.out.println("Prato " + id + " (" + name + ") pronto!");
            delivery();
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
