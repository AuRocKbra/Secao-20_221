package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Produto;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Path do arquivo:");
		String path = sc.nextLine();
		try(BufferedReader bf = new BufferedReader(new FileReader(path))){
			List<Produto>produtos = new ArrayList<>();
			String linha = bf.readLine();
			while(linha!=null) {
				String [] dados = linha.split(",");
				produtos.add(new Produto(dados[0], Double.valueOf(dados[1])));
				linha = bf.readLine();
			}
			Comparator<String> comp = (nome1,nome2) ->nome1.toUpperCase().compareTo(nome2.toUpperCase());
			double media = produtos.stream().map(x->x.getPreco()).reduce(0.0,(x,y)->x+y)/produtos.size();
			System.out.println("Média de preço = R$ "+String.format("%.2f",media));
			List<String> novaLista = produtos.stream().filter(p->p.getPreco()<media).map(p->p.getName()).sorted(comp.reversed()).collect(Collectors.toList());
			novaLista.forEach(System.out::println);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

}
