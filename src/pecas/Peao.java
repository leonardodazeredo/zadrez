package pecas;

import java.util.ArrayList;

import componentes.Constantes;
import componentes.Coordenada;
import componentes.Peca;
import componentes.Tabuleiro;

public class Peao extends Peca 
{
	public Peao(Tabuleiro tabuleiro,char cor) 
	{
		super(tabuleiro,cor);
		imagem = (cor == Constantes.branco) ? Constantes.imgPeaoBranco : Constantes.imgPeaoPreto;
	}

	@Override
	public void preencheDestinos() 
	{
		super.destinos.clear();
		
		Coordenada []temp = new Coordenada[4];
		
//		if(cor == Constantes.branco)
		if(this.isBranca())
		{
			//ystem.out.println("GGGGG"+coor);
			temp[0]= new Coordenada(coor.x-2,coor.y);
			temp[1]= new Coordenada(coor.x-1,coor.y);
			temp[2]= new Coordenada(coor.x-1,coor.y - 1);
			temp[3]= new Coordenada(coor.x-1,coor.y + 1);
			
			if(Tabuleiro.coordenadaValida(temp[0]))
				if(tabuleiro.getBotao(temp[0]).getPeca()==null && tabuleiro.getBotao(temp[1]).getPeca()==null && coor.x==6)
					super.destinos.add(temp[0]);
			
			if(Tabuleiro.coordenadaValida(temp[1]))
				if(tabuleiro.getBotao(temp[1]).getPeca()==null)
					super.destinos.add(temp[1]);
			
			if(Tabuleiro.coordenadaValida(temp[2]))
				if(tabuleiro.getBotao(temp[2]).getPeca()!=null && tabuleiro.getBotao(temp[2]).getPeca().cor != cor)
					super.destinos.add(temp[2]);
			
			if(Tabuleiro.coordenadaValida(temp[3]))
				if(tabuleiro.getBotao(temp[3]).getPeca()!=null && tabuleiro.getBotao(temp[3]).getPeca().cor != cor)
					super.destinos.add(temp[3]);
		}
//		else if(cor == Constantes.preto)
		else if(this.isPreta())
		{
			temp[0]= new Coordenada(coor.x+2,coor.y);
			temp[1]= new Coordenada(coor.x+1,coor.y);
			temp[2]= new Coordenada(coor.x+1,coor.y - 1);
			temp[3]= new Coordenada(coor.x+1,coor.y + 1);
			
			if(Tabuleiro.coordenadaValida(temp[0]))
				if(tabuleiro.getBotao(temp[0]).getPeca()==null && tabuleiro.getBotao(temp[1]).getPeca()==null && coor.x==1)
					super.destinos.add(temp[0]);
			
			if(Tabuleiro.coordenadaValida(temp[1]))
				if(tabuleiro.getBotao(temp[1]).getPeca()==null)
					super.destinos.add(temp[1]);
			
			if(Tabuleiro.coordenadaValida(temp[2]))
				if(tabuleiro.getBotao(temp[2]).getPeca()!=null && tabuleiro.getBotao(temp[2]).getPeca().cor != cor)
					super.destinos.add(temp[2]);
			
			if(Tabuleiro.coordenadaValida(temp[3]))
				if(tabuleiro.getBotao(temp[3]).getPeca()!=null && tabuleiro.getBotao(temp[3]).getPeca().cor != cor)
					super.destinos.add(temp[3]);
		}
	}

	@Override
	public ArrayList<Coordenada> caminho(Coordenada d) 
	{
		ArrayList<Coordenada> caminho = new ArrayList<Coordenada>();
		
		Coordenada []temp = new Coordenada[4];
		
//		if(cor == Constantes.branco)
		if(this.isBranca())
		{
			temp[0]= new Coordenada(coor.x-2,coor.y);
			temp[1]= new Coordenada(coor.x-1,coor.y);
			temp[2]= new Coordenada(coor.x-1,coor.y - 1);
			temp[3]= new Coordenada(coor.x-1,coor.y + 1);
			
			if(Tabuleiro.coordenadaValida(temp[0]) && temp[0].y == d.y )
				if(tabuleiro.getBotao(temp[0]).getPeca()==null && tabuleiro.getBotao(temp[1]).getPeca()==null && coor.x==6)
					caminho.add(temp[0]);
			
			if(Tabuleiro.coordenadaValida(temp[1]) && temp[1].y == d.y)
				if(tabuleiro.getBotao(temp[1]).getPeca()==null)
					caminho.add(temp[1]);
			
			if(Tabuleiro.coordenadaValida(temp[2]) && temp[2].equals(d))
				if(tabuleiro.getBotao(temp[2]).getPeca()!=null && tabuleiro.getBotao(temp[2]).getPeca().cor != cor)
					caminho.add(temp[2]);
			
			if(Tabuleiro.coordenadaValida(temp[3]) && temp[3].equals(d))
				if(tabuleiro.getBotao(temp[3]).getPeca()!=null && tabuleiro.getBotao(temp[3]).getPeca().cor != cor)
					caminho.add(temp[3]);
		}
		else if(this.isPreta())
		{
			temp[0]= new Coordenada(coor.x+2,coor.y);
			temp[1]= new Coordenada(coor.x+1,coor.y);
			temp[2]= new Coordenada(coor.x+1,coor.y - 1);
			temp[3]= new Coordenada(coor.x+1,coor.y + 1);
			
			if(Tabuleiro.coordenadaValida(temp[0]) && temp[0].y == d.y )
				if(tabuleiro.getBotao(temp[0]).getPeca()==null && tabuleiro.getBotao(temp[1]).getPeca()==null && coor.x==1)
					caminho.add(temp[0]);
			
			if(Tabuleiro.coordenadaValida(temp[1]) && temp[1].y == d.y)
				if(tabuleiro.getBotao(temp[1]).getPeca()==null)
					caminho.add(temp[1]);
			
			if(Tabuleiro.coordenadaValida(temp[2]) && temp[2].equals(d))
				if(tabuleiro.getBotao(temp[2]).getPeca()!=null && tabuleiro.getBotao(temp[2]).getPeca().cor != cor)
					caminho.add(temp[2]);
			
			if(Tabuleiro.coordenadaValida(temp[3]) && temp[3].equals(d))
				if(tabuleiro.getBotao(temp[3]).getPeca()!=null && tabuleiro.getBotao(temp[3]).getPeca().cor != cor)
					caminho.add(temp[3]);
		}
		
		return caminho;
	}
}
