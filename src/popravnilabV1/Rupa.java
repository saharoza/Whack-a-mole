package popravnilabV1;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rupa extends Canvas implements Runnable {

	protected Basta mojaBasta;
	private Zivotinja zivuljka;
	private Thread nitRupe;
	protected int koraci, trenutniKorak;
	protected boolean slobodnaRupa;

	public Rupa(Basta b) {
		mojaBasta = b;
		this.setBackground(Color.getHSBColor((float) 0.1, (float) 0.57, (float) 0.35));
		slobodnaRupa = true;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (zivuljka != null)
					zivuljka.efekatUdarene();
				repaint();
			}
		});
		// zivuljka.iscrtajZivotinju();
		this.setVisible(true);
	}

	public Zivotinja getZivuljka() {
		return zivuljka;
	}

	public void setZivuljka(Zivotinja zivuljka) {
		this.zivuljka = zivuljka;
		// repaint();
	}

	public void zgaziRupu() {
		if (zivuljka != null)
			zivuljka.efekatUdarene();
		slobodnaRupa = true;
		// repaint();
	}

	/*
	 * public void iscrtajRupu() { //System.out.println("crtam rupu"); if(zivuljka
	 * != null) zivuljka.iscrtajZivotinju(); //repaint(); }
	 */

	public boolean rupaPokrenuta() {
		return nitRupe.isAlive();
	}

	public void pokreniNit() {
		if (nitRupe == null)
			nitRupe = new Thread(this);
		nitRupe.start();
		// System.out.println("pokrenuta nit rupe");
	}

	public void run() {
		if (zivuljka != null) {
			trenutniKorak = 1;
			while (trenutniKorak < koraci) {
				try {
					Thread.sleep(100);
					repaint();
				} catch (InterruptedException e) {
				}
				// iscrtajRupu();
				repaint();
				trenutniKorak++;
			}
			try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {
				this.zaustaviNit();
			}
			this.zaustaviNit();
			repaint();
		}
	}

	public void zaustaviNit() {
		if (nitRupe != null)
			if (nitRupe.isInterrupted() == false) {
				nitRupe.interrupt();
				nitRupe = null;
				repaint();
			}
		if (zivuljka != null) {
			if (zivuljka.udarena == false) {
				zivuljka.efekatPobegle();
				repaint();
			}
			zivuljka = null;
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		if (zivuljka != null)
			zivuljka.iscrtajZivotinju();
	}

	public void setKoraci(int koraci) {
		this.koraci = koraci;
	}

	public Thread getNitRupe() {
		return nitRupe;
	}

}
