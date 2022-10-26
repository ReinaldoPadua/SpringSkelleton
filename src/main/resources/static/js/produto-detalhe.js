
    const produtoId = window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);

    let produto = {};

    $.ajax({
        type: "GET",
        url: "/api/produtos/" + produtoId,
        success: function (response) {
            produto = response;
            console.log("produto", response);
            $("#nome").val(produto.nome),
                $("#descricao").val(produto.descricao),
                $("#valor").val(produto.valor)
        },
        dataType: "json",
        contentType: "application/json"
    });


    const salvar = () => {

        produto.nome = $("#nome").val(),
            produto.descricao = $("#descricao").val(),
            produto.valor = $("#valor").val()
        console.log(produto);
        $.ajax({
            type: "PATCH",
            url: "/api/produtos/",
            data: JSON.stringify(produto),
            success: function () {
                alert("Deu boa!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // log the error to the console
                console.log("The following error occured: " + textStatus, errorThrown);
            },
            dataType: "json",
            contentType: "application/json"
        });

    }
