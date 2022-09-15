package edu.br.unoesc.app.produtos.services;


import edu.br.unoesc.app.produtos.dtos.ImagensDTO;
import edu.br.unoesc.app.produtos.entities.Imagen;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.repositories.ImagenRepository;
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
    public ImagensDTO buscaImagenPorId(Long Id){
        Imagen imagen = ImagenRepository.findById(Id).getId();
        if(imagen==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        ImagensDTO imagenDTO = new ImagensDTO(imagen);
        return imagenDTO;
    }

    public ImagensDTO salvarNovoImagen(ImagensDTO imagenDTO) {

        Imagen imagenQueVaiSerGravado;

        if(imagenDTO.getId()!=null){
            Imagen verificaSeExisteImagen = ImagenRepository.findById(imagenDTO.getId());
            if(verificaSeExisteImagen!=null)
                throw new RuntimeException("MENSAGEM_PRODUTO_EXISTENTE");

        }

        imagenQueVaiSerGravado = new Imagen();

        return this.registrarImagen(imagenQueVaiSerGravado,imagenDTO);
    }

    public ImagensDTO atualizarImagen(ImagensDTO imagenDTO) {

        if(imagenDTO.getId()==null)
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");

        Imagen imagenQueVaiSerAtualizado = ImagenRepository.findById(imagenDTO.getId());

        if(imagenQueVaiSerAtualizado==null){
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        }

        boolean podeAlterar = this.validarAlteracaoImagen(imagenQueVaiSerAtualizado,"PRAZO_EM_DIAS_PARA_ALTERACAO");

        if(podeAlterar) {

            imagenQueVaiSerAtualizado.setDataAtualizacao(LocalDateTime.now());

            return this.registrarImagen(imagenQueVaiSerAtualizado,imagenDTO);

        } else throw new RuntimeException("MENSAGEM_NAO_PODE_ALTERAR_PRODUTO");


    }

    public void deletarImagen(Long imagenId){

        Imagen imagenQueVaiSerDeletado = ImagenRepository.findById(imagenId);

        if(imagenQueVaiSerDeletado==null){
            throw new RuntimeException("MENSAGEM_PRODUTO_NAO_EXISTE");
        }

        boolean podeAlterar = this.validarAlteracaoImagen(imagenQueVaiSerDeletado,"PRAZO_EM_DIAS_PARA_ALTERACAO");

        if(podeAlterar) {

            imagenQueVaiSerDeletado.setDataAtualizacao(LocalDateTime.now());

            this.imagenRepository.delete(imagenQueVaiSerDeletado);
        } else throw new RuntimeException("MENSAGEM_NAO_PODE_ALTERAR_PRODUTO");

    }

    private ImagensDTO registrarImagen(Imagen imagenQueVaiSerGravado, ImagensDTO imagenDTO ){

        ImagenRepository.save(imagenQueVaiSerGravado);
        imagenDTO.setId(imagenQueVaiSerGravado.getId());

        return  imagenDTO;
    }
    private boolean validarAlteracaoImagen(Produto produto, int prazoEmDiasParaAlteracao){

        Period diff = between(produto.getDataCriacao().toLocalDate(),
                LocalDate.now());
        return (diff.getDays()<=prazoEmDiasParaAlteracao) ? true : false;

    }
}
