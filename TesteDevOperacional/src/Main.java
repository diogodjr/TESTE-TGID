import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		// SIMULANDO BANCO DE DADOS

		List<Produto> carrinho = new ArrayList<Produto>();
		List<Venda> vendas = new ArrayList<Venda>();

		Empresa empresa = new Empresa(1, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
		Empresa empresa2 = new Empresa(2, "Level Varejo", "53239160000154", 0.05, 0.0);
		Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

		int IdEmpresa1 = 1;
		int IdEmpresa2 = 1;
		int IdEmpresa3 = 1;

		// ------------------------ SafeWay Padaria ------------------------//
		Produto produto1 = new Produto(IdEmpresa1++, "Pão Frances", 5, 3.50, empresa);
		Produto produto2 = new Produto(IdEmpresa1++, "Sonho", 5, 8.50, empresa);
		Produto produto3 = new Produto(IdEmpresa1++, "Croissant", 7, 6.50, empresa);
		Produto produto4 = new Produto(IdEmpresa1++, "Chá Gelado", 4, 5.50, empresa);

		// ------------------------ Level Varejo ------------------------//
		Produto produto5 = new Produto(IdEmpresa2++, "Coturno", 10, 400.0, empresa2);
		Produto produto6 = new Produto(IdEmpresa2++, "Jaqueta Jeans", 15, 150.0, empresa2);
		Produto produto7 = new Produto(IdEmpresa2++, "Calça Sarja", 15, 150.0, empresa2);

		// ------------------------ SafeWay Restaurante ------------------------//
		Produto produto8 = new Produto(IdEmpresa3++, "Prato feito - Frango", 10, 25.0, empresa3);
		Produto produto9 = new Produto(IdEmpresa3++, "Prato feito - Carne", 10, 25.0, empresa3);
		Produto produto10 = new Produto(IdEmpresa3++, "Suco Natural", 30, 10.0, empresa3);

		Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
		Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

		Usuario usuario1 = new Usuario("admin", "1234", null, null);
		Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
		Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
		Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
		Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
		Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);

		List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6);
		List<Cliente> clientes = Arrays.asList(cliente, cliente2);
		List<Empresa> empresas = Arrays.asList(empresa, empresa2, empresa3);
		List<Produto> produtos = Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7,
				produto8, produto9, produto10);

		executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
	}

	public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);
		boolean sair = false;

		while (!sair) {
			System.out.println("Entre com seu usuário ('sair' para encerrar):");
			System.out.print("Usuário: ");
			String username = sc.next();

			if (username.equalsIgnoreCase("sair")) {
				System.out.println("Encerrando o programa... Até logo!");
				sair = true;
				break;
			}

			System.out.print("Senha: ");
			String senha = sc.next();

			List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
					.collect(Collectors.toList());

			if (usuariosSearch.size() > 0) {
				Usuario usuarioLogado = usuariosSearch.get(0);
				if ((usuarioLogado.getSenha().equals(senha))) {

					if (usuarioLogado.getUsername().equals("admin")) {
						System.out.println("Bem-vindo, admin!");
						System.out.println("Escolha uma opção para iniciar:");
						System.out.println("1 - Listar todas as vendas");
						System.out.println("2 - Verificar todos os produtos");
						System.out.println("0 - Deslogar");

						int escolhaAdmin = sc.nextInt();

						switch (escolhaAdmin) {
						case 1: {
							System.out.println("************************************************************");
							System.out.println("Todas as Vendas:");
							vendas.stream().forEach(venda -> {
								System.out.println("Código da Venda: " + venda.getCodigo());
								System.out.println("Empresa: " + venda.getEmpresa().getNome());
								System.out.println("Cliente: " + venda.getCliente().getNome());
								System.out.println("Itens da Venda:");
								venda.getItens().forEach(item -> {
									System.out.println("Produto: " + item.getNome());
									System.out.println("Preço: R$" + item.getPreco());
								});
								System.out.println("Total: R$" + venda.getValor());
								System.out.println("************************************************************");
							});
							break;
						}
						case 2: {
							System.out.println("************************************************************");
							System.out.println("Todos os Produtos:");
							produtos.forEach(produto -> {
								System.out.println("Código do Produto: " + produto.getId());
								System.out.println("Produto: " + produto.getNome());
								System.out.println("Quantidade em estoque: " + produto.getQuantidade());
								System.out.println("Valor: R$" + produto.getPreco());
								System.out.println("Empresa: " + produto.getEmpresa().getNome());
								System.out.println("************************************************************");
							});
							break;
						}
						case 0: {
							System.out.println("Deslogando...");
							break;
						}
						default: {
							System.out.println("Opção inválida.");
							break;
						}
						}

					} else if (usuarioLogado.isEmpresa()) {
						System.out.println("1 - Listar vendas");
						System.out.println("2 - Ver produtos");
						System.out.println("0 - Deslogar");
						Integer escolha = sc.nextInt();

						switch (escolha) {
						case 1: {
							System.out.println();
							System.out.println("************************************************************");
							System.out.println("VENDAS EFETUADAS");
							vendas.stream().forEach(venda -> {
								if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
									System.out.println("************************************************************");
									System.out.println("Venda de código: " + venda.getCodigo() + " no CPF "
											+ venda.getCliente().getCpf() + ": ");
									venda.getItens().stream().forEach(x -> {
										System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
									});
									System.out.println("Total Venda: R$" + venda.getValor());
									System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
									System.out.println("Total Líquido  para empresa: " + "R$" 
											+ (venda.getValor() - venda.getComissaoSistema()));
									System.out.println("************************************************************");
								}

							});
							System.out.println("Saldo Empresa: " + "R$" + usuarioLogado.getEmpresa().getSaldo());
							System.out.println("************************************************************");

							executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
						}
						case 2: {
							System.out.println();
							System.out.println("************************************************************");
							System.out.println("MEUS PRODUTOS");
							produtos.stream().forEach(produto -> {
								if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
									System.out.println("************************************************************");
									System.out.println("Código: " + produto.getId());
									System.out.println("Produto: " + produto.getNome());
									System.out.println("Quantidade em estoque: " + produto.getQuantidade());
									System.out.println("Valor: R$" + produto.getPreco());
									System.out.println("************************************************************");
								}

							});
							System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
							System.out.println("************************************************************");

							executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
						}
						case 0: {
							System.out.println("Deslogando...");
							break;
						}
						}
					}

					else if (usuarioLogado.isCliente()) {
						boolean clienteLog = true;
						while (clienteLog) {
							System.out.println("1 - Realizar Compras");
							System.out.println("2 - Ver Compras");
							System.out.println("0 - Deslogar");
							Integer escolha = sc.nextInt();
							switch (escolha) {
							case 1: {
								System.out.println(
										"Para realizar uma compra, escolha a empresa onde deseja comprar: ");
								empresas.stream().forEach(x -> {
									System.out.println(x.getId() + " - " + x.getNome());
								});
								Integer escolhaEmpresa = sc.nextInt();
								Integer escolhaProduto = -1;

								carrinho.clear(); // Limpa o carrinho antes de adicionar produtos

								do {
									System.out.println("Escolha os seus produtos: ");
									produtos.stream().forEach(x -> {
										if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
											System.out.println(x.getId() + " - " + x.getNome());
										}
									});
									System.out.println("0 - Finalizar compra");
									escolhaProduto = sc.nextInt();
									for (Produto produtoSearch : produtos) {
										if (produtoSearch.getId().equals(escolhaProduto)
												&& produtoSearch.getEmpresa().getId().equals(escolhaEmpresa)) {
											carrinho.add(produtoSearch);
										}
									}
								} while (escolhaProduto != 0);

								System.out.println("************************************************************");
								System.out.println("Resumo da compra: ");
								carrinho.stream().forEach(x -> {
									System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
								});

								Empresa empresaEscolhida = empresas.stream()
										.filter(x -> x.getId().equals(escolhaEmpresa)).findFirst().orElse(null);
								if (empresaEscolhida != null) {
									Cliente clienteLogado = clientes.stream()
											.filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
											.findFirst().orElse(null);
									if (clienteLogado != null) {
										Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
										System.out.println("Total: R$" + venda.getValor());
									}
								}

								System.out.println("************************************************************");
								carrinho.clear();

								System.out.println("Compra finalizada. Voltando ao menu de opções...");
								break;
							}
							case 2: {
								System.out.println();
								System.out.println("************************************************************");
								System.out.println("COMPRAS EFETUADAS");
								vendas.stream().forEach(venda -> {
									if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
										System.out.println(
												"************************************************************");
										System.out.println("Compra de código: " + venda.getCodigo() + " na empresa "
												+ venda.getEmpresa().getNome() + ": ");
										venda.getItens().stream().forEach(x -> {
											System.out.println(
													x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
										});
										System.out.println("Total: R$" + venda.getValor());
										System.out.println(
												"************************************************************");
									}

								});
								break;
							}
							case 0: 
								System.out.println("Deslogando... Até logo!");
								clienteLog = false;
								break;
								
							default:
								System.out.println("Opção inválida.");
								break;
							}
						}

					} else {
						System.out.println("Opção de usuário não reconhecida.");
					}

				} else {
					System.out.println("Senha incorreta");
				}
			} else {
				System.out.println("Usuário não encontrado");
			}
		}
		sc.close();
	}

	public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
		Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
		Double comissaoSistema = total * empresa.getTaxa();
		int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCodigo() + 1;

		List<Produto> itensVenda = new ArrayList<>(carrinho); // Cria uma cópia dos itens do carrinho
		Venda venda = new Venda(idVenda, itensVenda, total, comissaoSistema, empresa, cliente);
		empresa.setSaldo(empresa.getSaldo() + total);
		vendas.add(venda);

		return venda;
	}
}