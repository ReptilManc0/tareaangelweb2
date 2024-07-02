<!-- Modal para agregar cliente -->
<div class="modal fade" id="modalAddCliente" tabindex="-1" role="dialog" aria-labelledby="modalAddClienteLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalAddClienteLabel">Agregar Nuevo Cliente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="frmCliente">
          <div class="form-group">
            <label for="descnombre">Nombre</label>
            <input type="text" class="form-control" id="descnombre" name="descnombre" required>
          </div>
          <div class="form-group">
            <label for="descapellido">Apellido</label>
            <input type="text" class="form-control" id="descapellido" name="descapellido" required>
          </div>
          <div class="form-group">
            <label for="descemail">Email</label>
            <input type="email" class="form-control" id="descemail" name="descemail" required>
          </div>
          <div class="form-group">
            <label for="numtelefono">Teléfono</label>
            <input type="text" class="form-control" id="numtelefono" name="numtelefono" required>
          </div>
          <div class="form-group">
            <label for="mntsaldo">Saldo</label>
            <input type="number" class="form-control" id="mntsaldo" name="mntsaldo" required>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary" id="btnAgregarCliente">Agregar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal para actualizar cliente -->
<div class="modal fade" id="modalUpdateCliente" tabindex="-1" role="dialog" aria-labelledby="modalUpdateClienteLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalUpdateClienteLabel">Actualizar Saldo del Cliente</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="frmUpdateCliente">
                    <input type="hidden" id="idClienteUpdate" name="idcliente">
                    <div class="form-group">
                        <label for="nombreClienteUpdate">Nuevo Nombre</label>
                        <input type="text" class="form-control" id="nombreClienteUpdate" name="descnombre" required>
                        <label for="apellidoClienteUpdate">Nuevo Apellido</label>
                        <input type="text" class="form-control" id="apellidoClienteUpdate" name="descapellido" required>
                        <label for="emailClienteUpdate">Nuevo Email</label>
                        <input type="text" class="form-control" id="emailClienteUpdate" name="descemail" required>
                        <label for="telfonoClienteUpdate">Nuevo Telefono</label>
                        <input type="text" class="form-control" id="telfonoClienteUpdate" name="numtelefono" required>
                        <label for="saldoClienteUpdate">Nuevo Saldo</label>
                        <input type="number" class="form-control" id="saldoClienteUpdate" name="mntsaldo" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" id="btnActualizarCliente">Actualizar</button>
            </div>
        </div>
    </div>
</div>