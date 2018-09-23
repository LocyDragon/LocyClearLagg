package com.locydragon.lagg.neural;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.File;
import java.io.IOException;

public class NeuralNetwork {
	public static final String dataFileURL = ".//plugins//LocyClearLagg//Data//Neural.dat";
	public static MultiLayerPerceptron actor
			= new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 5, 14, 1);
	public static DataSet dataTraining = new DataSet(2, 1);
	static {
		File dataFile = new File(dataFileURL);
		if (dataFile.exists()) {
			dataTraining =
					DataSet.createFromFile(dataFileURL, 2, 1, "\t", true);
			actor.learn(dataTraining);
		} else {
			dataFile.getParentFile().mkdirs();
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
