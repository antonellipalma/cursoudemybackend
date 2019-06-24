package com.map.primeiroprojeto;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.map.primeiroprojeto.domain.Categoria;
import com.map.primeiroprojeto.domain.Cidade;
import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.domain.Endereco;
import com.map.primeiroprojeto.domain.Estado;
import com.map.primeiroprojeto.domain.ItemPedido;
import com.map.primeiroprojeto.domain.Pagamento;
import com.map.primeiroprojeto.domain.PagamentoComBoleto;
import com.map.primeiroprojeto.domain.PagamentoComCartao;
import com.map.primeiroprojeto.domain.Pedido;
import com.map.primeiroprojeto.domain.Produto;
import com.map.primeiroprojeto.domain.enums.EstadoPagamento;
import com.map.primeiroprojeto.domain.enums.TipoCliente;
import com.map.primeiroprojeto.repositories.CategoriaRepository;
import com.map.primeiroprojeto.repositories.CidadeRepository;
import com.map.primeiroprojeto.repositories.ClienteRepository;
import com.map.primeiroprojeto.repositories.EnderecoRepository;
import com.map.primeiroprojeto.repositories.EstadoRepository;
import com.map.primeiroprojeto.repositories.ItemPedidoRepository;
import com.map.primeiroprojeto.repositories.PagamentoRepository;
import com.map.primeiroprojeto.repositories.PedidoRepository;
import com.map.primeiroprojeto.repositories.ProdutoRepository;



@SpringBootApplication
public class PrimeiroProjetoApplication implements CommandLineRunner {

	
	@Autowired
	CategoriaRepository repoCat;
	
	@Autowired
	ProdutoRepository repoProd;
	
	@Autowired
	CidadeRepository repoCid;
	
	@Autowired
	EstadoRepository repoEst;
	
	@Autowired
	ClienteRepository repoCli;
	
	@Autowired
	EnderecoRepository repoEnd;
	
	@Autowired
	PagamentoRepository repoPaga;
	
	@Autowired
	PedidoRepository repoPedi;
	
	@Autowired
	ItemPedidoRepository repoItemPedi;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		//CATEGORIA e PRODUTO ------------------------------
		Categoria cat1= new Categoria(null,"Informatica");
		Categoria cat2= new Categoria(null,"Escritorio"); 
		
		Produto p1= new Produto(null,"Computador",2000.00);
		Produto p2= new Produto(null,"Impressora",800.00);
		Produto p3= new Produto(null, "Mouse",80.00);
				
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p1.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		
		//ESTADO e CIDADE ------------------------------
		Estado est1= new Estado(null,"Minas Gerais");
		Estado est2= new Estado(null,"Sao Paulo");
		
		Cidade c1= new Cidade(null,"Uberlandia",est1);
		Cidade c2= new Cidade(null,"Sao Paulo",est2);
		Cidade c3= new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
	
		Cliente cli1= new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","9383893"));
		
		Endereco e1= new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",c1,cli1);
		Endereco e2= new Endereco(null,"Avenida Matos","105","Sala800","Centro","38777012",c2,cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		//Instancia o objeto que trata de DATA e HORA
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		
		//Pedidos e pagamentos-------------------
		
		Pedido ped1= new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1,e1);
		Pedido ped2= new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1,e2);
			
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		ItemPedido ip1=new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2=new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3=new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		//ped1.setItens((Set<ItemPedido>) Arrays.asList(ip1,ip2));
		//ped2.setItens((Set<ItemPedido>) Arrays.asList(ip3));
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		//------------------------------
		
		repoCat.saveAll(Arrays.asList(cat1,cat2));
		repoProd.saveAll(Arrays.asList(p1,p2,p3));
		repoEst.saveAll(Arrays.asList(est1,est2));
		repoCid.saveAll(Arrays.asList(c1,c2,c3));
		
		repoCli.saveAll(Arrays.asList(cli1));
		repoEnd.saveAll(Arrays.asList(e1,e2));
		
		repoPedi.saveAll(Arrays.asList(ped1,ped2));
		repoPaga.saveAll(Arrays.asList(pagto1,pagto2));
		
		repoItemPedi.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
