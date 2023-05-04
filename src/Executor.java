import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dto.Funcionario;

public class Executor {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			System.out.println("Gostaria de Inserir um funcionário novo?");
			System.out.println("Digite para SIM ou NÃO");

			List<Funcionario> funcionarios = new ArrayList<>();

			// Caso queira utilizar o sistema de inserção apenas retirar os
			// comentários

			/*
			 * Inserir Funcionarios
			 */

			// String opcao = scanner.next().toUpperCase();

			/*
			 * while (opcao.equals("SIM")) { System.out.println("Nome: ");
			 * String nome = scanner.next();
			 * 
			 * System.out.println("Data de nascimento: "); LocalDate
			 * dtNascimento = LocalDate.parse(scanner.next(), formatter);
			 * 
			 * System.out.println("Salário: "); BigDecimal salario = new
			 * BigDecimal(scanner.next().replace(",", "."));
			 * 
			 * System.out.println("Funcao: "); String funcao = scanner.next();
			 * 
			 * funcionarios.add(new Funcionario(nome, dtNascimento, salario,
			 * funcao)); System.out.println("Novo funcionário adicionado!");
			 * System.out.println("Gostaria de Inserir um funcionário novo?");
			 * opcao = scanner.next().toUpperCase(); }
			 */
			/*
			 * Inserir Funcionarios
			 */

			funcionarios.add(new Funcionario("Maria", LocalDate.parse(
					"18/10/2000", formatter), new BigDecimal("2009.44"),
					"Operador"));
			funcionarios.add(new Funcionario("João", LocalDate.parse(
					"12/05/1990", formatter), new BigDecimal("2284.38"),
					"Operador"));
			funcionarios.add(new Funcionario("Caio", LocalDate.parse(
					"02/05/1961", formatter), new BigDecimal("9836.14"),
					"Coordenador"));
			funcionarios.add(new Funcionario("Miguel", LocalDate.parse(
					"14/10/1988", formatter), new BigDecimal("19119.88"),
					"Diretor"));
			funcionarios.add(new Funcionario("Alice", LocalDate.parse(
					"05/01/1995", formatter), new BigDecimal("2234.68"),
					"Recepcionista"));
			funcionarios.add(new Funcionario("Heitor", LocalDate.parse(
					"19/11/1999", formatter), new BigDecimal("1582.72"),
					"Operador"));
			funcionarios.add(new Funcionario("Arthur", LocalDate.parse(
					"31/03/1993", formatter), new BigDecimal("4071.84"),
					"Contador"));
			funcionarios.add(new Funcionario("Laura", LocalDate.parse(
					"08/07/1994", formatter), new BigDecimal("3017.45"),
					"Gerente"));
			funcionarios.add(new Funcionario("Heloísa", LocalDate.parse(
					"24/05/2003", formatter), new BigDecimal("1606.85"),
					"Eletricista"));
			funcionarios.add(new Funcionario("Helena", LocalDate.parse(
					"02/09/1996", formatter), new BigDecimal("2799.93"),
					"Gerente"));

			/*
			 * Excluir funcionarios
			 */
			System.out
					.println("Qual o nome do funcionário gostaria de remover?");
			String nome = scanner.nextLine();

			Iterator<Funcionario> iter = funcionarios.iterator();
			while (iter.hasNext()) {
				Funcionario funcionario = iter.next();
				if (funcionario.getNome().equals(nome)) {
					iter.remove();
				}
			}

			DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

			/*
			 * Impressão de todos os funcionarios
			 */

			for (Funcionario funcionario : funcionarios) {

				System.out.println("Nome: " + funcionario.getNome() + " - "
						+ "Data de nascimento: "
						+ funcionario.getDataNascimento().toString() + " - "
						+ "Salário: "
						+ formatoDecimal.format(funcionario.getSalario())
						+ " - " + "Função: " + funcionario.getFuncao());
			}

			/*
			 * Aumento salarios dos funcionarios em 10%
			 */
			for (Funcionario funcionario : funcionarios) {
				BigDecimal novoSalario = funcionario.getSalario();
				funcionario.setSalario(novoSalario.add(funcionario.getSalario()
						.multiply(new BigDecimal("1.10"))));
			}

