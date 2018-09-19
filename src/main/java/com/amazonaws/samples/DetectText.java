package com.amazonaws.samples;

import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.TextDetection;

public class DetectText {

 public static void main(String[] args) throws Exception {

    String photo = "input12.png";
    String bucket = "rishabh-demo-bucket";


    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("ap-south-1").build();

    DetectTextRequest request = new DetectTextRequest()
         .withImage(new Image()
         .withS3Object(new S3Object()
         .withName(photo).withBucket(bucket)));

    try {
       DetectTextResult result = rekognitionClient.detectText(request);
       List<TextDetection> labels = result.getTextDetections();

       System.out.println("Detected labels for " + photo);
       for (TextDetection label: labels) {
          System.out.println(label.getDetectedText());
       }
    } catch(AmazonRekognitionException e) {
       e.printStackTrace();
    }
 }
}
