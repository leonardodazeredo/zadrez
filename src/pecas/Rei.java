package pecas;

import java.util.ArrayList;

import principal.Jogo;
import componentes.Constantes;
import componentes.Coordenada;
import componentes.Peca;
import componentes.Tabuleiro;

public class Rei extends Peca 
{
	public Rei(Tabuleiro tabuleiro,char cor) 
	{
		super(tabuleiro,cor);
		imagem = (cor == Constantes.branco) ? Constantes.imgReiBranco : Constantes.imgReiPreto;
	}

	@Override
	public void preencheDestinos() 
	{
		super.destinos.clear();
		
		Coordenada []temp = new Coordenada[8];
		
		temp[0]= new Coordenada(coor.x,coor.y+1);
		temp[1]= new Coordenada(coor.x,coor.y-1);
		temp[2]= new Coordenada(coor.x+1,coor.y);
		temp[3]= new Coordenada(coor.x-1,coor.y);
		temp[4]= new Coordenada(coor.x+1,coor.y+1);
		temp[5]= new Coordenada(coor.x+1,coor.y-1);
		temp[6]= new Coordenada(coor.x-1,coor.y-1);
		temp[7]= new Coordenada(coor.x-1,coor.y+1);
		
		Peca []pecaTemp = new Peca[2];
		
		Coordenada []temp2 = new Coordenada[2];
		
		for (int i = 0 ; i < 8 ; i++)
			if (Tabuleiro.coordenadaValida(temp[i]))
				if( tabuleiro.getBotao(temp[i]).getPeca() == null || tabuleiro.getBotao(temp[i]).getPeca().cor != cor )
					if(!tabuleiro.sobAtaque(temp[i],Jogo.outroJogC(this.cor)))
						if(!temRei(temp[i]))
						{
//							if(this.cor == Constantes.branco)
							if(this.isBranca())
							{
								temp2[0]= new Coordenada(temp[i].x-1,temp[i].y + 1);
								temp2[1]= new Coordenada(temp[i].x-1,temp[i].y - 1);
								
								if(Tabuleiro.coordenadaValida(temp2[0]))
									pecaTemp[0] = tabuleiro.getBotao(temp2[0]).getPeca();
								else
									pecaTemp[0] = null;
								
								if(Tabuleiro.coordenadaValida(temp2[1]))
									pecaTemp[1] = tabuleiro.getBotao(temp2[1]).getPeca();
								else
									pecaTemp[1] = null;
								
//								if( (pecaTemp[0] == null || !(pecaTemp[0].getClass().getName()=="pecas.Peao" && pecaTemp[0].cor == 'P')) 
//										&& (pecaTemp[1] == null || !(pecaTemp[1].getClass().getName()=="pecas.Peao" && pecaTemp[1].cor == 'P')) )
								if( (pecaTemp[0] == null || !(pecaTemp[0] instanceof Peao && pecaTemp[0].cor == 'P')) 
										&& (pecaTemp[1] == null || !(pecaTemp[1]  instanceof Peao && pecaTemp[1].cor == 'P')) )
									super.destinos.add(temp[i]);
							}
							else if(this.isPreta())
							{
								temp2[0]= new Coordenada(temp[i].x+1,temp[i].y + 1);
								temp2[1]= new Coordenada(temp[i].x+1,temp[i].y - 1);
								
								if(Tabuleiro.coordenadaValida(temp2[0]))
									pecaTemp[0] = tabuleiro.getBotao(temp2[0]).getPeca();
								else
									pecaTemp[0] = null;
								
								if(Tabuleiro.coordenadaValida(temp2[1]))
									pecaTemp[1] = tabuleiro.getBotao(temp2[1]).getPeca();
								else
									pecaTemp[1] = null;
								
								//pecaTemp[0] = tab.grade.get(temp2[0]).getPeca();
								//pecaTemp[1] = tab.grade.get(temp2[1]).getPeca();
								
//								if( (pecaTemp[0] == null || !(pecaTemp[0].getClass().getName()=="pecas.Peao" && pecaTemp[0].cor == 'B')) && (pecaTemp[1] == null ||   !(pecaTemp[1].getClass().getName()=="pecas.Peao" && pecaTemp[1].cor == 'B')) )
								if((pecaTemp[0] == null || !(pecaTemp[0] instanceof Peao && pecaTemp[0].isBranca())) && (pecaTemp[1] == null || !(pecaTemp[1] instanceof Peao && pecaTemp[1].isBranca())))
									super.destinos.add(temp[i]);
							}
						}
	}

