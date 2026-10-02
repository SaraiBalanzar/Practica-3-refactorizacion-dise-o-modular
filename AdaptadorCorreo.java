public class AdaptadorCorreo implements Notificador {
    private CorreoLegacy correo;

    AdaptadorCorreo(CorreoLegacy correo) {
        this.correo = correo;
    }

    @Override
    public void enviar(String destino, String mensaje) {
        correo.send_email(destino, mensaje);
    }
}
