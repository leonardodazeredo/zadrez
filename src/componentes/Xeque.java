package componentes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pecas.Rei;

public class Xeque 
{
	private final Tabuleiro tab;
	
	public Peca pecaAtacante;
	
	public ArrayList<Coordenada> destinosPecaAtacante;
	
	public Xeque(Tabuleiro t,Peca peca)
	{
		tab = t;
		
		System.out.println(">setAtacante() TRUE");
		
		Rei reiAtivo = (Rei)tab.jogo.ativo.rei;
		
		tab.getBotao(reiAtivo.coor).setCor(Constantes.COR_XEQUE);
		
		pecaAtacante = peca;
		destinosPecaAtacante = peca.caminho(reiAtivo.coor);
		destinosPecaAtacante.add(pecaAtacante.coor);
		
		reiAtivo.corrigeDestinosReiEmXeque(pecaAtacante);
		
		atualizaDestinosEmXeque(tab);
	
		if(testaXequeMate(tab.jogo.ativo))
		{
			JOptionPane.showMessageDialog(tab.jogo, "Jogador " + tab.jogo.ativo.cor + ", você perdeu.", "XEQUE-MATE", JOptionPane.WARNING_MESSAGE);
			tab.jogo.iniciaJogo();
			//return;
		}
		else
		{
			JOptionPane.showMessageDialog(tab.jogo, "Jogador " + tab.jogo.ativo.cor + " em xeche.", "XEQUE", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void atualizaDestinosEmXeque(Tabuleiro tabuleiro)
	{
		System.out.println(">atualizaDestinosEmXeque()");
		ArrayList<Coordenada> temp = new ArrayList<Coordenada>();
		
		for (Peca p: tabuleiro.jogo.jogadorBranco.pecas)
		{
			temp.clear();
			for (Coordenada c: destinosPecaAtacante)
				if (p.destinos.contains(c))
					temp.add(c);
		
			p.destinos = new ArrayList<Coordenada>();
				
			p.destinos.addAll(temp);
		}
		
		for (Peca p: tabuleiro.jogo.jogadorPreto.pecas)
		{
			temp.clear();
			for (Coordenada c: destinosPecaAtacante)
				if (p.destinos.contains(c))
					temp.add(c);
		
			p.destinos = new ArrayList<Coordenada>();
				
			p.destinos.addAll(temp);
		}			
	}
	
	public static Xeque testeXeque(Tabuleiro tabuleiro)
	{
		System.out.println(">testeXeque()");
		
//		setAtacante(null);
		
		for(Botao botao: tabuleiro.getBotoes())
			if(botao.getPeca()!=null)
				for(Coordenada c: botao.getPeca().destinos)
					if (tabuleiro.getBotao(c).getPeca()!= null && tabuleiro.getBotao(c).getPeca() instanceof Rei)
					{				
						return new Xeque(tabuleiro,botao.getPeca());
					}
		
		tabuleiro.getBotao(tabuleiro.jogo.jogadorBranco.rei.coor).setCor(null);
		tabuleiro.getBotao(tabuleiro.jogo.jogadorPreto.rei.coor).setCor(null);
		
		return null;
	}
	
	private static boolean testaXequeMate(Jogador j)
	{
		System.out.println(j);
		for(Peca p: j.pecas)
			if (!p.destinos.isEmpty())
				return false;
		
		if(!j.rei.destinos.isEmpty())
			return false;
		
		return true;
	}
}
