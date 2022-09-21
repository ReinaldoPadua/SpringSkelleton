package edu.br.unoesc.app.produtos.services;


import edu.br.unoesc.app.produtos.dtos.ImagensDTO;
import edu.br.unoesc.app.produtos.entities.Imagen;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.repositories.ImagenRepository;
import edu.br.unoesc.app.produtos.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.Period.between;

@Service
public class ImagenService {
    public static final String MENSAGEM_PRODUTO_EXISTENTE = "Esse produto ja existe na base de dados";

    public static final String MENSAGEM_PRODUTO_NAO_EXISTE = "Esse produto nao existe na base de dados";

    public static final String MENSAGEM_NAO_PODE_ALTERAR_PRODUTO =
            "Não é possivel Alterar um produto que ultrapassou o prazo de alteração";

    public static final String MENSAGEM_CATEGORIA_NAO_EXISTE = "Categoria não existe na base de dados";

    public static final int PRAZO_EM_DIAS_PARA_ALTERACAO = 2;

    @Autowired
    ImagenRepository imagenRepository;

    public List<ImagensDTO> listarTodos() {
        List<ImagensDTO> imagenDTO = new ArrayList<>();
        List<Imagen> imagens = imagenRepository.findAll();
        imagens.forEach(imagen -> {
            ImagensDTO imagensDTO = new ImagensDTO(imagen);
            imagenDTO.add(imagensDTO);
        });
        return imagenDTO;
    }
    public ImagensDTO buscaImagenPorId(Long imagenId){
        Imagen imagen = imagenRepository.findById(imagenId);
        if(imagen==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        ImagensDTO imagenDTO = new ImagensDTO(imagen);
        return imagenDTO;
    }

    public ImagensDTO salvarNovoImagen(ImagensDTO imagenDTO) {

        Imagen imagenQueVaiSerGravado;

        if(imagenDTO.getId()!=null){
            Imagen verificaSeExisteImagen = imagenRepository.findById(imagenDTO.getId());
            if(verificaSeExisteImagen!=null)
                throw new RuntimeException("MENSAGEM_PRODUTO_EXISTENTE");

        }

        imagenQueVaiSerGravado = new Imagen();

        return this.registrarImagen(imagenQueVaiSerGravado,imagenDTO);
    }

    public ImagensDTO atualizarImagen(ImagensDTO imagenDTO) {
        Imagen imagenQueVaiSerGravado;

        if(imagenDTO.getId()==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");

        imagenQueVaiSerGravado = imagenRepository.findById(imagenDTO.getId());

        if(imagenQueVaiSerGravado==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");

        return this.registrarImagen(imagenQueVaiSerGravado,imagenDTO);
    }

    public void deletarImagen(Long imagenId){
        Imagen imagenQueVaiSerDeletado = imagenRepository.findById(imagenId);
        if(imagenQueVaiSerDeletado==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        imagenRepository.delete(imagenQueVaiSerDeletado);

    }

    private ImagensDTO registrarImagen(Imagen imagenQueVaiSerGravado, ImagensDTO imagenDTO ){
        try {
            Imagen imagen = imagenRepository.findById(imagenDTO.getId());
            if(imagen==null)
                throw new RuntimeException("MENSAGEM_IMAGEN_NAO_EXISTE");

            imagenQueVaiSerGravado.setProduto(imagen.getProduto());
        }
        catch (Exception e){
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        }

        imagenQueVaiSerGravado.setUrlArquivo(imagenDTO.getUrlArquivo());
        imagenRepository.save(imagenQueVaiSerGravado);
        return new ImagensDTO(imagenQueVaiSerGravado);
    }
}
