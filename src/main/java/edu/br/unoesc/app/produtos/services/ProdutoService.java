package edu.br.unoesc.app.produtos.services;

import edu.br.unoesc.app.produtos.dtos.ImagemDTO;
import edu.br.unoesc.app.produtos.dtos.ProdutoDTO;
import edu.br.unoesc.app.produtos.entities.Imagem;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.entities.Categoria;
import edu.br.unoesc.app.produtos.repositories.CategoriaRepository;
import edu.br.unoesc.app.produtos.repositories.ProdutoRepository;
import edu.br.unoesc.app.util.Phraseology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.Period.*;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;
    public List<ProdutoDTO> listarTodos(){
        List<ProdutoDTO> listaProdutosDTO = new ArrayList<ProdutoDTO>();
        List<Produto> produtos= produtoRepository.findAll();
        produtos.forEach(produto -> {
            ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(),produto.getNome(),produto.getDescricao(),
                    produto.getValor(),produto.getCategoria().getId(),produto.getCategoria().getNome());
            listaProdutosDTO.add(produtoDTO);
        });
        return listaProdutosDTO;
    }

    public ProdutoDTO buscaProdutoPorId(Long produtoId){
        Produto produto = produtoRepository.findById(produtoId);
        if(produto==null)
            throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_NAO_EXISTE);
        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(),produto.getNome(),produto.getDescricao(),
                produto.getValor(),produto.getCategoria().getId(),produto.getCategoria().getNome());
        return produtoDTO;
    }

    public ProdutoDTO salvarNovoProduto(ProdutoDTO produtoDTO) {

        Produto produtoQueVaiSerGravado;

        if(produtoDTO.getId()!=null){
              Produto verificaSeExisteProduto = produtoRepository.findById(produtoDTO.getId());
              if(verificaSeExisteProduto!=null)
                  throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_EXISTENTE);

        }

        produtoQueVaiSerGravado = new Produto();

        return this.registrarProduto(produtoQueVaiSerGravado,produtoDTO);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO) {

        if(produtoDTO.getId()==null)
            throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_NAO_EXISTE);

        Produto produtoQueVaiSerAtualizado = produtoRepository.findById(produtoDTO.getId());

        if(produtoQueVaiSerAtualizado==null){
            throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_NAO_EXISTE);
        }

        boolean podeAlterar = this.validarAlteracaoProduto(produtoQueVaiSerAtualizado,Phraseology.PRAZO_EM_DIAS_PARA_ALTERACAO);

        if(podeAlterar) {

            produtoQueVaiSerAtualizado.setDataAtualizacao(LocalDateTime.now());

            return this.registrarProduto(produtoQueVaiSerAtualizado,produtoDTO);

        } else throw new RuntimeException(Phraseology.MENSAGEM_NAO_PODE_ALTERAR_PRODUTO);


    }

    public void deletarProduto(Long produtoId){

        Produto produtoQueVaiSerDeletado = produtoRepository.findById(produtoId);

        if(produtoQueVaiSerDeletado==null){
            throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_NAO_EXISTE);
        }

        boolean podeAlterar = this.validarAlteracaoProduto(produtoQueVaiSerDeletado,Phraseology.PRAZO_EM_DIAS_PARA_ALTERACAO);

        if(podeAlterar) {

            produtoQueVaiSerDeletado.setDataAtualizacao(LocalDateTime.now());

            this.produtoRepository.delete(produtoQueVaiSerDeletado);
        } else throw new RuntimeException(Phraseology.MENSAGEM_NAO_PODE_ALTERAR_PRODUTO);

    }

    private ProdutoDTO registrarProduto(Produto produtoQueVaiSerGravado, ProdutoDTO produtoDTO ){

        try{
            Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId()).get();

            if(categoria==null)
                throw new RuntimeException(Phraseology.MENSAGEM_CATEGORIA_NAO_EXISTE);

            produtoQueVaiSerGravado.setCategoria(categoria);

        }catch (Exception e) {
            throw new RuntimeException(Phraseology.MENSAGEM_CATEGORIA_NAO_EXISTE,e);
        }

        produtoQueVaiSerGravado.setNome(produtoDTO.getNome());
        produtoQueVaiSerGravado.setDescricao(produtoDTO.getDescricao());
        produtoQueVaiSerGravado.setValor(produtoDTO.getValor());

        produtoRepository.save(produtoQueVaiSerGravado);
        produtoDTO.setId(produtoQueVaiSerGravado.getId());

        return  produtoDTO;
    }

    private boolean validarAlteracaoProduto(Produto produto, int prazoEmDiasParaAlteracao){

        Period diff = between(produto.getDataCriacao().toLocalDate(),
                LocalDate.now());
        return (diff.getDays()<=prazoEmDiasParaAlteracao) ? true : false;

    }



}
