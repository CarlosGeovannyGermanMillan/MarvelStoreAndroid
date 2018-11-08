package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.davidleal.marvelstore.Interfaces.ITerminosCondiciones;
import com.example.davidleal.marvelstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertaSesion extends BaseActivity implements ITerminosCondiciones {

    @BindView(R.id.alerta_wbv_notificacion)
    WebView wbvTerminosCondiciones;
    @BindView(R.id.alerta_pgb_Cargando)
    ProgressBar pgbCargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta_sesion);
        ButterKnife.bind(this);

        wbvTerminosCondiciones.setWebViewClient(new TerminosYCondiciones());
        String mostrar = "<html>\n" +
                "<body>\n" +
                "LEA CUIDADOSAMENTE LOS TÉRMINOS Y CONDICIONES DEL PRESENTE DOCUMENTO, ANTES DE LA UTILIZACIÓN DE ESTE PRODUCTO. ESTE CONTIENE SOFTWARE, CUYO USO ES OBJETO DE UNA LICENCIA OTORGADA POR EXPERTOS EN SOFTWARE S.L. (DE AQUÍ EN ADELANTE DENOMINADA MarvelStore) A FAVOR DE USTED, EL USUARIO FINAL ORIGINAL, PARA SU USO EXCLUSIVO COMO SE INDICA. SI USTED NO ESTA DE ACUERDO CON LOS TÉRMINOS Y CONDICIONES DE ESTE CONTRATO, NO UTILICE EL SOFTWARE. EL USO DE CUALQUIER PARTE DEL SOFTWARE INDICA QUE USTED ACEPTA ESTOS TÉRMINOS.\n" +
                "\n" +
                "<br>\n" +
                "CLÁUSULA 1. OBJETO.<br> Este acuerdo establece las condiciones de licenciamiento, instalación y uso del programa adquirido (de aquí en adelante denominado “el software”), programa este propiedad exclusiva de MarvelStore. La licencia objeto del presente acuerdo es concedida exclusivamente para el uso del cliente.\n" +
                "<br>\n" +
                "CLÁUSULA 2. LICENCIA. MarvelStore <br> le otorga a usted una licencia limitada, exclusiva, para el uso del software que se acompaña, sujeta a los términos y condiciones recogidos en el presente. No se le permite el uso del software en ninguna otra manera que no este expresamente autorizada por esta Licencia.\n" +
                "<br>\n" +
                "El Software es objeto de licencia para su uso en cualquier Dispositivo personal que disponga el sistema operativo correspondiente para el software adquirido.\n" +
                "<br>\n" +
                "Usted podrá reproducir y usar solo una (1) copia del Software.\n" +
                "<br>\n" +
                "CLÁUSULA 3. DERECHOS DE AUTORÍA. <br>El Software es propiedad exclusiva de MarvelStore, y está protegido por la legislación sobre derechos de autor, tratados internacionales y cualquier legislación aplicable.\n" +
                "<br>\n" +
                "CLÁUSULA 4. INSTALACIÓN. <br>El Software objeto del presente acuerdo será instalado en el equipo del Cliente.\n" +
                "<br>\n" +
                "CLÁUSULA 5. DISPOSICIONES GENERALES. <br>El Cliente no deberá usar o alterar el Software con ninguna finalidad, ni podrá utilizar el Software para cualquier otro propósito que el señalado con anterioridad, sin el consentimiento previo por escrito de MarvelStore, el cual podrá ser denegado por cualquier razón. La modificación, manipulación o alteración en cuanto a ingeniería o recopilación o desmontaje del software queda expresamente prohibido.\n" +
                "<br>\n" +
                "El uso del Software por parte de terceros o para el beneficio de queda expresamente prohibido. MarvelStore tiene el derecho de invalidar la Licencia del Software en cualquier momento, notificándole al Cliente con sólo un día de anticipación; el Cliente no tendrá derecho a reclamar o solicitar compensación. Igualmente, el Cliente no podrá (a) arrendar o transferir mediante ningún titulo, o incluso por causa sin ánimo de lucro, el uso del Software a terceros; (b) tomar aquellos pasos para, mediante la ingeniería inversa o cualquier otro método, descompilar, desensamblar, analizar o convertir el Software; (c) transferir el Software a otro Equipo, suyo o no. El Cliente se compromete a cumplir estrictamente las instrucciones de MarvelStore para el uso adecuado del Software.\n" +
                "<br>\n" +
                "CLÁUSULA 6. CONFIDENCIALIDAD. <br>El Software, como también cualquier documentación e información relacionada con éste, son propiedad exclusiva de MarvelStore. El Cliente conviene en mantener en estricta confidencialidad toda información confidencial que surja del Software, ya que toda la mencionada información es confidencial y propiedad de MarvelStore.\n" +
                "<br>\n" +
                "CLÁUSULA 7. PROPIEDAD. <br>El Software es, y seguirá siendo en todo momento, propiedad de MarvelStore. El Cliente no tendrá ningún derecho, titulo o interés en el Software, y no permitirá que ninguna obligación o gravamen exista sobre éste, ni permitirá el uso del Software por terceros, ni realizará ningún acto que pueda modificar los derechos de autor del Software. Por lo tanto, el CLIENTE no puede vender, arrendar, prestar, revelar, transmitir o transferir ningún titulo, ni copiar el Software para terceros.\n" +
                "<br>\n" +
                "CLÁUSULA 8. MODIFICACIÓN y ACTUALIZACIÓN DE VERSIONES. <br>MarvelStore podrá modificar o reinstalar el Software bajo los términos y condiciones incluidos en el presente, reemplazándolo por versiones actualizadas.\n" +
                "<br>\n" +
                "CLÁUSULA 9. RESPONSABILIDAD DEL CLIENTE; RIESGO DE PÉRDIDA. <br>El cliente deberá asumir todo riesgo de pérdida o daño del Software. Asimismo MarvelStore, no se responsabiliza de ningún daño o pérdida en su Dispositivo personal derivada del uso de este Software y de los manuales de instrucciones.\n" +
                "<br>\n" +
                "CLÁUSULA 10. ROBO, PERDIDA, O CAMBIO DE Dispositivo PERSONAL.\n" +
                "En caso de robo, pérdida o cambio de su Dispositivo personal, MarvelStore no se responsabiliza, como tampoco generará ni autorizará ni otorgará otra licencia de uso.\n" +
                "<br>\n" +
                "CLÁUSULA 11. PLAZO Y TERMINACIÓN este contrato será efectivo hasta que sea terminado.\n" +
                "Usted podrá terminarlo en cualquier momento, mediante la destrucción en cualquier forma del software.\n" +
                "También se terminara inmediatamente si usted incumple cualquier termino o condición del presente sin que esto signifique una renuncia de MarvelStore a su derecho de realizar acciones judiciales que se deriven del mismo.\n" +
                "<br>\n" +
                "CLÁUSULA 12. FACTURACIÓN Y FORMAS DE PAGO. <br>MarvelStore facturará al cliente por los servicios y/o productos con las tarifas vigentes en cada momento, que serán incrementadas con el IVA correspondiente. El pago se realizará mediante ingreso o transferencia bancaria, siempre por anticipado, no realizándose el suministro o prestación del servicio correspondiente hasta la confirmación efectiva del mismo.\n" +
                "<br>\n" +
                "CLÁUSULA 13. CONTRATO INTEGRAL.<br> Este acuerdo de licencia constituye el contrato completo entre el Cliente y MarvelStore, reemplaza cualquier acuerdo anterior o actual entre las partes. Este acuerdo no puede ser enmendado, modificado ni ratificado, excepto mediante documento por escrito firmado por ambas partes.\n" +
                "</body>\n" +
                "</html>\n";
        mostrarTerminosYCondiciones(mostrar);
    }

    @OnClick(R.id.alerta_btn_Aceptar)
    public void aceptarTerminos() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @OnClick(R.id.alerta_btn_Cancelar)
    public void cancelarTerminos() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void mostrarTerminosYCondiciones(String terminos) {
        wbvTerminosCondiciones.loadData(terminos, "text/html; charset=utf-8", "utf-8");
        wbvTerminosCondiciones.setWebViewClient(new WebViewClient() {


            public void onPageFinished(WebView view, String url) {
                pgbCargando.setVisibility(View.GONE);
            }
        });
    }

    private class TerminosYCondiciones extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
