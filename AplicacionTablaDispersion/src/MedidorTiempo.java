class MedidorTiempo {
    public static class ResultadoTiempo {
        public long tiempoInsercion;
        public long tiempoBusqueda;
        public int elementosInsertados;
        
        public ResultadoTiempo(long tiempoInsercion, long tiempoBusqueda, int elementosInsertados) {
            this.tiempoInsercion = tiempoInsercion;
            this.tiempoBusqueda = tiempoBusqueda;
            this.elementosInsertados = elementosInsertados;
        }
    }
    
    public static ResultadoTiempo medirRendimientoLineal(Cliente[] clientes) {
        TablaDispersionLineal tabla = new TablaDispersionLineal(clientes.length * 2);
        
        // Medir tiempo de inserción
        long inicioInsercion = System.nanoTime();
        int insertados = 0;
        for (Cliente cliente : clientes) {
            if (tabla.insertar(cliente)) {
                insertados++;
            }
        }
        long finInsercion = System.nanoTime();
        
        // Medir tiempo de búsqueda
        long inicioBusqueda = System.nanoTime();
        for (Cliente cliente : clientes) {
            tabla.buscar(cliente.getNombreCompleto());
        }
        long finBusqueda = System.nanoTime();
        
        return new ResultadoTiempo(
            finInsercion - inicioInsercion,
            finBusqueda - inicioBusqueda,
            insertados
        );
    }
    
    public static ResultadoTiempo medirRendimientoEncadenamiento(Cliente[] clientes) {
        TablaDispersionEncadenamiento tabla = new TablaDispersionEncadenamiento(clientes.length);
        
        // Medir tiempo de inserción
        long inicioInsercion = System.nanoTime();
        for (Cliente cliente : clientes) {
            tabla.insertar(cliente);
        }
        long finInsercion = System.nanoTime();
        
        // Medir tiempo de búsqueda
        long inicioBusqueda = System.nanoTime();
        for (Cliente cliente : clientes) {
            tabla.buscar(cliente.getNombreCompleto());
        }
        long finBusqueda = System.nanoTime();
        
        return new ResultadoTiempo(
            finInsercion - inicioInsercion,
            finBusqueda - inicioBusqueda,
            clientes.length
        );
    }
}