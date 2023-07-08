package popravnilabV1;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame {

	private Basta basta;
	private static Igra temp = null;
	private Panel panel1, panel2, panel3;
	private Label tezina, povrce;
	private Checkbox lako, srednje, tesko;
	private Button dugme;
	private boolean igraUToku = true;

	private Igra() {
		super("Igra");
		basta = new Basta(4, 4, this);
		basta.setBrojKoraka(10);
		basta.setInterval(1000);
		add(basta, BorderLayout.CENTER);
		this.setSize(600, 600);
		this.setVisible(true);
		// System.out.println(basta.getBrojKoraka());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (basta.nitBaste.isAlive())
					basta.zaustaviBastu();
				dispose();
			}
		});

		napraviPanele();
	}

	public static Igra novaIgra() {
		if (temp == null)
			temp = new Igra();
		temp.setSize(700, 700);
		return temp;
	}

	public static void main(String[] args) {
		Igra.novaIgra();
	}

	public void napraviPanele() {
		panel1 = new Panel();
		panel1.setLayout(new GridLayout(2, 1));
		panel2 = new Panel();
		panel2.setLayout(new GridLayout(5, 1));
		panel3 = new Panel();

		CheckboxGroup dugmici = new CheckboxGroup();
		tezina = new Label("Tezina:");

		lako = new Checkbox("Lako", dugmici, true);
		lako.addItemListener(e -> {
			if (lako.getState()) {
				basta.setBrojKoraka(10);
				basta.setInterval(1000);
			}
		});

		srednje = new Checkbox("Srednje", dugmici, false);
		srednje.addItemListener(e -> {
			if (srednje.getState()) {
				basta.setBrojKoraka(8);
				basta.setInterval(750);
			}
		});

		tesko = new Checkbox("Tesko", dugmici, false);
		tesko.addItemListener(e -> {
			if (tesko.getState()) {
				basta.setBrojKoraka(6);
				basta.setInterval(500);
			}
		});

		dugme = new Button("Kreni");
		dugme.setSize(80, 30);
		/*
		 * dugme.addMouseListener(new MouseAdapter() { public void
		 * mousePressed(MouseEvent me) { if(dugme.getLabel() == "Kreni") {
		 * dugme.setLabel("Stani"); basta.zaustaviBastu(); } dugme.setLabel("Kreni");
		 * basta.pokreniBastu(); }
		 * 
		 * });
		 */

		dugme.addActionListener(e -> {
			if (igraUToku) {
				dugme.setLabel("Stani");
				basta.napraviNitBaste();
				basta.pokreniBastu();
				azurirajDugmice(igraUToku);
			} else {
				dugme.setLabel("Kreni");
				basta.zaustaviBastu();
				azurirajDugmice(igraUToku);
				// azurirajPovrce(100);
			}
			igraUToku = !igraUToku;
		});

		panel2.add(tezina);
		tezina.setAlignment(Label.CENTER);
		panel2.add(lako);
		panel2.add(srednje);
		panel2.add(tesko);
		panel2.add(dugme);
		povrce = new Label("Povrce: " + this.basta.povrce);
		povrce.setAlignment(Label.CENTER);
		panel3.add(povrce);
		panel1.add(panel2);
		panel1.add(panel3);
		add(panel1, BorderLayout.EAST);
	}

	public void azurirajPovrce(int p) {
		povrce.setText("Povrce: " + p);
		repaint();
	}

	public void azurirajDugmice(boolean radi) {
		lako.setEnabled(!radi);
		srednje.setEnabled(!radi);
		tesko.setEnabled(!radi);
	}
}
