window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesProdutos');

    let dataTable =   new simpleDatatables.DataTable(datatablesSimple,);

    fetch('api/produtos/')
        .then((response) => response.json())
        .then((dataResponse) =>   {

            let headings =Object.keys(dataResponse[0]);
            headings.push("Detalhes");
            console.log(headings);
            const data= dataResponse.map(obj => {
                let arrayValues = Object.values(obj).map(val => val.toString());
                arrayValues[6]= "<a href='/produtos/"  + arrayValues[0] + "'>Detalhes</a>";
                return arrayValues;
            });
            console.log(data);
            dataTable.insert({headings, data})
        });



});
