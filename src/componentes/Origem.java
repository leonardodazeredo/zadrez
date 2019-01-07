package componentes;

import java.util.ArrayList;

public class Origem 
{
	public Coordenada coor;
	
	public Peca peca;
	
	public ArrayList<Coordenada> destinos;
	
	@SuppressWarnings("unchecked")
	public Origem(Peca p)
	{
		peca = p;
//		destinos = p.destinos;
		destinos = (ArrayList<Coordenada>) p.destinos.clone();
		coor = p.coor;
	}

//	public void setPeca(Peca p) 
//	{
//		if(p == null)
//		{
//			System.out.println(">setPeca() FALSE");
////			if(peca != null && tab.isEmXeque() && peca.getClass().getName()=="pecas.Rei")
//			if(peca != null && tab.isEmXeque() && peca instanceof Rei)
//				tab.grade.get(peca.coor).setCor(Botao.XEQUE);
//			//else if (peca != null)
//				//tab.grade.get(peca.coor).setCor(null);
//				
//			peca = null;
//			destinos = null;
//			coor = null;
//		}
//	}
}