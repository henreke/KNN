package knn;

import java.util.ArrayList;


class ponto{
	
	ArrayList<Float>atributos;
	public ponto()
	{
		atributos=new  ArrayList<Float>();
	}
	public void addatr(ArrayList<Float> atr)
	{
		for (int i=0;i<atr.size();i++)
			atributos.add(atr.get(i));
	}
	public int size()
	{
		return atributos.size();
	}
	public float get(int i)
	{
		return atributos.get(i);
	}
}
public class knn {
	
	public Object knnmetodo(ponto x[],Object y[],ponto xq,int k,int p)
	{
		int vizinhos[]=distancia(k,x,xq,p);
		
		ArrayList<Object> saidas=new ArrayList<Object>();
		boolean achou=false;
		for (int i=0;i<y.length;i++)
		{
			for (int j=0;j<saidas.size();j++)
				if (saidas.get(j)==y[i])
				{
					achou=true;
					break;
				}
			if (!achou)
				saidas.add(y[i]);
			achou=false;
		}
		int ranking[]=new int[saidas.size()];
		for (int i=0;i<k;i++)
			for (int j=0;j<saidas.size();j++)
				if (y[vizinhos[i]]==saidas.get(j))
					ranking[j]++;
		int max=0;
		int saida=0;
		for (int i=0;i<saidas.size();i++)
			if (ranking[i]>max)
			{
				max=ranking[i];
				saida=i;
			}
		return y[saida];
	}
	
	public int[] distancia(int k,ponto x[],ponto xi,int p)
	{
		float distancias[]=new float[x.length];
		for (int i=0;i<x.length;i++)
		{
			for (int j=0;j<((ponto)x[i]).size();j++)
			   distancias[i]+=Math.pow(((ponto)x[i]).get(j)-xi.get(j),p);
			distancias[i]=(float) Math.sqrt(distancias[i]);
			
		}
		return getmin(distancias,k);
	}
	public int[] getmin(float distancias[],int k)
	{
		int min[]=new int[k];
		float minaux=distancias[0];
		boolean achado=false;
		int kaux=0;
		int indaux=0;
		for (int i=0;i<distancias.length;i++)
		{
			if (distancias[i]<=minaux)
			{
				minaux=distancias[i];
				indaux=i;
			}
		}
		min[0]=indaux;
		kaux++;
		for (int j=1;j<k;j++)
		{
			minaux=678364786;
			for (int i=0;i<distancias.length;i++)
			{
				if (distancias[i]<=minaux)
				{
					minaux=distancias[i];
					indaux=i;
				}
				
			}
			min[kaux]=indaux;
			kaux++;
			distancias[indaux]=678364786;
		}
				
			
		
		return min;
	}

}
