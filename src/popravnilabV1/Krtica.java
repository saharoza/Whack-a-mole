package popravnilabV1;

import java.awt.*;

public class Krtica extends Zivotinja {

	public Krtica(Rupa r) {
		super(r);
	}

	@Override
	public void efekatUdarene() {
		if (this.dohvatiRupu().getZivuljka() == null)
			return;
		this.udarena = true;
		this.dohvatiRupu().slobodnaRupa = true;
		this.dohvatiRupu().zaustaviNit();
	}

	@Override
	public void efekatPobegle() {
		this.udarena = false;
		this.dohvatiRupu().mojaBasta.smanjiPovrce();
		this.dohvatiRupu().slobodnaRupa = true;
	}

	@Override
	public void iscrtajZivotinju() {
		// System.out.println("crtam krticu");
		Graphics g = this.dohvatiRupu().getGraphics();
		g.setColor(Color.DARK_GRAY);

		double procenat = (double) (this.dohvatiRupu().trenutniKorak) / (double) (this.dohvatiRupu().koraci);
		// System.out.println(procenat);
		g.fillOval((int) (this.dohvatiRupu().getX() * (1 - procenat) / 2),
				(int) (this.dohvatiRupu().getY() * (1 - procenat) / 2),
				(int) (this.dohvatiRupu().getWidth() * procenat), (int) (this.dohvatiRupu().getHeight() * procenat));
		/*
		 * g.fillOval((int)(((int)this.dohvatiRupu().getX()+(int)this.dohvatiRupu().
		 * getWidth())*procenat)/2,
		 * (int)(((int)this.dohvatiRupu().getY()+(int)this.dohvatiRupu().getHeight())*
		 * procenat)/2, (int)(this.dohvatiRupu().getWidth()*procenat),
		 * (int)(this.dohvatiRupu().getHeight()*procenat));
		 */
	}
}
