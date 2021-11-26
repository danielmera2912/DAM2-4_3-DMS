data class Tienda(val nombre: String, val clientes: List<Clientes>){
    fun obtenerConjuntoDeClientes(): Set<Clientes> = clientes.toSet()
    fun obtenerCiudadesDeClientes(): Set<Ciudad> = clientes.map{it.ciudad}.toSet()

    fun obtenerClientesPor(ciudad:Ciudad): List<Clientes> {
        return clientes.filter{it.ciudad==ciudad}
    }
    fun checkTodosClientesSonDe(ciudad : Ciudad): Boolean {
        return clientes.all{it.ciudad==ciudad}

    }
    fun hayClientesDe(ciudad: Ciudad): Boolean{
        return clientes.any{it.ciudad==ciudad}
    }
    fun cuentaClientesDe(ciudad: Ciudad): Int{
        return clientes.count(){it.ciudad==ciudad}
    }
    fun encuentraClienteDe(ciudad: Ciudad): Clientes?{
        return clientes.find{it.ciudad==ciudad}
    }
    fun obtenerClientesOrdenadosPorPedidos(): List<Clientes> = clientes.sortedByDescending { it.pedidos.size }

    fun obtenerClientesConPedidosSinEngregar(): Set<Clientes>{
        clientes.map{
            val (clientesSi,clientesNo) = it.pedidos.partition { it.estaEntregado }
            clientesNo.size>clientesSi.size
        }
        return clientes.toSet()
    }
    fun obtenerProductosPedidos(): Set<Producto> = clientes.flatMap(Clientes::obtenerProductosPedidos).toSet()
    fun obtenerNumeroVecesProductoPedido(producto: Producto): Int {
        return clientes.count(){it.obtenerProductosPedidos()==obtenerProductosPedidos()}
    }
    fun agrupaClientesPorCiudad(): Map<Ciudad, List<Clientes>> = clientes.groupBy { it.ciudad }
}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {

    override fun toString() = "$nombre from ${ciudad.nombre}"
    fun obtenerProductosPedidos(): List<Producto> = pedidos.flatMap(Pedido::productos).toList()
    fun encuentraProductoMasCaro(): Producto? {
        var p1=pedidos.filter { it.estaEntregado }
        return p1.flatMap(Pedido::productos).maxByOrNull { it.precio }
    }
    fun dineroGastado(): Double = pedidos.flatMap(Pedido::productos).sumOf { it.precio }
}

data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean)

data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}
fun main(args: Array<String>) {
    var c1=Ciudad("Almería")
    var c2=Ciudad("Cádiz")
    val numbers = listOf("one", "two", "three", "four")
}