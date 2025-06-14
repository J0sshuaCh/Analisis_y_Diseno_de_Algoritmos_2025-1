class ArbolBinarioBusqueda {
    private NodoArbol raiz;
    
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }
    
    public void insertar(Cliente cliente) {
        raiz = insertarRecursivo(raiz, cliente);
    }
    
    private NodoArbol insertarRecursivo(NodoArbol nodo, Cliente cliente) {
        if (nodo == null) {
            return new NodoArbol(cliente);
        }
        
        int comparacion = cliente.getNombreCompleto().compareToIgnoreCase(
            nodo.cliente.getNombreCompleto());
        
        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, cliente);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, cliente);
        }
        
        return nodo;
    }
    
    public Cliente buscar(String nombreCompleto) {
        return buscarRecursivo(raiz, nombreCompleto);
    }
    
    private Cliente buscarRecursivo(NodoArbol nodo, String nombreCompleto) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = nombreCompleto.compareToIgnoreCase(
            nodo.cliente.getNombreCompleto());
        
        if (comparacion == 0) {
            return nodo.cliente;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierdo, nombreCompleto);
        } else {
            return buscarRecursivo(nodo.derecho, nombreCompleto);
        }
    }
    
    public boolean isEmpty() {
        return raiz == null;
    }
}