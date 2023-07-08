package popravnilabV1;

import java.awt.*;
import java.util.Random;

public class Basta extends Panel implements Runnable {

	private Rupa[][] matricaRupa;
	protected int povrce = 100;
	protected Thread nitBaste;
	private int interval;
	private int brojKoraka;
	private int vrste, kolone;
	private Igra mojaIgra;

	public Basta(int v, int k, Igra igra) {
		matricaRupa = new Rupa[v][k];
		vrste = v;
		kolone = k;
		mojaIgra = igra;
		this.setBackground(Color.GREEN);
		this.setLayout(new GridLayout(v, k, 15, 15));
		for (int i = 0; i < v; i++)
			for (int j = 0; j < k; j++) {
				matricaRupa[i][j] = new Rupa(this);
				add(matricaRupa[i][j]);
			}
		nitBaste = new Thread(this);
	}

	public void smanjiPovrce() {
		povrce--;
		mojaIgra.azurirajPovrce(povrce);
		if (povrce == 0)
			this.zaustaviBastu();
	}

	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
		for (int i = 0; i < matricaRupa.length; i++)
			for (int j = 0; j < matricaRupa[0].length; j++) {
				matricaRupa[i][j].setKoraci(brojKoraka);
			}
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void run() {
		while (true) {
			Random broj = new Random();
			int x = broj.nextInt(matricaRupa.length);
			int y = broj.nextInt(matricaRupa[0].length);
			if (matricaRupa[x][y].getNitRupe() != null)
				continue;
			matricaRupa[x][y].setZivuljka(new Krtica(matricaRupa[x][y]));
			matricaRupa[x][y].pokreniNit();
			iscrtajBastu();
			repaint();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				break;
			}
			interval -= 0.01 * interval;
			iscrtajBastu();
			repaint();
		}
	}

	public void iscrtajBastu() {
		for (int i = 0; i < vrste; i++)
			for (int j = 0; j < kolone; j++)
				repaint();
	}

	public boolean pokrenutaBasta() {
		if (nitBaste.isAlive())
			return true;
		return false;
	}

	public void pokreniBastu() {
		nitBaste.start();
	}

	public void zaustaviBastu() {
		for (Rupa[] r : matricaRupa) {
			for (Rupa rr : r) {
				rr.zaustaviNit();
			}
		}
		nitBaste.interrupt();
		repaint();
	}

	public void napraviNitBaste() {
		nitBaste = new Thread(this);
	}
}
