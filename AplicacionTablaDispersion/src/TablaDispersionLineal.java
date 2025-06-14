class TablaDispersionLineal {
    private Cliente[] tabla;
    private boolean[] ocupado;
    private int tamaño;
    private int elementos;
    
    public TablaDispersionLineal(int tamaño) {
        this.tamaño = tamaño;
        this.tabla = new Cliente[tamaño];
        this.ocupado = new boolean[tamaño];
        this.elementos = 0;
    }
    
    private int funcionHash(String nombreCompleto) {
        int hash = 0;
        for (int i = 0; i < nombreCompleto.length(); i++) {
            hash = (hash * 31 + nombreCompleto.charAt(i)) % tamaño;
        }
        return Math.abs(hash);
    }
    
    public boolean insertar(Cliente cliente) {
        if (elementos >= tamaño) {
            return false; // Tabla llena
        }
        
        int indice = funcionHash(cliente.getNombreCompleto());
        int indiceOriginal = indice;
        
        // Reasignación lineal
        while (ocupado[indice]) {
            indice = (indice + 1) % tamaño;
            if (indice == indiceOriginal) {
                return false; // Tabla llena
            }
        }
        
        tabla[indice] = cliente;
        ocupado[indice] = true;
        elementos++;
        return true;
    }
    
    public Cliente buscar(String nombreCompleto) {
        int indice = funcionHash(nombreCompleto);
        int indiceOriginal = indice;
        
        while (ocupado[indice]) {
            if (tabla[indice].getNombreCompleto().equalsIgnoreCase(nombreCompleto)) {
                return tabla[indice];
            }
            indice = (indice + 1) % tamaño;
            if (indice == indiceOriginal) {
                break;
            }
        }
        
        return null;
    }
    
    public int getElementos() {
        return elementos;
    }
    
    public double factorCarga() {
        return (double) elementos / tamaño;
    }
}
