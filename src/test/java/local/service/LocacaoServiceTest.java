package local.service;

import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;
import local.model.Filme;
import local.model.Cliente;
import local.model.Locacao;
import local.util.DataUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
//TODO atualizar testes para trabalhar com os multiplos filmes

public class LocacaoServiceTest {

    private List<Filme> filmes;
    private Cliente cliente;
    public static Double VALOR_FILME = 4.00;

    @Before
    public void setUp() {
        cliente = new Cliente("Angelo Gonçalves da Luz");

        filmes = Arrays.asList(
                new Filme("O Cavaleiro das Trevas", 2, VALOR_FILME),
                new Filme("Brilho Eterno de Uma Mente Sem Lembranças", 2, VALOR_FILME),
                new Filme("O Fabuloso Destino de Amelie Poulain", 3, VALOR_FILME),
                new Filme("Avatar", 4, VALOR_FILME),
                new Filme("Gladiador", 2, VALOR_FILME),
                new Filme("Brokeback Mountain", 4, VALOR_FILME),
                new Filme("O Senhor dos Anéis: A Sociedade do Anel", 5, VALOR_FILME),
                new Filme("Quem Quer Ser um Milionário", 2, VALOR_FILME),
                new Filme("A Lista de Schindler", 5, VALOR_FILME),
                new Filme("Guerra nas Estrelas", 9, VALOR_FILME)
        );
    }

    @Test
    public void naoDeveLocarFilmeSemUsuario() {
        //Cenário
        Cliente cliente = null;
        LocacaoService ls = new LocacaoService();

        //Processamento e validação
        try {
            //TODO: Corrigir parâmetro para teste
            ls.alugarFilme(cliente, null);
            fail("Locação realizada com usuário null");
        } catch (LocadoraException | FilmeSemEstoqueException ex) {
            assertEquals("Impossível locar sem um usuário", ex.getMessage());
            assertThat(ex.getMessage(), is(equalTo("Impossível locar sem um usuário")));
        }
    }

    @Test
    public void deveValidarValorLocacao() throws FilmeSemEstoqueException, LocadoraException {
        //TODO: Reescrever teste
        //Cenário

        //Processamento
        //Validação
    }

    @Test
    public void deveRealizarLocacao() {
        //TODO: Deve realizar uma locação caso os argumentos sejam apropriados
        //Cenário

        //Processamento
        //Validação
    }

    @Test
    public void deveDarDescontoParaLocacaoDeTresFilmes() throws FilmeSemEstoqueException, LocadoraException {
        LocacaoService ls = new LocacaoService();
        Locacao locacao = new Locacao();
        locacao = ls.alugarFilme(cliente, Arrays.asList(filmes.get(0), filmes.get(1), filmes.get(2)));

        assertEquals(11.00, locacao.getValor(), 0.001);

    }

    @Test
    public void deveDarDescontoParaLocacaoDeQuatroFilmes() throws FilmeSemEstoqueException, LocadoraException {
        LocacaoService ls = new LocacaoService();
        Locacao locacao = new Locacao();
        locacao = ls.alugarFilme(cliente, Arrays.asList(filmes.get(0), filmes.get(1), filmes.get(2), filmes.get(3)));

        assertEquals(13.00, locacao.getValor(), 0.001);
    }

    @Test
    public void deveDarDescontoParaLocacaoDeCincoFilmes() throws FilmeSemEstoqueException, LocadoraException {
        LocacaoService ls = new LocacaoService();
        Locacao locacao = new Locacao();
        locacao = ls.alugarFilme(cliente, Arrays.asList(filmes.get(0), filmes.get(1), filmes.get(2), filmes.get(3), filmes.get(4)));

        assertEquals(13.00, locacao.getValor(), 0.001);
    }

    @Test
    public void verificaDataEntrega() throws LocadoraException {
        //TODO: Deve entregar o filme sempre no dia posterior a retirada
        LocacaoService ls = new LocacaoService();

        Locacao locacao = ls.alugarFilme(cliente, Arrays.asList(filmes.get(0), filmes.get(1)));

        Date data = locacao.getDataRetorno();

        assertTrue(DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(1)));

    }

}
