package popravnilabV1;

public abstract class Zivotinja {

	private Rupa zadataRupa;
	protected boolean udarena;

	public Zivotinja(Rupa r) {
		zadataRupa = r;
		udarena = false;
	}

	public abstract void iscrtajZivotinju();

	public abstract void efekatUdarene();

	public abstract void efekatPobegle();

	public Rupa dohvatiRupu() {
		return zadataRupa;
	}

}
