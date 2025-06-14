class TablaDispersionEncadenamiento {
    private ArbolBinarioBusqueda[] tabla;
    private int tamaño;
    private int elementos;
    
    public TablaDispersionEncadenamiento(int tamaño) {
        this.tamaño = tamaño;
        this.tabla = new ArbolBinarioBusqueda[tamaño];
        this.elementos = 0;
        
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new ArbolBinarioBusqueda();
        }
    }
    
    private int funcionHash(String nombreCompleto) {
        int hash = 0;
        for (int i = 0; i < nombreCompleto.length(); i++) {
            hash = (hash * 31 + nombreCompleto.charAt(i)) % tamaño;
        }
        return Math.abs(hash);
    }
    
    public void insertar(Cliente cliente) {
        int indice = funcionHash(cliente.getNombreCompleto());
        tabla[indice].insertar(cliente);
        elementos++;
    }
    
    public Cliente buscar(String nombreCompleto) {
        int indice = funcionHash(nombreCompleto);
        return tabla[indice].buscar(nombreCompleto);
    }
    
    public int getElementos() {
        return elementos;
    }
    
    public double factorCarga() {
        return (double) elementos / tamaño;
    }
}