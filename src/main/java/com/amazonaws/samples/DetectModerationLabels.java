package com.amazonaws.samples;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.ModerationLabel;
import com.amazonaws.services.rekognition.model.S3Object;

import java.util.List;

public class DetectModerationLabels
{
 public static void main(String[] args) throws Exception
 {
    String photo = "input10.jpg";
    String bucket = "rishabh-demo-bucket";
    
    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("ap-south-1").build();
    
    DetectModerationLabelsRequest request = new DetectModerationLabelsRequest()
      .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)))
      .withMinConfidence(60F);
    try
    {
         DetectModerationLabelsResult result = rekognitionClient.detectModerationLabels(request);
         List<ModerationLabel> labels = result.getModerationLabels();
         System.out.println("Detected labels for " + photo);
         for (ModerationLabel label : labels)
         {
            System.out.println("Label: " + label.getName()
             + "\n Confidence: " + label.getConfidence().toString() + "%"
             + "\n Parent:" + label.getParentName());
        }
     }
     catch (AmazonRekognitionException e)
     {
       e.printStackTrace();
     }
  }
}

    
