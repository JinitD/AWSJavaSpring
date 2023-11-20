package jdorado.learning.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ApiGtwLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final String BUCKET_NAME = "lambda-functions-java-jdorado";
  private static final String S3_PREFIX = "imgaenesHot/";

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event,
      Context context) {
    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    LambdaLogger logger = context.getLogger(); // Create the logger LambdaLogger

    logger.log("Loading Java Lambda handler of Proxy");
    logger.log(event.getBody());
    try {
      String requestBody = event.getBody(); // Acceder al cuerpo de la solicitud HTTP
      JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
      Archivo archivo = new Gson().fromJson(jsonObject, Archivo.class);// mapeamos el objeto json con la clase archivo

      byte[] imageBytes = Base64.getDecoder().decode(archivo.getBase64()); // trasformar la imagen en byte
      InputStream fis = new ByteArrayInputStream(imageBytes); // trasformar la imagen en InputStream

      AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient(); // Crear un cliente de amazon para hacer el put

      s3Client.putObject(BUCKET_NAME, this.getDirName(archivo.getName()), fis,
          this.getMetaData(imageBytes, archivo.getContenType()));

      logger.log("Put object in S3");

      response.setStatusCode(200);
      response.setBody("Solicitud procesada con éxito");

    } catch (Exception e) {
      e.printStackTrace();
      response.setStatusCode(500);
      response.setBody("Error al procesar la solicitud: " + e.getMessage());
    }
    return response;
  }

  private ObjectMetadata getMetaData(byte[] imageBytes, String contenType) {
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(imageBytes.length);
    metadata.setContentType(contenType);
    metadata.setCacheControl("public, max-age=31536000");
    return metadata;
  }

  private String getDirName(String name) {
    String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "_";
    String fileObjKeyName = S3_PREFIX + timestamp + name;
    return fileObjKeyName;
  }

}