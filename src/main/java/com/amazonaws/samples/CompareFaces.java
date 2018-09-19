
package com.amazonaws.samples;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import java.util.List;

public class CompareFaces {

	public static void main(String[] args) throws Exception {

		String photo1 = "input7.jpg";
		String photo2 = "input6.jpg";
		String bucket = "rishabh-demo-bucket";

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("ap-south-1").build();

		CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
				.withS3Object(new S3Object()
						.withName(photo1).withBucket(bucket))).withTargetImage(new Image()
								.withS3Object(new S3Object()
										.withName(photo2).withBucket(bucket))).withSimilarityThreshold(80F);

		try {

			CompareFacesResult result= rekognitionClient.compareFaces(compareFacesRequest);
			List<CompareFacesMatch> lists= result.getFaceMatches();

			System.out.println("Detected labels for " + photo1+ " and "+photo2);

			if(!lists.isEmpty()){
				for (CompareFacesMatch label: lists) {
					System.out.println(label.getFace() + ": Similarity is " + label.getSimilarity().toString());
				}
			}else{
				System.out.println("Faces Does not match");
			}
		} catch(AmazonRekognitionException e) {
			e.printStackTrace();
		}
	}
}


