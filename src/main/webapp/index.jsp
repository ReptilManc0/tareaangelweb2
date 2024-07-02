<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente TODOS</title>
        <jsp:include page="PartialView/Header.jsp"></jsp:include>
        </head>
        <body>
            <div class="row m-0 p-0">
                <div class="col-md-12 py-2">
                    <h1>Mantenimiento Cliente</h1>
                </div>
                <div class="col-md-12">
                    <button class="btn btn-success btn-sm" id="btnAddCliente">
                        <i class="fa-solid fa-plus"></i>
                    </button>
                </div>
                <div class="col-md-9 table-responsive mt-2">
                    <div id="mostrardatos">
                        <table class="table table-sm table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Email</th>
                                    <th>Telefono</th>
                                    <th>Saldo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="bodyTable">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-3 mt-2">
                    <div class="card text-center bg-info text-white">
                        <div class="card-body">
                            <h5 class="card-title">Total Clientes</h5>
                            <p class="card-text" id="bodyCardTCliente"></p>
                        </div>
                    </div>
                    <div class="card text-center bg-success text-white mt-2">
                        <div class="card-body">
                            <h5 class="card-title">Total Saldo</h5>
                            <p class="card-text" id="bodyCardTSaldo"></p>
                        </div>
                    </div>
                    <div class="card text-center bg-warning text-white mt-2">
                        <div class="card-body">
                            <h5 class="card-title">Total de Clientes con Telefono</h5>
                            <p class="card-text"id="bodyCardTClienteTelf"></p>
                        </div>
                    </div>
                </div>
            </div>

        <jsp:include page="Modal/ClientsModals.jsp"></jsp:include>    
        <jsp:include page="PartialView/Footer.jsp"></jsp:include>
        <script src="https://cdn.socket.io/4.7.5/socket.io.min.js"
                integrity="sha384-2huaZvOR9iDzHqslqwpR87isEmrfxqyWOF7hr7BY6KG0+hVKLoEXMPUJw3ynWuhO"
                crossorigin="anonymous">
        </script>
        <script>
            var socket = io.connect("ws://localhost:1990", {forceNew: true});
            socket.on("connect", () => {
                console.log("Socket conectado: " + socket.connected + "\nSocket id:" + socket.id);
                //Para el servidor
                socket.emit("socket_user", "Se conectó un usuario");
            });
            
            $("#btnAgregarCliente").on("click", () => {
                socket.emit("actTabla");
            });
            $("#btnActualizarCliente").on("click", () => {
                socket.emit("actTabla");
            });
            $("#btnEliminarCliente").on("click", () => {
                socket.emit("actTabla");
            });

        </script>
        <script src="Scripts/ClienteFunctions.js"></script>
    </body>
</html>
