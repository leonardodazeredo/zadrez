package componentes;

import java.util.ArrayList;

public abstract class Peca 
{
	protected static Tabuleiro tabuleiro;
	
	public ArrayList<Coordenada> destinos = new ArrayList<Coordenada>();
	
	public final char cor;
	
	public Coordenada coor;
	
	public boolean imovel;
	
	public Coordenada atacanteEmPotencial;
	
	public boolean capturavelPorRei;
	
	protected String imagem;
	
	public Peca(Tabuleiro t, char c)
	{
		tabuleiro = t;
		cor = c;
	}
	
	public String toString()
	{
		return this.getClass().getName().split("\\.")[1] + " " + cor;
	}
	
	public boolean isPreta()
	{
		if (this.cor == Constantes.preto) 
			return true;
		return false;
	}
	
	public boolean isBranca()
	{
		if (this.cor == Constantes.branco) 
			return true;
		return false;
	}

	public abstract void preencheDestinos();

	public abstract ArrayList<Coordenada> caminho(Coordenada d);
}
