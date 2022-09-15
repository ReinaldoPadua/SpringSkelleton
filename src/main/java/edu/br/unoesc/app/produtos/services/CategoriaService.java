package edu.br.unoesc.app.produtos.services;

import edu.br.unoesc.app.produtos.dtos.CategoriaDTO;
import edu.br.unoesc.app.produtos.dtos.ProdutoDTO;
import edu.br.unoesc.app.produtos.entities.Categoria;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.Period.between;

public class CategoriaService {

    public static final String MENSAGEM_CATEGORIA_EXISTENTE = "Essa categoria ja existe na base de dados";

    public static final String MENSAGEM_NAO_PODE_ALTERAR_CATEGORIA = "Não é possivel Alterar uma categoria que ultrapassou o prazo de alteração";

    public static final String MENSAGEM_CATEGORIA_NAO_EXISTE = "Categoria não existe na base de dados";

    public static final int PRAZO_EM_DIAS_PARA_ALTERACAO = 2;
    @Autowired
    CategoriaRepository categoriaRepository;
    public List<CategoriaDTO> listarTodas() {
       List<CategoriaDTO> categoriasDTO = new ArrayList<>();
       List<Categoria> categorias = categoriaRepository.findAll();
       categorias.forEach(categoria ->{
           CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
              categoriasDTO.add(categoriaDTO);
       });
       return categoriasDTO;
    }

    public CategoriaDTO buscarCategoriaPorId(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId);
        if (categoria == null) {
            throw new RuntimeException(MENSAGEM_CATEGORIA_NAO_EXISTE);
        }
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
        return categoriaDTO;
    }

    public CategoriaDTO salvarNovaCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoriaQueVaiSerGravado;
        if (categoriaDTO.getId() != null) {
            Categoria verificaSeExisteCategoria = categoriaRepository.findById(categoriaDTO.getId());
            if (verificaSeExisteCategoria == null) {
                throw new RuntimeException(MENSAGEM_CATEGORIA_EXISTENTE);
            }
        }
        categoriaQueVaiSerGravado = new Categoria();

        return  this.registrarNovaCategoria(categoriaQueVaiSerGravado, categoriaDTO);

    }

    public CategoriaDTO atualizarCategoria(CategoriaDTO categoriaDTO) {

        if(categoriaDTO.getId()==null)
            throw new RuntimeException(MENSAGEM_CATEGORIA_NAO_EXISTE);

        Categoria categoriaQueVaiSerAtualizada = categoriaRepository.findById(categoriaDTO.getId());

        if(categoriaQueVaiSerAtualizada==null){
            throw new RuntimeException(MENSAGEM_CATEGORIA_NAO_EXISTE);
        }

        boolean podeAlterar = this.validarAlteracaoCategoria(categoriaQueVaiSerAtualizada,PRAZO_EM_DIAS_PARA_ALTERACAO);

        if(podeAlterar) {

            categoriaQueVaiSerAtualizada.setDataAtualizacao(LocalDateTime.now());

            return this.registrarNovaCategoria(categoriaQueVaiSerAtualizada,categoriaDTO);

        } else throw new RuntimeException(MENSAGEM_NAO_PODE_ALTERAR_CATEGORIA);


    }

    public void deletarCategoria(Long categoriaId) {

        Categoria categoriaQueVaiSerDeletado = categoriaRepository.findById(categoriaId);

        if(categoriaQueVaiSerDeletado==null){
            throw new RuntimeException(MENSAGEM_CATEGORIA_NAO_EXISTE );
        }

        boolean podeAlterar = this.validarAlteracaoCategoria(categoriaQueVaiSerDeletado,PRAZO_EM_DIAS_PARA_ALTERACAO);

        if(podeAlterar) {

            categoriaQueVaiSerDeletado.setDataAtualizacao(LocalDateTime.now());

            this.categoriaRepository.delete(categoriaQueVaiSerDeletado);
        } else throw new RuntimeException(MENSAGEM_NAO_PODE_ALTERAR_CATEGORIA);

    }

    private CategoriaDTO registrarNovaCategoria(Categoria categoriaQueVaiSerGravado, CategoriaDTO categoriaDTO ){
        categoriaQueVaiSerGravado.setNome(categoriaDTO.getNome());

        categoriaRepository.save(categoriaQueVaiSerGravado);
        categoriaDTO.setId(categoriaQueVaiSerGravado.getId());

        return  categoriaDTO;
    }

    private boolean validarAlteracaoCategoria(Categoria categoria, int prazoEmDiasParaAlteracao){

        Period diff = between(categoria.getDataCriacao().toLocalDate(),
                LocalDate.now());
        return (diff.getDays()<=prazoEmDiasParaAlteracao) ? true : false;

    }

}