			/*
			 * Criação do map
			 */
			Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
			for (Funcionario funcionario : funcionarios) {
				String funcao = funcionario.getFuncao();
				List<Funcionario> listaFuncionarios = funcionariosPorFuncao
						.getOrDefault(funcao, new ArrayList<Funcionario>());
				listaFuncionarios.add(funcionario);
				funcionariosPorFuncao.put(funcao, listaFuncionarios);
			}

			/*
			 * Impressão por função dos funcionarios
			 */
			for (String funcao : funcionariosPorFuncao.keySet()) {
				List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao
						.get(funcao);
				System.out.println("Funcionários da função " + funcao + ":");
				for (Funcionario funcionario : funcionariosDaFuncao) {
					System.out.println("- " + funcionario.getNome()
							+ ", Salário: R$ "
							+ formatoDecimal.format(funcionario.getSalario()));
				}
			}

			/*
			 * Impressão por funcionarios que nasceram no mes 10 e 12
			 */
			System.out
					.println("Funcionários com aniversário em outubro (mês 10) e dezembro (mês 12):");
			for (Funcionario funcionario : funcionarios) {
				if (funcionario.getDataNascimento().getMonthValue() == 10
						|| funcionario.getDataNascimento().getMonthValue() == 12) {
					System.out.println("Nome: " + funcionario.getNome() + " - "
							+ "Data de nascimento: "
							+ funcionario.getDataNascimento().toString()
							+ " - " + "Salário: "
							+ formatoDecimal.format(funcionario.getSalario())
							+ " - " + "Função: " + funcionario.getFuncao());
				}
			}

			/*
			 * Funcionarios mais velho
			 */

			Funcionario funcionarioMaisVelho = funcionarios.get(0);
			int idadeMaisVelho = funcionarioMaisVelho.getDataNascimento()
					.until(LocalDate.now()).getYears();

			for (int i = 1; i < funcionarios.size(); i++) {
				Funcionario funcionarioAtual = funcionarios.get(i);
				int idadeAtual = funcionarioAtual.getDataNascimento()
						.until(LocalDate.now()).getYears();

				if (idadeAtual > idadeMaisVelho) {
					funcionarioMaisVelho = funcionarioAtual;
					idadeMaisVelho = idadeAtual;
				}
			}
			System.out.println("Funcionário mais velho: "
					+ funcionarioMaisVelho.getNome() + ", Idade: "
					+ idadeMaisVelho);

			/*
			 * Impressão funcionarios por ordem alfabética
			 */
			Collections.sort(funcionarios, new Comparator<Funcionario>() {
				@Override
				public int compare(Funcionario f1, Funcionario f2) {
					return f1.getNome().compareTo(f2.getNome());
				}
			});
			System.out.println("Lista de funcionários em ordem alfabética:");
			for (Funcionario funcionario : funcionarios) {

				System.out.println("Nome: " + funcionario.getNome() + " - "
						+ "Data de nascimento: "
						+ funcionario.getDataNascimento().toString() + " - "
						+ "Salário: "
						+ formatoDecimal.format(funcionario.getSalario())
						+ " - " + "Função: " + funcionario.getFuncao());
			}

			/*
			 * Total de salários de todos os funcionarios
			 */
			BigDecimal totalSalarios = BigDecimal.ZERO;
			for (Funcionario funcionario : funcionarios) {
				totalSalarios = totalSalarios.add(funcionario.getSalario());
			}
			System.out.println("Total de salários: R$ "
					+ formatoDecimal.format(totalSalarios));

			/*
			 * Quantidade de salario minimo de cada salario
			 */
			BigDecimal salarioMinimo = new BigDecimal(scanner.next().replace(
					",", "."));
			for (Funcionario funcionario : funcionarios) {
				BigDecimal salario = funcionario.getSalario();
				System.out.println(funcionario.getNome()
						+ " recebe "
						+ salario.divide(salarioMinimo, 0, RoundingMode.FLOOR)
								.intValue() + " salários mínimos");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			scanner.close();
		}

	}

}
