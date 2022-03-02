package br.com.joston.brzip.v1.provider.correios.client;

import br.com.joston.brzip.v1.provider.correios.wsdl.ConsultaCEP;
import br.com.joston.brzip.v1.provider.correios.wsdl.ConsultaCEPResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * Wsdl Correios client
 * Provide a connection with Correios ws to get zip code information
 * @see <a href="https://spring.io/guides/gs/consuming-web-service/">Spring Boot consuming SOAP documentation</a>
 * @author Joston Jorge
 * */
public class SearchCepClient extends WebServiceGatewaySupport {
    private final static String URI = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl";

    public SearchCepClient(){
        this.config();
    }
    @SuppressWarnings("unchecked")
    public ConsultaCEPResponse execute(String cep) {
        ConsultaCEP request = new ConsultaCEP();
        request.setCep(cep);
        JAXBElement<ConsultaCEP> xmlRequest = new JAXBElement<>(new QName("http://cliente.bean.master.sigep.bsb.correios.com.br/", "consultaCEP"),ConsultaCEP.class,request);
        JAXBElement<ConsultaCEPResponse> response = (JAXBElement<ConsultaCEPResponse>) getWebServiceTemplate().marshalSendAndReceive(URI,xmlRequest);
        return response.getValue();
    }

    private void config(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //NOTE: this package must match the package in the <generatePackage> specified in pom.xml
        marshaller.setContextPath("br.com.joston.brzip.v1.provider.correios.wsdl");
        this.setDefaultUri(URI);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }
}
