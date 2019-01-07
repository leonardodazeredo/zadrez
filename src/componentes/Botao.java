package componentes;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton 
{
	private Color cor;
	private Peca peca;
	
	private final Tabuleiro tabuleiro;
	public final Coordenada coordenada;
	
	public Botao(int x, int y, char cor, Tabuleiro t) 
	{
//		super("(" + x + "," + y + ")");
		
		coordenada = new Coordenada(x,y);
		
		this.tabuleiro = t;
		
		this.setCor(cor);
		
		this.setBackground(this.cor);
		
		this.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
//				if (tab.grade.get(coor).getPeca()!= null)
//					for (Coordenada g: tab.grade.get(coor).getPeca().destinos)
//						System.out.println("<"+g);
				tabuleiro.cliqueBotao(coordenada);
			}		
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	public void setCor(Color cor)
	{
		if(cor==null)
			this.setBackground(this.cor);
		else
			this.setBackground(cor);
	}
	
	private void setCor(char cor)
	{
		if (cor == Constantes.branco)
			this.cor = Constantes.COR_BRANCO;
		else if (cor == Constantes.preto)
			this.cor = Constantes.COR_PRETO;
	}
	
	public void setPeca(Peca peca)
	{
		this.peca = peca;
		
		if(this.peca==null)
		{
			this.setIcon(null);
		}
		else
		{
			this.setIcon(new javax.swing.ImageIcon(this.peca.imagem));
			
			this.peca.coor=this.coordenada;
		}
	}
	
	public Peca getPeca()
	{
		return peca;
	}
	
	public String toString()
	{
		return "Botao" + coordenada.toString();
	}
}
