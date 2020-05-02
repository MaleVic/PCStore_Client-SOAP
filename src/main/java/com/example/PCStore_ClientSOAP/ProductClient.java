package com.example.PCStore_ClientSOAP;

import com.example.PCStore_ClientSOAP.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ProductClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(ProductClient.class);

    public GetALLProductsResponse getAllProducts(String product){
        GetALLProductsRequest request = new GetALLProductsRequest();
        request.setName(product);
        log.info("Requesting location for " + product);

        Object obj = getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetALLProductsRequest"));

        GetALLProductsResponse response = (GetALLProductsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetALLProductsRequest"));

        return response;
    }

    public GetProductResponse getProduct(String product) {

        GetProductRequest request = new GetProductRequest();
        request.setName(product);

        log.info("Requesting location for " + product);

        GetProductResponse response = (GetProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetProductRequest"));

        return response;
    }

    public DeleteProductResponse deleteProduct(int country){
        DeleteProductRequest request = new DeleteProductRequest();
        request.setIdProduct(country);

        DeleteProductResponse response = (DeleteProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/DeleteProductRequest"));

        return response;
    }

    public AddProductResponse setProduct(int id_product, String product, int price) {
        AddProductRequest request = new AddProductRequest();

        request.setIdProduct(id_product);
        request.setName(product);
        request.setPrice(price);


        log.info("Requesting location for " + id_product);

        AddProductResponse response = (AddProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/AddProductRequest"));

        return response;
    }

    public EditProductResponse editProduct(String oldName,String name, int price) {
        EditProductRequest request = new EditProductRequest();

        request.setOldName(oldName);
        request.setNewName(name);
        request.setNewPrice(price);

        log.info("Requesting location for " + name);

        EditProductResponse response = (EditProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/products", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/EditProductRequest"));

        return response;
    }
}
