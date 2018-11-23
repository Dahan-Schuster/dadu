package objetos;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Calculadora {
	
	/** Responsável pela comunicação da calculadora com outros objetos. 
	 * Recebe uma conta e retorna seu resultado
	 * 
	 * @param conta - uma string representando um cálculo matemático
	 * Ex.: 2+2
	 * @return resultado - uma string representando o resultado
	 * Ex.: 4.0
	 */
	public static String organizarCalculos(String conta) {
		String resultado = "0", resultadoParcial = "0";
		if (contaValida(conta)) {
			resultadoParcial = multiplicarEdividir(conta);
			resultado = somarEsubtrair(resultadoParcial);
			return resultado;
		}

		// Instancia uma exceção sem lançá-la e retorna sua mensagem
		throw new SintaxeIncorretaException("Operador(es) sem número.");

	}

	/** Realiza uma operação avançada (sin, cos, tan, raiz quadrada e potência)
	 * Recebe uma conta, calcula seu resultado, aplica o cálculo e retorna o resultado.
	 * 
	 * @param conta
	 * @param avancado - sin, cos, tan, sqrt, quad, cubo, pot
	 * @return
	 */
	public static String organizarCalculos(String conta, String calculo) {
		String resultado = "0", resultadoParcial = "0";
		if (contaValida(conta)) {
			resultadoParcial = multiplicarEdividir(conta);
			resultadoParcial = somarEsubtrair(resultadoParcial);
			resultado = calculoAvancado(resultadoParcial, calculo);
			
			return resultado;
		}

		// Instancia uma exceção sem lançá-la e retorna sua mensagem
		throw new SintaxeIncorretaException("Operador(es) sem número.");

	}
	
	/**
	 * Testa a validade da conta
	 * 
	 * @param conta
	 * @return
	 */
	private static boolean contaValida(String conta) {
		// Procura por operadores sem número no...
		if (conta.contains("×÷") || conta.contains("×+") || conta.contains("÷×") || conta.contains("÷+")
				|| conta.contains("+×") || conta.contains("+÷") || conta.contains("××") || conta.contains("÷÷")
				|| conta.contains("++") || conta.contains("--") || conta.contains("-×") || conta.contains("-÷")
				|| conta.contains("-+")) // ... meio da conta
			return false;
		else if (conta.replaceAll("\\+*$|\\-*$|\\×*$|\\÷*$", "").length() < conta.length()) // ... fim da conta
			return false;
		if (conta.replaceAll("^[\\+*]|^[\\×*]|^[\\÷*]", "").length() < conta.length()) // ... início da conta
			return false;

		return true;
	}
	
	
	// MÉTODOS DE CÁLCULO
	
	/**
	 * M�todo para realizar uma das quatro opera��es b�sicas e retornar o resultado.
	 * 
	 * @param v1 O primerio valor da opera��o
	 * @param v2 O segundo valor da opera��o
	 * @param op A opera��o (+, -, / ou *)
	 * @return o resultado da conta (v1 op v2)
	 * @throws ArithmeticException em casos como (n / 0)
	 */
	private static Double calcular(Double v1, String op, Double v2) throws ArithmeticException {
		Double resultado = 0.0;

		switch (op.charAt(0)) {
		case '+': // soma
			resultado = v1 + v2;
			break;
		case '-': // subtrai
			resultado = v1 - v2;
			break;
		case '*': // multiplica
			resultado = v1 * v2;
			break;
		case '/': // divide
			resultado = v1 / v2;
			break;
		}

		return resultado;
	}

	/**
	 * Separar as contas (ex.: 4+3*2 → 4+(3*2)) e mandá-las para o método Calcular
	 * 
	 * @param conta a equação
	 */
	private static String multiplicarEdividir(String conta) {
		double resultadoParcial = 0;

		// Primeiro, verifica a existência de × e ÷ na conta, afim de resolver suas
		// contas primeiro

		while (conta.contains("×") || conta.contains("÷")) {
			String[] multis = conta.split("×");
			String[] divs = conta.split("÷");

			// Por regra, resolve o que vem antes
			if (multis[0].length() < divs[0].length()) {
				// Cenário em que a multiplicação aparece primeiro
				String parte1 = retirarContasIniciais(multis[0]); // Removendo outras eventuais contas de + ou - que
																	// venham antes
				String parte2 = retirarContasFinais(multis[1]); // Removendo outras eventuais contas que
																// venham depois

				// String antes de retirar as contas: 2-8×10÷64+1
				// String após retitar as contas: 8×10

				// Agora basta resolver :)
				resultadoParcial = calcular(Double.parseDouble(parte1), "*", Double.parseDouble(parte2));
				conta = conta.replaceFirst(parte1 + "\\×" + parte2, "" + resultadoParcial);
			} else {
				// Cenário em que a divisão aparece primeiro
				String parte1 = retirarContasIniciais(divs[0]); // Removendo outras eventuais contas de + ou - que
																// venham antes
				String parte2 = retirarContasFinais(divs[1]); // Removendo outras eventuais contas que
																// venham depois

				// String antes de retirar as contas: 2-8÷10×64+1
				// String após retitar as contas: 8÷10

				// Agora basta resolver :)
				resultadoParcial = calcular(Double.parseDouble(parte1), "/", Double.parseDouble(parte2));
				conta = conta.replaceFirst(parte1 + "\\÷" + parte2, "" + resultadoParcial);
			}

		}

		return conta;
	}

	private static String somarEsubtrair(String conta) {
		Double resultadoParcial = 0.0;

		// Verificar contas de + e -

		while (conta.contains("+") || conta.substring(1).contains("-")) {
			// O método split da classe String não suporta como pattern o caractere "+"
			// Por isso será usado a classe StringTokenizer e ArrayList (o arraylist
			// substitui o vetor string
			// já que não tem como saber quantas contas de + terão para criar um vetor com
			// um
			// tamanho predefinido

			ArrayList<String> somas = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(conta, "+");
			while (st.hasMoreTokens())
				somas.add(st.nextToken());

			String[] subs = conta.substring(1).split("-");
			subs[0] = conta.charAt(0)+subs[0];
			

			// Por regra, resolve o que vem antes
			if (somas.get(0).length() < subs[0].length()) {
				// Cenário em que a soma aparece primeiro
				String parte1 = somas.get(0); // Não é necessário remover as contas iniciais porque elas não existem

				String parte2 = retirarContasFinais(somas.get(1)); // Removendo outras eventuais contas que
																	// venham depois

				// String antes de retirar as contas: 2+5-6+1
				// String após retitar as contas: 2+5

				// Agora basta resolver :)
				resultadoParcial = calcular(Double.parseDouble(parte1), "+", Double.parseDouble(parte2));
				conta = conta.replaceFirst(parte1 + "\\+" + parte2, "" + resultadoParcial);
			} else {
				// Cenário em que a subtração aparece primeiro
				String parte1 = subs[0]; // Removendo outras eventuais contas de + ou - que
											// venham antes
				String parte2 = retirarContasFinais(subs[1]); // Removendo outras eventuais contas que
																// venham depois

				// String antes de retirar as contas: 2-5+6-1
				// String após retitar as contas: 2-5

				// Agora basta resolver :)
				resultadoParcial = calcular(Double.parseDouble(parte1), "-", Double.parseDouble(parte2));

				conta = conta.replaceFirst(parte1 + "\\-" + parte2, "" + resultadoParcial);
			}
		}
		return conta;
	}
	
	private static String calculoAvancado(String valor, String calculo) {
		float resultadoParcial = 0;
		double v = Double.parseDouble(valor);
		String resultado = "0";
		
		if (calculo.equals("sin")) {
			resultadoParcial = (float) Math.sin(v);
		} else if (calculo.equals("cos")) {
			resultadoParcial = (float) Math.cos(v);
		} else if (calculo.equals("tan")) {
			resultadoParcial = (float) Math.tan(v);
		} else if (calculo.equals("Raiz Q")) {
			resultadoParcial = (float) Math.sqrt(v);
		} else if (calculo.equals("x^2")) {
			resultadoParcial = (float) Math.pow(v, 2);
		} else if (calculo.equals("x^3")) {
			resultadoParcial = (float) Math.pow(v, 3);
		}
		
		resultado = ""+resultadoParcial;
		
		return resultado; 
	}
	
	// FIM MÉTODOS DE CÁLCULO

	// MÉTODOS RETIRARCONTAS

	private static String retirarContasIniciais(String parte1) {
		// System.out.println(parte1.substring(1));
		while (parte1.contains("+") || parte1.substring(1).contains("-")) {
			parte1 = parte1.replaceAll("^\\d*", "");
			parte1 = parte1.replaceAll("^\\+*", "");
			parte1 = parte1.replaceAll("^\\.*", "");

			// retira apenas os sinais negativos que não estão imediatamente do início
			// Ex.: 2-9+2-8 → -8
			if (parte1.substring(1).contains("-") || parte1.contains("+"))
				parte1 = parte1.replaceAll("^\\-*", "");
		}

		return parte1;
	}

	private static String retirarContasFinais(String parte2) {
		while (parte2.contains("+") || parte2.contains("÷") || parte2.contains("×")
				|| parte2.substring(1).contains("-")) {
			parte2 = parte2.replaceAll("\\d*$", "");
			parte2 = parte2.replaceAll("\\+*$", "");
			parte2 = parte2.replaceAll("\\÷*$", "");
			parte2 = parte2.replaceAll("\\×*$", "");
			parte2 = parte2.replaceAll("\\.*$", "");

			if (parte2.substring(1).contains("-"))
				parte2 = parte2.replaceAll("\\-*$", "");
		}

		return parte2;
	}

	// FIM MÉTODOS RETIRARCONTAS

}
