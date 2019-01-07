package componentes;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import pecas.*;

import principal.Jogo;

@SuppressWarnings("serial")
public class Promover extends JDialog 
{
	private final Jogo jogo;
	
	private final Coordenada peao;
	
	private Promover eu;
	
	public Promover(Coordenada p,Jogo j)
	{
		super(j,true);
		
		jogo = j;
		
		eu=this;
		
		peao = p;
		
		this.setSize(200, 300);
		super.setLocationRelativeTo(null); 
		setUndecorated(true);  
		
		final JPanel botoes = new JPanel();
		botoes.setLayout(new GridLayout(4, 1));
		
		final JButton rainha = new JButton("Rainha");
		
		rainha.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Clicou na rainha");
				Peca peca = new Rainha(jogo.tabuleiro,jogo.tabuleiro.getBotao(peao).getPeca().cor);
				jogo.tabuleiro.getBotao(peao).setPeca(peca);
				
				if(peca.cor == 'B')
				{
					jogo.jogadorBranco.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorBranco.pecas.add(peca);
				}
				else if(peca.cor == 'P')
				{
					jogo.jogadorPreto.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorPreto.pecas.add(peca);
				}
				
				eu.setVisible(false);
				eu=null;
//				((JFrame)(e.getSource())).setVisible(false);
			}
		});
		
		final JButton torre = new JButton("Torre");
		
		torre.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Clicou na torre");	
				Peca peca = new Torre(jogo.tabuleiro,jogo.tabuleiro.getBotao(peao).getPeca().cor);
				jogo.tabuleiro.getBotao(peao).setPeca(peca);
				
				if(peca.cor == 'B')
				{
					jogo.jogadorBranco.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorBranco.pecas.add(peca);
				}
				else if(peca.cor == 'P')
				{
					jogo.jogadorPreto.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorPreto.pecas.add(peca);
				}
				
				eu.setVisible(false);
				eu=null;
			}
		});
		
		final JButton bispo = new JButton("Bispo");
		
		bispo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Clicou no bispo");
				Peca peca = new Bispo(jogo.tabuleiro,jogo.tabuleiro.getBotao(peao).getPeca().cor);
				jogo.tabuleiro.getBotao(peao).setPeca(peca);
				
				if(peca.cor == 'B')
				{
					jogo.jogadorBranco.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorBranco.pecas.add(peca);
				}
				else if(peca.cor == 'P')
				{
					jogo.jogadorPreto.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorPreto.pecas.add(peca);
				}
				
				eu.setVisible(false);
				eu=null;
			}
		});
		
		final JButton cavalo = new JButton("Cavalo");
		cavalo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Clicou no cavalo");
				Peca peca = new Cavalo(jogo.tabuleiro,jogo.tabuleiro.getBotao(peao).getPeca().cor);
				jogo.tabuleiro.getBotao(peao).setPeca(peca);
				
				if(peca.cor == 'B')
				{
					jogo.jogadorBranco.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorBranco.pecas.add(peca);
				}
				else if(peca.cor == 'P')
				{
					jogo.jogadorPreto.pecas.remove(jogo.tabuleiro.getBotao(peao));
					jogo.jogadorPreto.pecas.add(peca);
				}
				
				eu.setVisible(false);
				eu=null;
			}
		});
	
		botoes.add(rainha);
		botoes.add(bispo);
		botoes.add(torre);
		botoes.add(cavalo);
		
		this.add(botoes);
		
		this.setVisible(true);
	}
}