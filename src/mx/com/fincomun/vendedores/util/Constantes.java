package mx.com.fincomun.vendedores.util;

import java.util.Properties;

public class Constantes {

    public final static String ARCHIVO_PROPERTIES = "./fcvendedoresws/conf/vendedoresws.properties";
    public static Properties properties = VendedoresProperties.cargaConfiguracion(ARCHIVO_PROPERTIES);

    public static final String PASSWORD_KEY = properties.getProperty("password.key");
    public static final String PASSWORD_IV = properties.getProperty("password.iv");

    public final static String GENERA_SERVICE_TOKEN = properties.getProperty("genera.token.security");
    public final static String VALIDA_SERVICE_TOKEN = properties.getProperty("valida.token.security");

    public final static String RECARGAS_SERVICE_QIUBO = properties.getProperty("recargas.service.qiubo");
    public final static String SERVICIOS_SERVICE_QIUBO = properties.getProperty("servicios.service.qiubo");
    public final static String CATALOGO_SERVICE_QIUBO = properties.getProperty("catalogo.service.qiubo");

    public static final String CODIGO_ERROR = "-3";
    public static final String CODIGO_ERROR_OPERACION = "-2";
    public static final String CODIGO_ERROR_REQUEST = "-1";
    public static final String CODIGO_EXITO = "0";

    public static final String MSJ_ERROR_LOGIN = "El usuario y/o contrase\u00f1a son incorrectos";
    public static final String MSJ_ERROR_TOKEN = "El token no es valido";
    public static final String MSJ_ERROR_USUARIO = "El usuario ya existe";
    public static final String MSJ_ERROR_NO_EXISTEN_TIPO_PAGO = "El tipo de pago no existe";
    public static final String MSJ_ERROR_STOCK = "No existen productos suficientes para la venta. Verifica tu inventario.";
    public static final String MSJ_ERROR_NO_EXISTEN_VENDEDORES = "No se obtuvieron vendedores";
    public static final String MSJ_ERROR_NO_EXISTE_TIPPO_SERVICIO = "El tipo de servicio no existe";
    public static final String MSJ_ERROR_NO_EXISTE_VENDEDOR = "El vendedor no existe";
    public static final String MSJ_ERROR_RECARGA = "No se realizo la recarga";
    public static final String MSJ_ERROR_PAGO_SERVICIO = "No se realizo el pago del servicio";
    public static final String MSJ_ERROR = "Ocurri\u00f3 un error al realizar la operaci\u00f3n.";
    public static final String MSJ_EXITO = "Operaci\u00f3n Exitosa.";

    public static final String SERVICIO_CFE_CODIGO = "19";
    public static final String SERVICIO_GAS_NATURAL_CODIGO = "14";
    public static final String SERVICIO_IZZI_CODIGO = "15";
    public static final String SERVICIO_MAXCOM_CODIGO = "21";
    public static final String SERVICIO_OMNIBUS_CODIGO = "16";
    public static final String SERVICIO_PASE_URBANO_CODIGO = "09";
    public static final String SERVICIO_TELEVIA_CODIGO = "10";
    public static final String SERVICIO_TELMEX_CODIGO = "11";
    public static final String SERVICIO_SKY_CODIGO = "17";
    public static final String SERVICIO_DISH_CODIGO = "12";

    public static final String SERVICIO_ARTICULOS = "00";

    public static final int TIPO_PAGO_EFECTIVO = 1;
    public static final int TIPO_PAGO_CODI = 3;

    public static final String listaServers=properties.getProperty("lista.servidores");
    public interface SMS {
        public static final String REGION_MX = "52";
        public static final String MSJ_TOKEN = "FinComun: Retiro Sin Tarjeta \n Tu clave de seguridad es: {0}";
        public static final String URL_REQUEST_MX = "https://app.smsmasivos.com.mx/components/api/api.envio.sms.php";
        public static final String API_KEY_MX = "2612d30f3dd98a206ccd2f8771adf99949ae2384";
    }
}
