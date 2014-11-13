package knn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N=29;
		int D=4;
		double w1=1;
		double w0=1;
		ponto xtreino[]= new ponto[N];
		Object ytreino[]=new Object[N];
		//String ytreino[]=new String[N];
		double wi[]=new double[D];
		int inicio=0;
		int colunax=3;
		int colunay=4;
		double alfa=0.01;
		double tolerancia=4;
		int passos=100000;
		int p=0;
		int NVizinhos=5;
		int P=2;//euclidiano
		double erro=100;
		String aux="";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/Users/henriqueaugusto/Documents/Eng Computacao/IA/knn/iris.data2.txt"));
			aux=br.readLine();
			while(aux!=null)
			{		
	 			String aux2[]=aux.split(",");
	 			ArrayList<Float> atrr= new ArrayList<Float>();
	 			for (int i=inicio;i<(D-inicio);i++)
	 				atrr.add(Float.parseFloat(aux2[i]));
	 			xtreino[p]= new ponto();
	 			xtreino[p].addatr(atrr);
		 		
		 		ytreino[p]=aux2[colunay];

	 			p++;
				aux=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		knn k = new  knn();
		float i1,i2;
		Scanner input=new Scanner(System.in);
		do{
			ponto xq= new ponto();
			ArrayList<Float> atrr= new ArrayList<Float>();
	        System.out.println("Entre com o I:");
	        atrr.add(Float.parseFloat(input.nextLine()));
	        System.out.println("Entre com o j:");
	        atrr.add(Float.parseFloat(input.nextLine()));
	        System.out.println("Entre com o x:");
	        atrr.add(Float.parseFloat(input.nextLine()));
	        System.out.println("Entre com o z:");
	        atrr.add(Float.parseFloat(input.nextLine()));
	     
	        xq.addatr(atrr);
	        System.out.println("A IRIS eh "+String.valueOf(k.knnmetodo(xtreino, ytreino, xq, NVizinhos, P)));
	        i1=xq.get(0);
	        i2=xq.get(1);
		}while ((i1+i2)!=0);
		input.close();

		
		
		//teste de acuracia
		int Nteste=150;
		ponto xteste[]= new ponto[Nteste];
		Object yteste[]=new Object[Nteste];
		
		p=0;
		try {
			br = new BufferedReader(new FileReader("/Users/henriqueaugusto/Documents/Eng Computacao/IA/knn/iris.data.txt"));
			aux=br.readLine();
			while(aux!=null)
			{		
	 			String aux2[]=aux.split(",");
	 			ArrayList<Float> atrr= new ArrayList<Float>();
	 			for (int i=inicio;i<(D-inicio);i++)
	 				atrr.add(Float.parseFloat(aux2[i]));
	 			xteste[p]= new ponto();
	 			xteste[p].addatr(atrr);
		 		
		 		yteste[p]=aux2[colunay];

	 			p++;
				aux=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		int acuracia=0;
		for (int ac=0;ac<xteste.length;ac++)
		{
			ponto xq= new ponto();
			ArrayList<Float> atrr= new ArrayList<Float>();
			for (int j=0;j<D;j++)
				atrr.add(xteste[ac].get(j));
			xq.addatr(atrr);
			Object saida=k.knnmetodo(xtreino, ytreino, xq, NVizinhos, P);
			if (saida.equals(yteste[ac]))
				acuracia++;
				
		}
		System.out.println("A acuracia eh:"+String.valueOf((acuracia*100.00)/Nteste)+"%");
	}

}
