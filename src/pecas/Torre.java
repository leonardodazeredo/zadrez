package pecas;

import java.util.ArrayList;

import componentes.Constantes;
import componentes.Coordenada;
import componentes.Peca;
import componentes.Tabuleiro;

public class Torre extends Peca 
{
	public Torre(Tabuleiro tabuleiro, char cor) 
	{
		super(tabuleiro,cor);
		imagem = (cor == Constantes.branco) ? Constantes.imgTorreBranco : Constantes.imgTorrePreto;
	}

	@Override
	public void preencheDestinos() 
	{
		super.destinos.clear();
		
		Peca [] fim = new Peca[1];
		
		fim[0]=null;
	
		for (int i = coor.y + 1 ; i <= 7 ; i++ )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (coor.x,i)))
				break;
		}
		
		fim[0]=null;

		for (int i = coor.y - 1 ; i >= 0 ; i-- )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (coor.x,i)))
				break;
		}
		
		fim[0]=null;
		
		for (int i = coor.x + 1 ; i <= 7 ; i++ )
		{
			if(avaliaCasaDestinos( fim ,new Coordenada (i,coor.y)))
				break;
		}
		
		fim[0]=null;
		
		for (int i = coor.x - 1 ; i >= 0 ; i-- )
		{			
			if(avaliaCasaDestinos( fim ,new Coordenada (i,coor.y)))
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
	public ArrayList<Coordenada> caminho(Coordenada destino) 
	{
		ArrayList<Coordenada> caminho = new ArrayList<Coordenada>();
		
		if ((coor.x == destino.x) && (coor.y < destino.y))
		{
			for (int i = coor.y + 1 ; i <= 7 ; i++ )
			{
				if (avaliaCasaCaminho(caminho,new Coordenada (coor.x,i))) 
					break;
			}
		}
		else if ((coor.x == destino.x) && (coor.y > destino.y))
		{	
			for (int i = coor.y - 1 ; i >= 0 ; i-- )
			{
				if (avaliaCasaCaminho(caminho,new Coordenada (coor.x,i))) 
					break;
			}
		}
		else if ((coor.y == destino.y) && (coor.x < destino.x))
		{	
			for (int i = coor.x + 1 ; i <= 7 ; i++ )
			{		
				if (avaliaCasaCaminho(caminho,new Coordenada (i,coor.y))) 
					break;
			}
		}
		else if ((coor.y == destino.y) && (coor.x > destino.x))
		{	
			for (int i = coor.x - 1 ; i >= 0 ; i-- )
			{
				if (avaliaCasaCaminho(caminho,new Coordenada (i,coor.y))) 
					break;
			}
		}
	
		return caminho;
	}
	
	private boolean avaliaCasaCaminho(ArrayList<Coordenada> caminho , Coordenada coordenada)
	{
		Peca peca = tabuleiro.getBotao(coordenada).getPeca();
		
		if (peca == null || peca.cor != cor)
			caminho.add(coordenada);
	
		if (peca != null)
			return true;
		
		return false;
	}
}
