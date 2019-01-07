package componentes;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JPanel;

import pecas.*;
import principal.Jogo;

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel 
{
	public final Jogo jogo;
	
	private HashMap<Coordenada,Botao> grade = new HashMap<Coordenada,Botao>();
	
	private Xeque xeque;
	private Origem origem;
	
	public Tabuleiro(Jogo jog) 
	{
		super(new GridLayout(8,8));
		
		Botao temp = null;
		this.setBackground(Color.DARK_GRAY);
		jogo = jog;
		
		for (int i = 0; i <8 ; i++) 
			for (int j = 0 ; j < 8 ; j++)
			{
				if ((i%2==0 && j % 2 == 0) || (i%2!=0 && j % 2 != 0) )
				{
					temp = new Botao(i,j,Constantes.branco,this);

					grade.put(temp.coordenada,temp);
					
					super.add(temp);
				}
					
				else if ((i%2==0 && j % 2 != 0) || (i%2!=0 && j % 2 == 0) )
				{
					temp = new Botao(i,j,Constantes.preto,this);
					
					grade.put(temp.coordenada,temp);
					
					super.add(temp);
				}
			}
	}
	
	public Collection<Botao> getBotoes()
	{
		return this.grade.values();
	}
	
	public Botao getBotao(Coordenada coordenada)
	{
		return grade.get(coordenada);
	}
	
	public void reiniciaTabuleiro()
	{
//		if (xeque.isEmXeque()) 
//			xeque.setAtacante(null);
		xeque = null;
//		grade.get(jogo.jogB.rei.coor).setCor(null);
//		grade.get(jogo.jogP.rei.coor).setCor(null);
		
		resetCores();
		
		desmarcaDestinos();
		origem = null;
		
		for (Botao b: grade.values())
			b.setPeca(null);
		
		preencheTabuleiro();
		
		atualizaDestinos();
		
		if( jogo.ativo == jogo.jogadorBranco )
		{
			super.removeAll();
			for (int i = 0; i <8 ; i++) 
				for (int j = 0 ; j < 8 ; j++)
					super.add(grade.get(new Coordenada(i,j)));
		}
	}
	private void preencheTabuleiro()
	{
		System.out.println(">preencheTabuleiro()");
		
		jogo.jogadorBranco.pecas.clear();
		jogo.jogadorPreto.pecas.clear();
		
		jogo.jogadorBranco.rei = new Rei(this,'B');
		
		jogo.jogadorPreto.rei = new Rei(this,'P');
		
		//jogo.jogB.pecas.add(jogo.jogB.rei);
		//jogo.jogP.pecas.add(jogo.jogP.rei);
		
		grade.get(new Coordenada(7,4)).setPeca(jogo.jogadorBranco.rei);
		//grade.get(new Coordenada(4,5)).setPeca(jogo.jogB.rei);
		
		grade.get(new Coordenada(0, 4)).setPeca(jogo.jogadorPreto.rei);
		
		Peca []temp = new Peca[7];
		
		temp[0]= new Rainha(this,'B');
		temp[1]= new Bispo(this,'B');
		temp[2]= new Bispo(this,'B');
		temp[3]= new Cavalo(this,'B');
		temp[4]= new Cavalo(this,'B');
		temp[5]= new Torre(this,'B');
		temp[6]= new Torre(this,'B');
			
		grade.get(new Coordenada(7, 3)).setPeca(temp[0]);
			
		grade.get(new Coordenada(7, 2)).setPeca(temp[1]);
		grade.get(new Coordenada(7, 5)).setPeca(temp[2]);
		
		grade.get(new Coordenada(7, 1)).setPeca(temp[3]);
		grade.get(new Coordenada(7, 6)).setPeca(temp[4]);
			
		grade.get(new Coordenada(7, 0)).setPeca(temp[5]);
		grade.get(new Coordenada(7, 7)).setPeca(temp[6]);
		
		for(int i=0 ; i < 7 ; i++)
			jogo.jogadorBranco.pecas.add(temp[i]);
		
		temp[0]= new Rainha(this,'P');
		temp[1]= new Bispo(this,'P');
		temp[2]= new Bispo(this,'P');
		temp[3]= new Cavalo(this,'P');
		temp[4]= new Cavalo(this,'P');
		temp[5]= new Torre(this,'P');
		temp[6]= new Torre(this,'P');
	
		grade.get(new Coordenada(0, 3)).setPeca(temp[0]);
		
		grade.get(new Coordenada(0, 2)).setPeca(temp[1]);
		grade.get(new Coordenada(0, 5)).setPeca(temp[2]);
		
		grade.get(new Coordenada(0, 1)).setPeca(temp[3]);
		grade.get(new Coordenada(0, 6)).setPeca(temp[4]);
		
		grade.get(new Coordenada(0, 0)).setPeca(temp[5]);
		grade.get(new Coordenada(0, 7)).setPeca(temp[6]);
		
		for(int i=0 ; i < 7 ; i++)
			jogo.jogadorPreto.pecas.add(temp[i]);
			
		for (int i=0 ; i < 8 ; i++)
		{
			temp[0] = new Peao(this,'B');
			
			grade.get(new Coordenada(6, i)).setPeca(temp[0]);
			jogo.jogadorBranco.pecas.add(temp[0]);
			
			temp[0] = new Peao(this,'P');
			
			grade.get(new Coordenada(1, i)).setPeca(temp[0]);
			jogo.jogadorPreto.pecas.add(temp[0]);
		}
		/*
		temp[0] = new Rainha(this,'B');
		grade.get(new Coordenada(4, 4)).setPeca(temp[0]);
		jogo.jogB.pecas.add(temp[0]);
		
		temp[0] = new Rainha(this,'P');
		grade.get(new Coordenada(1, 4)).setPeca(temp[0]);
		jogo.jogP.pecas.add(temp[0]);
		*/
		
		//grade.get(new Coordenada(4, 4)).setPeca(new Rainha(this,'B'));
		//grade.get(new Coordenada(2, 3)).setPeca(new Cavalo(this,'B'));
	}
	
	public boolean isEmXeque()
	{
		return xeque != null;
	}
	
	/**
	Método estático que determina os destinos possíveis baseando-se apenas numa grade qualquer.
	*/
	public static void atualizaDestinos(HashMap<Coordenada,Botao> grade)
	{
		Rei[] reis = new Rei[2];
		
		ArrayList<Peca> pecas = new ArrayList<Peca>();
		
//		Percorrendo a grade captando as peças (os reis separadamente) ao mesmo tempo reseta 
//		as duas propriedades de controle 'imovel' e 'capturavelPorRei'
		for(Botao casa: grade.values())
		{
			if(casa.getPeca()!=null)
			{
				if (!(casa.getPeca() instanceof Rei)) 
				{
					casa.getPeca().imovel = false;
					casa.getPeca().capturavelPorRei = true;
					
					pecas.add(casa.getPeca());
				}
				else
				{
					if(casa.getPeca().cor == Constantes.branco)
						reis[0] = (Rei) casa.getPeca();
					else if(casa.getPeca().cor == Constantes.preto)
						reis[1] = (Rei) casa.getPeca();
				}
			}
		}
		
//		Invocando o metodo de autopreenchimento dos destinos de todas as pecas.
//		Os dos Reis são chamados necessariamente por ultimo por dependerem dos destinos das peças do oponente.
		for (Peca peca: pecas)
		{
			peca.preencheDestinos();
		}
		
		reis[0].preencheDestinos();
		reis[1].preencheDestinos();
		
//		Corrigindo os destinos dos Reis, no caso de conterem como destino alguma peca defendida.
		for (Peca peca: pecas)
		{
			if (!(peca.capturavelPorRei))
			{ 
				if (peca.cor == Constantes.branco) 
				{
					reis[1].destinos.remove(peca.coor); //Rei Preto
				} 
				else if (peca.cor == Constantes.preto) 
				{
					reis[0].destinos.remove(peca.coor); //Rei Branco
				}	
			}
		}
		
//		Corrigindo destinos possíveis de peças que são as únicas a defender o Rei de um ataque do oponente.
		for (Peca peca: pecas)
		{
			if (peca.imovel)
			{ 
				peca.destinos.clear();
				peca.destinos.addAll(peca.caminho(peca.atacanteEmPotencial));
				
				if (peca.cor == Constantes.branco) 
				{
					peca.destinos.addAll(peca.caminho(reis[0].coor)); //Rei Branco
				} 
				else if (peca.cor == Constantes.preto) 
				{
					peca.destinos.addAll(peca.caminho(reis[1].coor)); //Rei Preto
				}
			}
		}
	}

	private void atualizaDestinos()
	{	
		atualizaDestinos(grade);
/*
		for (Peca p: jogo.jogB.pecas)
		{
			p.imovel = false;
			p.capturavelPorRei = true;
		}
		for (Peca p: jogo.jogP.pecas)
		{
			p.imovel = false;
			p.capturavelPorRei = true;
		}
		
		for (Peca p: jogo.jogB.pecas)
			p.preencheDestinos();
		for (Peca p: jogo.jogP.pecas)
			p.preencheDestinos();
		
		jogo.jogB.rei.preencheDestinos();
		jogo.jogP.rei.preencheDestinos();
		
		for (Peca p: jogo.jogB.pecas)
			if (p.imovel)
			{ 
				p.destinos.clear();
				p.destinos.addAll(p.caminho(p.atacanteEmPotencial));
				p.destinos.addAll(p.caminho(jogo.jogB.rei.coor));
			}
		for (Peca p: jogo.jogP.pecas)
			if (p.imovel)
			{ 
				p.destinos.clear();
				p.destinos.addAll(p.caminho(p.atacanteEmPotencial));
				p.destinos.addAll(p.caminho(jogo.jogP.rei.coor));
			}
		
		for (Peca p: jogo.jogB.pecas)
			if (!(p.capturavelPorRei))
			{ 
				jogo.jogP.rei.destinos.remove(p.coor);
			}
		for (Peca p: jogo.jogP.pecas)
			if (!(p.capturavelPorRei))
			{ 
				jogo.jogB.rei.destinos.remove(p.coor);
			}
*/
	}
	
	private void resetCores()
	{
		for (Botao botao: grade.values())
			if (botao.getPeca()==null || !((Object)(botao.getPeca()) instanceof Rei))
				botao.setCor(null);
	}
	
	private void marcaDestinos(ArrayList<Coordenada> a)
	{
		System.out.println(">marcaDestinos()");
		
		resetCores();
		
		grade.get(origem.coor).setCor(Constantes.COR_ORIGEM);
		
		for (Coordenada p: a)
			if (grade.get(p).getPeca() == null)
				grade.get(p).setCor(Constantes.COR_DESTINO);
			else
				grade.get(p).setCor(Constantes.COR_AMEACADA);

	}
	private void desmarcaDestinos()
	{
		if (origem == null) 
			return;
		
		for (Coordenada p: origem.destinos)
			if (origem.peca!=grade.get(p).getPeca())
				grade.get(p).setCor(null);
			
//		grade.get(origem.coor).setCor(null);
	}
	
	/**
	Retorna "true" se o botao na coordenada esta sob ataque do jogador "pc"
	*/
	public boolean sobAtaque(Coordenada a, char pc)
	{
		if (pc == Constantes.branco)
		{
			for (Peca peca: jogo.jogadorBranco.pecas)
				if(peca.destinos.contains(a) && !(peca instanceof Peao))
					return true;
		}
		else if (pc == Constantes.preto)
		{
			for (Peca peca: jogo.jogadorPreto.pecas)
				if(peca.destinos.contains(a) && !(peca instanceof Peao))
					return true;	
		}
		
		return false;
	}

	public void cliqueBotao(Coordenada c)
	{
		if (origem != null)
			setDestino(c);
		else
			if (getBotao(c).getPeca() != null && jogo.ativo.cor == getBotao(c).getPeca().cor)
				setOrigem(c);
	}
	
	private void setOrigem(Coordenada o)
	{
		Peca peca = grade.get(o).getPeca();
		
		if(peca == null)
		{
			if(origem.peca != null && isEmXeque() && origem.peca instanceof Rei)
				grade.get(origem.peca.coor).setCor(Constantes.COR_XEQUE);
			
			origem = null;
		}
		else
		{
			origem = new Origem(peca);
			marcaDestinos(origem.destinos);
		}	
	}

	private void setDestino(Coordenada destino) 
	{
		System.out.println(">setDestino()");
		
		if(origem.destinos.contains(destino))
		{	
			jogo.ativo.play(origem.coor,destino);
			
			atualizaDestinos();
					
//			xeque.setAtacante(null);
					
			trocaJogador();
					
			xeque = Xeque.testeXeque(this);
		}
		else
		{
			resetCores();
		}
		
		desmarcaDestinos();
		
		origem = null;
	}
	
	private void trocaJogador()
	{
		if (jogo.perspectiva){
			super.removeAll();
			
			jogo.ativo = (jogo.ativo == jogo.jogadorBranco)? jogo.jogadorPreto : jogo.jogadorBranco;
			if ( jogo.ativo == jogo.jogadorBranco )
			{
				for (int i = 0; i <8 ; i++) 
					for (int j = 0 ; j < 8 ; j++)
						super.add(grade.get(new Coordenada(i,j)));
			}
			else
			{
				for (int i = 7; i >= 0 ; i--) 
					for (int j = 7 ; j >= 0 ; j--)
						super.add(grade.get(new Coordenada(i,j)));
			}
		}
		else
		{
			jogo.ativo = (jogo.ativo == jogo.jogadorBranco)? jogo.jogadorPreto : jogo.jogadorBranco;
			super.removeAll();
			for (int i = 0; i <8 ; i++) 
				for (int j = 0 ; j < 8 ; j++)
					super.add(grade.get(new Coordenada(i,j)));
		}
	}
	
	public static boolean coordenadaValida(Coordenada coordenada)
	{
		if(coordenada.x >= 0 && coordenada.y >= 0 && coordenada.x <= 7 && coordenada.y <= 7)
			return true;
		return false;
	}
}
