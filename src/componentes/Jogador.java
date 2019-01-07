package componentes;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import pecas.Peao;
import principal.Jogo;

@SuppressWarnings("serial")
public class Jogador extends JPanel 
{
	public final ArrayList<Peca> pecas = new ArrayList<Peca>();
	
	private Jogo jogo;
	
	public Peca rei;
	
	public char cor;
	
	private int cap = 0;
	
	public Jogador(char c, Jogo jog)
	{
		super( new GridLayout(8,2) );
		
		JButton temp;
		for(int i = 0 ; i < 16 ; i++)
		{
//			temp = new JButton(String.valueOf(i));
			
			temp = new JButton();
			temp.setIcon(new javax.swing.ImageIcon(Constantes.imgVazia));
			
			temp.setBackground( Color.LIGHT_GRAY);
			super.add(temp);
		}
		
		this.setBackground( Color.DARK_GRAY);
		
		cor = c;
		jogo = jog;
	}
	
	public void limpaCap()
	{
		for(int i = 0 ; i<16 ; i++)
			((JButton)(this.getComponent(i))).setIcon(new javax.swing.ImageIcon(Constantes.imgVazia));
		
		cap = 0;
	}
	
	public void play(Coordenada origem, Coordenada destino) 
	{
		System.out.println("<<play>>");
		
		jogo.tabuleiro.getBotao(destino).setCor(Constantes.COR_A);	
		jogo.tabuleiro.getBotao(origem).setCor(Constantes.COR_B);
		
		if( jogo.tabuleiro.getBotao(destino).getPeca() == null)
		{
			jogo.tabuleiro.getBotao(destino).setPeca(jogo.tabuleiro.getBotao(origem).getPeca());
			jogo.tabuleiro.getBotao(origem).setPeca(null);
		}
		else
		{
			((JButton)(this.getComponent(cap++))).setIcon(new javax.swing.ImageIcon(jogo.tabuleiro.getBotao(destino).getPeca().imagem));
			
//			System.out.println(jogo.outroJog(this).pecas.remove(jogo.tab.grade.get(d).getPeca()));
			
			jogo.outroJog(this).pecas.remove(jogo.tabuleiro.getBotao(destino).getPeca());
			
			jogo.tabuleiro.getBotao(destino).setPeca(jogo.tabuleiro.getBotao(origem).getPeca());
			jogo.tabuleiro.getBotao(origem).setPeca(null);
		}
		
		promoverPeao(destino);
	}
	
	public String toString()
	{
		return String.valueOf(cor);
	}
	
	private void promoverPeao(Coordenada d)
	{
//		if (jogo.tab.getBotao(d).getPeca().getClass().getName().equals("pecas.Peao"))
		if (jogo.tabuleiro.getBotao(d).getPeca() instanceof Peao)
		{
			if (d.x==0 || d.x==7)
			{
				System.out.println("Peao promovido");
				
				@SuppressWarnings("unused")
				Promover prom = new Promover(d,jogo);
			}
		}
	}
}