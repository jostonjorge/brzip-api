package br.com.joston.brzip.v1.service;

import br.com.joston.brzip.v1.domain.Address;
import br.com.joston.brzip.v1.provider.correios.client.SearchCepClient;
import br.com.joston.brzip.v1.provider.correios.wsdl.ConsultaCEPResponse;
import br.com.joston.brzip.v1.provider.correios.wsdl.EnderecoERP;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;

@Service("SearchCepServiceV1")
public class SearchCepService {
    public Address execute(String cep){
        Address address;
        try {
            cep = cep.replaceAll("\\D", "");
            this.validateCep(cep);
            SearchCepClient searchCep = new SearchCepClient();
            ConsultaCEPResponse response = searchCep.execute(cep);
            address = this.parseAddress(response);
        }catch (SoapFaultClientException e){
            System.err.println(e.getMessage());
            address = new Address();
        }
        return address;
    }

    private void validateCep(String cep){
        if(cep.length() < 8){
            throw new IllegalArgumentException("Invalid CEP!");
        }
    }

    private Address parseAddress(ConsultaCEPResponse wsResponse){
        EnderecoERP response = wsResponse.getReturn();
        Address address = new Address();
        address.setStreet(response.getEnd().isEmpty() ? "" : response.getEnd());
        address.setDistrict(response.getBairro().isEmpty() ? "" : response.getBairro());
        address.setCity(response.getCidade().isEmpty() ? "" : response.getCidade());
        address.setComplement(response.getComplemento2().isEmpty() ? "" : response.getComplemento2());
        address.setState(response.getUf().isEmpty() ? "" : response.getUf());
        address.setZipCode(response.getCep().isEmpty() ? "" : response.getCep());
        return address;
    }
}
