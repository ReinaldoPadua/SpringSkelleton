package edu.br.unoesc.app.produtos.services;

import edu.br.unoesc.app.produtos.dtos.ImagemDTO;
import edu.br.unoesc.app.produtos.dtos.ProdutoDTO;
import edu.br.unoesc.app.produtos.entities.Imagem;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.repositories.ImagemRepository;
import edu.br.unoesc.app.produtos.repositories.ProdutoRepository;
import edu.br.unoesc.app.util.Phraseology;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoImagemService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ImagemRepository imagemRepository;

    public List<ImagemDTO> listarTodasImagensDoProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId);
        if(produto==null)
            throw new RuntimeException(Phraseology.MENSAGEM_PRODUTO_NAO_EXISTE);
        List<Imagem> imagens = produto.getImagens();
        List<ImagemDTO> listaDeImagensDTO = new ArrayList<ImagemDTO>();
        imagens.forEach(imagem -> {
            ImagemDTO imagemDTO = new ImagemDTO(imagem.getId(),
                    imagem.getNome(),
                    imagem.getUrlArquivo());
            listaDeImagensDTO.add(imagemDTO);
        });
        return listaDeImagensDTO;
    }

    public ImagemDTO buscaImagemPorProdutoIdEimagemId(Long produtoId,Long imagemId) {
        Imagem imagem = imagemRepository.findByProdutoAndId(produtoId, imagemId);
        if(imagem==null)
            throw new RuntimeException(Phraseology.IMAGEM_NAO_EXISTE);

        ImagemDTO imagemDTO = new ImagemDTO(imagem.getId(),
                imagem.getNome(),
                imagem.getUrlArquivo());

        return  imagemDTO;
    }

    public ImagemDTO salvarNovaImagem(Long produtoId,ImagemDTO novaImagemDTO) {
        Imagem imagem = new Imagem();
        imagem.setNome(novaImagemDTO.getNome());
        imagem.setUrlArquivo(novaImagemDTO.getUrlArquivo());
        Produto produto = produtoRepository.findById(produtoId);
        imagem.setProduto(produto);
        imagem = imagemRepository.save(imagem);
        novaImagemDTO.setId(imagem.getId());
        return  novaImagemDTO;
    }

    public ImagemDTO atualizarImagem(Long produtoId,ImagemDTO imagemAtualizadaDTO) {

        try {
            Imagem imagem = imagemRepository.findByProdutoAndId(produtoId,imagemAtualizadaDTO.getId());
            imagem.setNome(imagemAtualizadaDTO.getNome());
            imagem.setDataAtualizacao(LocalDateTime.now());
            imagemRepository.save(imagem);
            imagemAtualizadaDTO.setUrlArquivo(imagemAtualizadaDTO.getUrlArquivo());
            return imagemAtualizadaDTO;
        } catch (Exception e){
            throw new RuntimeException(Phraseology.IMAGEM_NAO_PODE_SER_ALTERADA,e);
        }
    }

    public void deletarImagem(Long produtoId,Long imagemId) {
        Imagem imagem = imagemRepository.findByProdutoAndId(produtoId,imagemId);

        if(imagem==null){
            throw new RuntimeException(Phraseology.IMAGEM_NAO_EXISTE);
        }
        imagemRepository.delete(imagem);
    }
}