	@Override
	public ArrayList<Coordenada> caminho(Coordenada b) 
	{
		ArrayList<Coordenada> caminho = new ArrayList<Coordenada>();
		
		Coordenada []temp = new Coordenada[8];
		
		temp[0]= new Coordenada(coor.x,coor.y+1);
		temp[1]= new Coordenada(coor.x,coor.y-1);
		temp[2]= new Coordenada(coor.x+1,coor.y);
		temp[3]= new Coordenada(coor.x-1,coor.y);
		temp[4]= new Coordenada(coor.x+1,coor.y+1);
		temp[5]= new Coordenada(coor.x+1,coor.y-1);
		temp[6]= new Coordenada(coor.x-1,coor.y-1);
		temp[7]= new Coordenada(coor.x-1,coor.y+1);
		
		for (int i = 0 ; i < 8 ; i++)
			if (Tabuleiro.coordenadaValida(temp[i]))
				if( tabuleiro.getBotao(temp[i]).getPeca() == null || tabuleiro.getBotao(temp[i]).getPeca().cor != cor )
					if (temp[i].equals(b) )
						if(!tabuleiro.sobAtaque(temp[i],Jogo.outroJogC(this.cor)))
							caminho.add(temp[i]);
		
		return caminho;
	}

	private boolean temRei(Coordenada c)
	{
		Coordenada []temp = new Coordenada[8];
		
		temp[0]= new Coordenada(c.x,c.y+1);
		temp[1]= new Coordenada(c.x,c.y-1);
		temp[2]= new Coordenada(c.x+1,c.y);
		temp[3]= new Coordenada(c.x-1,c.y);
		temp[4]= new Coordenada(c.x+1,c.y+1);
		temp[5]= new Coordenada(c.x+1,c.y-1);
		temp[6]= new Coordenada(c.x-1,c.y-1);
		temp[7]= new Coordenada(c.x-1,c.y+1);
		
		for (int i=0 ; i<=7 ; i++)
			if(tabuleiro.getBotao(temp[i]) != null && tabuleiro.getBotao(temp[i]).getPeca() == tabuleiro.jogo.outroJog(this.cor).rei)
				return true;
		
		return false;
	}
	
	public void corrigeDestinosReiEmXeque(Peca pecaAtacante)
	{
//		String nomePeca = pecaAtacante.getClass().getName();
//		if(nomePeca=="pecas.Bispo" || nomePeca=="pecas.Torre" || nomePeca=="pecas.Rainha")
		if(pecaAtacante instanceof Torre || pecaAtacante instanceof Bispo || pecaAtacante instanceof Rainha)
			if((pecaAtacante.coor.x == this.coor.x) && (pecaAtacante.coor.y < this.coor.y))
				this.destinos.remove(new Coordenada(this.coor.x,this.coor.y+1));
		
			else if((pecaAtacante.coor.x == this.coor.x) && (pecaAtacante.coor.y > this.coor.y))
				this.destinos.remove(new Coordenada(this.coor.x,this.coor.y-1));
		
			else if((pecaAtacante.coor.y == this.coor.y) && (pecaAtacante.coor.x < this.coor.x))
				this.destinos.remove(new Coordenada(this.coor.x+1,this.coor.y));
		
			else if((pecaAtacante.coor.y == this.coor.y) && (pecaAtacante.coor.x > this.coor.x))
				this.destinos.remove(new Coordenada(this.coor.x-1,this.coor.y));
			//-----------------------------
			else if(pecaAtacante.coor.x < this.coor.x && pecaAtacante.coor.y < this.coor.y) 
				this.destinos.remove(new Coordenada(this.coor.x+1,this.coor.y+1));
		
			else if(pecaAtacante.coor.x > this.coor.x && pecaAtacante.coor.y > this.coor.y) 
				this.destinos.remove(new Coordenada(this.coor.x-1,this.coor.y-1));	
		
			else if(pecaAtacante.coor.x < this.coor.x && pecaAtacante.coor.y > this.coor.y) 
				this.destinos.remove(new Coordenada(this.coor.x+1,this.coor.y-1));
		
			else if(pecaAtacante.coor.x > this.coor.x && pecaAtacante.coor.y < this.coor.y) 
				this.destinos.remove(new Coordenada(this.coor.x-1,this.coor.y+1));
	}
}