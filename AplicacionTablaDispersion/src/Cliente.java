class Cliente {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;
    private String codigoPostal;
    
    public Cliente(String codigo, String nombres, String apellidos, String telefono, 
                   String correo, String direccion, String codigoPostal) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }
    
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "codigo='" + codigo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }
}