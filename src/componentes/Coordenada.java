package componentes;

public class Coordenada 
{
	public final int x,y; 
	
	public Coordenada(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	@Override
	public boolean equals(Object arg0) 
	{
		if (((Coordenada)arg0).x == this.x && ((Coordenada)arg0).y == this.y)
			return true;
			
		return false;
	}

	@Override
	public int hashCode() 
	{
		return (x + y)*37 + 17;
	}

	@Override
	public String toString() 
	{
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
   
	public boolean compara(int x, int y)
	{
		if (this.x == x && this.y == y)
			return true;
		
		return false;	
	}
}
