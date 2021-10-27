function getContent(i, data) {
    return        `<tr>
                   <td>${i + 1}</td>
               <td>${data[i].name}</td>
               <td>${data[i].description}</td>
               <td>${data[i].image}</td>
               <td>${data[i].price}</td>
            </tr>`;
}
getAll();
function getAll() {
        $.ajax({
            type: "GET",
            url: "/products",
            success: function (data) {
                let content = "";
                for (let i = 0; i < data.length; i++) {
                    content += getContent(i, data)
                }
                document.getElementById("product").innerHTML = content;
            }
        });
}

function add() {
    let name = $('#name').val();
    let description = $('#description').val();
    let image = $('#image').val();
    let price = $('#price').val();
    let newProduct = {
        name: name,
        description: description,
        image: image,
        price: price
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newProduct),
        url: "/products",
        success: getAll

    })
}

function addNewProduct(){
    document.getElementById("add-product").innerHTML=`<table>
<tr>
<td><input type="text" id="name" placeholder="name"></td>
</tr>

<tr>
<td><input type="text" id="description" placeholder="description"></td>
</tr>

<tr>
<td><input type="text" id="image" placeholder="image"></td>
</tr>

<tr>
<td><input type="text" id="price" placeholder="price"></td>
</tr>

<tr>
<td><button value="add" onclick="add()"></button></td>
</tr>
</table>`
}