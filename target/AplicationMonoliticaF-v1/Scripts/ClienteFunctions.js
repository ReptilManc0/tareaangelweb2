
// Mostrar modal para agregar cliente
$("#btnAddCliente").on("click", () => {
    $("#modalAddCliente").modal("show");
});

// Agregar nuevo cliente
$("#btnAgregarCliente").on("click", () => {
    var formData = $("#frmCliente").serialize() + "&type=add";
    $.ajax({
        type: "POST",
        data: formData,
        url: "ServletCliente",
        success: function (arg) {
            if (arg.success) {
                   var socket = io.connect("ws://localhost:1990", {forceNew: true});
    socket.on("connect", () => {
                console.log("Socket conectado: " + socket.connected + "\nSocket id:" + socket.id);
                //Para el servidor
                socket.emit("socket_user", "Se conectó un usuario");
            });
                alert("Cliente agregado correctamente");
                $("#modalAddCliente").modal("hide");
                $("#frmCliente")[0].reset(); // Limpiar el formulario
                cargarClientes(); // Función para recargar la lista de clientes
            } else {
                alert("Error: " + arg.message);
            }
        },
        error: function () {
            alert("Error al procesar la solicitud");
        }
    });
});
// Función para cargar la lista de clientes
function cargarClientes() {
    $.ajax({
        type: "GET",
        data: { type: "1" },
        url: "ServletCliente",
        success: function(arg) {
            var content = "";
            for (var i = 0; i < arg.body.length; i++) {
                content += `<tr>`;
                content += `<td>${arg.body[i].idcliente}</td>`;
                content += `<td>${arg.body[i].descnombre}</td>`;
                content += `<td>${arg.body[i].descapellido}</td>`;
                content += `<td>${arg.body[i].descemail}</td>`;
                content += `<td>${arg.body[i].numtelefono}</td>`;
                content += `<td>${arg.body[i].mntsaldo}</td>`;
                content += `<td>
                                <button class="btn btn-sm btn-warning btnActualizarCliente" id="btnActualizarCliente" data-id="${arg.body[i].idcliente}"><i class="fa-solid fa-marker"></i></button>
                                <button class="btn btn-sm btn-danger btnEliminarCliente" id="btnEliminarCliente" data-id="${arg.body[i].idcliente}"><i class="fa-solid fa-trash-can"></i></button>
                            </td>`;
                content += `</tr>`;
            }
            $("#bodyTable").html(content);
        }
    });
}

// Cargar clientes al iniciar la página
$(document).ready(function() {
    
    cargarClientes();
});

// Eliminar cliente
$("body").on("click", ".btnEliminarCliente", function () {
    var idCliente = $(this).data("id");
    if (confirm('¿Estás seguro de que deseas eliminar este cliente?')) {
        $.ajax({
            type: "POST",
            url: "ServletCliente",
            data: {
                type: "delete",
                idCliente: idCliente
            },
            beforeSend: function () {
                // Opcional: código para mostrar un loader mientras se envía la solicitud
            },
            success: function (arg) {
                console.log(arg);
                if (arg.success) {
                    
                    socket.emit("actTabla");
                    alert("Cliente eliminado correctamente");
                    location.reload(); // Recargar la página para ver los cambios
                } else {
                    alert("Error: " + arg.message);
                }
            }
        });
    }
});
// Mostrar modal para actualizar cliente
$("body").on("click", ".btnActualizarCliente", function () {
    var idCliente = $(this).data("id");
    var saldoActual = $(this).closest("tr").find("td:eq(5)").text();
    $("#idClienteUpdate").val(idCliente);
    $("#saldoClienteUpdate").val(saldoActual);
    $("#modalUpdateCliente").modal("show");
});

// Actualizar cliente
$("#btnActualizarCliente").on("click", function () {
    var idCliente = $("#idClienteUpdate").val();
    var nombre = $("#nombreClienteUpdate").val();
    var apellido = $("#apellidoClienteUpdate").val();
    var email = $("#emailClienteUpdate").val();
    var numTelefono = $("#telfonoClienteUpdate").val();
    var nuevoSaldo = $("#saldoClienteUpdate").val();
    $.ajax({
        type: "POST",
        url: "ServletCliente",
        data: {
            type: "update",
            idcliente: idCliente,
            descNombre:nombre,
            descApellido:apellido,
            descEmail:email,
            numTelefono:numTelefono,
            mntsaldo: nuevoSaldo
        },
        success: function (arg) {
            if (arg.success) {
                
                alert("Cliente actualizado correctamente");
                $("#modalUpdateCliente").modal("hide");
                location.reload(); // Recargar la página para ver los cambios
            } else {
                alert("Error: " + arg.message);
            }
        }
    });
});
$(document).on("click", ".btnEliminarCliente", function() {
    // El código de eliminación se maneja aquí
    // Después de una eliminación exitosa:
    socket.emit("actTabla");
});
socket.on("CambiarTabla", (arg) => {
                
                $("#bodyTable").html("");
                cargarClientes();
            });