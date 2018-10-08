package local.service;

import static local.util.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import local.model.Filme;
import local.model.Locacao;
import local.model.Cliente;
import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;

public class LocacaoService {
     int acc = 0;
     double acm=0;
//TODO atualizar para muitos filmes
    public Locacao alugarFilme(Cliente cliente, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
       
        if (cliente == null) {
            throw new LocadoraException("Impossível locar sem um usuário");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Nenhum filme foi selecionado");
        }

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);

        for(Filme filme: filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException("Filme sem estoque");
            }

            locacao.addFilme(filme);
            locacao.setDataLocacao(new Date());
            locacao.setValor(filme.getPrecoLocacao());
            acc=acc+1;
            switch (acc) {
                case 1: acm = acm+filme.getPrecoLocacao();
                break;
                
                case 2: acm = acm+filme.getPrecoLocacao();
                break;
                
                case 3: acm = acm+filme.getPrecoLocacao()*0.75;
                break;
                
                case 4: acm = acm+filme.getPrecoLocacao()*0.50;
                break;
                
                case 5: acm = acm+filme.getPrecoLocacao()*0;
                
                    break;
                    
                default:
                    throw new AssertionError();
            }

            //Entrega no dia seguinte
            Date dataEntrega = new Date();
            dataEntrega = adicionarDias(dataEntrega, 1);
            locacao.setDataRetorno(dataEntrega);
        }
        locacao.setValor(acm);
        //Salvando a locacao...	
        //TODO adicionar método para salvar
        return locacao;
    }
}
