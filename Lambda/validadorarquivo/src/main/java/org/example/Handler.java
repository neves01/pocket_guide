package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Handler implements RequestHandler<S3Event, String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(S3Event event, Context context) {
        LambdaLogger logger = context.getLogger();

        var record = event.getRecords().get(0);
        String objectName = record.getS3().getObject().getUrlDecodedKey();
        String bucket = record.getS3().getBucket().getName();

        logger.log("OBJECT NAME: " + objectName);
        logger.log("BUCKET NAME: " + bucket);

        String[] allowedTypes = System.getenv().get("allowedTypes").split(",");

        var typeObject = objectName.split("\\.")[1].toUpperCase();
        boolean isTypeObjectValid = Arrays.stream(allowedTypes).anyMatch(typeObject::equals);

        if (!isTypeObjectValid) {
            try {
                AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
                DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, objectName);
                s3Client.deleteObject(deleteObjectRequest);

                logger.log(" ========== IT'S AN INVALID OBJECT!");
                logger.log(" ========== OBJECT EXCLUDED WITH SUCCESS!");

                return "Exclusion made correctly!";
            } catch (Exception e) {
                logger.log(e.getMessage());
                throw new RuntimeException();
            }
        }

        logger.log(" ========== IT'S A VALID OBJECT!");

        return "File: " + objectName + " is valid!";
    }

}
