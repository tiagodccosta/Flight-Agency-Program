import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	
	public static Scanner scanner = new Scanner(System.in);

	
	public static int inputInt;
	static int numeroVoo;
	static String ficheiroReservas = "reservas.txt";
	static String ficheiroVoos = "voos.txt";
	
	public static int posicao;
	public static int count = 0;
	public static int lastPos;
	
	public static String[][] ficheiroVoosArray = new String[3][20];
	public static int[] numeroFicheiroVoosArray = new int[20];
	public static String[][] ficheiroReservasArray = new String[2][200];
	public static int[][] numeroFicheiroReservasArray = new int[2][200];

	public static String origemReserva;
	public static String destinoReserva;
	public static int diaReserva;
	
	public static int numCCReserva;
	public static String nomeReserva;
	public static String vooReserva;
	
	public static int numeroPassageiros;
	
	public static String nvoo;
	
	public static void Menu() {
		
		//
		//MENU
		//
		System.out.println("                     Companhia de Aviação \"Já Fui\" \n                   ");
		System.out.println("Programa de Reservas");
		System.out.println("0 - Sair do programa");
		System.out.println("1 - Listar passageiros de um voo");
		System.out.println("2 - Numero de passageiros de um periodo de dias");
		System.out.println("3 - Escrita em ficheiro");
		System.out.println("4 - Analise economica");
		System.out.println("5 - Reserva simples de um voo");
		
	}
	
	
	//
	//Este metodo le os ficheiros assim que o programa 
	//Inicia. Este bloco e chamado no inicio do Metodo Main();
	//
	public static void leituraDeFicheiros() {
		
		//
		//Ficheiro reservas e Ficheiro Voos
		//
		
		
		//
		//Le o ficheiro reservas.txt e guarda a informacao dentro
		//De Arrays de Arrays
		//
		try {
			File ficheiro1 = new File(ficheiroReservas);
			Scanner leitorFicheiroReservas = new Scanner(ficheiro1);
			
			posicao = 0;
			
			while(leitorFicheiroReservas.hasNextLine()) {
				String linha = leitorFicheiroReservas.nextLine();
				String parts[] = linha.split(":");
				
				numeroFicheiroReservasArray[0][posicao] = Integer.parseInt(parts[2]);
				ficheiroReservasArray[0][posicao] = parts[0];
				ficheiroReservasArray[1][posicao] = parts[1];
				numeroFicheiroReservasArray[1][posicao] = Integer.parseInt(parts[3]);
				
				posicao++;
			}	
				leitorFicheiroReservas.close();
			} catch (FileNotFoundException e) {
			System.err.println("Erro: " + ficheiroReservas + " nao foi encontrado!");
		}
		
		try{
			
			//
			//Le o ficheiro voos.txt
			//Guarda a informacao dentro de Arrays de Arrays
			//
			
			File ficheiro2 = new File(ficheiroVoos);
			Scanner leitorFicheiroVoos = new Scanner(ficheiro2);
			
			posicao = 0;
			
			while(leitorFicheiroVoos.hasNextLine()) {
				String linha = leitorFicheiroVoos.nextLine();
				String parts[] = linha.split(":");
				
				ficheiroVoosArray[0][posicao] = parts[0];
				ficheiroVoosArray[1][posicao] = parts[1];
				ficheiroVoosArray[2][posicao] = parts[2];
				numeroFicheiroVoosArray[posicao] = Integer.parseInt(parts[3]);
				
				posicao++;
			}
			leitorFicheiroVoos.close();
		}catch(FileNotFoundException e) {
			System.err.println("Erro: " + ficheiroVoos + " nao foi encontrado!");
		}
		
	}
	
	//
	//Este metodo vai efetuar um calculo para nos dar
	//A ultima posicao dos array
	//Assim quando formos escrever em ficheiro ja sabemos a posicao
	//Em que devemos escrever, lastPos;
	//Este bloco e chamado no inico do while loop no metodo Main
	//Assim sempre que esta posicao estara sempre a ser atualizada 
	//
	public static void ultimaPosicaoArray() {
		for(posicao = 0; posicao < ficheiroReservasArray[1].length; posicao++) {
			if(ficheiroReservasArray[0][posicao] == null) {
				lastPos = posicao;
				break;
			}
		}
	}
	
	
	// Passo 1 do menu
	//Listagem de passageiros
	@SuppressWarnings("resource")
	public static void listarPassageiros() {
				
		Scanner scan = new Scanner(System.in);
		
		try{
			
			System.out.println("Introduza o numero do voo: ");
			String numeroVoo = scan.nextLine();
				
			
			System.out.println("Introduza o dia do voo: ");
			int diaVoo = scanner.nextInt();
			
			while(diaVoo < 1 || diaVoo > 30) {
				System.err.println("Introduza um dia valido!");
				System.out.println("Introduza o dia do voo: ");
				diaVoo = scanner.nextInt();
			}
			
			//
			//For loops para percorrer os arrays do ficheiro de voos e verificar quando ha igualdades entre User 
			// input e os ficheiros
			//
			
			for(posicao = 0; posicao < ficheiroVoosArray[1].length; posicao++) {
				for(posicao = 0; posicao < numeroFicheiroVoosArray.length; posicao++) {
					if(Objects.equals(numeroVoo, ficheiroVoosArray[0][posicao]) 
							&& (numeroFicheiroVoosArray[posicao] == diaVoo)) {
									//Output para a listagem de passageiros
									System.out.println("O seu voo é dia " + diaVoo);	
									System.out.println("A origem do seu voo é " + ficheiroVoosArray[1][posicao]); 
									System.out.println("O destino do seu voo é " + ficheiroVoosArray[2][posicao] + "\n");
					}
				}
			}
			
			//
			//For loops para percorrer os arrays do ficheiro de reservas e verificar quando ha igualdades entre User
			//input e os ficheiros
			//
			
			System.out.println("Listagem de passageiros:");
			for(posicao = 0; posicao < ficheiroReservasArray[1].length; posicao++) {
				for(posicao = 0; posicao < numeroFicheiroReservasArray[1].length; posicao++) {
					if(Objects.equals(numeroVoo, ficheiroReservasArray[1][posicao])
							&& (numeroFicheiroReservasArray[1][posicao] == diaVoo)) {
						System.out.println(ficheiroReservasArray[0][posicao]);
					}
				}				
			}
			
			
		} catch(InputMismatchException e){
			System.err.println("Insira um numero valido!");
		}
	}
	
	//
	//Passo 2 do menu
	//Pede ao utitlizador input de dois dias MENOR e MAIOR
	//E da todos os passageiros dos voos nesse periodo de tempo
	//
	public static void numeroPassageirosPeriodoTempo() {
		
		numeroPassageiros = 0;
		
	
		try{
		
		System.out.println("Introduza um dia:");
		int menorDia = scanner.nextInt();
		System.out.println("Introduza um dia:");
		int maiorDia = scanner.nextInt();
		
		while(menorDia >= maiorDia) {
			System.err.println("O primeiro dia tem de ser menor que o segundo dia! Tente novamente:");
			System.out.println("Introduza um dia:");
			menorDia = scanner.nextInt();
			System.out.println("Introduza um dia:");
			maiorDia = scanner.nextInt();
		}
		
		
		String numeroVoo = ficheiroReservasArray[1][0];

		
		for(posicao = 0; posicao < numeroFicheiroReservasArray[1].length; posicao++) {
			for(posicao = 0; posicao < ficheiroReservasArray[1].length; posicao++) {
				if(menorDia <= maiorDia) {
					
					if(numeroFicheiroReservasArray[1][posicao] == menorDia 
							&& Objects.equals(numeroVoo, ficheiroReservasArray[1][posicao])) {
						
						numeroPassageiros++;
						
					} else if(menorDia != numeroFicheiroReservasArray[1][posicao] 
							&& !(Objects.equals(numeroVoo, ficheiroReservasArray[1][posicao]))) {
						
						System.out.println("No dia " + menorDia + ":");
						System.out.println(numeroVoo + " " + numeroPassageiros + " passageiros.");
						numeroVoo = ficheiroReservasArray[1][posicao];
						numeroPassageiros = 1;
						menorDia++;
						
					}else if(!(Objects.equals(numeroVoo, ficheiroReservasArray[1][posicao])) 
							&& menorDia == numeroFicheiroReservasArray[1][posicao]){
						
						System.out.println("No dia " + menorDia + ":");
						System.out.println(numeroVoo + " " + numeroPassageiros + " passageiros.");
						numeroVoo = ficheiroReservasArray[1][posicao];
						numeroPassageiros = 1;
						
					}	
				}
			}
		}
		
		}catch(InputMismatchException ex) {
			System.err.println("Introduza um numero valido!");
		}
	}
	
	//
	//Passo 3 do menu
	//Escreve no ficheiro reservas.txt
	//Atualiza este ficheiro com as reservas entretanto efetuadas
	//
	public static void escritaEmFicheiro() {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiroReservas));
			
			posicao = 0;
			while(posicao < ficheiroReservasArray[1].length 
					&& posicao < numeroFicheiroReservasArray[1].length 
						&& ficheiroReservasArray[0][posicao] != null) {
				writer.write(ficheiroReservasArray[0][posicao] + 
						":" + ficheiroReservasArray[1][posicao] + ":" + numeroFicheiroReservasArray[1][posicao] +
						":" + numeroFicheiroReservasArray[0][posicao]);
				posicao++;
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao escrever a sua reserva."
					+ " Por favor tente novamente!");
		}
		System.out.println("\nBase de dados de reservas atualizada!");
	}
	
	//
	//Passo 5 do menu
	//Efetua a reserva de um voo
	//
	@SuppressWarnings("resource")
	public static void reservaDeUmVoo() {
		
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);


		try{
			System.out.println("Introduza a origem do voo:");
			origemReserva = scan2.nextLine();
			
			System.out.println("Introduza o destino do voo:");
			destinoReserva = scan.nextLine();
			
			System.out.println("Introduza o dia em que pretende viajar:");
			diaReserva = scan2.nextInt();

		for(posicao = 0; posicao < ficheiroVoosArray[1].length; posicao++) {
			for(posicao = 0; posicao < numeroFicheiroVoosArray.length; posicao++) {
				if(Objects.equals(origemReserva, ficheiroVoosArray[1][posicao])
						&& (Objects.equals(destinoReserva, ficheiroVoosArray[2][posicao]))
						&& (diaReserva == numeroFicheiroVoosArray[posicao])) {
					nvoo = ficheiroVoosArray[0][posicao];
				}
			}
		}
		
		numeroPassageiros = 0;
		
		for(posicao = 0; posicao < ficheiroReservasArray[1].length; posicao++) {
			for(posicao = 0; posicao < numeroFicheiroReservasArray[1].length; posicao++) {
				if((diaReserva == numeroFicheiroReservasArray[1][posicao])
						&& (Objects.equals(nvoo, ficheiroReservasArray[1][posicao]))) {
					numeroPassageiros++;
				}
			}
		}
		
		if(nvoo == null || numeroPassageiros >= 17) {
			
			sugestaoDeVoos();
			
		} else {

			System.out.println("Voos que satisfazem os seus requisitos:");
			
			System.out.println(nvoo + " " + (20 - numeroPassageiros) + " vagas");
			
			
			
			System.out.println("\nIntroduza o numero do voo que pretende reservar:");
			vooReserva = scan.nextLine();
			
			
			if(Objects.equals(vooReserva, nvoo)) {
								
				System.out.println("Introduza o seu numero de cartao de cidadao:");
				numCCReserva = scan.nextInt();
				
				System.out.println("Introduza o seu nome:");
				nomeReserva = scan3.nextLine();
				
			} else {
				System.out.println("O voo introduzido nao é válido!");
			}		
			
				
				numeroFicheiroReservasArray[0][lastPos] = numCCReserva;
				ficheiroReservasArray[0][lastPos] = nomeReserva;
				ficheiroReservasArray[1][lastPos] = vooReserva;
				numeroFicheiroReservasArray[1][lastPos] = diaReserva;
				
				System.out.println("Restam " + (19 - numeroPassageiros) + " vagas para o vôo " + vooReserva);
				
			}
		} catch(InputMismatchException ex) {
			System.err.println("Insira um valor valido!");
		}
	}
	
	//
	//Este metodo vai executar dois while loops para 
	//Dar ao utilizador os numeros e os dias dos voos sugeridos
	//Voos 4dias anteriores e 4 posteriores ao dia da reserva que este tentou efteuar
	//
	public static void sugestaoDeVoos() {
	
		System.out.println("\nSugestao de voos alternativos nos "
				+ "4 dias anterios e 4 posteriores:");
		
		count = diaReserva;
		posicao = 0;
		while(count <= diaReserva + 4 && posicao < ficheiroVoosArray[1].length) {
			
			if(numeroFicheiroVoosArray[posicao] == count) {
				System.out.println("Voo " + ficheiroVoosArray[0][posicao] 
						+ " dia " + numeroFicheiroVoosArray[posicao]);
			} else if(posicao == ficheiroVoosArray[1].length - 1){
				count++;
				posicao = 0;	
			}
			posicao++;
		}
		
		while(count >= diaReserva - 4 && posicao >= 0) {
					
			if(numeroFicheiroVoosArray[posicao] == count) {
				System.out.println("Voo " + ficheiroVoosArray[0][posicao] 
						+ " dia " + numeroFicheiroVoosArray[posicao]);
			} else if(posicao == 0){
				count--;
				posicao = ficheiroVoosArray[1].length - 1;	
			}
			posicao--;
		}
		
	}
	
	
	public static void analiseEconomica() {
		
		count = 0;
		posicao = 0;
		numeroPassageiros = 0;
		int numHastags = 0;
		
		String nvoo;
		
		while(posicao < ficheiroReservasArray[1].length && count < ficheiroVoosArray[1].length) {
			
			nvoo = ficheiroVoosArray[0][count];
			
			if(Objects.equals(nvoo, ficheiroReservasArray[1][posicao])
					&& (numeroFicheiroVoosArray[count]) == numeroFicheiroReservasArray[1][posicao]) {
				numeroPassageiros++;
			} else if(posicao == ficheiroReservasArray[1].length - 1) {
				
				if(numeroPassageiros == 20) {
					numHastags = 30;
				} else if(numeroPassageiros == 15) {
					numHastags = 23;
				} else if(numeroPassageiros == 10) {
					numHastags = 15;
				} else if(numeroPassageiros == 5) {
					numHastags = 8;
				} else if (numeroPassageiros == 0){
					numHastags = 0;
				} else if(numeroPassageiros > 0 && numeroPassageiros < 5) {
					numHastags = 4;
				} else if(numeroPassageiros > 5 && numeroPassageiros < 10) {
					numHastags = 11;
				} else if(numeroPassageiros > 10 && numeroPassageiros < 15) {
					numHastags = 18;
				} else if(numeroPassageiros > 15 && numeroPassageiros < 20) {
					numHastags = 26;
				}
				
				System.out.print(nvoo + " ");

				while(numHastags > 0) {
					System.out.print('#');
					numHastags--;
				}
				
				System.out.println();
				
				count++;
				posicao = -1;
				numeroPassageiros = 0;
			}
			posicao++;
		}
	}
	
	
	
	//
	//METODO MAIN
	//
	public static void main(String []args) {

		boolean loop = true;
		
		
		//Le os ficheiros reservas e voos assim que inicia o programa
		leituraDeFicheiros();
		

		Scanner scanner = new Scanner(System.in);
		
		while(loop) {
		
			ultimaPosicaoArray();
			
			Menu();
			
			String input = null;
		
			while(input == null) {
				
				input = scanner.nextLine();
				
				try{
							
					
					
					inputInt = Integer.parseInt(input);
					
					while(inputInt < 0 || inputInt > 5) {
							System.err.println("Insira um numero valido!");	
							inputInt = scanner.nextInt();
					}
				
				} catch(NumberFormatException e) {
						System.err.println("Insira um numero valido!");	
						input = null;
				} 
			}
			
			
			//
			//Controlador de input do menu
			//
			if(inputInt == 0) {
				loop = false;
				System.err.println("Programa terminado.");
				System.exit(1);
			} else if (inputInt == 1) {
				//Listar passageiros de um voo
				listarPassageiros();
			} else if (inputInt == 2) {
				//Numero de passageiros de um periodo de dias
				//User pode escolher o periodo de dias que quer ver
				numeroPassageirosPeriodoTempo();
			}else if (inputInt == 3) {
				//Escrita em ficheiro
				//Atualiza o ficheiro reservas.txt
				//No final o ficheiro deve ser fechado
				escritaEmFicheiro();
			}else if (inputInt == 4) {
				//Analise economica
				//Visualizar a ocupacao dos voos no mes
				//os voos devem mostrar a ocupacao total num dado momento
				//deve conter todos os voos do mes 
				analiseEconomica();
			}else if (inputInt == 5) {
				reservaDeUmVoo();
			}
		}
		scanner.close();
	} 
}