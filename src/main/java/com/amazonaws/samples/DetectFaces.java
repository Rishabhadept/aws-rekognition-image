
package com.amazonaws.samples;

import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;

public class DetectFaces {

	public static void main(String[] args) throws Exception {

		String photo = "input8.jpg";
		String bucket = "rishabh-demo-bucket";


		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("ap-south-1").build();

		DetectFacesRequest request = new DetectFacesRequest()
				.withImage(new Image()
						.withS3Object(new S3Object()
								.withName(photo).withBucket(bucket)));

		try {
			DetectFacesResult result = rekognitionClient.detectFaces(request);
			List<FaceDetail> labels = result.getFaceDetails();

			System.out.println("Detected FaceDetail for " + photo);
			for (FaceDetail label: labels) {
				System.out.println("Face details are : "+label + " \n");
			}
		} catch(AmazonRekognitionException e) {
			e.printStackTrace();
		}
	}
}



