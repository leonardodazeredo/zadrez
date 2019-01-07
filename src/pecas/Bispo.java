package pecas;

import java.util.ArrayList;

import componentes.Constantes;
import componentes.Coordenada;
import componentes.Peca;
import componentes.Tabuleiro;

public class Bispo extends Peca 
{
	public Bispo(Tabuleiro tabuleiro,char cor) 
	{
		super(tabuleiro,cor);
		imagem = (cor == Constantes.branco) ? Constantes.imgBispoBranco : Constantes.imgBispoPreto;
	}

	@Override
	public void preencheDestinos() 
	{
		super.destinos.clear();
		
		Peca [] fim = new Peca[1];
		
		fim[0]=null;
		
		for(int i = coor.x + 1, j = coor.y + 1 ; (i <= 7 && j<=7 ) ; i++,j++ )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (i,j)))
				break;
		}

		fim[0]=null;
		
		for(int i = coor.x - 1, j = coor.y - 1 ; (i >= 0 && j >= 0 ) ; i--, j-- )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (i,j)))
				break;
		}
		
		fim[0]=null;
		
		for(int i = coor.x + 1, j = coor.y - 1 ; (i <= 7 && j >= 0) ; i++, j-- )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (i,j)))
				break;
		}
		
		fim[0]=null;
		
		for(int i = coor.x - 1, j = coor.y + 1 ; (i >= 0 && j <= 7) ; i--, j++ )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (i,j)))
				break;	
		}
	}
	
	private boolean avaliaCasaDestinos(Peca [] primeiraPeca, Coordenada coordenada)
	{
		Peca peca = tabuleiro.getBotao(coordenada).getPeca();
		
		if(primeiraPeca[0]==null)
		{
			if( peca == null || peca.cor != cor)
				super.destinos.add(coordenada);
	
			if(peca != null)
			{
				primeiraPeca[0] = peca;
				
				if(primeiraPeca[0].cor == this.cor)
				{
					primeiraPeca[0].capturavelPorRei=false;
				}
			}
		}
		else 
		{
			if(tabuleiro.jogo.outroJog(this.cor).rei==peca)
			{
				if(primeiraPeca[0].cor != this.cor)
				{
					primeiraPeca[0].imovel = true;
					primeiraPeca[0].atacanteEmPotencial = coor;
				}
			}	
			
			if(peca!=null) 
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public ArrayList<Coordenada> caminho(Coordenada d) 
	{
		ArrayList<Coordenada> caminho = new ArrayList<Coordenada>();
		
		if(coor.x < d.x && coor.y < d.y) 
		{
			for(int i = coor.x + 1, j = coor.y + 1 ; (i < d.x && j < d.y ) ; i++,j++ )
			{
				if(avaliaCasaCaminho(caminho,new Coordenada (i,j))) 
					break;
			}
		}
		else if(coor.x > d.x && coor.y > d.y) 
		{
			for(int i = coor.x - 1, j = coor.y - 1 ; (i >= 0 && j >= 0 ) ; i--, j-- )
			{
				if(avaliaCasaCaminho(caminho,new Coordenada (i,j))) 
					break;
			}
		}
		else if(coor.x < d.x && coor.y > d.y) 
		{
			for(int i = coor.x + 1, j = coor.y - 1 ; (i <= 7 && j >= 0) ; i++, j-- )
			{
				if(avaliaCasaCaminho(caminho,new Coordenada (i,j))) 
					break;
			}
		}
		else if(coor.x > d.x && coor.y < d.y) 
		{
			for(int i = coor.x - 1, j = coor.y + 1 ; (i >= 0 && j <= 7) ; i--, j++ )
			{
				if(avaliaCasaCaminho(caminho,new Coordenada (i,j))) 
					break;
			}
		}
		
		return caminho;
	}
	
	private boolean avaliaCasaCaminho(ArrayList<Coordenada> caminho , Coordenada coordenada)
	{
		Peca peca = tabuleiro.getBotao(coordenada).getPeca();
		
		if(peca == null || peca.cor != cor)
			caminho.add(coordenada);
	
		if(peca != null)
			return true;
		
		return false;
	}
}