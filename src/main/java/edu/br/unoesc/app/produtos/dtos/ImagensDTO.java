package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Imagen;

public class ImagensDTO {

    private Long id;

    public ImagensDTO(Imagen imagen) {
        super();
        if (imagen.getId()!=null)
            this.id = imagen.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
