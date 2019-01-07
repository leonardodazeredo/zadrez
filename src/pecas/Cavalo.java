package pecas;

import java.util.ArrayList;

import componentes.Constantes;
import componentes.Coordenada;
import componentes.Peca;
import componentes.Tabuleiro;

public class Cavalo  extends Peca 
{
	public Cavalo(Tabuleiro tabuleiro,char cor) 
	{
		super(tabuleiro,cor);
		imagem = (cor == Constantes.branco) ? Constantes.imgCavaloBranco : Constantes.imgCavaloPreto;
	}

	@Override
	public void preencheDestinos() 
	{
		super.destinos.clear();
		
		Coordenada []temp = new Coordenada[8];
		
		temp[0]= new Coordenada(coor.x+2,coor.y+1);
		temp[1]= new Coordenada(coor.x+2,coor.y-1);
		
		temp[2]= new Coordenada(coor.x-2,coor.y+1);
		temp[3]= new Coordenada(coor.x-2,coor.y-1);
		
		temp[4]= new Coordenada(coor.x+1,coor.y+2);
		temp[5]= new Coordenada(coor.x+1,coor.y-2);
		
		temp[6]= new Coordenada(coor.x-1,coor.y+2);
		temp[7]= new Coordenada(coor.x-1,coor.y-2);
												
		for (int i=0 ; i < 8 ; i++)
		{	
			//System.out.println(temp[i]);
			if (Tabuleiro.coordenadaValida(temp[i]))
				if (tabuleiro.getBotao(temp[i]).getPeca()==null || tabuleiro.getBotao(temp[i]).getPeca().cor != cor)
					super.destinos.add(temp[i]);
		}
	}

	@Override
	public ArrayList<Coordenada> caminho(Coordenada d) 
	{
		ArrayList<Coordenada> caminho = new ArrayList<Coordenada>();
		
		Coordenada []temp = new Coordenada[8];
		
		temp[0]= new Coordenada(coor.x+2,coor.y+1);
		temp[1]= new Coordenada(coor.x+2,coor.y-1);
		
		temp[2]= new Coordenada(coor.x-2,coor.y+1);
		temp[3]= new Coordenada(coor.x-2,coor.y-1);
		
		temp[4]= new Coordenada(coor.x+1,coor.y+2);
		temp[5]= new Coordenada(coor.x+1,coor.y-2);
		
		temp[6]= new Coordenada(coor.x-1,coor.y+2);
		temp[7]= new Coordenada(coor.x-1,coor.y-2);
												
		for(int i=0 ; i < 8 ; i++)
		{	
			//System.out.println(temp[i]);
			if(Tabuleiro.coordenadaValida(temp[i]))
				if (tabuleiro.getBotao(temp[i]).getPeca()==null || tabuleiro.getBotao(temp[i]).getPeca().cor != cor)
					if(d.equals(temp[i]))
						caminho.add(temp[i]);
		}
		
		return caminho;
	}
}