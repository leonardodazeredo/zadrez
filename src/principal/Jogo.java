package principal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import componentes.Constantes;
import componentes.Jogador;
import componentes.Tabuleiro;

@SuppressWarnings("serial")
public class Jogo extends JFrame 
{
	public Jogador ativo;
	
	public final Jogador jogadorBranco = new Jogador(Constantes.branco,this);
	public final Jogador jogadorPreto = new Jogador(Constantes.preto,this);
	
	public final Tabuleiro tabuleiro = new Tabuleiro(this);
	
	public boolean perspectiva = false;
	
	public Jogo() 
	{
		super("Xadrez");
		
		JMenuBar barraMenu = new JMenuBar();
		JMenu menu;
		JMenuItem itemMenu;

		menu = new JMenu("Jogo");
		
		itemMenu = new JMenuItem("Ativar troca de perspectiva"); 
		itemMenu.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				perspectiva();
			}
		});
		
		menu.add(itemMenu);  
		menu.addSeparator(); 
		
		itemMenu = new JMenuItem("Reiniciar"); 
		itemMenu.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				iniciaJogo();
			}
		});
		
		menu.add(itemMenu);  
		menu.addSeparator(); 
		itemMenu = new JMenuItem("Fechar"); 	
		itemMenu.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0); 
			}
		});
		menu.add(itemMenu); 
		
		barraMenu.add(menu); 
		
		this.setJMenuBar(barraMenu);
		this.setSize(1100,700);
	
		tabuleiro.setSize(700, 700);
		
		this.add(tabuleiro,BorderLayout.CENTER);
		
		//janela.pack();
		
		this.addWindowListener(new WindowAdapter() 
		{	
			@Override
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		
		jogadorBranco.setSize(200, 700);
		
		jogadorBranco.setBorder(new EmptyBorder(1, 1, 1, 13));
		
		this.add(jogadorBranco,BorderLayout.WEST);
		
		
		jogadorPreto.setSize(200, 700);
				
		jogadorPreto.setBorder(new EmptyBorder(1, 10, 1, 1));
		
		this.add(jogadorPreto,BorderLayout.EAST);
		
		iniciaJogo();
		
		super.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public static char outroJogC(char j)
	{
		if(j == Constantes.preto)
			return Constantes.branco;
		
		else if (j == Constantes.branco)
			return Constantes.preto;
		
		else
			return '\0';
	}
	
	public Jogador outroJog(char j)
	{
		if(j == Constantes.preto)
			return jogadorBranco;
		
		else if (j == Constantes.branco)
			return jogadorPreto;
		
		else
			return null;
	}
	
	public Jogador outroJog(Jogador j)
	{
		if(j==jogadorPreto)
			return jogadorBranco;
		else if (j==jogadorBranco)
			return jogadorPreto;
		else
			return null;
	}
	
	public void iniciaJogo()
	{
		ativo = jogadorBranco;
		
		jogadorBranco.limpaCap();
		jogadorPreto.limpaCap();
		
		tabuleiro.reiniciaTabuleiro();
	}
	private void perspectiva()
	{
		if (perspectiva)
		{
			this.getJMenuBar().getMenu(0).getItem(0).setText("Ativar troca de perspectiva");
			perspectiva = false;
		}
		else
		{
			this.getJMenuBar().getMenu(0).getItem(0).setText("Desativar troca de perspectiva");
			perspectiva = true;
		}
	}
}